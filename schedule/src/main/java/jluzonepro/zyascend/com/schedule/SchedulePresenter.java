package jluzonepro.zyascend.com.schedule;

import android.content.Context;
import android.util.Log;
import android.widget.HorizontalScrollView;


import java.util.List;

import jluzonepro.zyascend.com.common.base.BasePresenter;
import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.Term;
import jluzonepro.zyascend.com.common.http.OkHttpUtils;
import jluzonepro.zyascend.com.common.model.data.DataListener;
import jluzonepro.zyascend.com.common.model.data.DataUtils;
import jluzonepro.zyascend.com.common.model.net.HttpManager;
import jluzonepro.zyascend.com.common.model.net.HttpManagerListener;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import jluzonepro.zyascend.com.common.utils.ShareUtils;

/**
 *
 * Created by Administrator on 2016/10/14.
 */

public class SchedulePresenter extends BasePresenter<ScheduleConstract.View>
implements ScheduleConstract.Presenter{
    private static final String TAG = "TAG_SchedulePresenter";
    private final Context mContext;
    private DataListener mDataUtils;
    private HttpManagerListener mHttpUtils;

    public SchedulePresenter(Context context) {
        mHttpUtils = HttpManager.getInstance();
        mDataUtils = DataUtils.getInstance(context.getApplicationContext());
        mContext = context;
    }


    @Override
    public void loadTermList() {
        //先从网络中获取
        mHttpUtils.getSemester(TAG_PRESENTER,new HttpManagerListener.TermCallBack() {
            @Override
            public void onSuccess(List<Term> terms) {
                if (!isViewAttached()){
                    return;
                }

                if (ActivityUtils.NotNullOrEmpty(terms)){
                     mViewListener.showLoadTermSuccess(terms);
                    //获取成功，存入数据库
                    mDataUtils.saveTerms(terms);
                    Log.d(TAG, "onSuccess: "+terms.size());
                }else {
                    //返回为空时也从数据库中查找
                    getTermsFromData();
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (!isViewAttached()){
                    return;
                }
                getTermsFromData();
            }
        });
    }

    private void getTermsFromData() {
        //获取失败，从数据库找
        List<Term> terms = mDataUtils.getTerms();
        if (ActivityUtils.NotNullOrEmpty(terms)){
            mViewListener.showLoadTermSuccess(terms);
        }else {
            //数据库中也无，显示失败
            mViewListener.showLoadFail(null);
        }
    }

    @Override
    public void loadSchedule(int termId,boolean refresh) {
        // TODO: 2016/10/15 判断：在数据库否存在对应termId的数据
        //若有，则读取
        //若无，则再次加载

        if (!refresh){
            List<Course> courses = mDataUtils.getAllCoursesByTerm(termId);
            if (ActivityUtils.NotNullOrEmpty(courses)){
                mViewListener.showLoadCourseSuccess(courses);
                Log.d(TAG, "loadSchedule: from data size = "+courses.size());
            }else {
                loadScheduleFromNet(termId);
            }
        }else {
            loadScheduleFromNet(termId);
        }
    }

    @Override
    public void shareSchedule(HorizontalScrollView scrollView) {
        ShareUtils.getInstance(new ShareUtils.ShareCallback() {
            @Override
            public void onSuccess() {
                mViewListener.shareOK();
            }

            @Override
            public void onFail() {
                mViewListener.shareFail();
            }
        }).shareSchedule(scrollView,mContext);
    }

    @Override
    public void loadCurrentTerm() {
        mViewListener.showLoadCurrentTerm(mDataUtils.getCurrentTerm());
    }

    private void loadScheduleFromNet(final int termId) {
        mHttpUtils.getSchedule(TAG_PRESENTER,termId, new HttpManagerListener.ScheduleCallBack() {
            @Override
            public void onSuccess(List<Course> courses) {

                mViewListener.showLoadCourseSuccess(courses);
                if (ActivityUtils.NotNullOrEmpty(courses)){
                    for (Course course : courses) {
                        mDataUtils.saveSchedule(course);
                    }
                    Log.d(TAG, "onLoadedList: 已添加");
                }

//                if (!mRefresh){
//
//                }else {
//                    mDataUtils.upDateCourse(courses,termId);
//                    Log.d(TAG, "onLoadedList: 已更新");
//                }
            }

            @Override
            public void onFailure(Exception e) {
                mViewListener.showLoadFail(e);
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
        mDataUtils = null;
        mHttpUtils = null;
        OkHttpUtils.getInstance().cancelTag(TAG_PRESENTER);

    }
}

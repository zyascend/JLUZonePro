package jluzonepro.zyascend.com.news.news;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import jluzonepro.zyascend.com.common.base.BasePresenter;
import jluzonepro.zyascend.com.common.entity.News;
import jluzonepro.zyascend.com.common.model.data.DataUtils;
import jluzonepro.zyascend.com.common.model.net.JsoupListener;
import jluzonepro.zyascend.com.common.model.net.JsoupUtils;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/8/4.
 */
public class NewsPresenter extends BasePresenter<NewsContract.View>
        implements NewsContract.Presenter, JsoupListener.NewsCallBack {

    public static final String TAG_JWC = "jwc";
    public static final String TAG_XIAO = "xiao";
    public static final String TAG_XIAO_CONTENT = "xiao_cintent";
    public static final String TAG_JWC_CONTENT = "jwc_cintent";
    public static final String TAG  = "TAG_NewsPresenter";
    private  DataUtils mData;
    private JsoupUtils mJouspUtils;

    public NewsPresenter(Context context){
        mJouspUtils = JsoupUtils.getInstance();
        mData = DataUtils.getInstance(context.getApplicationContext());
    }

    @Override
    public void getNews(String tag,int page) {

        if (TextUtils.equals(tag,TAG_JWC)){
            mJouspUtils.getJwNews(this,page);
        }else {
            mJouspUtils.getSchoolNews(this,page);
        }
    }

    @Override
    public void getXiaoContent(String url) {
        mJouspUtils.getXiaoContent(url, new JsoupListener.ContentCallback() {
            @Override
            public void onSuccess(String s) {
                mViewListener.loadContent(s);
                Log.d(TAG, "onSuccess: "+s);
            }

            @Override
            public void onFailure(Exception e) {
                mViewListener.showFailure();
            }
        });
    }

    @Override
    public void getJwcContent(String url) {
        mJouspUtils.getJwContent(url, new JsoupListener.ContentCallback() {
            @Override
            public void onSuccess(String s) {
                mViewListener.loadContent(s);
            }

            @Override
            public void onFailure(Exception e) {
                mViewListener.showFailure();
            }
        });
    }

    @Override
    public void saveShowList(List<String> list) {
        Log.d(TAG, "saveShowList: size = "+list.size());
       mData.saveEditor(list,true);
    }

    @Override
    public void saveHideList(List<String> list) {
        mData.saveEditor(list,false);
    }

    @Override
    public List<String> getShowList() {
        return mData.getEditor(true);
    }

    @Override
    public List<String> getHideList() {
        return mData.getEditor(false);
    }

    @Override
    public void getNewsByEditor(String editor) {
        mViewListener.loadNews(mData.getNewsByEditor(editor));
    }


    @Override
    public void onSuccess(List<News> newsList) {

        for (News news : newsList) {
            mData.saveNews(news);
        }

        mViewListener.loadNews(newsList);
        Log.d(TAG, "onLoadedList: size = "+newsList.size());
    }



    @Override
    public void onFailure(Exception e) {
        Log.d(TAG, "onFailure: "+e.toString());

    }


    @Override
    public void detachView() {
        super.detachView();
        mData = null;
        mJouspUtils = null;
    }
}

package com.zyascend.JLUZonePro.login;

import android.content.Context;
import android.util.Log;

import jluzonepro.zyascend.com.common.base.BasePresenter;
import jluzonepro.zyascend.com.common.entity.StuInfo;
import jluzonepro.zyascend.com.common.http.OkHttpUtils;
import jluzonepro.zyascend.com.common.model.data.DataListener;
import jluzonepro.zyascend.com.common.model.data.DataUtils;
import jluzonepro.zyascend.com.common.model.net.HttpManager;
import jluzonepro.zyascend.com.common.model.net.HttpManagerListener;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private static final String TAG = "TAG_LoginPresenter";
    private DataListener mDataListener;
    private HttpManagerListener mHttpUtils;
    private StuInfo mStuinfo;

    public LoginPresenter (Context context){
        mDataListener = DataUtils.getInstance(context.getApplicationContext());
        mHttpUtils = HttpManager.getInstance();
    }


    @Override
    public void saveStuInfo(StuInfo stuInfo) {
        mDataListener.saveStuInfo(stuInfo);
    }

    @Override
    public void getStuInfo() {
        mDataListener.getStuInfo(new HttpManagerListener.LoginCallBack() {
            @Override
            public void onSuccess(StuInfo stuInfo) {
                mViewListener.loadStuInfo(stuInfo);
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }

    @Override
    public void login(StuInfo stuInfo) {
        mStuinfo = stuInfo;
        mViewListener.showLoginProgress();
        mHttpUtils.login(TAG_PRESENTER,stuInfo.getIsLoginOutside(), stuInfo.getAccount()
                , stuInfo.getPassWord(), new HttpManagerListener.LoginCallBack() {
            @Override
            public void onSuccess(StuInfo stuInfo) {
                Log.d(TAG, "onSuccess: ");
                mStuinfo.setStuId(stuInfo.stuId);
                mStuinfo.setName(stuInfo.name);
                mStuinfo.setCurrentTerm(stuInfo.currentTerm);
                saveStuInfo(mStuinfo);
                mViewListener.showLoginSuccess();

            }

            @Override
            public void onFailure(Exception e) {
                mViewListener.dismissLoginProgress();
                mViewListener.showLoginFailure();
            }

        });
    }

    @Override
    public void cancel() {
        mHttpUtils.cancel();
    }

    @Override
    public void detachView() {
        super.detachView();
//        mHttpUtils.cancel();
        mViewListener = null;
        mHttpUtils = null;
        OkHttpUtils.getInstance().cancelTag(TAG_PRESENTER);
    }
}

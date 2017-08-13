package com.zyascend.JLUZonePro.login;


import jluzonepro.zyascend.com.common.entity.StuInfo;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public interface LoginContract {
    interface Presenter {
        void saveStuInfo(StuInfo stuInfo);
        void getStuInfo();
        void login(StuInfo stuInfo);
        void cancel();
    }

    interface View {
        void loadStuInfo(StuInfo stuInfo);
        void showLoginSuccess();
        void showLoginFailure();
        void showLoginProgress();
        void dismissLoginProgress();
    }
}

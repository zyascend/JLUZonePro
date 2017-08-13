package com.zyascend.JLUZonePro.explore;

/**
 *
 * Created by Administrator on 2016/10/13.
 */

public interface ExploreContract {
    interface View {}
    interface Presenter{
        void enterSchedule();
        void enterScore();
        void enterNews();
        void enterLesson();
        void enterJob();
        void enterPic();
        void enterRate();
        void enterLive();
    }
}

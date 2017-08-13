package jluzonepro.zyascend.com.schedule;

import android.widget.HorizontalScrollView;


import java.util.List;

import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.Term;

/**
 *
 * Created by Administrator on 2016/10/14.
 */

public interface ScheduleConstract {
    interface View{
        void showLoadTermSuccess(List<Term> terms);
        void showLoadFail(Exception e);
        void showLoadCourseSuccess(List<Course> courses);
        void showLoadCurrentTerm(int id);
        void shareOK();
        void shareFail();

    }
    interface Presenter{
        void loadTermList();
        void loadSchedule(int termId, boolean refresh);
        void shareSchedule(HorizontalScrollView scrollView);

        void loadCurrentTerm();
    }
}

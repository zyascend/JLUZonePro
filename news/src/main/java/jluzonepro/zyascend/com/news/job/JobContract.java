package jluzonepro.zyascend.com.news.job;



import java.util.List;

import jluzonepro.zyascend.com.common.entity.Job;
import jluzonepro.zyascend.com.common.entity.JobContent;

/**
 *
 * Created by Administrator on 2016/10/20.
 */

public interface JobContract {
    interface Presenter{
        void getJobList(String tag, int page);
        void getJobContent(int id);


    }
    interface View{
        void onLoadedList(List<Job> jobs);
        void onFailed(Exception e);
        void onLoadedContent(JobContent content);

    }
}

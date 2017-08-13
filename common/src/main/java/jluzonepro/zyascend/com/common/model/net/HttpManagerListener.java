package jluzonepro.zyascend.com.common.model.net;



import java.util.List;

import jluzonepro.zyascend.com.common.base.BaseCallBack;
import jluzonepro.zyascend.com.common.entity.AvgScore;
import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.EvaluateItem;
import jluzonepro.zyascend.com.common.entity.Job;
import jluzonepro.zyascend.com.common.entity.JobContent;
import jluzonepro.zyascend.com.common.entity.LiveChannel;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.ScoreDetail;
import jluzonepro.zyascend.com.common.entity.StuInfo;
import jluzonepro.zyascend.com.common.entity.Term;
import jluzonepro.zyascend.com.common.entity.Weather;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public interface HttpManagerListener {

    void cancel();

    void login(String tag, boolean isLoginOutside, String user, String passWord, LoginCallBack myCallBack);
    void getCurrentInfo();
    void getSemester(String tag, TermCallBack myCallBack);
    void getSchedule(String tag, int termId, ScheduleCallBack callBack);

    void getNewScore(String tag, int row, ScoreCallBack httpCallBack);
    void getAllScore(String tag, ScoreCallBack callBack);
    void getYearScore(String tag, int year, ScoreCallBack callBack);
    void getAvgScore(String tag, AvgScoreCallback callback);
    void getScoreDetail(String tag, String asId, ScoreDetailCallback callback);

    void getXiaoZhaoList(String tag, int page, JobListCallback callback);
    void getShixiList(String tag, int page, JobListCallback callback);
    void getJobContent(String tag, int id, JobContentCallback callback);
    void getWeather(String tag, WeatherCallback callback);
    void getChannel(ChannelCallback channelCallback);

    void loadEvaluateList(BaseCallBack<List<EvaluateItem>> callBack);
    void evaluate(String itemId, int type, BaseCallBack callBack);

    interface ScoreDetailCallback extends BaseCallBack<ScoreDetail>{}
    interface AvgScoreCallback extends BaseCallBack<AvgScore>{}
    interface LoginCallBack extends BaseCallBack<StuInfo> {
    }
    interface ScoreCallBack extends BaseCallBack<List<Score>>{
    }
    interface TermCallBack extends BaseCallBack<List<Term>>{}
    interface ScheduleCallBack extends BaseCallBack<List<Course>>{}
    interface JobListCallback extends BaseCallBack<List<Job>>{}
    interface JobContentCallback extends BaseCallBack<JobContent> {}

    interface WeatherCallback extends BaseCallBack<Weather>{}
    interface ChannelCallback extends BaseCallBack<List<LiveChannel>>{}
}

package jluzonepro.zyascend.com.common.router;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 功能：
 * 作者：zyascend on 2017/8/13 10:45
 * 邮箱：zyascend@qq.com
 */

public class RouterUtils {
    /**
     * 各Module的路径
     * @param path
     */


    public static final String APP_LOGIN = "/app/LoginActivity";
    public static final String APP_MAIN = "/app/MainActivity";

    public static final String SCHEDULE_MAIN = "/schedule/ScheduleActivity";
    public static final String SCORE_MAIN = "/score/ScoreActivity";

    public static final String SCORE_DETAIL = "/score/ScoreDetailActivity";
    public static final String SCORE_STATICS = "/score/ScoreStaticsActivity";

    public static final String SHARE_SCORE = "/common/ShareScoreActivity";
    public static final String SHARE_CONTENT = "/common/ShareContentActivity";

    public static final String JOB_MAIN = "/news/JobActivity";
    public static final String NEWS_MAIN = "/news/NewsActivity";

    public static final String LESSON_MAIN = "/lesson/LessonActivity";
    public static final String EVALUATE_MAIN = "/lesson/EvaluateActivity";

    public static final String MAP_MAIN = "/map/MapActivity";
    public static final String TV_MAIN = "/iptv/LiveActivity";

    public static Object navigation(String path){

        return ARouter.getInstance()
                .build(path)
                .navigation();
    }

    public static Object navigation(String path, Bundle bundle){
        return ARouter.getInstance()
                .build(path)
                .with(bundle)
                .navigation();
    }

}

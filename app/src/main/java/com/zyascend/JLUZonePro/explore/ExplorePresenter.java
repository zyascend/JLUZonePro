package com.zyascend.JLUZonePro.explore;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import jluzonepro.zyascend.com.common.base.BasePresenter;


/**
 *
 * Created by Administrator on 2016/10/13.
 */

public class ExplorePresenter extends BasePresenter<ExploreContract.View> implements
            ExploreContract.Presenter{


    public static final String INTENT_MAP = "intent_map";
    private Context mContext;

    public ExplorePresenter(Context context) {
        mContext = context;
    }

    @Override
    public void enterSchedule() {
//        ActivityUtils.enterActivity(mContext, ScheduleActivity.class);
    }

    @Override
    public void enterScore() {
//        ActivityUtils.enterActivity(mContext,ScoreActivity.class);
    }

    @Override
    public void enterNews() {
//        ActivityUtils.enterActivity(mContext, NewsActivity.class);
    }

    @Override
    public void enterLesson() {
        Toast.makeText(mContext, "现在不是选课时间哟", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enterJob() {
//        ActivityUtils.enterActivity(mContext, JobActivity.class);
    }

    @Override
    public void enterPic() {

    }

    @Override
    public void enterRate() {
//        ActivityUtils.enterActivity(mContext, EvaluateActivity.class);
    }

    @Override
    public void enterLive() {
//        ActivityUtils.enterActivity(mContext, LiveListActivity.class);

    }

    public void enterMap(String map) {
//        Intent intent = new Intent(mContext,MapActivity.class);
//        intent.putExtra(INTENT_MAP,map);
//        mContext.startActivity(intent);
    }
}

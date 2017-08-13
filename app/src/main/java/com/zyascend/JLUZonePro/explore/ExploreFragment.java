package com.zyascend.JLUZonePro.explore;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyascend.JLUZonePro.R;

import butterknife.BindView;
import butterknife.OnClick;
import jluzonepro.zyascend.com.common.base.BaseFragment;

/**
 *
 * Created by Administrator on 2016/10/13.
 */

public class ExploreFragment extends BaseFragment<ExploreContract.View, ExplorePresenter> {


    @BindView(R.id.ll_schedule)
    LinearLayout llSchedule;
    @BindView(R.id.ll_score)
    LinearLayout llScore;
    @BindView(R.id.ll_news)
    LinearLayout llNews;
    @BindView(R.id.ll_job)
    LinearLayout llJob;
    @BindView(R.id.ll_lesson)
    LinearLayout llLesson;
    @BindView(R.id.ll_rate)
    LinearLayout llRate;
    @BindView(R.id.tv_live)
    TextView tvLive;
    @BindView(R.id.tv_map)
    TextView tv_map;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected ExplorePresenter getPresenter() {
        return new ExplorePresenter(getActivity());
    }


    @OnClick({R.id.ll_schedule, R.id.ll_score, R.id.ll_news, R.id.ll_job, R.id.ll_lesson, R.id.ll_rate, R.id.tv_live,R.id.tv_map})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_schedule:
                mPresenter.enterSchedule();
                break;
            case R.id.ll_score:
                mPresenter.enterScore();
                break;
            case R.id.ll_news:
                mPresenter.enterNews();
                break;
            case R.id.ll_job:
                mPresenter.enterJob();
                break;
            case R.id.ll_lesson:
                mPresenter.enterLesson();
                break;
            case R.id.ll_rate:
                mPresenter.enterRate();
                break;
            case R.id.tv_live:
                mPresenter.enterLive();
                break;
            case R.id.tv_map:
//                mPresenter.enterMap();
                enterMapActivity();
                break;
        }
    }

    private void enterMapActivity() {
        final String[] maps = getResources().getStringArray(R.array.map_list);
        mPresenter.enterMap(maps[0]);
    }
}

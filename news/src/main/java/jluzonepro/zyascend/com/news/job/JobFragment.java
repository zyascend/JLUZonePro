package jluzonepro.zyascend.com.news.job;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import butterknife.BindView;
import jluzonepro.zyascend.com.common.base.BaseFragment;
import jluzonepro.zyascend.com.common.base.BaseReAdapter;
import jluzonepro.zyascend.com.common.entity.Job;
import jluzonepro.zyascend.com.common.entity.JobContent;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import jluzonepro.zyascend.com.news.R;
import jluzonepro.zyascend.com.news.R2;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by Administrator on 2016/10/20.
 */

public class JobFragment extends BaseFragment<JobContract.View,JobPresenter> implements SwipeRefreshLayout.OnRefreshListener,JobContract.View, BaseReAdapter.OnItemClickListener {

    private static final String JOB_BUNDLE_KEY = "job_bundle";
    private static final String TAG = "TAG_JOBFragment";
    private String mTag;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private JobAdapter adapter;
    private List<Job> mList = new ArrayList<>();
    private int page = 1;

    public JobFragment() {}

    public static JobFragment getInstance(String tag) {

        JobFragment jobFragment = new JobFragment();
        Bundle bundle = new Bundle();
        bundle.putString(JOB_BUNDLE_KEY, tag);
        jobFragment.setArguments(bundle);
        return jobFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTag = getArguments().getString(JOB_BUNDLE_KEY);
        Log.d(TAG, "onCreate: get tag = " + mTag);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    @Override
    protected void initViews() {
        adapter = new JobAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    Log.d(TAG, "onScrollStateChanged: IDLE");
                    int lastPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    if (lastPosition + 1 == adapter.getItemCount()){
                        loadData();
                        Log.d(TAG, "onScrollStateChanged: loadData()");
                    }
                }
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    private void loadData() {
        page++;
        mPresenter.getJobList(mTag,page);
    }

    @Override
    protected JobPresenter getPresenter() {
        return new JobPresenter(getActivity());
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: mtag = "+ mTag);
        mPresenter.getJobList(mTag,1);
    }

    @Override
    public void onLoadedList(List<Job> jobs) {
        swipeRefreshLayout.setRefreshing(false);
        if (ActivityUtils.NotNullOrEmpty(jobs)){
            adapter.setList(jobs);
            mList.addAll(jobs);
        }else{
            onFailed(null);
        }

    }

    @Override
    public void onFailed(Exception e) {
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), "加载失败，请检查网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadedContent(JobContent content) {
        //do nothing !!!
    }

    @Override
    public void onItemClick(int position) {
        if (ActivityUtils.NotNullOrEmpty(mList)){
            Intent intent = new Intent(getActivity(),JobContentActivity.class);
            intent.putExtra(JobContentActivity.PARCELABLE_JOBS,mList.get(position));
            getActivity().startActivity(intent);
        }

    }
}

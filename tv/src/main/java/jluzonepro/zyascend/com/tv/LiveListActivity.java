package jluzonepro.zyascend.com.tv;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jluzonepro.zyascend.com.common.base.BaseActivity;
import jluzonepro.zyascend.com.common.entity.LiveChannel;
import jluzonepro.zyascend.com.common.router.RouterUtils;


/**
 *
 * Created by Administrator on 2017/3/22.
 */
@Route(path = RouterUtils.TV_MAIN)
public class LiveListActivity extends BaseActivity<LiveContract.View, LivePresenter>
        implements LiveContract.View, SwipeRefreshLayout.OnRefreshListener, BaseReAdapter.OnItemClickListener {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private LiveAdapter adapter;
    private List<LiveChannel> mList = new ArrayList<>();

    @Override
    protected void doOnCreate() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        });
    }

    @Override
    protected void initView() {

        setToolbarTitle("IPTV");
        adapter = new LiveAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected LivePresenter getPresenter() {
        return new LivePresenter();
    }

    @Override
    protected void loadFragment() {

    }


    @Override
    public void onGetChannelList(List<LiveChannel> channels) {
        if (channels != null && !channels.isEmpty()){
            mList = channels;
            adapter.clear();
            adapter.addAll(channels);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFail() {
        Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onRefresh() {
        mPresenter.fetchChannel();
    }

    @Override
    public void onItemClick(int position) {

        LiveChannel channel = mList.get(position);
        if (channel == null)return;
        PlayActivity.init(this)
                .setTitle(channel.getNme())
                .setUrl(channel.getLink())
                .isLive(true)
                .play();

    }
}

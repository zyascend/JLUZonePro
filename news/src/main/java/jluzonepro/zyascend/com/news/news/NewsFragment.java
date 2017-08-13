package jluzonepro.zyascend.com.news.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;


import butterknife.BindView;
import jluzonepro.zyascend.com.common.base.BaseFragment;
import jluzonepro.zyascend.com.common.base.BaseReAdapter;
import jluzonepro.zyascend.com.common.entity.News;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import jluzonepro.zyascend.com.news.R;
import jluzonepro.zyascend.com.news.R2;
import me.gujun.android.taggroup.TagGroup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/8/4.
 */
public class NewsFragment extends BaseFragment<NewsContract.View, NewsPresenter>
        implements NewsContract.View
        , SwipeRefreshLayout.OnRefreshListener
        , BaseReAdapter.OnItemClickListener, View.OnClickListener {

    private static final String NEWS_BUNDLE_KEY = "news_bundle";
    private static final String TAG = "TAG_NewsFragment";

    private String mTag;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter adapter = new NewsAdapter();
    private List<News> mList = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showFilter();
                    break;
            }

        }
    };
    private PopupWindow mPopupMenu;
    private List<String> mHideList = new ArrayList<>();
    private List<String> mShowList = new ArrayList<>();
    private TagGroup hideTag;
    private TagGroup nowTag;
    private boolean idEditMode = false;
    private Button btnEdit;
    private int page = 1;


    public NewsFragment() {

    }

    public static NewsFragment getInstance(String tag) {
        Log.d(TAG, "getInstance: " + tag);
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_BUNDLE_KEY, tag);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTag = getArguments().getString(NEWS_BUNDLE_KEY);
        Log.d(TAG, "onCreate: get tag = " + mTag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        NewsActivity activity = (NewsActivity) context;
        activity.setmHandler(handler);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }





    @Override
    protected void initViews() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(this);

        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
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
        mPresenter.getNews(mTag,page);
    }

    @Override
    protected NewsPresenter getPresenter() {
        return new NewsPresenter(getActivity());
    }

    @Override
    public void onRefresh() {
        mPresenter.getNews(mTag, 1);
    }

    @Override
    public void onItemClick(int position) {
        News news = mList.get(position);
        Intent intent = new Intent(getActivity(), NewsContentActivity.class);
        intent.putExtra(NewsContentActivity.PARCELABLE_NEWS, news);
        intent.putExtra(NewsContentActivity.INTENT_NEWS_TAG, mTag);
        getActivity().startActivity(intent);
    }

    @Override
    public void loadNews(List<News> newsList) {

        if (!ActivityUtils.NotNullOrEmpty(newsList)){
            showFailure();
            return;
        }
        mHideList = mPresenter.getHideList();
        Log.d(TAG, "loadNews: hide Size = "+ mHideList.size());
        swipeRefreshLayout.setRefreshing(false);
        for (int i = 0; i < newsList.size(); i++) {
            if (ActivityUtils.NotNullOrEmpty(mHideList) && mHideList.contains(newsList.get(i).getEditor())){
                newsList.remove(i);
            }
        }
        mList.addAll(newsList);
        adapter.setList(newsList);
    }

    @Override
    public void showFailure() {
        Toast.makeText(getActivity(), "加载失败，请检查网络", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void loadContent(String content) {

    }

    private void showFilter() {

        Log.d(TAG, "showFilter: ");
        for (News news : mList) {

            if (!mShowList.contains(news.getEditor()))
            mShowList.add(news.getEditor());

        }

        Log.d(TAG, "showFilter: mLIst size "+mShowList.size());

        View view = View.inflate(getActivity(), R.layout.view_filter, null);

        hideTag = (TagGroup) view.findViewById(R.id.tg_hide);
        nowTag = (TagGroup) view.findViewById(R.id.tg_now);
        hideTag.setTags(mHideList);
        nowTag.setTags(mShowList);

        btnEdit = (Button) view.findViewById(R.id.btn_edit);
        ImageView btnClose = (ImageView) view.findViewById(R.id.iv_close);

        btnClose.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

        hideTag.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
               onHideClick(tag);
            }
        });

        nowTag.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                onShowClick(tag);
            }
        });

        if (mPopupMenu == null) {
            mPopupMenu = new PopupWindow(getActivity());
            mPopupMenu.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupMenu.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupMenu.setBackgroundDrawable(new BitmapDrawable());
            mPopupMenu.setFocusable(true);
            mPopupMenu.setOutsideTouchable(true);
        }

        mPopupMenu.setContentView(view);
        mPopupMenu.setAnimationStyle(R.style.Popwindow_anim);
        mPopupMenu.showAtLocation(mRootView, Gravity.BOTTOM, 0, 0);
        mPopupMenu.update();
    }

    private void onShowClick(String tag) {

        if (idEditMode){
            mShowList.remove(tag);
            if (!mHideList.contains(tag)){
                mHideList.add(tag);
                Log.d(TAG, "onShowClick: hide add"+tag);
                Log.d(TAG, "onShowClick: hideSize = "+mHideList.size());
            }
            updateTags();
        }else {
            mPresenter.getNewsByEditor(tag);
            mPopupMenu.dismiss();
        }
    }

    private void onHideClick(String tag) {
        if (!mShowList.contains(tag)){
            mShowList.add(tag);
        }
        mHideList.remove(tag);
        Log.d(TAG, "onHideClick: remove"+tag + mHideList.size());
        updateTags();
    }

    private void updateTags() {
        hideTag.setTags(mHideList);
        nowTag.setTags(mShowList);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_close){
            mPopupMenu.dismiss();
            Log.d(TAG, "onClick: 没Hide+ size" +mHideList.size());
            mPresenter.saveHideList(mHideList);
//                mPresenter.saveShowList(mShowList);
            onRefresh();
        }else if(id == R.id.btn_edit){
            idEditMode = !idEditMode;
            btnEdit.setText(idEditMode ? "完成" : "编辑");
            if (idEditMode){
                Toast.makeText(getActivity(), "点击标签可屏蔽", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_filter){
            showFilter();
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.zyascend.JLUZonePro.main;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zyascend.JLUZonePro.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jluzonepro.zyascend.com.common.base.BaseFragment;
import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.MainImage;
import jluzonepro.zyascend.com.common.entity.Todo;
import jluzonepro.zyascend.com.common.entity.Weather;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;

/**
 *
 * Created by Administrator on 2016/10/13.
 */

public class MainFragment extends BaseFragment<MainContract.View, MainPresenter> implements
        MainContract.View, TodoAdapter.ImageClickListener {


    private static final String TAG = "TAG_MainFragment";
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv_weather_refresh)
    ImageView ivWeatherRefresh;
    @BindView(R.id.city)
    TextView tvCity;
    @BindView(R.id.weather)
    TextView tvWeather;
    @BindView(R.id.tmp)
    TextView tmp;
    @BindView(R.id.weather_image)
    ImageView weatherImage;
    @BindView(R.id.iv_schedule_more)
    ImageView ivScheduleMore;
    @BindView(R.id.iv_schedule_next)
    ImageView ivScheduleNext;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.schedule_list)
    RecyclerView scheduleList;
    @BindView(R.id.tv_noSchedule)
    TextView tvNoSchedule;
    @BindView(R.id.iv_tips_complete)
    ImageView ivTipsComplete;
    @BindView(R.id.iv_tips_add)
    ImageView ivTipsAdd;
    @BindView(R.id.todo_list)
    RecyclerView todoList;
    @BindView(R.id.tv_noTodos)
    TextView tvNoTodos;
    @BindView(R.id.nestedView)
    NestedScrollView nestedView;
    @BindView(R.id.main_linear)
    LinearLayout mainLinear;

    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    private MainScheduleAdapter scheduleAdapter;
    private TodoAdapter todoAdapter;
    private List<Todo> mTodoList = new ArrayList<>();
    private boolean isWeatherRefresh = false;
    private boolean isNextSchedule = false;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initViews() {

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        scheduleAdapter = new MainScheduleAdapter();
        todoAdapter = new TodoAdapter();
        todoAdapter.setImageListner(this);

        todoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        scheduleList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        todoList.setAdapter(todoAdapter);
        scheduleList.setAdapter(scheduleAdapter);

        loadData();

    }

    private void loadData() {
        //先加载Banner
        mPresenter.loadImages();
    }


    @Override
    public void onLoadImagesSuccess(List<MainImage> imageList) {

        if (ActivityUtils.NotNullOrEmpty(imageList)) {
            for (MainImage image : imageList) {
                images.add(image.getUrl());
                titles.add(image.getTitle());
            }
        } else {
            //传入默认的图片
            // TODO: 2016/10/25  补充
            images.add("http://www.jlu.edu.cn/images/qiu.jpg");
            titles.add("秋");
            images.add("http://www.jlu.edu.cn/images/xue.jpg");
            titles.add("学");
            images.add("http://www.jlu.edu.cn/images/15/09/26/3mnfjos13f/1.jpg");
            titles.add("湖");
        }
        startBanner();
        //开始加载课表
        mPresenter.loadSchedule(1);

    }

    @Override
    public void onLoadWeather(Weather weather) {
        try {
            if (weather != null) {
                String weatherName = weather.getData().getForecast().get(0).getType();
                tvWeather.setText(weatherName);
                tmp.setText(weather.getData().getWendu() + "°");
                weatherImage.setImageResource(getWeatherImage(weatherName));
            }
        } catch (Exception e) {
            Log.d(TAG, "onLoadWeather: error = " + e.toString());
            tmp.setTextSize(15);
            tmp.setText("暂无天气信息⊙︿⊙，刷新试试");
            weatherImage.setImageResource(R.drawable.ic_sad);
        }
        //开始加载Todo
        if (!isWeatherRefresh) {
            mPresenter.loadTodo();
        }
    }

    private int getWeatherImage(String weatherInfo) {
        if (weatherInfo.contains("云")) {
            return R.drawable.ic_cloudy;
        } else if (weatherInfo.contains("晴")) {
            return R.drawable.ic_sunny;
        } else if (weatherInfo.contains("雨")) {
            return R.drawable.ic_rainy;
        } else if (weatherInfo.contains("雪")) {
            return R.drawable.ic_snow;
        } else if (weatherInfo.contains("阴")) {
            return R.drawable.ic_ying;
        } else {
            return R.drawable.ic_cloudy;
        }
    }

    @Override
    public void onLoadSchedule(List<Course> courses) {
        if (ActivityUtils.NotNullOrEmpty(courses)) {
            Log.d(TAG, "onLoadSchedule: " + courses.size());
            scheduleAdapter.setCoursesList(courses);
            tvNoSchedule.setVisibility(View.GONE);
            scheduleList.setVisibility(View.VISIBLE);
        } else {
            //显示今天没课的逻辑
            tvNoSchedule.setVisibility(View.VISIBLE);
            scheduleList.setVisibility(View.GONE);
        }
        //开始加载天气
        if (!isNextSchedule) {
            mPresenter.loadWeather();
        }
    }

    @Override
    public void onLoadTodo(List<Todo> list) {
        mTodoList = list;
        todoAdapter.setTodoList(mTodoList);
        if (!ActivityUtils.NotNullOrEmpty(list)) {
            tvNoTodos.setVisibility(View.VISIBLE);
            todoList.setVisibility(View.GONE);
        } else {
            tvNoTodos.setVisibility(View.GONE);
            todoList.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void failed() {

    }

    @OnClick({R.id.iv_weather_refresh, R.id.iv_schedule_more, R.id.iv_schedule_next, R.id.iv_tips_complete, R.id.iv_tips_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_weather_refresh:
                isWeatherRefresh = true;
                mPresenter.loadWeather();
                break;

            case R.id.iv_schedule_more:

                //跳转到课表Activity
                //ActivityUtils.enterActivity(getActivity(), ScheduleActivity.class);
                break;

            case R.id.iv_schedule_next:
                if (isNextSchedule) {
                    isNextSchedule = false;
                    mPresenter.loadSchedule(1);
                } else {
                    isNextSchedule = true;
                    mPresenter.loadSchedule(2);
                }
                //处理图标与文字的变换逻辑
                tvDay.setText(isNextSchedule ? "明日课程" : "今日课程");
                ivScheduleNext.setImageResource(isNextSchedule ? R.drawable.ic_before : R.drawable.ic_next);
                break;

            case R.id.iv_tips_complete:
                for (int i = 0; i < mTodoList.size(); i++) {
                    mTodoList.get(i).setIsChecked(true);
                }
                todoAdapter.setTodoList(mTodoList);
                mPresenter.saveTodo(mTodoList);
                break;
            case R.id.iv_tips_add:
                addTodo();
                break;
        }
    }

    private void addTodo() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialog = inflater.inflate(R.layout.layout_add_todo, (ViewGroup) getActivity().findViewById(R.id.ll_dialog));
        final EditText editText = (EditText) dialog.findViewById(R.id.et_add);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("添加一个任务")
                .setIcon(R.drawable.ic_tips)
                .setView(dialog)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: " + editText.getText());
                        mTodoList.add(new Todo(null, false, editText.getText().toString()));
                        Log.d(TAG, "onClick: size = " + mTodoList.size());
                        todoAdapter.setTodoList(mTodoList);
                        mPresenter.saveTodo(mTodoList);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        alertDialog.show();
    }

    @Override
    public void onImageClick(View view, int position) {
        switch (view.getId()) {
            case R.id.iv_check:
                boolean lastCheck = mTodoList.get(position).getIsChecked();
                Log.d(TAG, "onImageClick: checked = " + lastCheck);
                mTodoList.get(position).setIsChecked(!lastCheck);
                updateAndSaveTodo();
                break;
            case R.id.iv_close:
                mTodoList.remove(position);
                updateAndSaveTodo();
                break;
        }
    }

    private void updateAndSaveTodo() {
        mPresenter.saveTodo(mTodoList);
        todoAdapter.setTodoList(mTodoList);
    }

    private void startBanner() {
        //设置图片集合
        banner.setImages(images);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private class GlideImageLoader implements ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }
}

package com.zyascend.JLUZonePro.main;

import android.content.Context;
import android.util.Log;


import java.util.Calendar;
import java.util.List;

import jluzonepro.zyascend.com.common.base.BasePresenter;
import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.MainImage;
import jluzonepro.zyascend.com.common.entity.StuInfo;
import jluzonepro.zyascend.com.common.entity.Todo;
import jluzonepro.zyascend.com.common.entity.Weather;
import jluzonepro.zyascend.com.common.http.OkHttpUtils;
import jluzonepro.zyascend.com.common.model.data.DataListener;
import jluzonepro.zyascend.com.common.model.data.DataUtils;
import jluzonepro.zyascend.com.common.model.net.HttpManager;
import jluzonepro.zyascend.com.common.model.net.HttpManagerListener;

/**
 *
 * Created by Administrator on 2016/10/13.
 */

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    private DataListener mDataUtils;
    private static final String TAG = "TAG_MainPresenter";
    private HttpManagerListener mHttp;
    private int termId;

    public MainPresenter(Context context) {
        mDataUtils = DataUtils.getInstance(context.getApplicationContext());
        mHttp = HttpManager.getInstance();
    }

    @Override
    public void loadImages() {
        Log.d(TAG, "loadImages: ");
        List<MainImage> images = mDataUtils.getImages();
        mViewListener.onLoadImagesSuccess(images);
    }

    @Override
    public void loadWeather() {
        mHttp.getWeather(TAG_PRESENTER,new HttpManagerListener.WeatherCallback() {
            @Override
            public void onSuccess(Weather weather) {

                //当网速不好的时候，结果将在一段时间之后调用到此，
                // 由于viewpager的切换，这时mViewLisentner可能会为空
                if (isViewAttached()){
                    mViewListener.onLoadWeather(weather);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (isViewAttached()){
                    mViewListener.failed();
                }
            }
        });
    }

    @Override
    public void loadSchedule(int day) {

        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int realDay = getRealDay(day,currentDay);

        mDataUtils.getStuInfo(new HttpManagerListener.LoginCallBack() {
            @Override
            public void onSuccess(StuInfo stuInfo) {
                if (stuInfo != null){
                    termId = stuInfo.getCurrentTerm();
                    Log.d(TAG, "onSuccess: getTermID = "+termId);
                }
            }

            @Override
            public void onFailure(Exception e) {
                mViewListener.failed();
            }
        });

        List<Course> courses = mDataUtils.getCourseByDayAndTerm(realDay,termId);
        mViewListener.onLoadSchedule(courses);
    }

    private int getRealDay(int day, int currentDay) {
        int realDay;
        // day == 1 代表今天
        // day == 2 代表明天
        if (day == 1){
            if (currentDay != 1){
                realDay = currentDay - 1;
            }else {
                realDay = 7;
            }
        }else {

            if (currentDay != 1){
                realDay = currentDay;
            }else {
                realDay = 1;
            }
        }
        return realDay;
    }

    @Override
    public void loadTodo() {
        mViewListener.onLoadTodo(mDataUtils.getTodos());
    }

    @Override
    public void saveTodo(List<Todo> todoList) {
        mDataUtils.saveTodos(todoList);

    }

    @Override
    public void detachView() {
        super.detachView();
        OkHttpUtils.getInstance().cancelTag(TAG_PRESENTER);
        mHttp = null;
        mDataUtils = null;
    }
}

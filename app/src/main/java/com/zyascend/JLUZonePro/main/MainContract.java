package com.zyascend.JLUZonePro.main;



import java.util.List;

import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.MainImage;
import jluzonepro.zyascend.com.common.entity.Todo;
import jluzonepro.zyascend.com.common.entity.Weather;

/**
 *
 * Created by Administrator on 2016/10/13.
 */

public interface MainContract {
    interface View{
        void onLoadImagesSuccess(List<MainImage> imageList);
        void onLoadWeather(Weather weather);
        void onLoadSchedule(List<Course> courses);
        void onLoadTodo(List<Todo> todoList);
        void failed();
    }
    interface Presenter{
        void loadImages();
        void loadWeather();
        void loadSchedule(int day);
        void loadTodo();
        void saveTodo(List<Todo> todoList);
    }
}

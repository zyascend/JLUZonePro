package jluzonepro.zyascend.com.common.model.data;



import java.util.List;

import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.Job;
import jluzonepro.zyascend.com.common.entity.MainImage;
import jluzonepro.zyascend.com.common.entity.News;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.StuInfo;
import jluzonepro.zyascend.com.common.entity.Term;
import jluzonepro.zyascend.com.common.entity.Todo;
import jluzonepro.zyascend.com.common.model.net.HttpManagerListener;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public interface DataListener {
    void saveStuInfo(StuInfo stuInfo);

    void getStuInfo(HttpManagerListener.LoginCallBack callBack);

    void saveSchedule(Course course);

    List<Course> getAllCoursesByTerm(int termId);

    Course getCourse(int id);

    void upDateCourse(List<Course> courses, int termId);

    List<Score> getScoresByType(int type, int id);

    void saveScore(Score score);

    void saveNews(News news);
    News getNewsByUrl(String url);
    List<News> getNewsByEditor(String editor);
    List<News> getAllNews();

    void saveJobs(Job job);
    List<Job> getAllJobsByType(String type);

    void saveEditor(List<String> list, boolean type);
    List<String> getEditor(boolean type);

    List<MainImage> getImages();
    List<Todo> getTodos();
    List<Course> getCourseByDayAndTerm(int day, int termId);
    void saveTodos(List<Todo> todoList);

    List<Term> getTerms();
    void saveTerms(List<Term> terms);

    void saveCurrentTerm(int currentTerm);
    int getCurrentTerm();
}
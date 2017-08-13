package jluzonepro.zyascend.com.common.model.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import jluzonepro.zyascend.com.common.entity.ConstValue;
import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.CourseDao;
import jluzonepro.zyascend.com.common.entity.DaoMaster;
import jluzonepro.zyascend.com.common.entity.DaoSession;
import jluzonepro.zyascend.com.common.entity.Editor;
import jluzonepro.zyascend.com.common.entity.EditorDao;
import jluzonepro.zyascend.com.common.entity.ImageValue;
import jluzonepro.zyascend.com.common.entity.Job;
import jluzonepro.zyascend.com.common.entity.JobDao;
import jluzonepro.zyascend.com.common.entity.MainImage;
import jluzonepro.zyascend.com.common.entity.MainImageDao;
import jluzonepro.zyascend.com.common.entity.News;
import jluzonepro.zyascend.com.common.entity.NewsDao;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.ScoreDao;
import jluzonepro.zyascend.com.common.entity.StuInfo;
import jluzonepro.zyascend.com.common.entity.StuInfoDao;
import jluzonepro.zyascend.com.common.entity.Term;
import jluzonepro.zyascend.com.common.entity.TermDao;
import jluzonepro.zyascend.com.common.entity.Todo;
import jluzonepro.zyascend.com.common.entity.TodoDao;
import jluzonepro.zyascend.com.common.model.net.HttpManagerListener;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public class DataUtils implements DataListener {

    private static SharedPreferences sf;
    private static final String TAG = "TAG_DataUtils";
    private static DataUtils dataUtils;
    private static DaoSession daoSession;
    private DataUtils(Context context){
        sf = context.getSharedPreferences(ConstValue.KEY_STU_INFO, Activity.MODE_PRIVATE);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "jluzone.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public static DataUtils getInstance(Context context){
        if (dataUtils == null){
            synchronized (DataUtils.class){
                if (dataUtils == null){
                    dataUtils = new DataUtils(context);
                }
            }
        }
        return dataUtils;
    }

    @Override
    public void saveStuInfo(StuInfo stuInfo) {
        StuInfoDao dao = daoSession.getStuInfoDao();
        try{
            dao.insertOrReplace(stuInfo);
            Log.d(TAG, "saveStuInfo: saved"+stuInfo.getAccount()+stuInfo.getName());
        }catch (Exception e){
            e.printStackTrace();
            printError(e);
        }
        saveCurrentTerm(stuInfo.currentTerm);
    }

    @Override
    public int getCurrentTerm() {
        return sf.getInt(ConstValue.CURRENT_TERM,0);
    }

    @Override
    public void saveCurrentTerm(int currentTerm) {
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt(ConstValue.CURRENT_TERM,currentTerm);
        editor.apply();
    }

    private void printError(Exception e) {
        Log.d(TAG, "error: "+e.toString());
    }

    @Override
    public void getStuInfo(HttpManagerListener.LoginCallBack callBack) {
        StuInfoDao dao = daoSession.getStuInfoDao();
        StuInfo info = new StuInfo();
        try {
            info = dao.queryBuilder().where(StuInfoDao.Properties.Key.eq(1)).build().unique();
            Log.d(TAG, "getStuInfo: ");
        }catch (Exception e){
            e.printStackTrace();
            printError(e);
        }
        callBack.onSuccess(info);
    }

    @Override
    public void saveSchedule(Course course) {
        CourseDao dao = daoSession.getCourseDao();
        try {
            dao.insertOrReplace(course);
            Log.d(TAG, "saveSchedule: "+course);
        }catch (Exception e) {
            e.printStackTrace();
            printError(e);
        }
    }

    @Override
    public List<Course> getAllCoursesByTerm(int termId) {
        CourseDao dao = daoSession.getCourseDao();
        List<Course> courses = new ArrayList<>();
        try {
            courses = dao.queryBuilder().where(CourseDao.Properties.TermId.eq(termId)).build().list();
            Log.d(TAG, "getAllCoursesByTerm: size = "+courses.size());
        }catch (Exception e){
            Log.d(TAG, "getAllCoursesByTerm: error"+e.toString());
        }
        return courses;
    }

    @Override
    public Course getCourse(int id) {
        CourseDao dao = daoSession.getCourseDao();
        return dao.queryBuilder().where(CourseDao.Properties.Id.eq(id)).build().unique();
    }

    @Override
    public void upDateCourse(List<Course> courses, int termId) {
        // TODO: 2016/10/17 待优化
        CourseDao dao = daoSession.getCourseDao();
        dao.deleteInTx(getAllCoursesByTerm(termId));
        for (Course course : courses) {
            saveSchedule(course);
        }
    }

    @Override
    public List<Score> getScoresByType(int type, int id) {
        List<Score> scores = null;
        ScoreDao dao = daoSession.getScoreDao();
        switch (type){
            case ConstValue.SCORE_TYPE_ALL:
                scores = dao.loadAll();
                break;
            case ConstValue.SCORE_TYPE_YEAR:
                scores = dao.queryBuilder().where(ScoreDao.Properties.TermId.between(id-1,id+2)).build().list();
                break;
        }

        return scores;
    }

    @Override
    public void saveScore(Score score) {
        ScoreDao dao = daoSession.getScoreDao();
        dao.insertOrReplace(score);
    }

    @Override
    public void saveNews(News news) {
        // TODO: 2016/10/23 有问题
        NewsDao dao = daoSession.getNewsDao();
        try {
            dao.insertOrReplace(news);
            Log.d(TAG, "saveNews: ");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public News getNewsByUrl(String url) {

        NewsDao dao = daoSession.getNewsDao();
        return dao.queryBuilder().where(NewsDao.Properties.Url.eq(url)).build().unique();
    }

    @Override
    public List<News> getNewsByEditor(String editor) {

        NewsDao dao = daoSession.getNewsDao();
        List<News> news = new ArrayList<>();
        try {
            news = dao.queryBuilder().where(NewsDao.Properties.Editor.eq(editor)).build().list();
            Log.d(TAG, "getNewsByEditor: "+news.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    @Override
    public List<News> getAllNews() {
        NewsDao dao = daoSession.getNewsDao();
        return dao.loadAll();
    }

    @Override
    public void saveJobs(Job job) {
        JobDao dao = daoSession.getJobDao();

        if (dao.getKey(job) != null){
            dao.update(job);
        }else {
            dao.insert(job);
        }
    }

    @Override
    public List<Job> getAllJobsByType(String type) {
        List<Job> jobs;
        JobDao dao = daoSession.getJobDao();
        if (TextUtils.equals(type,ConstValue.TAG_SHIXI)){
            jobs = dao.queryBuilder().where(JobDao.Properties.Type.eq(3)).list();
        }else {
            jobs = dao.queryBuilder().where(JobDao.Properties.Type.eq(1)).list();
        }
        return jobs;
    }

    @Override
    public void saveEditor(List<String> list,boolean type) {
        EditorDao dao = daoSession.getEditorDao();
        try {
            dao.deleteAll();
            for (String s : list) {
                dao.insert(new Editor(null,s,type));
                Log.d(TAG, "saveEditor: insert"+s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getEditor(boolean type) {
        List<String> list = new ArrayList<>();
        EditorDao dao = daoSession.getEditorDao();

        try {
            List<Editor> editorList = dao.loadAll();

            if (ActivityUtils.NotNullOrEmpty(editorList)){
                Log.d(TAG, "getEditor: size = "+editorList.size());
                for (Editor editor : editorList) {
                    list.add(editor.getName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG, "getEditor: "+list.size());
        return list;
    }

    @Override
    public List<MainImage> getImages() {
        MainImageDao dao = daoSession.getMainImageDao();
        List<MainImage> images = new ArrayList<>();
        try {
            images = dao.loadAll();
            Log.d(TAG, "getImages: size = "+images.size());
        }catch (Exception e){
            Log.d(TAG, "getImages: error = "+e.toString());
        }
        return images;
    }

    @Override
    public List<Todo> getTodos() {
        TodoDao dao = daoSession.getTodoDao();
        List<Todo> todos = new ArrayList<>();
        try {
            todos = dao.loadAll();
            Log.d(TAG, "gettodoss: size = "+todos.size());
        }catch (Exception e){
            Log.d(TAG, "gettodoss: error = "+e.toString());
        }
        return todos;
    }

    @Override
    public List<Course> getCourseByDayAndTerm(int day,int termId) {
        CourseDao dao = daoSession.getCourseDao();
        List<Course> courses = new ArrayList<>();
        try {
            courses = dao.queryBuilder().where(CourseDao.Properties.TermId.eq(termId))
                    .where(CourseDao.Properties.DayOfWeek.eq(day)).build().list();
            Log.d(TAG, "getCourseByDayAndTerm: "+ courses.size());
        }catch (Exception e){
            Log.d(TAG, "getCourseByDay: error = "+e.toString());
        }
        return courses;
    }

    @Override
    public void saveTodos(List<Todo> todos) {
        TodoDao dao = daoSession.getTodoDao();
        dao.deleteAll();
        if (ActivityUtils.NotNullOrEmpty(todos)){
            for (Todo t : todos) {
                dao.insertOrReplace(t);
            }
        }
    }

    @Override
    public List<Term> getTerms() {
        List<Term> terms = new ArrayList<>();
        TermDao dao = daoSession.getTermDao();
        try {
            terms = dao.loadAll();
            Log.d(TAG, "getTerms: size = "+terms.size());
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "getTerms: error = "+e.toString());
        }
        return terms;
    }

    @Override
    public void saveTerms(List<Term> terms) {
        TermDao dao = daoSession.getTermDao();
        try {
            dao.deleteAll();
            if (ActivityUtils.NotNullOrEmpty(terms)){
                for (Term t : terms) {
                    dao.insertOrReplace(t);
                }
                Log.d(TAG, "saveTerms: "+terms.size());
            }
        }catch (Exception e){
            printError(e);
        }
    }


    public void saveCurrentWeek(int week){
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt(ConstValue.CURRENT_WEEK,week);
        editor.apply();
        Log.d(TAG, "saveCurrentWeek: "+week);
    }

    public int getCurrentWeek() {
        Log.d(TAG, "getCurrentWeek: ");
        return sf.getInt(ConstValue.CURRENT_WEEK,0);
    }

    public void saveImages(String message) {
        Log.d(TAG, "saveImages: "+message);
        ImageValue value = new ImageValue();
        try {
            value = JSON.parseObject(message,ImageValue.class);
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "saveImages: 解析错误 Message = "+message);
        }
        MainImageDao dao = daoSession.getMainImageDao();
        try {
            dao.deleteAll();
            List<MainImage> images = mapImages(value);
            Log.d(TAG, "saveImages: iamges size = "+images.size());
            for (MainImage image : images) {
                dao.insert(image);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<MainImage> mapImages(ImageValue value) {
        List<MainImage> images = new ArrayList<>();
        if (value!= null){
            for (int i = 0; i < value.getList().size(); i++) {
                ImageValue.ListBean bean = value.getList().get(i);
                MainImage image = new MainImage(null,bean.getTitle(),bean.getUrl());
                images.add(image);
            }
        }
        return images;
    }
}

package jluzonepro.zyascend.com.common.model.net;

import android.os.Handler;
import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import jluzonepro.zyascend.com.common.base.BaseCallBack;
import jluzonepro.zyascend.com.common.entity.AvgScore;
import jluzonepro.zyascend.com.common.entity.ConstValue;
import jluzonepro.zyascend.com.common.entity.Course;
import jluzonepro.zyascend.com.common.entity.CourseResult;
import jluzonepro.zyascend.com.common.entity.EvaluateItem;
import jluzonepro.zyascend.com.common.entity.EvaluateParams;
import jluzonepro.zyascend.com.common.entity.EvaluateResult;
import jluzonepro.zyascend.com.common.entity.Job;
import jluzonepro.zyascend.com.common.entity.JobContent;
import jluzonepro.zyascend.com.common.entity.JobListResult;
import jluzonepro.zyascend.com.common.entity.LiveChannel;
import jluzonepro.zyascend.com.common.entity.LiveResult;
import jluzonepro.zyascend.com.common.entity.MyResponseBody;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.ScoreDetail;
import jluzonepro.zyascend.com.common.entity.ScoreValue;
import jluzonepro.zyascend.com.common.entity.StuInfo;
import jluzonepro.zyascend.com.common.entity.Term;
import jluzonepro.zyascend.com.common.entity.Weather;
import jluzonepro.zyascend.com.common.http.OkHttpUtils;
import jluzonepro.zyascend.com.common.http.ResponseCallBack;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import okhttp3.OkHttpClient;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public class HttpManager implements HttpManagerListener {

    private static final String TAG = "TAG_HTTPUTILS";
    private static final String MOUSE_PATH = "dFgABdAAAOdAQCddBAC0cBgDEbCwDWbDwDmaEgD2aFgEHaFwEYZGAEoZGQJlZIQKYZJAKsZKQK5ZLQLJZMwLaZOwLqaPQL7aQQMMaQwMcaRwMtaSQM%2BaTQNPaUgNfaWQNwaXwOAaZgORabQOjadAOyaewPDagwPUajAPkalAP1amQQHanQQXaogQoapgQ5aqgRJarwRaauwR%2BavgSMawQSeawgTJawwTWaxATfaxQURaxgUmaxwUzayAVDayQVWaywVta0QWJa0wWXa1AWpa1QW%2Ba1gXJa2AXga2gXua3AX7Z4AYgZ4QY2Z4gY%2BZ5AZOZ5wZvfXARi";
    private static HttpManager INSTANCE;
    private Handler mHandler;
    private OkHttpClient mHttpClient;
    private static int mStudId;
    private static String mStudName;
    private int mTermId;
    private LoginCallBack mLoginCallBack;
    private OkHttpUtils okHttpUtils;

    private HttpManager(){
        okHttpUtils = OkHttpUtils.getInstance();
        mHttpClient = okHttpUtils.getHttpClient();
        mHandler = okHttpUtils.getHandler();
//        mHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {
//            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
//            //Tip：这里的key必须是String HttpUrl不行
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                cookieStore.put(url.host(), cookies);
//            }
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                List<Cookie> cookies = cookieStore.get(url.host());
//                return cookies != null ? cookies : new ArrayList<Cookie>();
//            }
//        }).build();

    }

    public static HttpManager getInstance(){
        if (INSTANCE == null){
            synchronized (HttpManager.class){
                if (INSTANCE == null){
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void cancel() {
        okHttpUtils.cancelAll();
    }

    @Override
    public void login(String tag, boolean isLoginOutside, String user, String passWord,
                      final LoginCallBack loginCallBack) {

        mLoginCallBack = loginCallBack;
        String url = isLoginOutside ? ConstValue.LOGIN_OUT_URL : ConstValue.LOGIN_IN_URL;
        String md5PassWord = getMD5Str("UIMS" + user + passWord);

        OkHttpUtils.post()
                .url(url)
                .addParams("j_username",user)
                .addParams("j_password",md5PassWord)
                .addParams("mousePath",MOUSE_PATH)
                .tag(tag)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        getCurrentInfo();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        loginCallBack.onFailure(e);
                    }
                });
    }

    @Override
    public void getCurrentInfo() {
        OkHttpUtils.post()
                .url(ConstValue.CURRENT_INFO)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        int term = 0;
                        try {
                            JSONObject responses = JSON.parseObject(response);
                            JSONObject defRes = JSON.parseObject(responses.getString("defRes"));
                            term = defRes.getInteger("teachingTerm");
                            mStudId = responses.getInteger("userId");
                            mStudName = responses.getString("nickName");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        StuInfo stuInfo = new StuInfo();
                        stuInfo.name = mStudName;
                        stuInfo.stuId = mStudId;
                        stuInfo.currentTerm = term;

                        if (mStudId == 0){
                            onFailure(new Exception("登录信息获取失败"));
                            return;
                        }
                        assert mLoginCallBack != null;
                        mLoginCallBack.onSuccess(stuInfo);
                        Log.d(TAG, "onResponse: 获取登录信息成功");
                        Log.d(TAG, "onResponse: stuId = "+mStudId+"stuName = "+mStudName+"term "+term);

                    }
                    @Override
                    public void onFailure(Exception e) {
                        assert mLoginCallBack != null;
                        mLoginCallBack.onFailure(e);
                    }
                });
    }

    @Override
    public void getSemester(String tag, final TermCallBack callback){
        Log.d(TAG, "getSemester: ");
        String requestString = "{\"tag\":\"search@teachingTerm\",\"branch\":\"default\",\"params\":{}}";
        OkHttpUtils.post()
                .url(ConstValue.SEMES_SCORE_URL)
                .json(requestString)
                .tag(tag)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        List<Term> list = null;
                        try {
                            JSONObject responses = JSON.parseObject(response);
                            MyResponseBody body = JSON.parseObject(responses.toString(), MyResponseBody.class);
                            Log.d(TAG, "responseTerm: value = "+body.getValue());
                            list = JSON.parseObject(body.getValue(), new TypeReference<ArrayList<Term>>() {});
                        } catch (Exception e) {
                            IOException ioException = new IOException("解析出错");
                            onFailure(ioException);
                            e.printStackTrace();
                        }
                        callback.onSuccess(list);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getNewScore(String tag, int row, final ScoreCallBack scoreCallBack){
        String json = "{\"tag\":\"archiveScore@queryCourseScore\",\"branch\":\"latest\",\"params\":{},\"rowLimit\":15}\n";
        requestScore(tag,json,scoreCallBack);
    }

    @Override
    public void getAllScore(String tag, final ScoreCallBack callBack) {
        String requestString = "{\"type\":\"search\",\"tag\":\"archiveScore@queryCourseScore\",\"branch\":\"byYear\",\"params\":{\"studId\":"
                +mStudId
                +"},\"orderBy\":\"teachingTerm.termId, course.courName\"}";
        requestScore(tag,requestString,callBack);
//
    }

    @Override
    public void getYearScore(String tag, int year, final ScoreCallBack callBack) {
        String requestString = "{\"type\":\"search\",\"tag\":\"archiveScore@queryCourseScore\",\"branch\":\"byYear\",\"params\":{\"studId\":"+mStudId
                +",\"year\":\""+year+"\"},\"orderBy\":\"teachingTerm.termId, course.courName\"}";
        requestScore(tag,requestString,callBack);
    }

    private void requestScore(String tag, String requestString, final ScoreCallBack callBack) {
        OkHttpUtils.post()
                .url(ConstValue.SEMES_SCORE_URL)
                .json(requestString)
                .tag(tag)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        List<ScoreValue> scoreValues = null;
                        try {
                            JSONObject responses = JSON.parseObject(response);
                            MyResponseBody responseBody = JSON.parseObject(responses.toString(),MyResponseBody.class);
                            scoreValues = JSON.parseObject(responseBody.getValue(), new TypeReference<ArrayList<ScoreValue>>() {
                            });
                        } catch (Exception e) {
                            IOException ioException = new IOException("未登录");
                            onFailure(ioException);
                            e.printStackTrace();
                        }
                        List<Score> scoreList = mapScore(scoreValues);
                        Log.d(TAG, "responseScore: size = "+scoreList.size());
                        callBack.onSuccess(scoreList);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callBack.onFailure(e);
                    }
                });
    }

    @Override
    public void getAvgScore(String tag, final AvgScoreCallback callback) {
        //获取选修课统计
        //http://uims.jlu.edu.cn/ntms/service/res.do
        //{"type":"query","res":"stat-credit-stud","params":{"studId":238615}}


        //获取平均成绩绩点统计
        //http://uims.jlu.edu.cn/ntms/service/res.do
        //

        String requestString = "{\"type\":\"query\",\"res\":\"stat-avg-gpoint\",\"params\":{\"studId\":"+mStudId+"}}";
        OkHttpUtils.post()
                .url(ConstValue.SEMES_SCORE_URL)
                .json(requestString)
                .tag(tag)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "onSuccess: avgScore = "+response);
                        AvgScore avgScore = null;
                        try {
                            JSONObject responses = JSON.parseObject(response);
                            avgScore = JSON.parseObject(responses.toString(),AvgScore.class);
                        } catch (Exception e) {
                            IOException ioException = new IOException("未登录");
                            onFailure(ioException);
                            e.printStackTrace();
                        }
                        callback.onSuccess(avgScore);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callback.onFailure(e);
                    }
                });

    }

    @Override
    public void getScoreDetail(String tag, String asId, final ScoreDetailCallback callback) {
        final String requestString = "{\"asId\":\""+asId+"\"}";
        OkHttpUtils.post()
                .url(ConstValue.SCORE_DETAIL_URL)
                .json(requestString)
                .tag(tag)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        ScoreDetail detail = new ScoreDetail();
                        try {
                            detail = JSON.parseObject(response,ScoreDetail.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                            IOException ioException = new IOException("解析失败");
                            onFailure(ioException);
                        }
                       callback.onSuccess(detail);
                    }
                    @Override
                    public void onFailure(Exception e) {
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getXiaoZhaoList(String tag, int page, JobListCallback callback) {

        requestJobList(tag,ConstValue.URL_XIAOZHAO+page,callback);

    }

    private void requestJobList(String tag,String url, final JobListCallback callback) {
        OkHttpUtils.get()
                .tag(tag)
                .url(url)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        JobListResult results = null;
                        try {
                            results = JSON.parseObject(response,JobListResult.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                            IOException ioException = new IOException("解析失败");
                            onFailure(ioException);
                        }
                        callback.onSuccess(mapJobs(results));
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getShixiList(String tag, int page, JobListCallback callback) {
        requestJobList(tag,ConstValue.URL_SHIXI+page,callback);
    }

    @Override
    public void getJobContent(String tag, int id, final JobContentCallback callback) {

        OkHttpUtils.get()
                .url(ConstValue.URL_JOB_CONTENT + id)
                .tag(tag)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        JobContent content = null;
                        try {
                            content = JSON.parseObject(response,JobContent.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                            IOException ioException = new IOException("解析失败");
                            onFailure(ioException);
                        }
                       callback.onSuccess(content);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callback.onFailure(e);
                    }
                });

    }

    @Override
    public void getWeather(String tag, final WeatherCallback callback) {
        OkHttpUtils.get()
                .url(ConstValue.URL_WEATHER)
                .tag(tag)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        Weather weather = new Weather();
                        try {
                            Log.d(TAG, "responseWeather: "+response);
                            weather = JSON.parseObject(response,Weather.class);
                        }catch (Exception e){
                            e.printStackTrace();
                            IOException ioException = new IOException("解析失败");
                            onFailure(ioException);
                            Log.d(TAG, "responseDetailScore: error = "+e.toString());
                        }
                        callback.onSuccess(weather);
                    }
                    @Override
                    public void onFailure(Exception e) {
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getChannel(final ChannelCallback callback) {
        OkHttpUtils.get()
                .url(ConstValue.URL_CHANNEL)
                .tag(callback)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        List<LiveChannel> liveChannels = new ArrayList<LiveChannel>();
                        try {
                            Log.d(TAG, "responseChannel: "+response);
                            LiveResult result = JSON.parseObject(response,LiveResult.class);
                            liveChannels = mapChannel(result);
                        }catch (Exception e){
                            e.printStackTrace();
                            IOException ioException = new IOException("解析失败");
                            onFailure(ioException);
                            Log.d(TAG, "responseDetailScore: error = "+e.toString());
                        }
                        callback.onSuccess(liveChannels);

                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "onFailure: channel:    "+e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    private List<LiveChannel> mapChannel(LiveResult result) {
        if (result != null && result.getResultValue() != null){
            return result.getResultValue();
        }
        return null;
    }

    @Override
    public void loadEvaluateList(final BaseCallBack<List<EvaluateItem>> callBack) {
        String json = "{\"tag\":\"student@evalItem\",\"branch\":\"self\",\"params\":{\"blank\":\"Y\"}}";
        OkHttpUtils.post()
                .tag(callBack)
                .url(ConstValue.SEMES_SCORE_URL)
                .json(json)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "loadEvaluateList: ----->"+response);
                        List<EvaluateItem> items = null;
                        try {
                            EvaluateResult result = JSON.parseObject(response,EvaluateResult.class);
                            items = mapEvaluate(result);
                        }catch (Exception e){
                            e.printStackTrace();
                            IOException ioException = new IOException("解析失败");
                            onFailure(ioException);
                            callBack.onFailure(e);
                        }
                        callBack.onSuccess(items);
                    }

                    @Override
                    public void onFailure(Exception e) {
                            callBack.onFailure(e);
                    }
                });
    }

    private List<EvaluateItem> mapEvaluate(EvaluateResult result) {
        if (result == null)return null;
        List<EvaluateItem> items = new ArrayList<>();
        for (EvaluateResult.ValueBean bean : result.getValue()) {
            EvaluateItem item = new EvaluateItem(bean.getTargetClar().getNotes().replace("讲授","").replace("课程","")
                    ,bean.getTarget().getName()
                    ,bean.getEvalItemId()
                    ,bean.getEvalActTime().getEvalTime().getDateStart()
                    ,bean.getEvalActTime().getEvalTime().getDateStop()
                    ,false);
            items.add(item);
        }
        Log.d(TAG, "mapEvaluate:------> "+items.size());
        return items;
    }

    @Override
    public void evaluate(String itemId, int type, final BaseCallBack callBack) {
        String json = getRequestString(itemId,type);
        OkHttpUtils.post()
                .url(ConstValue.URL_EVALUATE)
                .json(json)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        callBack.onSuccess(null);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callBack.onFailure(e);
                    }
                });
    }

    private String getRequestString(String itemId, int type) {

        String s = null;
        String t = "A";
        switch (type){
            case 0:
                t = "A";
                break;
            case 1:
                t = "B";
                break;
            case 2:
                t = "C";
                break;
            case 3:
                t = "D";
                break;
        }
        Log.d(TAG, "getRequestString: t = "+t);
        try {
            s = JSON.toJSONString(new EvaluateParams(itemId,new EvaluateParams.Answers(t)));
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "getRequestString: error = "+e.toString());
        }finally {
            //{"evalItemId":"3436805","answers":{"prob11":"A","prob12":"A","prob13":"D","prob14":"A"
            // ,"prob15":"A","prob21":"A","prob22":"A","prob23":"A","prob31":"A","prob32":"A","prob41":"A","prob42":"A","prob43":"A","prob51":"A","prob52":"A","sat6":"A","mulsel71":"L","advice8":"无"}}
            s = "{\"evalItemId\":\""+itemId+"\",\"answers\":{\"prob11\":\"A\",\"prob12\":\"A\",\"prob13\"" +
                    ":\"D\",\"prob14\":\"A\",\"prob15\":\"A\",\"prob21\":\"A\",\"prob22\":\"A\",\"prob23\":\"A\",\"prob31\":\"A\",\"prob32\":\"A\",\"prob41\":\"A\",\"prob42\":\"A\",\"prob43\":\"A\",\"prob51\":\"A\",\"prob52\":\"A\",\"sat6\":\"A\",\"mulsel71\":\"L\",\"advice8\":\"无\"}}";
        }
        Log.d(TAG, "getRequestString: "+ s);
        return s;
    }


    @Override
    public void getSchedule(String tag, int termId, final ScheduleCallBack callBack) {
        mTermId = termId;
        String requestString = "{\"tag\":\"teachClassStud@schedule\",\"branch\":\"default\",\"params\":{\"termId\":"+termId+",\"studId\":"+mStudId+"}}";
        OkHttpUtils.post()
                .url(ConstValue.SEMES_SCORE_URL)
                .json(requestString)
                .tag(tag)
                .call(new ResponseCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        List<CourseResult> results = null;
                        try {
                            JSONObject responses = JSON.parseObject(response);
                            MyResponseBody responseBody = JSON.parseObject(responses.toString(),MyResponseBody.class);
                            results = JSON.parseObject(responseBody.getValue(), new TypeReference<ArrayList<CourseResult>>() {
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                            IOException ioException = new IOException("未登录");
                            onFailure(ioException);
                        }
                        callBack.onSuccess(mapCourse(results));
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callBack.onFailure(e);
                    }
                });

    }

    private List<Score> mapScore(List<ScoreValue> scoreValues) {

        List<Score> scores = new ArrayList<Score>();
        if (ActivityUtils.NotNullOrEmpty(scoreValues)){
            for (ScoreValue scoreValue : scoreValues) {
                Score item = new Score();
                item.setCourseId(Long.parseLong(scoreValue.getCourse().getCourseId()));
                item.setScore(scoreValue.getScore());
                item.setCredit(scoreValue.getCredit());
                item.setGpoint(scoreValue.getGpoint());
                item.setIsPass(scoreValue.getIsPass());
                item.setName(scoreValue.getCourse().getCourName());
                item.setAsId(Integer.parseInt(scoreValue.getAsId()));
                item.setTermId(Integer.parseInt(scoreValue.getTeachingTerm().getTermId()));
                item.setTermName(scoreValue.getTeachingTerm().getTermName());
                item.setStuId(Integer.parseInt(scoreValue.getStudent().getStudId()));
                item.setStuName(scoreValue.getStudent().getName());
                scores.add(item);
            }
        }
        return scores;

    }

    public String getMD5Str(String str)  {

        StringBuilder md5StrBuff = new StringBuilder();
        try {
            MessageDigest messageDigest = null;
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] byteArray = messageDigest.digest();
            for (byte aByteArray : byteArray) {
                if (Integer.toHexString(0xFF & aByteArray).length() == 1)
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return md5StrBuff.toString();
    }

    private List<Job> mapJobs(JobListResult result) {
        List<Job> jobs = new ArrayList<>();
        if (result != null){
            List<JobListResult.DataBean> datas = result.getData();
            for (JobListResult.DataBean data : datas) {
                Job job = new Job();
                job.setId(Long.parseLong(data.getTid()));
                job.setTitle(data.getTitle());
                job.setAddress(data.getAddress());
                job.setDate(data.getUpdatetime());
                job.setHits(Integer.parseInt(data.getHits()));
                job.setType(data.getType());
                jobs.add(job);
            }
        }
        return jobs;
    }

    private List<Course> mapCourse(List<CourseResult> results) {

        //垃圾数据，一点也不规范，cao
        List<Course> courses = new ArrayList<>();
        if (ActivityUtils.NotNullOrEmpty(results)){
            for (CourseResult result : results) {
                if (result.getTeachClassMaster().getLessonSchedules()!= null && ! result.getTeachClassMaster().getLessonSchedules().isEmpty()){
                    for (int i = 0; i < result.getTeachClassMaster().getLessonSchedules().size(); i++) {
                        Course course = new Course();
                        course.setId(Long.parseLong(result.getTeachClassMaster().getLessonSegment().getLssgId()+ i));
                        course.setName(result.getTeachClassMaster().getLessonSegment().getFullName());
                        course.setPlace(result.getTeachClassMaster().getLessonSchedules().get(i).getClassroom().getFullName());

                        CourseResult.TeachClassMasterBean.LessonSchedulesBean.TimeBlockBean time =
                                result.getTeachClassMaster().getLessonSchedules().get(i).getTimeBlock();

                        if (time != null){
                            if (time.getBeginWeek() != null
                                    && time.getEndWeek()!= null){
                                course.setBeginWeek(Integer.parseInt(time.getBeginWeek()));
                                course.setEndWeek(Integer.parseInt(time.getEndWeek()));
                            }
                            if (time.getDayOfWeek()!= null){
                                course.setDayOfWeek(Integer.parseInt(time.getDayOfWeek()));
                            }
                            course.setTime(time.getName());
                        }

                        course.setTeacher(result.getTeachClassMaster().getLessonTeachers().get(0).getTeacher().getName());
                        course.setTermId(mTermId);
                        Log.d(TAG, "mapCourse: setteerm = "+mTermId);
                        courses.add(course);
                    }
                }
            }
        }
        return courses;
    }



}

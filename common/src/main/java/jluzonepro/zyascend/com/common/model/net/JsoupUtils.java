package jluzonepro.zyascend.com.common.model.net;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jluzonepro.zyascend.com.common.entity.ConstValue;
import jluzonepro.zyascend.com.common.entity.News;

/**
 *
 * Created by Administrator on 2016/8/4.
 */
public class JsoupUtils implements JsoupListener {

    public static final String TAG_JWC = "tag_jsw";
    public static final String TAG_XIAO = "tag_xiao";
    public static final String TAG_JWC_CONTENT = "tag_jwc_content";
    public static final String TAG_XIAO_CONTENT = "tag_xiao_content";


    private static JsoupUtils INSTANCE;
    private String mTag;
    private static final String TAG = "TAG_JsoupUtils";
    private NewsCallBack mNewsListCallback;
    private ContentCallback mContentCallback;

    private JsoupUtils(){
    }

    public static JsoupUtils getInstance(){
        if (INSTANCE == null) {
            synchronized (JsoupUtils.class){
                if (INSTANCE == null) {
                    INSTANCE = new JsoupUtils();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getJwNews(NewsCallBack newsCallBack,int page) {
        Log.d(TAG, "getJwNews: ");
        mTag = TAG_JWC;
        mNewsListCallback = newsCallBack;
        new NewsListTask().execute(ConstValue.JWC_URL);
    }

    @Override
    public void getSchoolNews(NewsCallBack newsCallBack,int page) {
        Log.d(TAG, "getSchoolNews: ");
        mTag = TAG_XIAO;
        mNewsListCallback = newsCallBack;
        mNewsListCallback.onFailure(new Exception("数据源变动，无法解析"));
//        return;
//        new NewsListTask().execute(ConstValue.XIAO_URL + page);
    }

    @Override
    public void getJwContent(String url, ContentCallback callback) {
        mTag = TAG_JWC_CONTENT;
        mContentCallback = callback;
        new NewsContentTask().execute(url);
    }

    @Override
    public void getXiaoContent(String url, ContentCallback callback) {
        mTag = TAG_XIAO_CONTENT;
        mContentCallback = callback;
        new NewsContentTask().execute(url);
    }

    @Override
    public void getMainImages(ImageCallback callback) {
        ImageCallback mImageCallback = callback;
        Log.d(TAG, "getMainImages: ");
    }


    private class NewsListTask extends AsyncTask<String ,Void,List<News>>{

        @Override
        protected List<News> doInBackground(String... params) {
            Log.d(TAG, "doInBackground: ");
            return loadJwcNews(params[0]);
        }

        private List<News> loadJwcNews(String param) {

            List<News> list = new ArrayList<>();
            String title = "";
            String date = "";
            String editor = "";
            String url = "";
            long id = 0;

            String host = "http://jwc.jlu.edu.cn";

            try {
                Document document = getDocument(param);
                Element clearFix = document.getElementsByClass("clearfix").last();
                Log.d(TAG, "loadJwcNews: clearFix = "+clearFix.text());
                Elements li = clearFix.getElementsByTag("li");
                for (Element e : li){

                    date = e.getElementsByClass("time").text();
                    title = e.getElementsByTag("a").first().attr("title");
                    url = e.getElementsByTag("a").first().attr("href");

                    //处理数据
                    //../info/1051/2291.htm
                    //url = http://jwc.jlu.edu.cn/info/1050/2441.htm
                    url = host+url.replace("..","");
                    date = date.replace("[","").replace("]","");

                    Log.d(TAG, "date = "+date);
                    Log.d(TAG, "title = "+title);
                    Log.d(TAG, "url = "+url);

                    //处理date
                    News news = new News(id,title,editor,date,"",url);
                    list.add(news);
                }
            }catch (Exception e){
                onGetListError(e);
                Log.e(TAG, "loadJwcNews: "+e.toString());
            }
            Log.d(TAG, "loadJwcNews: "+ list.size());
            return list;
        }



        @Override
        protected void onPostExecute(List<News> newses) {
            super.onPostExecute(newses);
            if (mNewsListCallback != null){
                mNewsListCallback.onSuccess(newses);
            }


        }
    }

    private Document getDocument(String param) throws IOException {
//            //获取请求连接
//            Connection con = Jsoup.connect(param);
//            //请求头设置，特别是cookie设置
//            con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
//            con.header("Referer", "http://jwc.jlu.edu.cn/zxzx/ksap.htm");
//            con.header("Cookie", "lzstat_uv=19835556632710270647|2434659; _ga=GA1.3.1097107285.1464624067; JSESSIONID=60C3B42DDA3714D93163E95A145E9217");
//            //解析请求结果
//            Document doc=con.get();

        Document document = Jsoup.connect(param)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                .referrer("http://jwc.jlu.edu.cn/zxzx/ksap.htm")
                .cookie("Cookie","lzstat_uv=19835556632710270647|2434659; _ga=GA1.3.1097107285.1464624067; JSESSIONID=60C3B42DDA3714D93163E95A145E9217")
                .get();
        Log.d(TAG, "loadJwcNews: doc = "+document.text());
        return document;
    }

    private class NewsContentTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "doInBackground: content");
            return loadJWcContent(params[0]);
        }

        private String loadJWcContent(String param) {
            String content = "";
            try {
                Document document = getDocument(param);
                Element element = document.getElementsByAttributeValue("name","_newscontent_fromname").first();
                if (element != null){
                    content = element.toString();
                    Log.d(TAG, "getContent: "+element.toString());
                }
            }catch (Exception e){
                onGetContentError(e);
            }
            return content;
        }

        private String loadXiaoContent(String url) {
            String content = "";
            try {
                Document document = Jsoup.connect(url).get();
                Element element = document.getElementById("showContent");
                content = element.toString();
                Log.d(TAG, "Content: "+content);
            }catch (Exception e){
                onGetContentError(e);
            }
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: before+ " + s);
            super.onPostExecute(s);

            if (mContentCallback != null){
                mContentCallback.onSuccess(s);
                Log.d(TAG, "onPostExecute: "+s);
            }
        }
    }

    private void onGetListError(final Exception e) {

       new Handler(Looper.getMainLooper()).post(new Runnable() {
           @Override
           public void run() {
               if (mNewsListCallback != null ){
                   mNewsListCallback.onFailure(e);
               }
           }
       });
    }

    private void onGetContentError(final Exception e){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (mContentCallback != null ){
                    mContentCallback.onFailure(e);
                }
            }
        });
    }
}

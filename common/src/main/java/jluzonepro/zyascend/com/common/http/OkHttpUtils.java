package jluzonepro.zyascend.com.common.http;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jluzonepro.zyascend.com.common.base.BaseApplication;
import jluzonepro.zyascend.com.common.utils.NetStateUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * Created by Administrator on 2016/11/11.
 */

public class OkHttpUtils {




    private static final int MAX_AGE_DEFAULT = 30;
    private static final int MAX_STALE_DEFAULT = 60 * 60 * 24 * 3; // 没网失效3天

    private Handler mHandler;
    private OkHttpClient.Builder okHttpBuilder;
    private static OkHttpUtils INSTANCE;
    private static Application context;//全局上下文
    private OkHttpClient okHttpClient;


    public static void init(Application app){
        context = app;
    }

    private OkHttpUtils(){
        File cacheDir = new File(context.getExternalCacheDir(),"zhiHuApi-cache");
        //最大缓存为10M
        Cache cache = new Cache(cacheDir,10*1024*1024);

        okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(10000, TimeUnit.MILLISECONDS);
        okHttpBuilder.readTimeout(20000, TimeUnit.MILLISECONDS);
        okHttpBuilder.cookieJar(new CookiesManager());
        okHttpBuilder.cache(cache)
                .addInterceptor(CACHE_INTERCEPTOR)
                .addNetworkInterceptor(CACHE_INTERCEPTOR);

        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpUtils getInstance(){
        if (INSTANCE == null){
            synchronized (OkHttpUtils.class){
                if (INSTANCE == null){
                    INSTANCE = new OkHttpUtils();
                }
            }
        }
        return INSTANCE;
    }


    public void build(){
        okHttpClient = okHttpBuilder.build();
    }

    public OkHttpClient getHttpClient(){
        return okHttpClient;
    }

    public OkHttpUtils setConnectTimeOut(long timeOut){
        okHttpBuilder.connectTimeout(timeOut, TimeUnit.MILLISECONDS);
        return this;
    }

    public OkHttpUtils setReadTimeOut(long timeOut){
        okHttpBuilder.readTimeout(timeOut, TimeUnit.MILLISECONDS);
        return this;
    }

    public Handler getHandler() {
        return mHandler;
    }


    public static PostCall post(){
        return new PostCall();
    }

    public static GetCall get(){
        return new GetCall();
    }


    private static final Interceptor CACHE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (!NetStateUtil.isNetworkAvailable(BaseApplication.context)){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response baseResponse = chain.proceed(request);
            Response finalResponse;

            if (NetStateUtil.isNetworkAvailable(BaseApplication.context)){
                //检查CacheControl是否为空
                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)){
                    cacheControl = "Cache-Control: public, max-age=" + MAX_AGE_DEFAULT;
                }

                finalResponse = baseResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .removeHeader("ETag")
                        .header("Cache-Control", cacheControl)
                        .build();
            }else {

                finalResponse = baseResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .removeHeader("ETag")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + MAX_STALE_DEFAULT)
                        .build();
            }
            return finalResponse;
        }
    };

    public void cancelTag(Object tag){

        for (Call call : getHttpClient().dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : getHttpClient().dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public void cancelAll(){
        getHttpClient().dispatcher().cancelAll();
    }


    private class CookiesManager implements CookieJar {

        private final PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }
        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;
        }
    }


}

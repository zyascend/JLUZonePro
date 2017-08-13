package jluzonepro.zyascend.com.common.http;

import android.os.Handler;
import android.text.TextUtils;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * Created by Administrator on 2016/11/11.
 */

public class PostCall {

    private Handler uiHandler;
    private OkHttpUtils okHttpUtils;
    private RequestBody requestBody;
    private Request request;
    private LinkedHashMap<String,String> params;
    private String url;
    private String jsonString;
    private Object tag;
    public static final MediaType MYJSON= MediaType.parse("application/json; charset=utf-8");
    private boolean executed;


    public PostCall() {
        okHttpUtils = OkHttpUtils.getInstance();
        uiHandler = okHttpUtils.getHandler();
        params = new LinkedHashMap<>();
    }

    public PostCall url(String url){
        this.url = url;
        return this;
    }

    public PostCall addParams(String key , String value){
        params.put(key,value);
        return this;
    }

    public PostCall tag(Object tag){
        this.tag = tag;
        return this;
    }

    public PostCall json(String json){
        this.jsonString = json;
        return this;
    }

    public void call(final ResponseCallBack responseCallBack){

        if (params != null && !params.isEmpty()){
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : params.keySet()) {
                builder.add(key,params.get(key));

            }
            requestBody = builder.build();
        }else if (!TextUtils.isEmpty(jsonString)){
            requestBody = RequestBody.create(MYJSON,jsonString);
        }

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        requestBuilder.tag(tag);
        if (requestBody!= null){
            requestBuilder.post(requestBody);
        }
        request = requestBuilder.build();

        synchronized (this) {
            if (executed) throw new IllegalStateException("Already executed.");
            executed = true;
        }

        okHttpUtils.getHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        responseCallBack.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String r = null;
                try {
                    r = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    responseCallBack.onFailure(e);
                }
                final String finalR = r;
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        responseCallBack.onSuccess(finalR);
                    }
                });
            }
        });

    }

}

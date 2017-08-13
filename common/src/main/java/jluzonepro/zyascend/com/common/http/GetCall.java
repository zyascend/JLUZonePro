package jluzonepro.zyascend.com.common.http;

import android.os.Handler;
import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/22.
 */

public class GetCall {

    private Handler uiHandler;
    private OkHttpUtils okHttpUtils;
    private String url;
    private Object tag;

    public GetCall() {
        okHttpUtils = OkHttpUtils.getInstance();
        uiHandler = okHttpUtils.getHandler();
    }

    public GetCall url(String url){
        this.url = url;
        return this;
    }

    public GetCall tag(Object tag){
        this.tag = tag;
        return this;
    }

    public void call(final ResponseCallBack callBack){
        if (TextUtils.isEmpty(url)){
            callBack.onFailure(new IllegalArgumentException("url is null"));
            return;
        }

        Request request = new Request.Builder()
                .get()
                .tag(tag)
                .url(url)
                .build();
        okHttpUtils.getHttpClient()
                .newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onFailure(e);
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String r = null;
                        try {
                            r = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                            callBack.onFailure(e);
                        }
                        final String finalR = r;
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(finalR);
                            }
                        });
                    }
                });
    }
}

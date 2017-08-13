package jluzonepro.zyascend.com.common.http;

/**
 *
 * Created by Administrator on 2016/11/11.
 */

public interface ResponseCallBack {
    void onSuccess(String response);
    void onFailure(Exception e);
}

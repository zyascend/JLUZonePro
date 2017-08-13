package jluzonepro.zyascend.com.common.router;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 功能：
 * 作者：zyascend on 2017/8/13 10:45
 * 邮箱：zyascend@qq.com
 */

public class RouterUtils {
    /**
     * 各Module的路径
     * @param path
     */

    /**
     * app
     */
    public static final String APP_LOGIN = "/app/LoginActivity";
    public static final String APP_MAIN = "/app/MainActivity";


    public static Object navigation(String path){

        return ARouter.getInstance()
                .build(path)
                .navigation();
    }

    public static Object navigation(String path, Bundle bundle){
        return ARouter.getInstance()
                .build(path)
                .with(bundle)
                .navigation();
    }

}

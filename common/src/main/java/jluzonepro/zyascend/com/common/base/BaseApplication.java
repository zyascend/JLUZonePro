package jluzonepro.zyascend.com.common.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.SDKInitializer;

import cn.jpush.android.api.JPushInterface;
import jluzonepro.zyascend.com.common.BuildConfig;
import jluzonepro.zyascend.com.common.http.OkHttpUtils;

/**
 *
 * Created by Administrator on 2016/11/2.
 */

public class BaseApplication extends Application {

    private boolean isNight;
    public static Context context;
//    public RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
//        refWatcher = LeakCanary.install(this);
        JPushInterface.init(this);
        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()
                .build();
        SDKInitializer.initialize(this);

        if (BuildConfig.DEBUG) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.printStackTrace(); // 打印日志的时候打印线程堆栈
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        BaseApplication application = (BaseApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

}

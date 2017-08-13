package jluzonepro.zyascend.com.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 *
 * Created by Administrator on 2016/7/7.
 */
public class ActivityUtils {

//    public static void addFragmentToActivity(android.support.v4.app.FragmentManager manager, Fragment fragment){
//
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.contentFrame,fragment);
//        transaction.commit();
//    }

    public static void enterActivity(Context preContext,Class<?> cla){
        Intent intent = new Intent(preContext,cla);
        preContext.startActivity(intent);
    }

    public static void showSnackBar(View view,String msg){
        Snackbar.make(view,msg, Snackbar.LENGTH_SHORT).show();
    }

    public static boolean NotNullOrEmpty(List list){
        return list !=null && !list.isEmpty();
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 检查是否有APP可以接受这个Intent
     */
    public static boolean isIntentSafe(Intent intent,Context context) {
        PackageManager packageManager = context.getPackageManager();
        List activities = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return activities.size() > 0;
    }

    public static void openUrl(Activity activity, String url){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);

    }

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }
}
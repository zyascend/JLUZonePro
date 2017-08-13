package jluzonepro.zyascend.com.common.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * Created by Administrator on 2016/10/22.
 */

public class ShareUtils {

    private static ShareCallback mCallBack;
    private int h = 0;
    private static final String TAG = "TAG_ShareUtils";
    private static ShareUtils INSTANCE;

    private ShareUtils(){

    }

    public static ShareUtils getInstance(ShareCallback callback){
        mCallBack = callback;
        if (INSTANCE == null){
            return new ShareUtils();
        }
        return INSTANCE;
    }



    public void saveContent(ScrollView scrollView){
        saveBitmap(getBitmapByView(scrollView));
    }

    public void shareContent(Context context, ScrollView scrollView){
        share(saveBitmap(getBitmapByView(scrollView)),context);
    }

    private void share(String bitmapUri ,Context context) {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        File file = new File(bitmapUri);
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        shareIntent.setType("image/jpeg/png");
//        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }


    public Bitmap getBitmapByView(ScrollView scrollView) {
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundResource(android.R.color.white);
        }
        Bitmap bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    private String saveBitmap(Bitmap bitmap){
        File appDir = new File(Environment.getExternalStorageDirectory(), "知吉");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, h + "share.png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            if (mCallBack != null){
                mCallBack.onSuccess();
            }

        } catch (IOException e) {
            e.printStackTrace();

            if (mCallBack != null){
                mCallBack.onFail();
            }
        }finally {
            bitmap.recycle();
        }

        return file.getPath();
    }

    public void shareSchedule(HorizontalScrollView scrollView,Context context) {
        share(saveBitmap(getBitmapByHSView(scrollView)),context);
    }

    private Bitmap getBitmapByHSView(HorizontalScrollView scrollView) {

        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getWidth();
            scrollView.getChildAt(i).setBackgroundResource(android.R.color.white);
        }
        Bitmap bitmap = Bitmap.createBitmap(h,scrollView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }


    public void shareView(Context context ,View view){
        share(saveBitmap(getBitmapByNormal(view)),context);
    }

    private Bitmap getBitmapByNormal(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public interface ShareCallback {
        void onSuccess();
        void onFail();
    }
}

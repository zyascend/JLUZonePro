package com.zyascend.JLUZonePro.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


import com.zyascend.JLUZonePro.login.LoginActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jluzonepro.zyascend.com.common.base.BasePresenter;
import jluzonepro.zyascend.com.common.entity.StuInfo;
import jluzonepro.zyascend.com.common.model.data.DataUtils;
import jluzonepro.zyascend.com.common.model.net.HttpManagerListener;
import jluzonepro.zyascend.com.common.utils.CacheCleanUtils;

/**
 *
 * Created by Administrator on 2016/11/2.
 */

public class UserPresenter extends BasePresenter<UserContract.View> implements UserContract.Presenter {


    public static final String INTENT_LOGIN_TYPE = "type_login";
    public static final String TYPE_OUT = "type_out";
    private final CacheCleanUtils mCacheUtils;
    private final DataUtils mDataUtils;
    private Context mContext;
    private static final String TAG = "TAG_UserPrsenter";

    public UserPresenter (Context context){
        mContext = context;
        mCacheUtils = CacheCleanUtils.getInstance(context.getApplicationContext());
        mDataUtils = DataUtils.getInstance(context.getApplicationContext());
    }

    @Override
    public void saveImage(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "知吉");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, "header.png");
        Log.d(TAG, "saveImage: "+file.getAbsolutePath());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Log.d(TAG, "saveImage: done!!!");
        } catch (IOException e) {
            Log.d(TAG, "saveImage: e = "+e.toString());
            e.printStackTrace();
        }finally {
            bitmap.recycle();
        }

        //传到bomb

    }

    @Override
    public void loadUserName() {
        mDataUtils.getStuInfo(new HttpManagerListener.LoginCallBack() {
            @Override
            public void onSuccess(StuInfo stuInfo) {
                if (stuInfo != null){
                    mViewListener.omNameLoaded(stuInfo.getName());
                }else {
                    Log.d(TAG, "onSuccess: stuinfo nulll");
                }
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void loadUserAvator() {
        Log.d(TAG, "loadUserAvator: ");
        File appDir = new File(Environment.getExternalStorageDirectory(), "知吉");
        File file = new File(appDir, "header.png");
        Log.d(TAG, "loadUserAvator: path = "+file.getAbsolutePath());
        Bitmap bt = BitmapFactory.decodeFile((file.getAbsolutePath()));// 从Sd中找头像，转换成Bitmap
        if (bt != null){
            mViewListener.onImageLoaded(bt);
        }else {
            Log.d(TAG, "loadUserAvator: nullll");
            //从Bomb获得bitmap
            mViewListener.onImageLoaded(null);
        }
    }

    @Override
    public void clearCache() {
        mCacheUtils.clearAllCache(mContext);
        mViewListener.onCacheCleared(mCacheUtils.getTotalCacheSize(mContext));
    }

    @Override
    public void loadCacheSize() {
        mViewListener.onCacheCleared(mCacheUtils.getTotalCacheSize(mContext));
    }


    @Override
    public void logout() {
//        Intent intent = new Intent(mContext, LoginActivity.class);
//        intent.putExtra(INTENT_LOGIN_TYPE,TYPE_OUT);
//        mContext.startActivity(intent);
    }
}

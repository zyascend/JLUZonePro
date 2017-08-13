package com.zyascend.JLUZonePro.user;

import android.graphics.Bitmap;

/**
 *
 * Created by Administrator on 2016/11/2.
 */

public interface UserContract {

    interface Presenter{

        void saveImage(Bitmap bitmap);
        void loadUserName();
        void loadUserAvator();
        void clearCache();
        void loadCacheSize();
        void logout();

    }
    interface View{

        void onImageLoaded(Bitmap bitmap);
        void omNameLoaded(String name);
        void onCacheCleared(String cacheSize);

    }

}

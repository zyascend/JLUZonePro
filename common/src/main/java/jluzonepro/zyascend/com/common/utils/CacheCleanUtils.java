package jluzonepro.zyascend.com.common.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;

import com.bumptech.glide.Glide;

import java.io.File;
import java.math.BigDecimal;

/**
 *
 * Created by Administrator on 2016/8/22.
 */
public class CacheCleanUtils {

    private static CacheCleanUtils cacheCleanUtils;
    private static String ImageExternalCatchDir;

    private CacheCleanUtils(Context context){
        ImageExternalCatchDir = context.getExternalCacheDir() + "/image_cache";
    }

    public static CacheCleanUtils getInstance(Context context) {
        if (cacheCleanUtils == null) {
            synchronized (CacheCleanUtils.class){
                if (cacheCleanUtils == null){
                    cacheCleanUtils = new CacheCleanUtils(context);
                }
            }
        }
        return cacheCleanUtils;
    }




    public String getTotalCacheSize(Context context) {
        long cacheSize = 0;
        try {
            cacheSize = getFolderSize(context.getCacheDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String state = Environment.getExternalStorageState();
        if (state != null && state.equals(Environment.MEDIA_MOUNTED)) {
            try {
                cacheSize += getFolderSize(context.getExternalCacheDir());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getFormatSize(cacheSize + getGlideCacheSize(context));
    }

    public void clearAllCache(Context context) {

        deleteDir(context.getCacheDir());
        String state = Environment.getExternalStorageState();
        if (state != null && state.equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }

        clearGlideAllCache(context);

     }

    /**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                });
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 清除图片所有缓存
     */
    public void clearGlideAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        deleteFolderFile(ImageExternalCatchDir, true);
    }
    /**
     * 获取Glide造成的缓存大小
     *
     * @return CacheSize
     */
    public double getGlideCacheSize(Context context) {
        try {
            return getFolderSize(new File(context.getCacheDir() + "/image_cache"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private  boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * *
     *
     * @param context
     */
    public  void cleanDatabases(Context context) {
        deleteDir(new File(context.getFilesDir().getPath()
                + context.getPackageName() + "/databases"));
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
     *
     * @param context
     */
    public  void cleanSharedPreference(Context context) {
//        deleteDir(new File("/data/data/"
//                + context.getPackageName() + "/shared_prefs"));
        deleteDir(new File(context.getFilesDir().getPath()
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }


}

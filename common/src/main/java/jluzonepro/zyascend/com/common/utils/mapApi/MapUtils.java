package jluzonepro.zyascend.com.common.utils.mapApi;

import com.baidu.location.LocationClientOption;

/**
 * Created by Administrator on 2016/12/5.
 */

public class MapUtils {


    public static LocationClientOption getLocationOption() {
        LocationClientOption option = new LocationClientOption();
        //设置定位模式/高精度/低功耗/仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        //设置发起定位请求的间隔需要大于等于1000ms才是有效的
        //默认为0，即仅定位一次
        option.setScanSpan(1000);
        //设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //默认false,设置是否使用gps
        option.setOpenGps(true);
        //设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setLocationNotify(false);
        //默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIsNeedLocationPoiList(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setEnableSimulateGps(false);
        return option;
    }
}

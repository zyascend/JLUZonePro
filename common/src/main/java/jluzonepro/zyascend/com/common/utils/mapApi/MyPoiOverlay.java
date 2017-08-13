package jluzonepro.zyascend.com.common.utils.mapApi;


import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.search.core.PoiInfo;

/**
 * Created by Administrator on 2016/12/3.
 */

public class MyPoiOverlay extends PoiOverlay {
    private final Context mContext;
    private final BaiduMap mMap;

    /**
     * 构造函数
     *
     * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
     */
    public MyPoiOverlay(BaiduMap baiduMap, Context context) {
        super(baiduMap);
        this.mContext = context;
        this.mMap = baiduMap;
    }


    @Override
    public boolean onPoiClick(final int i) {
        super.onPoiClick(i);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                onClick(i);
            }
        });
        return true;
    }

    private void onClick(int i) {
        mMap.hideInfoWindow();
        PoiInfo info = getPoiResult().getAllPoi().get(i);
//        Toast.makeText(mContext, "点击了"+info.name, Toast.LENGTH_SHORT).show();
        TextView textView = new TextView(mContext);
        textView.setBackgroundColor(Color.BLACK);
        textView.setTextColor(Color.WHITE);
        textView.setText(info.name);
        InfoWindow window = new InfoWindow(textView,info.location,-47);
        mMap.showInfoWindow(window);
    }






}

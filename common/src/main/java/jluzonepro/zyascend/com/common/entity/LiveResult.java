package jluzonepro.zyascend.com.common.entity;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/3/22.
 */

public class LiveResult {

    /**
     * resultStatus : {"message":"ok","code":"1"}
     * resultValue : [{"link":"http://iptv.jlu.edu.cn/hls/cctv1hd.m3u8","nme":"CCTV-1高清"},{"link":"http://iptv.jlu.edu.cn/hls/cctv3hd.m3u8","nme":"CCTV-3高清"},{"link":"http://iptv.jlu.edu.cn/hls/cctv5hd.m3u8","nme":"CCTV-5高清"},{"link":"http://iptv.jlu.edu.cn/hls/cctv5phd.m3u8","nme":"CCTV-5+高清"},{"link":"http://iptv.jlu.edu.cn/hls/cctv6hd.m3u8","nme":"CCTV-6高清"},{"link":"http://iptv.jlu.edu.cn/hls/cctv8hd.m3u8","nme":"CCTV-8高清"},{"link":"http://iptv.jlu.edu.cn/hls/chchd.m3u8","nme":"CHC高清电影"},{"link":"http://iptv.jlu.edu.cn/hls/btv1hd.m3u8","nme":"北京卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/btv2hd.m3u8","nme":"北京文艺高清"},{"link":"http://iptv.jlu.edu.cn/hls/btv6hd.m3u8","nme":"北京体育高清"},{"link":"http://iptv.jlu.edu.cn/hls/btv11hd.m3u8","nme":"北京纪实高清"},{"link":"http://iptv.jlu.edu.cn/hls/hunanhd.m3u8","nme":"湖南卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/zjhd.m3u8","nme":"浙江卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/jshd.m3u8","nme":"江苏卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/dfhd.m3u8","nme":"东方卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/ahhd.m3u8","nme":"安徽卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/hljhd.m3u8","nme":"黑龙江卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/lnhd.m3u8","nme":"辽宁卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/szhd.m3u8","nme":"深圳卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/gdhd.m3u8","nme":"广东卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/tjhd.m3u8","nme":"天津卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/hbhd.m3u8","nme":"湖北卫视高清"},{"link":"http://iptv.jlu.edu.cn/hls/sdhd.m3u8","nme":"山东卫视高清"}]
     */

    private ResultStatusBean resultStatus;
    private List<LiveChannel> resultValue;

    public ResultStatusBean getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatusBean resultStatus) {
        this.resultStatus = resultStatus;
    }

    public List<LiveChannel> getResultValue() {
        return resultValue;
    }

    public void setResultValue(List<LiveChannel> resultValue) {
        this.resultValue = resultValue;
    }

    public static class ResultStatusBean {
        /**
         * message : ok
         * code : 1
         */

        private String message;
        private String code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}

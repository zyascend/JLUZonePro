package jluzonepro.zyascend.com.common.entity;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/10/24.
 */

public class Weather {


    /**
     * desc : OK
     * status : 1000
     * data : {"wendu":"-4","ganmao":"昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。","forecast":[{"fengxiang":"西北风","fengli":"3-4级","high":"高温 3℃","type":"阵雪","low":"低温 -8℃","date":"30日星期天"},{"fengxiang":"西风","fengli":"微风级","high":"高温 -2℃","type":"晴","low":"低温 -9℃","date":"31日星期一"},{"fengxiang":"西南风","fengli":"微风级","high":"高温 1℃","type":"晴","low":"低温 -5℃","date":"1日星期二"},{"fengxiang":"西南风","fengli":"微风级","high":"高温 5℃","type":"晴","low":"低温 -4℃","date":"2日星期三"},{"fengxiang":"西南风","fengli":"微风级","high":"高温 5℃","type":"晴","low":"低温 -1℃","date":"3日星期四"}],"yesterday":{"fl":"微风","fx":"西风","high":"高温 1℃","type":"晴","low":"低温 -5℃","date":"29日星期六"},"aqi":"87","city":"长春"}
     */

    private String desc;
    private int status;
    /**
     * wendu : -4
     * ganmao : 昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。
     * forecast : [{"fengxiang":"西北风","fengli":"3-4级","high":"高温 3℃","type":"阵雪","low":"低温 -8℃","date":"30日星期天"},{"fengxiang":"西风","fengli":"微风级","high":"高温 -2℃","type":"晴","low":"低温 -9℃","date":"31日星期一"},{"fengxiang":"西南风","fengli":"微风级","high":"高温 1℃","type":"晴","low":"低温 -5℃","date":"1日星期二"},{"fengxiang":"西南风","fengli":"微风级","high":"高温 5℃","type":"晴","low":"低温 -4℃","date":"2日星期三"},{"fengxiang":"西南风","fengli":"微风级","high":"高温 5℃","type":"晴","low":"低温 -1℃","date":"3日星期四"}]
     * yesterday : {"fl":"微风","fx":"西风","high":"高温 1℃","type":"晴","low":"低温 -5℃","date":"29日星期六"}
     * aqi : 87
     * city : 长春
     */

    private DataBean data;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String wendu;
        private String ganmao;
        /**
         * fl : 微风
         * fx : 西风
         * high : 高温 1℃
         * type : 晴
         * low : 低温 -5℃
         * date : 29日星期六
         */

        private YesterdayBean yesterday;
        private String aqi;
        private String city;
        /**
         * fengxiang : 西北风
         * fengli : 3-4级
         * high : 高温 3℃
         * type : 阵雪
         * low : 低温 -8℃
         * date : 30日星期天
         */

        private List<ForecastBean> forecast;

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            private String fl;
            private String fx;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class ForecastBean {
            private String fengxiang;
            private String fengli;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}

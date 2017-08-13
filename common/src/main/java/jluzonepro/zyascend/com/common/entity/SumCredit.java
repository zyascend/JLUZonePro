package jluzonepro.zyascend.com.common.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/29.
 */
public class SumCredit {

    /**
     * status : 0
     * value : [{"courType2":null,"type5":4160,"sumCredit":110.5},{"courType2":null,"type5":4161,"sumCredit":1.5},{"courType2":3029,"type5":4163,"sumCredit":5},{"courType2":3030,"type5":4163,"sumCredit":4},{"courType2":3033,"type5":4163,"sumCredit":2},{"courType2":null,"type5":4164,"sumCredit":4}]
     * resName : stat-credit-stud
     * msg :
     */

    private int status;
    private String resName;
    private String msg;
    /**
     * courType2 : null
     * type5 : 4160
     * sumCredit : 110.5
     */

    private List<ValueBean> value;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ValueBean> getValue() {
        return value;
    }

    public void setValue(List<ValueBean> value) {
        this.value = value;
    }

    public static class ValueBean {
        private Object courType2;
        private int type5;
        private double sumCredit;

        public Object getCourType2() {
            return courType2;
        }

        public void setCourType2(Object courType2) {
            this.courType2 = courType2;
        }

        public int getType5() {
            return type5;
        }

        public void setType5(int type5) {
            this.type5 = type5;
        }

        public double getSumCredit() {
            return sumCredit;
        }

        public void setSumCredit(double sumCredit) {
            this.sumCredit = sumCredit;
        }
    }
}

package jluzonepro.zyascend.com.common.entity;

/**
 * Created by Administrator on 2016/7/5.
 */
public class RequestParams {

    /**
     * studId : 238615
     * termId : 130
     */

    private int termId;
    private int studId;
    private String xh;
    private int year;

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getStudId() {
        return studId;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}

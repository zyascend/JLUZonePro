package jluzonepro.zyascend.com.common.entity;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/10/29.
 */
public class AvgScore {

    /**
     * status : 0
     * value : [{"avgScoreBest":79.2992125984252,"avgScoreFirst":78.74803149606299,"gpaFirst":2.6476377952755907,"gpaBest":2.688976377952756}]
     * resName : stat-avg-gpoint
     * msg :
     */

    private int status;
    private String resName;
    private String msg;
    /**
     * avgScoreBest : 79.2992125984252
     * avgScoreFirst : 78.74803149606299
     * gpaFirst : 2.6476377952755907
     * gpaBest : 2.688976377952756
     */

    private List<ScoreStatics> value;

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

    public List<ScoreStatics> getValue() {
        return value;
    }

    public void setValue(List<ScoreStatics> value) {
        this.value = value;
    }


}

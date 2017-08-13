package jluzonepro.zyascend.com.common.entity;

/**
 * Created by Administrator on 2016/12/5.
 */

public class EvaluateItem {
    private String className;
    private String teacher;
    private String itemId;
    private String startTime;
    private String endTime;

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public void setEvaluated(boolean evaluated) {
        isEvaluated = evaluated;
    }

    private boolean isEvaluated;

    public EvaluateItem(String className, String teacher, String itemId, String startTime
            , String endTime, boolean isEvaluated) {
        this.className = className;
        this.teacher = teacher;
        this.itemId = itemId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isEvaluated = isEvaluated;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

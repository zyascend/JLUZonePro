package jluzonepro.zyascend.com.common.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 *
 * Created by Administrator on 2016/10/15.
 */

@Entity
public class Course {

    @Id
    private long id;
    private String name;
    private String place;
    private int beginWeek;
    private int endWeek;
    private int dayOfWeek;
    private String time;
    private String teacher;
    private int termId;
    public String getTeacher() {
        return this.teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getDayOfWeek() {
        return this.dayOfWeek;
    }
    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public int getEndWeek() {
        return this.endWeek;
    }
    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }
    public int getBeginWeek() {
        return this.beginWeek;
    }
    public void setBeginWeek(int beginWeek) {
        this.beginWeek = beginWeek;
    }
    public String getPlace() {
        return this.place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getTermId() {
        return this.termId;
    }
    public void setTermId(int termId) {
        this.termId = termId;
    }
    @Generated(hash = 1875210426)
    public Course(long id, String name, String place, int beginWeek, int endWeek,
            int dayOfWeek, String time, String teacher, int termId) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.beginWeek = beginWeek;
        this.endWeek = endWeek;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.teacher = teacher;
        this.termId = termId;
    }
    @Generated(hash = 1355838961)
    public Course() {

    }
    

}

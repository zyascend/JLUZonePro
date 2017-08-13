package jluzonepro.zyascend.com.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2016/7/5.
 */
@Entity
public class Score implements Parcelable {
    @Id
    private long courseId;

    private String name;
    private String score;
    private String gpoint;
    private String credit;
    private String isPass;
    
    private int asId;
    private String stuName;
    private int stuId;
    private int termId;
    private String termName;




    @Generated(hash = 1879919449)
    public Score(long courseId, String name, String score, String gpoint,
            String credit, String isPass, int asId, String stuName, int stuId,
            int termId, String termName) {
        this.courseId = courseId;
        this.name = name;
        this.score = score;
        this.gpoint = gpoint;
        this.credit = credit;
        this.isPass = isPass;
        this.asId = asId;
        this.stuName = stuName;
        this.stuId = stuId;
        this.termId = termId;
        this.termName = termName;
    }

    @Generated(hash = 226049941)
    public Score() {
    }


    protected Score(Parcel in) {
        courseId = in.readLong();
        name = in.readString();
        score = in.readString();
        gpoint = in.readString();
        credit = in.readString();
        isPass = in.readString();
        asId = in.readInt();
        stuName = in.readString();
        stuId = in.readInt();
        termId = in.readInt();
        termName = in.readString();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGpoint() {
        return gpoint;
    }

    public void setGpoint(String gpoint) {
        this.gpoint = gpoint;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getTermName() {
        return this.termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public int getTermId() {
        return this.termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getStuId() {
        return this.stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getAsId() {
        return this.asId;
    }

    public void setAsId(int asId) {
        this.asId = asId;
    }

    public long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(courseId);
        dest.writeString(name);
        dest.writeString(score);
        dest.writeString(gpoint);
        dest.writeString(credit);
        dest.writeString(isPass);
        dest.writeInt(asId);
        dest.writeString(stuName);
        dest.writeInt(stuId);
        dest.writeInt(termId);
        dest.writeString(termName);
    }
}

package jluzonepro.zyascend.com.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2016/10/20.
 */
@Entity
public class Job implements Parcelable{

    @Id
    private long id;
    private String title;
    private String date;
    private String address;
    private int hits;
    private String type;

    protected Job(Parcel in) {
        id = in.readLong();
        title = in.readString();
        date = in.readString();
        address = in.readString();
        hits = in.readInt();
        type = in.readString();
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    public int getHits() {
        return this.hits;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Generated(hash = 4186408)
    public Job(long id, String title, String date, String address, int hits,
            String type) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.address = address;
        this.hits = hits;
        this.type = type;
    }
    @Generated(hash = 1361810761)
    public Job() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(address);
        dest.writeInt(hits);
        dest.writeString(type);
    }
}

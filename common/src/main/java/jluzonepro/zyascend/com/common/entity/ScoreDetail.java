package jluzonepro.zyascend.com.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/10/29.
 */
public class ScoreDetail implements Parcelable{

    public ScoreDetail() {
    }

    /**
     * count : 5
     * errno : 0
     * identifier : seq
     * items : [{"count":0,"label":"优秀(>90)","percent":53.125,"seq":0},{"count":0,"label":"良好(80-90)","percent":46.875,"seq":1},{"count":0,"label":"中等(70-80)","percent":0,"seq":2},{"count":0,"label":"及格(60-70)","percent":0,"seq":3},{"count":0,"label":"不及格(<60)","percent":0,"seq":4}]
     * label : label
     * msg :
     * status : 0
     */

    private int count;
    private int errno;
    private String identifier;
    private String label;
    private String msg;
    private int status;
    /**
     * count : 0
     * label : 优秀(>90)
     * percent : 53.125
     * seq : 0
     */

    private List<ItemsBean> items;

    protected ScoreDetail(Parcel in) {
        count = in.readInt();
        errno = in.readInt();
        identifier = in.readString();
        label = in.readString();
        msg = in.readString();
        status = in.readInt();
    }

    public static final Creator<ScoreDetail> CREATOR = new Creator<ScoreDetail>() {
        @Override
        public ScoreDetail createFromParcel(Parcel in) {
            return new ScoreDetail(in);
        }

        @Override
        public ScoreDetail[] newArray(int size) {
            return new ScoreDetail[size];
        }
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeInt(errno);
        dest.writeString(identifier);
        dest.writeString(label);
        dest.writeString(msg);
        dest.writeInt(status);
    }

    public static class ItemsBean implements Parcelable{
        private int count;
        private String label;
        private double percent;
        private int seq;

        public ItemsBean() {

        }

        protected ItemsBean(Parcel in) {
            count = in.readInt();
            label = in.readString();
            percent = in.readDouble();
            seq = in.readInt();
        }

        public static final Creator<ItemsBean> CREATOR = new Creator<ItemsBean>() {
            @Override
            public ItemsBean createFromParcel(Parcel in) {
                return new ItemsBean(in);
            }

            @Override
            public ItemsBean[] newArray(int size) {
                return new ItemsBean[size];
            }
        };

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public double getPercent() {
            return percent;
        }

        public void setPercent(double percent) {
            this.percent = percent;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(count);
            dest.writeString(label);
            dest.writeDouble(percent);
            dest.writeInt(seq);
        }
    }
}

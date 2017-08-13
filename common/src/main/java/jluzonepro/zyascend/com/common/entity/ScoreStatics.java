package jluzonepro.zyascend.com.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by Administrator on 2017/3/30.
 */

public class ScoreStatics implements Parcelable{
    private double avgScoreBest;
    private double avgScoreFirst;
    private double gpaFirst;
    private double gpaBest;

    public ScoreStatics() {
        super();
    }

    public ScoreStatics(double avgScoreBest, double avgScoreFirst, double gpaFirst, double gpaBest) {
        this.avgScoreBest = avgScoreBest;
        this.avgScoreFirst = avgScoreFirst;
        this.gpaFirst = gpaFirst;
        this.gpaBest = gpaBest;
    }

    protected ScoreStatics(Parcel in) {
        avgScoreBest = in.readDouble();
        avgScoreFirst = in.readDouble();
        gpaFirst = in.readDouble();
        gpaBest = in.readDouble();
    }

    public static final Creator<ScoreStatics> CREATOR = new Creator<ScoreStatics>() {
        @Override
        public ScoreStatics createFromParcel(Parcel in) {
            return new ScoreStatics(in);
        }

        @Override
        public ScoreStatics[] newArray(int size) {
            return new ScoreStatics[size];
        }
    };

    public double getAvgScoreBest() {
        return avgScoreBest;
    }

    public void setAvgScoreBest(double avgScoreBest) {
        this.avgScoreBest = avgScoreBest;
    }

    public double getAvgScoreFirst() {
        return avgScoreFirst;
    }

    public void setAvgScoreFirst(double avgScoreFirst) {
        this.avgScoreFirst = avgScoreFirst;
    }

    public double getGpaFirst() {
        return gpaFirst;
    }

    public void setGpaFirst(double gpaFirst) {
        this.gpaFirst = gpaFirst;
    }

    public double getGpaBest() {
        return gpaBest;
    }

    public void setGpaBest(double gpaBest) {
        this.gpaBest = gpaBest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(avgScoreBest);
        dest.writeDouble(avgScoreFirst);
        dest.writeDouble(gpaFirst);
        dest.writeDouble(gpaBest);
    }
}
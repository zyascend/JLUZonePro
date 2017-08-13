package jluzonepro.zyascend.com.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 *
 * Created by Administrator on 2016/8/4.
 */
@Entity
public class News implements Parcelable {

    @Id
    private long id;
    private String title;
    private String editor;
    private String date;
    private String content;
    private String url;


    protected News(Parcel in) {
        id = in.readLong();
        title = in.readString();
        editor = in.readString();
        date = in.readString();
        content = in.readString();
        url = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getEditor() {
        return this.editor;
    }
    public void setEditor(String editor) {
        this.editor = editor;
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
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Generated(hash = 1554106411)
    public News(long id, String title, String editor, String date, String content,
            String url) {
        this.id = id;
        this.title = title;
        this.editor = editor;
        this.date = date;
        this.content = content;
        this.url = url;
    }
    @Generated(hash = 1579685679)
    public News() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(editor);
        dest.writeString(date);
        dest.writeString(content);
        dest.writeString(url);
    }
}

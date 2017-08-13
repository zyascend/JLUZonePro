package jluzonepro.zyascend.com.common.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2016/10/24.
 */

@Entity
public class Todo {
    @Id(autoincrement = true)
    private Long id;
    private boolean isChecked;
    private String title;
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean getIsChecked() {
        return this.isChecked;
    }
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1673795413)
    public Todo(Long id, boolean isChecked, String title) {
        this.id = id;
        this.isChecked = isChecked;
        this.title = title;
    }
    @Generated(hash = 1698043777)
    public Todo() {
    }
}

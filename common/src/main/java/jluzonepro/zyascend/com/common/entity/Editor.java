package jluzonepro.zyascend.com.common.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 *
 * Created by Administrator on 2016/10/23.
 */
@Entity
public class Editor {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private boolean isShow;
    public boolean getIsShow() {
        return this.isShow;
    }
    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 286709024)
    public Editor(Long id, String name, boolean isShow) {
        this.id = id;
        this.name = name;
        this.isShow = isShow;
    }
    @Generated(hash = 33843963)
    public Editor() {
    }

    public Editor(String name,boolean isShow){

    }
}

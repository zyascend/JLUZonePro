package jluzonepro.zyascend.com.common.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
@Entity
public class StuInfo {
    @Id
    private long key;
    
    public int stuId;
    public int currentTerm;
    public String account;
    public String name;
    public String passWord;
    public boolean isAutoLogin;
    public boolean isRememberPass;
    public boolean isLoginOutside;
    public boolean getIsLoginOutside() {
        return this.isLoginOutside;
    }
    public void setIsLoginOutside(boolean isLoginOutside) {
        this.isLoginOutside = isLoginOutside;
    }
    public boolean getIsRememberPass() {
        return this.isRememberPass;
    }
    public void setIsRememberPass(boolean isRememberPass) {
        this.isRememberPass = isRememberPass;
    }
    public boolean getIsAutoLogin() {
        return this.isAutoLogin;
    }
    public void setIsAutoLogin(boolean isAutoLogin) {
        this.isAutoLogin = isAutoLogin;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccount() {
        return this.account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public int getStuId() {
        return this.stuId;
    }
    public void setStuId(int stuId) {
        this.stuId = stuId;
    }
    public long getKey() {
        return this.key;
    }
    public void setKey(long key) {
        this.key = key;
    }
    public int getCurrentTerm() {
        return this.currentTerm;
    }
    public void setCurrentTerm(int currentTerm) {
        this.currentTerm = currentTerm;
    }
    @Generated(hash = 1645141150)
    public StuInfo(long key, int stuId, int currentTerm, String account,
            String name, String passWord, boolean isAutoLogin,
            boolean isRememberPass, boolean isLoginOutside) {
        this.key = key;
        this.stuId = stuId;
        this.currentTerm = currentTerm;
        this.account = account;
        this.name = name;
        this.passWord = passWord;
        this.isAutoLogin = isAutoLogin;
        this.isRememberPass = isRememberPass;
        this.isLoginOutside = isLoginOutside;
    }
    @Generated(hash = 724382685)
    public StuInfo() {
    }
}

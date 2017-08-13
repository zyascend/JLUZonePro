package jluzonepro.zyascend.com.common.base;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public abstract class BasePresenter<T> {
    protected T mViewListener;
    public String TAG_PRESENTER = this.getClass().getSimpleName();
    public void attachView(T view){
        mViewListener = view;

    }

    protected T getView(){
        return mViewListener;
    }

    public boolean isViewAttached(){
        return mViewListener != null;
    }

    public void detachView(){
        if (mViewListener != null){
            mViewListener = null;
        }
    }

}

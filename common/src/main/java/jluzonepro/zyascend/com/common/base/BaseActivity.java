package jluzonepro.zyascend.com.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import jluzonepro.zyascend.com.common.R;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public abstract class BaseActivity <V,T extends BasePresenter<V>> extends AppCompatActivity {

    private static final String TAG = "TAG_BaseActivity";
    protected Activity mContext;
    protected Toolbar mToolbar;
    protected T mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mContext = this;
        mPresenter = getPresenter();
        mPresenter.attachView((V) this);
        initToolBar();
        initView();
        doOnCreate();

    }

    protected abstract void doOnCreate();

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {

            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            Log.d(TAG, "setToolBar: 设置返回键");

        }else{
            Log.d(TAG, "setToolBar: null");
        }
    }

    public void setToolbarTitle(String toolbarTitle) {
        ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(toolbarTitle);
        }else {
            Log.d(TAG, "setToolbarTitle: bar_null");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter = null;
        unbinder.unbind();
//
//        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);
//        refWatcher.watch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected abstract T getPresenter();

    protected abstract void loadFragment();
}

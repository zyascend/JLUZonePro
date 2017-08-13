package jluzonepro.zyascend.com.common.share;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jluzonepro.zyascend.com.common.R;
import jluzonepro.zyascend.com.common.R2;
import jluzonepro.zyascend.com.common.base.BaseActivity;
import jluzonepro.zyascend.com.common.router.RouterUtils;

/**
 *
 * Created by Administrator on 2016/10/22.
 */
@Route(path = RouterUtils.SHARE_CONTENT)
public class ShareContentActivity extends BaseActivity<ShareContract.View, SharePresenter>
        implements ShareContract.View {


    public static final String KEY_SHARE_TYPE = "key_share_type";
    public static final String SHARE_TYPE_JOB = "type_job";
    public static final String SHARE_TYPE_NEWS = "type_job";
    public static final String KEY_SHARE_TITLE = "key_share_title";
    public static final String KEY_SHARE_CONTENT = "key_share_content";

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.tags)
    ImageView tags;
    @BindView(R2.id.tv_type)
    TextView tvType;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.scrollView)
    ScrollView scrollView;
    @BindView(R2.id.btn_save)
    Button btnSave;
    @BindView(R2.id.btn_share)
    Button btnShare;

    @Override
    protected void doOnCreate() {

    }

    @Override
    protected void initView() {
        String type = getIntent().getStringExtra(KEY_SHARE_TYPE);
        tvType.setText(type);
        if (TextUtils.equals(type, "校园招聘") || TextUtils.equals(type, "实习招聘")) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(getIntent().getStringExtra(KEY_SHARE_TITLE));
        } else {
            tvTitle.setVisibility(View.GONE);
        }

        RichText.fromHtml(getIntent().getStringExtra(KEY_SHARE_CONTENT)).into(tvContent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected SharePresenter getPresenter() {
        return new SharePresenter(this);
    }


    @OnClick({R2.id.btn_save, R2.id.btn_share})
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_save){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            } else {
                mPresenter.saveContent(scrollView);
                showLoading();
            }
        }else if(id == R.id.btn_share){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            } else {
                mPresenter.shareContent(scrollView);
                showLoading();
            }
        }
    }


    private void showLoading() {

    }

    @Override
    protected void loadFragment() {

    }

    @Override
    public void onSuccess() {
    }

    @Override
    public void onFailed() {

    }


}

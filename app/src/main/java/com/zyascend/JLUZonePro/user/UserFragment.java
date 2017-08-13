package com.zyascend.JLUZonePro.user;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zyascend.JLUZonePro.R;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import jluzonepro.zyascend.com.common.base.BaseFragment;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;

import static android.app.Activity.RESULT_OK;

/**
 *
 * Created by Administrator on 2016/11/2.
 */

public class UserFragment extends BaseFragment<UserContract.View, UserPresenter> implements UserContract.View  {

    private static final int CODE_IMAGE = 1;
    private static final String EMAIL_ADDRESS = "www.1334553391@qq.com";
    private static final String WEIBO_ADDRESS = "weibo.com/zyascend";
    private static final String TAG = "TAG_UserFragment";
    private static final int CODE_CROP = 2;

    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.iv_user)
    CircleImageView ivUserIamge;
    @BindView(R.id.tv_user)
    TextView tvUserName;
    @BindView(R.id.tv_cache_size)
    TextView tvCacheSize;
    @BindView(R.id.layout_clear_cache)
    RelativeLayout layoutClearCache;
    @BindView(R.id.layout_advice)
    RelativeLayout layoutAdvice;
    @BindView(R.id.rl_weibo)
    RelativeLayout rlWeibo;
    @BindView(R.id.btn_logOut)
    AppCompatButton btnLogOut;
    private Context mContext;


    @Override
    protected void initViews() {

        mContext = getActivity();
        mPresenter.loadUserAvator();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected UserPresenter getPresenter() {
        return new UserPresenter(getActivity());
    }


    @OnClick({R.id.iv_user, R.id.tv_user, R.id.layout_clear_cache, R.id.layout_advice, R.id.rl_weibo, R.id.btn_logOut})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user:
                chooseImage();
                break;
            case R.id.tv_user:
                break;
            case R.id.layout_clear_cache:
                mPresenter.clearCache();
                break;
            case R.id.layout_advice:
                sendEmail();
                break;
            case R.id.rl_weibo:
                ActivityUtils.openUrl(getActivity(),WEIBO_ADDRESS);
                break;
            case R.id.btn_logOut:
                mPresenter.logout();
                getActivity().finish();
                break;
        }
    }
    private void sendEmail() {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        if (ActivityUtils.isIntentSafe(email,mContext.getApplicationContext())) {
            email.setData(Uri.parse("mailto:" + EMAIL_ADDRESS));
            email.putExtra(Intent.EXTRA_SUBJECT, "NoBoring用户信息反馈");
            email.putExtra(Intent.EXTRA_TEXT, "你好，");
            startActivity(email);
        } else {
            ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Activity.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(ClipData.newPlainText(null, EMAIL_ADDRESS));
//            ActivityUtils.showSnackBar(findViewById(R.id.coordinator), "地址已复制");
            Toast.makeText(mContext, "地址已复制", Toast.LENGTH_SHORT).show();

        }
    }
    private void chooseImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, CODE_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case CODE_IMAGE:
                    resizeImage(data.getData());
                    break;
                case CODE_CROP:
                    if (data != null) {
                        showResizeImage(data);
                    }
                    break;
            }
        }
    }

    public void resizeImage(Uri uri) {
        Log.d(TAG, "resizeImage: "+uri);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_CROP);
    }

    private void showResizeImage(Intent data) {

        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            ivUserIamge.setImageBitmap(photo);

            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    mPresenter.saveImage(photo);
                }
            } else {
                mPresenter.saveImage(photo);
            }
        }
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        if (bitmap != null){
            ivUserIamge.setImageBitmap(bitmap);
        }else {
            ivUserIamge.setImageResource(R.drawable.ic_jlu_100px);
        }
        mPresenter.loadUserName();
    }

    @Override
    public void omNameLoaded(String name) {
        tvUserName.setText(name==null ? "未登录" : name);
        mPresenter.loadCacheSize();
    }

    @Override
    public void onCacheCleared(String cacheSize) {
        tvCacheSize.setText(cacheSize);
    }
}

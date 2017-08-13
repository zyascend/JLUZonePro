package jluzonepro.zyascend.com.common.share;

import android.content.Context;
import android.widget.ScrollView;

import jluzonepro.zyascend.com.common.base.BasePresenter;
import jluzonepro.zyascend.com.common.utils.ShareUtils;


/**
 *
 * Created by Administrator on 2016/10/22.
 */

public class SharePresenter extends BasePresenter<ShareContract.View> implements ShareContract.Presenter
        , ShareUtils.ShareCallback {

    private final Context mContext;
    private final ShareUtils mShareUtils;

    public SharePresenter(Context context) {
        mContext = context;
        mShareUtils = ShareUtils.getInstance(this);
    }

    @Override
    public void saveContent(ScrollView scrollView) {
        mShareUtils.saveContent(scrollView);
    }

    @Override
    public void shareContent(ScrollView scrollView) {
        mShareUtils.shareContent(mContext,scrollView);
    }

    @Override
    public void onSuccess() {
        mViewListener.onSuccess();
    }

    @Override
    public void onFail() {
        mViewListener.onFailed();
    }
}

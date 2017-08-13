package jluzonepro.zyascend.com.news.news;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import jluzonepro.zyascend.com.common.base.BaseActivity;
import jluzonepro.zyascend.com.common.entity.ConstValue;
import jluzonepro.zyascend.com.common.entity.News;
import jluzonepro.zyascend.com.common.share.ShareContentActivity;
import jluzonepro.zyascend.com.news.R;
import jluzonepro.zyascend.com.news.R2;

import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnURLClickListener;

import java.util.List;


/**
 *
 * Created by Administrator on 2016/10/21.
 */

public class NewsContentActivity extends BaseActivity<NewsContract.View, NewsPresenter>
        implements NewsContract.View, OnURLClickListener {

    public static final String PARCELABLE_NEWS = "pa_news";
    public static final String INTENT_NEWS_TAG = "INTENT_NEWS";
    private static final String TAG = "TAG_NewsContentActivity";
    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.scrollView)
    ScrollView scrollView;

    private String mTag;
    private String mUrl;
    private String mContent;

    @Override
    protected void doOnCreate() {
        News mNews = getIntent().getParcelableExtra(PARCELABLE_NEWS);
        mTag = getIntent().getStringExtra(INTENT_NEWS_TAG);
        if (mNews != null) {
            mUrl = mNews.getUrl();
            Log.d(TAG, "doOnCreate: "+ mUrl);
            if (TextUtils.equals(mTag, NewsPresenter.TAG_XIAO) && mUrl != null) {
                mPresenter.getXiaoContent(mUrl);
            } else if (TextUtils.equals(mTag, NewsPresenter.TAG_JWC) && mUrl != null) {
                mPresenter.getJwcContent(mUrl);
            }
        }
    }

    @Override
    protected void initView() {
        setToolbarTitle("详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_content;
    }

    @Override
    protected NewsPresenter getPresenter() {
        return new NewsPresenter(this);
    }

    @Override
    public void showFailure() {

    }

    @Override
    public void loadContent(String content) {
        mContent = content;
        RichText.fromHtml(content).noImage(true).urlClick(this).into(tvContent);

    }

    @Override
    protected void loadFragment() {

    }

    @Override
    public void loadNews(List<News> newsList) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.content,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share){
            sharePic();
        }else if (id == R.id.action_link){
            openLinks(mUrl);
        }else {
            onBackPressed();
        }
        return true;
    }

    private void openLinks(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);


    }

    private void sharePic() {
        Intent intent = new Intent(NewsContentActivity.this, ShareContentActivity.class);
        intent.putExtra(ShareContentActivity.KEY_SHARE_CONTENT,mContent);
        intent.putExtra(ShareContentActivity.KEY_SHARE_TYPE,"校内通知");
        startActivity(intent);
    }

    @Override
    public boolean urlClicked(String url) {
        if (TextUtils.equals(mTag,NewsPresenter.TAG_JWC)){
            if (url.contains("http")){
                openLinks(url);
            }else {
                openLinks(ConstValue.URL_HOST_JWC+url);
            }
        }else {
            try{
                String u = url.split("/")[2];
                openLinks(ConstValue.URL_HOST_XIAO+u);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }
}

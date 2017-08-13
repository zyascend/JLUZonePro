package jluzonepro.zyascend.com.news.job;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnImageClickListener;

import java.util.List;

import butterknife.BindView;
import jluzonepro.zyascend.com.common.base.BaseActivity;
import jluzonepro.zyascend.com.common.entity.Job;
import jluzonepro.zyascend.com.common.entity.JobContent;
import jluzonepro.zyascend.com.news.R;
import jluzonepro.zyascend.com.news.R2;


/**
 *
 * Created by Administrator on 2016/10/20.
 */

public class JobContentActivity extends BaseActivity<JobContract.View, JobPresenter>
implements JobContract.View{

    public static final String PARCELABLE_JOBS = "parcelable_job";
    private static final String TAG = "TAG_JObContent";
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.tv_place)
    TextView tvPlace;
    @BindView(R2.id.tv_date)
    TextView tvDate;
    private Job mJobs;
    private String html;

    @Override
    protected void doOnCreate() {
        mJobs = getIntent().getParcelableExtra(PARCELABLE_JOBS);
        if (mJobs != null){
            tvDate.setText(mJobs.getDate());
            tvPlace.setText(mJobs.getAddress());
            tvTitle.setText(mJobs.getTitle());
            mPresenter.getJobContent((int) mJobs.getId());
        }

    }

    @Override
    protected void initView() {
        setToolbarTitle("招聘详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_job_content;
    }

    @Override
    protected JobPresenter getPresenter() {
        return new JobPresenter(this);
    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void onLoadedContent(JobContent content) {
        if (content != null){
            String body = content.getData().getBody();
            html = generateHtml(body);
            RichText.fromHtml(html).imageClick(new OnImageClickListener() {
                @Override
                public void imageClicked(List<String> imageUrls, int position) {
                    Log.d(TAG, "imageClicked: "+position);
                    // TODO: 2016/10/21 进入PhotoActivity
                }
            }).into(tvContent);

        }
    }

    private String generateHtml(String string2) {
        return "<html>" +
                "<head>" +
                "</head>" +
                "<body>" +
                string2 +
                "</body>" +
                "</html>";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_only,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share){
//            Intent intent = new Intent(JobContentActivity.this, ShareContentActivity.class);
//            intent.putExtra(ShareContentActivity.KEY_SHARE_TYPE,getTypes(mJobs.getType()));
//            intent.putExtra(ShareContentActivity.KEY_SHARE_TITLE,mJobs.getTitle());
//            intent.putExtra(ShareContentActivity.KEY_SHARE_CONTENT,html);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private String getTypes(String type) {
        switch (type){
            case "1":
                return "校园招聘";
            case "3":
                return "实习招聘";
        }
        return "校园招聘";
    }

    @Override
    public void onLoadedList(List<Job> jobs) {

    }
    @Override
    protected void loadFragment() {

    }
}

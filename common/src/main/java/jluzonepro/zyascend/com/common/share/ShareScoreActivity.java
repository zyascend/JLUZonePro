package jluzonepro.zyascend.com.common.share;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jluzonepro.zyascend.com.common.R;
import jluzonepro.zyascend.com.common.R2;
import jluzonepro.zyascend.com.common.base.BaseActivity;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.ScoreDetail;
import jluzonepro.zyascend.com.common.router.RouterUtils;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import jluzonepro.zyascend.com.common.utils.PieChart;


/**
 *
 * Created by Administrator on 2016/11/4.
 */
@Route(path = RouterUtils.SHARE_SCORE)
public class ShareScoreActivity extends BaseActivity<ShareContract.View, SharePresenter>
        implements ShareContract.View {

    public static final String EXTRA_SCORE = "SCORE";
    public static final String EXTRA_DETAIL = "DEATIL";

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.tags)
    ImageView tags;
    @BindView(R2.id.tv_type)
    TextView tvType;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_score)
    TextView tvScore;
    @BindView(R2.id.tv_gpoint)
    TextView tvGpoint;
    @BindView(R2.id.fenbu)
    TextView fenbu;
    @BindView(R2.id.pie)
    PieChart pie;
    @BindView(R2.id.createrBy)
    TextView createrBy;
    @BindView(R2.id.tv_stuName)
    TextView tvStuName;
    @BindView(R2.id.score)
    TextView score;
    @BindView(R2.id.gpoint)
    TextView gpoint;
    @BindView(R2.id.scrollView)
    ScrollView scrollView;

    @Override
    protected void doOnCreate() {

    }

    @Override
    protected void initView() {
        setToolbarTitle("分享成绩");
        tvType.setText("单科成绩");
        Score mScore = getIntent().getParcelableExtra(EXTRA_SCORE);
        if (mScore != null) {
            tvName.setText(mScore.getName());
            tvGpoint.setText(mScore.getGpoint());
            tvScore.setText(mScore.getScore());
            tvStuName.setText(mScore.getStuName());
        }

        ArrayList<ScoreDetail.ItemsBean> mItems = getIntent().getParcelableArrayListExtra(EXTRA_DETAIL);
        if (mItems != null && !mItems.isEmpty()) {
            double[] data = new double[5];
            String[] titles = new String[5];

            for (int i = 0; i < mItems.size(); i++) {
                data[i] = mItems.get(i).getPercent();
                String s = getTitles(mItems.get(i).getLabel());
                titles[i] = s + "  " + (int) data[i] + "%";
            }

            pie.setDatas(data);
            pie.setTitles(titles);

            if (ActivityUtils.isNumeric(mScore.getScore())) {
                pie.setMainCount(Integer.parseInt(mScore.getScore()));
            }
        }

    }

    private String getTitles(String s) {
        while (true) {
            int i1 = s.indexOf("(");
            if (i1 == -1)
                break;
            int i2 = s.indexOf(")");
            if (i2 == -1)
                break;
            s = s.replace(s.substring(i1, i2 + 1), "");
        }
        return s;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_score_share;
    }

    @Override
    protected SharePresenter getPresenter() {
        return new SharePresenter(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.score_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_share) {
            mPresenter.shareContent(scrollView);
        }
        return super.onOptionsItemSelected(item);
    }
}

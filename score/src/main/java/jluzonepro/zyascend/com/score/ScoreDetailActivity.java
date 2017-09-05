package jluzonepro.zyascend.com.score;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jluzonepro.zyascend.com.common.base.BaseActivity;
import jluzonepro.zyascend.com.common.entity.AvgScore;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.ScoreDetail;
import jluzonepro.zyascend.com.common.router.RouterUtils;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import jluzonepro.zyascend.com.common.utils.PieChart;

/**
 *
 * Created by Administrator on 2016/10/29.
 */
@Route(path = RouterUtils.SCORE_DETAIL)
public class ScoreDetailActivity extends BaseActivity<ScoreContract.View, ScorePresenter> implements ScoreContract.View {

    public static final String EXTRA_SCORE = "SCORE";
    public static final String EXTRA_DETAIL = "DEATIL";
    private static final String TAG = "TAG_SCoreDetail";
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_score)
    TextView tvScore;
    @BindView(R2.id.tv_gpoint)
    TextView tvGpoint;
    @BindView(R2.id.pie)
    PieChart pie;
    @BindView(R2.id.tv_stuName)
    TextView tvStuName;
    @BindView(R2.id.btn_info)
    Button btnInfo;
    @BindView(R2.id.tv_info)
    TextView tvInfo;
    @BindView(R2.id.card_info)
    CardView cardInfo;
    private Score mScore;
    private boolean isShowInfo;
    private static final String info = "###### 关于成绩的声明\n" +
            "- 成绩信息由学校官方发布，本软件不做任何修改，并只提供查询服务。\n" +
            "- 若对成绩的准确度有疑问，请登录[学校教务官网](http://uims.jlu.edu.cn)查询确认。\n";

    private ArrayList<ScoreDetail.ItemsBean> mItems;

    @Override
    protected void doOnCreate() {
        generateData();
    }

    private void generateData() {
        mPresenter.getScoreDetail(String.valueOf(mScore.getAsId()));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_score_detail;
    }

    @Override
    protected ScorePresenter getPresenter() {
        return new ScorePresenter(this);
    }

    @Override
    protected void loadFragment() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share){
            share();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        mScore = getIntent().getParcelableExtra(EXTRA_SCORE);
        setToolbarTitle("分数详情");
        if (mScore != null) {
            tvName.setText(mScore.getName());
            tvGpoint.setText(mScore.getGpoint());
            tvScore.setText(mScore.getScore());
            tvStuName.setText(mScore.getStuName());
        }
        RichText.fromMarkdown(info).into(tvInfo);
    }

    private void share() {
        RouterUtils.navigation(RouterUtils.SHARE_SCORE);
    }

    @Override
    public void loadScoreDetail(ScoreDetail detail) {
        if (detail != null) {

            double[] data = new double[5];
            String[] titles = new String[5];
            List<ScoreDetail.ItemsBean> itemsBeen = detail.getItems();
            if (itemsBeen == null){
//                showFailure();
                return;
            }

            mItems = (ArrayList<ScoreDetail.ItemsBean>) itemsBeen;
            for (int i = 0; i < itemsBeen.size(); i++) {
                data[i] = itemsBeen.get(i).getPercent();
                String s = getTitles(itemsBeen.get(i).getLabel());
                titles[i] = s + "  " + (int) data[i] + "%";
                Log.d(TAG, "loadScoreDetail: " + data[i] + titles[i]);
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
    public void loadScore(List<Score> scoreList) {

    }

    @Override
    public void showFailure() {
        Toast.makeText(this, "加载失败，请检查网络后重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSorted(List<Score> scores) {

    }

    @Override
    public void loadAvgScore(AvgScore score) {

    }


    @OnClick(R2.id.btn_info)
    public void onClick() {
        isShowInfo = !isShowInfo;
        if (isShowInfo){
            btnInfo.setText("隐藏声明");
            cardInfo.setVisibility(View.VISIBLE);
        }else {
            btnInfo.setText("显示声明");
            cardInfo.setVisibility(View.GONE);
        }
    }
}

package jluzonepro.zyascend.com.score;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jluzonepro.zyascend.com.common.base.BaseFragmentActivity;
import jluzonepro.zyascend.com.common.entity.ScoreStatics;
import jluzonepro.zyascend.com.common.router.RouterUtils;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import jluzonepro.zyascend.com.common.utils.ShareUtils;
import jluzonepro.zyascend.com.common.utils.view.NumAnim;


/**
 *
 * Created by Administrator on 2017/3/30.
 */
@Route(path = RouterUtils.SCORE_STATICS)
public class ScoreStaticsActivity extends BaseFragmentActivity implements Animator.AnimatorListener {


    public static final String INTENT_SCORE = "intent_score";
    private static final String TAG = "ScoreStaticsActivity";

    @BindView(R2.id.firstGp_text)
    TextView firstGpText;
    @BindView(R2.id.firstGp_view)
    RelativeLayout firstGpView;
    @BindView(R2.id.firstScore_text)
    TextView firstScoreText;
    @BindView(R2.id.firstScore_view)
    RelativeLayout firstScoreView;
    @BindView(R2.id.bestGp_text)
    TextView bestGpText;
    @BindView(R2.id.bestGp_view)
    RelativeLayout bestGpView;
    @BindView(R2.id.bestScore_text)
    TextView bestScoreText;
    @BindView(R2.id.bestScore_view)
    RelativeLayout bestScoreView;
    @BindView(R2.id.deadLine)
    TextView deadLineText;
    @BindView(R2.id.btn_share)
    ImageView btnShare;
    @BindView(R2.id.btn_close)
    ImageView btnClose;


    private ScoreStatics scoreStatics;
    private int currentAnimation = -1;

    @Override
    protected void doOnCreate() {

        firstGpView.setAlpha(0);
        firstScoreView.setAlpha(0);
        bestGpView.setAlpha(0);
        bestScoreView.setAlpha(0);

        deadLineText.setText("(截至" + ActivityUtils.getCurrentDate() + ")");

        scoreStatics = getIntent().getParcelableExtra(INTENT_SCORE);
        if (scoreStatics != null) showScore();
        else showError();

    }

    private void showScore() {


        ObjectAnimator animator1 = ObjectAnimator.ofFloat(firstGpView, "alpha", 0f, 1f);
        animator1.addListener(this);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(firstScoreView, "alpha", 0f, 1f);
        animator2.addListener(this);
        animator2.setStartDelay(1500);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(bestGpView, "alpha", 0f, 1f);
        animator3.addListener(this);
        animator3.setStartDelay(3000);

        ObjectAnimator animator4 = ObjectAnimator.ofFloat(bestScoreView, "alpha", 0f, 1f);
        animator4.addListener(this);
        animator4.setStartDelay(4500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1, animator2, animator3, animator4);
        set.setDuration(500);
        set.start();

    }

    private void showError() {
        Toast.makeText(this, "加载数据出错...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_statistis_page1;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        currentAnimation++;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        startTextViewAnim();
    }

    private void startTextViewAnim() {
        Log.d(TAG, "startTextViewAnim: num = " + currentAnimation);
        switch (currentAnimation) {
            case 0:
                Log.d(TAG, "startTextViewAnim:fir " + scoreStatics.getGpaFirst());
                NumAnim.startAnim(firstGpText, formatDoubleToFloat(scoreStatics.getGpaFirst()), 500);
                break;
            case 1:
                NumAnim.startAnim(firstScoreText, formatDoubleToFloat(scoreStatics.getAvgScoreFirst()), 500);
                break;
            case 2:
                NumAnim.startAnim(bestGpText, formatDoubleToFloat(scoreStatics.getGpaBest()), 500);
                break;
            case 3:
                NumAnim.startAnim(bestScoreText, formatDoubleToFloat(scoreStatics.getAvgScoreBest()), 500);
                break;
        }
    }

    private float formatDoubleToFloat(double num) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return Float.parseFloat(df.format(num));
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }


    @OnClick({R2.id.btn_share, R2.id.btn_close})
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.btn_share) {
            ShareUtils.getInstance(new ShareUtils.ShareCallback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFail() {
                    Toast.makeText(ScoreStaticsActivity.this, "分享失败...", Toast.LENGTH_SHORT).show();

                }
            }).shareView(this, getCurrentView());
            btnClose.setVisibility(View.VISIBLE);
            btnShare.setVisibility(View.VISIBLE);
        } else if (id == R2.id.btn_close) {
            onBackPressed();
        }

    }

    private View getCurrentView() {
        btnClose.setVisibility(View.VISIBLE);
        btnShare.setVisibility(View.VISIBLE);
        return findViewById(android.R.id.content);
    }

}

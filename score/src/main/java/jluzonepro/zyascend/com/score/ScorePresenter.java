package jluzonepro.zyascend.com.score;

import android.content.Context;
import android.util.Log;

import jluzonepro.zyascend.com.common.base.BasePresenter;
import jluzonepro.zyascend.com.common.entity.AvgScore;
import jluzonepro.zyascend.com.common.entity.ConstValue;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.ScoreDetail;
import jluzonepro.zyascend.com.common.http.OkHttpUtils;
import jluzonepro.zyascend.com.common.model.data.DataListener;
import jluzonepro.zyascend.com.common.model.data.DataUtils;
import jluzonepro.zyascend.com.common.model.net.HttpManager;
import jluzonepro.zyascend.com.common.model.net.HttpManagerListener;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import jluzonepro.zyascend.com.common.utils.ScoreSortUtils;

import java.util.List;


/**
 *
 * Created by Administrator on 2016/8/3.
 */
public class ScorePresenter extends BasePresenter<ScoreContract.View>implements ScoreContract.Presenter, HttpManagerListener.ScoreCallBack {

    private DataListener mDataListener;
    private HttpManagerListener mHttpUtils;
    private int mType;

    private static final String TAG = "TAG_ScorePresenter";


    public ScorePresenter(Context context){
        mDataListener = DataUtils.getInstance(context.getApplicationContext());
        mHttpUtils = HttpManager.getInstance();
    }



    @Override
    public void getScore(int params, int type) {
        mType = type;
        Log.d(TAG, "getScore: "+params);
        if (type == ConstValue.SCORE_TYPE_NEW){
            mHttpUtils.getNewScore(TAG_PRESENTER,params,this);
        }else{
            List<Score> scores = mDataListener.getScoresByType(type, params);
            if (ActivityUtils.NotNullOrEmpty(scores)){
                mViewListener.loadScore(scores);
            }else {
                if (type == ConstValue.SCORE_TYPE_YEAR){
                    mHttpUtils.getYearScore(TAG_PRESENTER,params,this);
                }else if (type == ConstValue.SCORE_TYPE_ALL){
                    mHttpUtils.getAllScore(TAG_PRESENTER,this);
                }
            }
        }
    }

    @Override
    public void getScoreDetail(String asId) {
        mHttpUtils.getScoreDetail(TAG_PRESENTER,asId, new HttpManagerListener.ScoreDetailCallback() {
            @Override
            public void onSuccess(ScoreDetail detail) {
                Log.d(TAG, "onSuccess: ");
                mViewListener.loadScoreDetail(detail);
            }

            @Override
            public void onFailure(Exception e) {
                mViewListener.showFailure();
            }
        });
    }

    @Override
    public void sort(int type, List<Score> scores) {
        if (ActivityUtils.NotNullOrEmpty(scores)){
            List<Score> list = ScoreSortUtils.getInstance().sort(type,scores);
            mViewListener.onSorted(list);
        }
    }


    @Override
    public void getAvgScore() {
        mHttpUtils.getAvgScore(TAG_PRESENTER, new HttpManagerListener.AvgScoreCallback() {
            @Override
            public void onSuccess(AvgScore avgScore) {
                mViewListener.loadAvgScore(avgScore);
            }

            @Override
            public void onFailure(Exception e) {
                assert mViewListener != null;
                mViewListener.showFailure();
            }
        });
    }


    @Override
    public void detachView() {
        super.detachView();
        mHttpUtils.cancel();
        mHttpUtils = null;
        mDataListener = null;
        OkHttpUtils.getInstance().cancelTag(TAG_PRESENTER);
    }

    @Override
    public void onSuccess(List<Score> scores) {
        mViewListener.loadScore(scores);
        Log.d(TAG, "onLoadedList: "+scores.size());
        // TODO: 2016/10/16 数据库的操作
//        for (Score score : scores) {
//            mDataListener.saveScore(score);
//        }
    }

    @Override
    public void onFailure(Exception e) {
        mViewListener.showFailure();
    }
}

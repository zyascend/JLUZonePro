package jluzonepro.zyascend.com.score;


import java.util.List;

import jluzonepro.zyascend.com.common.entity.AvgScore;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.ScoreDetail;

/**
 * Created by Administrator on 2016/8/3.
 */
public interface ScoreContract {

    interface Presenter{

        void getScore(int params, int type);
        void getScoreDetail(String asId);
        void sort(int type, List<Score> scores);
        void getAvgScore();
    }

    interface View {
        void loadScore(List<Score> scoreList);
        void showFailure();
        void loadScoreDetail(ScoreDetail detail);
        void onSorted(List<Score> scores);
        void loadAvgScore(AvgScore score);
    }
}

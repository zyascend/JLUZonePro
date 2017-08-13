package jluzonepro.zyascend.com.common.utils;

import android.util.Log;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jluzonepro.zyascend.com.common.entity.Score;

/**
 *
 * Created by Administrator on 2016/10/28.
 */

public class ScoreSortUtils {
    private static ScoreSortUtils utils;
    private List<Score> mList = new ArrayList<>();
    private static final String TAG = "TAG_ScoreSort";
    public ScoreSortUtils(){}

    public static ScoreSortUtils getInstance(){
        if (utils == null){
            return new ScoreSortUtils();
        }
        return utils;
    }

    public List<Score> sort(int type,List<Score> data){
        mList = data;
        switch (type){
            case 0:
                scoreHight2Low();
                break;
            case 1:
                scoreLow2High();
                break;
            case 3:
                creditHight2Low();
                break;
            case 2:
                creditLow2High();
                break;
        }
        return mList;
    }

    private void scoreLow2High() {
        int N = mList.size();
        for (int i = 1; i < N; i++) {
            for (int j = i; j>0 && moreScore(mList.get(j),mList.get(j-1)); j--) {
                exchange(j,j-1);
            }
        }
        sortTextScore();
    }

    private void sortTextScore() {
        int s = 0;
        int N = mList.size();
        for (int i = 1; i < N; i++) {
            for (int j = i; j >0 && !isNumeric(mList.get(j).getScore()) && moreText(mList.get(j),mList.get(j-1)); j--) {
                exchange(j,j-1);
                s = j;
            }
        }
        Log.d(TAG, "sortTextScore: "+ s);
        //将文字分数移到列表最后
        List<Score> textScores = mList.subList(s+3,N-1);
        for (int i = 0; i < s + 3; i++) {
            textScores.add(mList.get(i));
        }
        mList = textScores;

    }

    private boolean moreText(Score score, Score score1) {
        int sortCode1 = getSortCode(score.getScore());
        int sortCode2 = getSortCode(score1.getScore());
        return sortCode1 <= sortCode2;
    }



    private void scoreHight2Low() {
        int N = mList.size();
        for (int i = 1; i < N; i++) {
            for (int j = i; j>0 && lessScore(mList.get(j),mList.get(j-1)); j--) {
                exchange(j,j-1);
            }
        }
        sortTextScore();
    }


    private void creditHight2Low() {

        int N = mList.size();
        for (int i = 1; i < N; i++) {
            for (int j = i; j>0 && moreCredit(mList.get(j),mList.get(j-1)); j--) {
                exchange(j,j-1);
            }
        }


    }

    private boolean lessCredit(Score score, Score score1) {
        return Double.valueOf(score.getCredit()) >= Double.valueOf( score1.getCredit());
    }

    private boolean moreCredit(Score score, Score score1){
        return Double.valueOf(score.getCredit()) <= Double.valueOf( score1.getCredit());
    }

    private void creditLow2High() {
        int N = mList.size();
        for (int i = 1; i < N; i++) {
            for (int j = i; j>0 && lessCredit(mList.get(j),mList.get(j-1)); j--) {
                exchange(j,j-1);
            }
        }
    }

    private boolean moreScore(Score score1, Score score2) {
        String s1 = score1.getScore();
        String s2 = score2.getScore();
        boolean b1 = isNumeric(s1);
        boolean b2 = isNumeric(s2);
        if (b1 && b2) {
            return Integer.parseInt(s1) <= Integer.parseInt(s2);
        } else
            return !b1 && b2;
    }

    private boolean lessScore(Score score1, Score score2) {

        String s1 = score1.getScore();
        String s2 = score2.getScore();
        boolean b1 = isNumeric(s1);
        boolean b2 = isNumeric(s2);
        if (b1 && b2) {
            return Integer.parseInt(s1) >= Integer.parseInt(s2);
        } else
            return !b1 && b2;
    }


    private boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    private void exchange(int j, int i) {
        Score t = mList.get(j);
        mList.set(j,mList.get(i));
        mList.set(i,t);
    }
    private int getSortCode(String score) {
        switch (score){
            case "优秀":
                return 5;
            case "良好":
                return 4;
            case "中等":
                return 3;
            case "及格":
                return 2;
            default:
                return 1;
        }

    }

}

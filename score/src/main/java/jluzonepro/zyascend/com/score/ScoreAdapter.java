package jluzonepro.zyascend.com.score;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jluzonepro.zyascend.com.common.base.BaseReAdapter;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;


/**
 *
 * Created by Administrator on 2016/8/3.
 */
public class ScoreAdapter extends BaseReAdapter {


    private static final String TAG = "TAG_ScoreAdapter";
    private List<Score> mScoreList = null;

    public void setList(List<Score> mScoreList) {
        this.mScoreList = mScoreList;
        Log.d(TAG, "setList: getData = " + mScoreList.size());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ScoreViewHolder viewHolder = (ScoreViewHolder) holder;
        Score score = mScoreList.get(position);
        String credit = "学分：" + score.getCredit();
        String gp = "绩点：" + score.getGpoint();
        String pass = "通过：" + score.getIsPass();
        viewHolder.tvCredit.setText(credit);
        viewHolder.tvGpoint.setText(gp);
        viewHolder.tvIsPass.setText(pass);
        viewHolder.tvName.setText(score.getName());
        viewHolder.tvScore.setText(score.getScore());
        viewHolder.tvScore.setBackgroundResource(getColorByScore(score.getScore()));

        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.getAdapterPosition());
                }
            });
        }
    }

    private int getColorByScore(String s) {
        if (isNumeric(s)) {
            int score = Integer.parseInt(s);
            if (0 <= score && score < 60) {
                return R.color.list_one;
            } else if (60 <= score && score < 70) {
                return R.color.list_two;
            } else if (70 <= score && score < 80) {
                return R.color.list_three;
            } else if (80 <= score && score < 90) {
                return R.color.list_four;
            } else {
                return R.color.list_five;

            }
        } else {
            return R.color.colorPrimary;
        }
    }


    private boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return ActivityUtils.NotNullOrEmpty(mScoreList) ? mScoreList.size() : 0;
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_score)
        TextView tvScore;
        @BindView(R2.id.tv_name)
        TextView tvName;
        @BindView(R2.id.tv_credit)
        TextView tvCredit;
        @BindView(R2.id.tv_gpoint)
        TextView tvGpoint;
        @BindView(R2.id.tv_isPass)
        TextView tvIsPass;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

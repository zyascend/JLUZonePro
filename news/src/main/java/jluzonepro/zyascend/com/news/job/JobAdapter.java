package jluzonepro.zyascend.com.news.job;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jluzonepro.zyascend.com.common.base.BaseReAdapter;
import jluzonepro.zyascend.com.common.entity.Job;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;
import jluzonepro.zyascend.com.news.R;
import jluzonepro.zyascend.com.news.R2;

/**
 *
 * Created by Administrator on 2016/10/20.
 */

public class JobAdapter extends BaseReAdapter {


    private List<Job> mList = new ArrayList<>();

    public void setList(List<Job> List) {
        mList.addAll(List);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final JobViewHolder viewHolder = (JobViewHolder) holder;
        if (ActivityUtils.NotNullOrEmpty(mList)) {
            Job job = mList.get(position);
            viewHolder.tvDate.setText(job.getDate().split(" ")[0]);
            viewHolder.tvHits.setText(String.valueOf(job.getHits()));
            if (TextUtils.equals(job.getType(), "3")){
                viewHolder.tvPlace.setVisibility(View.INVISIBLE);
            }else {
                viewHolder.tvPlace.setText(job.getAddress());
            }
            viewHolder.tvTitle.setText(job.getTitle());
        }

        if (null != mOnItemClickListener) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.getAdapterPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return ActivityUtils.NotNullOrEmpty(mList) ? mList.size() : 0;
    }


    class JobViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_place)
        TextView tvPlace;
        @BindView(R2.id.tv_title)
        TextView tvTitle;
        @BindView(R2.id.tv_hits)
        TextView tvHits;
        @BindView(R2.id.tv_date)
        TextView tvDate;
        public JobViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

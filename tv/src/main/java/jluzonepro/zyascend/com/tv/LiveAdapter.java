package jluzonepro.zyascend.com.tv;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jluzonepro.zyascend.com.common.base.EasyAdapter;
import jluzonepro.zyascend.com.common.entity.LiveChannel;

/**
 *
 * Created by Administrator on 2017/3/22.
 */

public class LiveAdapter extends EasyAdapter<LiveAdapter.LiveHolder> {


    private List<LiveChannel> list = new ArrayList<>();

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<LiveChannel> channels) {
        list.addAll(channels);
        notifyDataSetChanged();
    }



    @Override
    protected int getLayout() {
        return R.layout.item_live;
    }

    @Override
    protected LiveHolder getHolder(View v) {
        return new LiveHolder(v);
    }

    @Override
    protected void doOnBindHolder(RecyclerView.ViewHolder holder, int position) {
        final LiveHolder liveHolder = (LiveHolder) holder;

        liveHolder.channel.setText(list.get(position).getNme());
        liveHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(liveHolder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    protected int getDataCount() {
        return list.size();
    }

    public class LiveHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.channel)
        TextView channel;
        public LiveHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

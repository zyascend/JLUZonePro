package jluzonepro.zyascend.com.common.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/8/3.
 */
public abstract class BaseReAdapter extends RecyclerView.Adapter{

    private static final String TAG = "TAG_BaseAdapter";

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    protected BaseReAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

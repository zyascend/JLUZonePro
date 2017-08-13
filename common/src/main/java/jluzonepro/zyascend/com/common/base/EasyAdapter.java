package jluzonepro.zyascend.com.common.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**\
 * Created by Administrator on 2017/3/21.
 */

public abstract class EasyAdapter<T extends RecyclerView.ViewHolder> extends BaseReAdapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
        return getHolder(v);
    }

    protected abstract int getLayout();

    protected abstract T getHolder(View v);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        doOnBindHolder(holder,position);
    }

    protected abstract void doOnBindHolder(RecyclerView.ViewHolder holder, int position);


    @Override
    public int getItemCount() {
        return getDataCount();
    }

    protected abstract int getDataCount();



}

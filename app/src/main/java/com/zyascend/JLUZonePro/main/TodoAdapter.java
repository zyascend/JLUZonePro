package com.zyascend.JLUZonePro.main;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyascend.JLUZonePro.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jluzonepro.zyascend.com.common.entity.Todo;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;

/**
 *
 * Created by Administrator on 2016/10/25.
 */

public class TodoAdapter extends RecyclerView.Adapter {
    private static final String TAG = "TAG_TodoAdapter";


    private List<Todo> todoList = new ArrayList<>();

    public void setImageListner(ImageClickListener mListner) {
        this.mListner = mListner;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
        Log.d(TAG, "setTodoList: ");
        notifyDataSetChanged();

    }

    private boolean isChecked;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TodoViewHolder viewHolder = (TodoViewHolder) holder;

        if (ActivityUtils.NotNullOrEmpty(todoList)) {
            viewHolder.tvTask.setText(todoList.get(position).getTitle());
            isChecked = todoList.get(position).getIsChecked();
        }
        if (isChecked) {
            viewHolder.ivCheck.setImageResource(R.drawable.ic_check);
        } else {
            viewHolder.ivCheck.setImageResource(R.drawable.ic_unchecked);
        }
        viewHolder.ivCheck.setOnClickListener(new MyClick(position));
        viewHolder.ivClose.setOnClickListener(new MyClick(position));
        viewHolder.cardView.setCardBackgroundColor(getColorByPosition(position));


    }

    private int getColorByPosition(int position) {
        Log.d(TAG, "getColorByPosition: " + position);
        switch ((position + 3) % 3) {
            case 0:
                Log.d(TAG, "getColorByPosition: 0");
                return Color.parseColor("#384259");
            case 1:
                Log.d(TAG, "getColorByPosition: 1");
                return Color.parseColor("#F73859");
            case 2:
                Log.d(TAG, "getColorByPosition: 2");
                return Color.parseColor("#7AC7C4");
            default:
                return R.color.material_blue_gray;
        }

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_close)
        ImageView ivClose;
        @BindView(R.id.iv_check)
        ImageView ivCheck;
        @BindView(R.id.tv_task)
        TextView tvTask;
        @BindView(R.id.cardview)
        CardView cardView;

        public TodoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

    private ImageClickListener mListner;

    public interface ImageClickListener {
        void onImageClick(View view, int position);
    }

    private class MyClick implements View.OnClickListener {
        private int position;

        public MyClick(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {

            if (mListner != null) {
                mListner.onImageClick(v, position);
            }

        }
    }
}

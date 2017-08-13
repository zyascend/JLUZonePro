package com.zyascend.JLUZonePro.main;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyascend.JLUZonePro.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jluzonepro.zyascend.com.common.entity.Course;

/**
 *
 * Created by Administrator on 2016/10/25.
 */

public class MainScheduleAdapter extends RecyclerView.Adapter {

    private List<Course> coursesList = new ArrayList<>();


    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_main, parent, false);
        return new MainScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MainScheduleViewHolder viewHolder = (MainScheduleViewHolder) holder;
        viewHolder.name.setText(coursesList.get(position).getName());
        viewHolder.place.setText(coursesList.get(position).getPlace());
        viewHolder.teacher.setText(coursesList.get(position).getTeacher());
        viewHolder.cardView.setCardBackgroundColor(getColorByPosition(position));
    }


    private int getColorByPosition(int position) {

        switch ((position + 3) % 3) {
            case 0:
                return Color.parseColor("#384259");
            case 1:
                return Color.parseColor("#F73859");
            case 2:
                return Color.parseColor("#7AC7C4");
            default:
                return R.color.material_blue_gray;
        }

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    class MainScheduleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.place)
        TextView place;
        @BindView(R.id.teacher)
        TextView teacher;
        @BindView(R.id.tv_alarm)
        TextView tvAlarm;
        @BindView(R.id.cardview)
        CardView cardView;

        public MainScheduleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


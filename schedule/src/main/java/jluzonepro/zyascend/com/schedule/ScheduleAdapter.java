package jluzonepro.zyascend.com.schedule;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jluzonepro.zyascend.com.common.base.BaseReAdapter;
import jluzonepro.zyascend.com.common.entity.Course;

/**
 *
 * Created by Administrator on 2016/10/14.
 */

public class ScheduleAdapter extends BaseReAdapter {
    private static final String TAG = "TAG_SCADapter";
    private final Context mContext;

    private List<Course> mList = new ArrayList<>();
    private List<String> mNameList;
    private HashMap<String,Integer> colorMap ;
    private int colorIndex = 0;

    public ScheduleAdapter(Context context) {
        mContext = context;
        mNameList = new ArrayList<>();
        colorMap = new HashMap<String, Integer>();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        Course course = getCourseByPosition(position);
        if (course != null) {
            String time = course.getTime();
            if (time.contains("双") || time.contains("单")){
                viewHolder.tvAlarm.setVisibility(View.VISIBLE);
            }
            viewHolder.name.setText(course.getName());
            String placeString = course.getPlace();
            if (!placeString.contains("-")){
                viewHolder.place.setText(placeString);
            }else {
                String[] p = course.getPlace().split("-");
                viewHolder.place.setText(p[1]);
            }
            String teacherAndWeek = course.getTeacher()+" "+course.getBeginWeek()+"~"+course.getEndWeek()+"周";

            viewHolder.teacher.setText(teacherAndWeek);
            viewHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(mContext,getColorByName(course.getName())));
        } else {
            viewHolder.cardView.setAlpha(0);
        }

    }

    private int getColorByName(String key) {
        if (colorMap.containsKey(key)) {
            return getColor(colorMap.get(key));
        }else {
            colorMap.put(key, colorIndex++);
            return getColor(colorMap.get(key));
        }
    }

    private int getColor(int i) {
        Log.d(TAG, "getColor: "+i);
        int[] color = {R.color.material_blue, R.color.material_deep_orange, R.color.material_green
                , R.color.material_deep_purple, R.color.material_red};
        int flags = i >= 5 ? i % 5 : i;
        Log.d(TAG, "getColor: flags:    " + flags);
        return color[flags];
    }

    private Course getCourseByPosition(int position) {
        if (mList != null && !mList.isEmpty()) {
            for (Course course : mList) {
                String time = course.getTime();
                int start = 0;
                if (time != null){

                    start = Integer.parseInt(time.substring(3, 4));

                }
                int day = course.getDayOfWeek();

                if (day == 7) {
                    day = 0;
                }
                // TODO: 2016/10/16 处理有晚自习的情况
                Log.d(TAG, "getCourseByPosition: before = " + course.getName());
                if ((position + 8) % 7 == day  && position / 7 == (start+1) / 2 - 1) {
                    Log.d(TAG, "getCourseByPosition: after = " + course.getName());
                    return course;
                }
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 35;
    }

    public void setData(List<Course> courses, int mCurrentWeek) {
        Log.d(TAG, "setData: " + courses.size());
        this.mList = courses;
        int mWeek = mCurrentWeek;
        colorIndex = 0 ;
        colorMap.clear();
        notifyDataSetChanged();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.name)
        TextView name;
        @BindView(R2.id.place)
        TextView place;
        @BindView(R2.id.teacher)
        TextView teacher;
        @BindView(R2.id.tv_alarm)
        TextView tvAlarm;
        @BindView(R2.id.cardView)
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

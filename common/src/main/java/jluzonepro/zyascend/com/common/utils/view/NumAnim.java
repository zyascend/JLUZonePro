package jluzonepro.zyascend.com.common.utils.view;

import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

public class NumAnim {

    //每秒刷新多少次
    private static final int COUNTPERS = 100;

    public static void startAnim(TextView textV, float num) {
        startAnim(textV, num, 500);
    }

    public static void startAnim(TextView textV, float num, long time) {
        if (num == 0) {
            textV.setText(numberFormat(num,2));
            return;
        }

        Float[] nums = splitnum(num, (int)((time/1000f)*COUNTPERS));

        Counter counter = new Counter(textV, nums, time);

        textV.removeCallbacks(counter);
        textV.post(counter);
    }

    private static String numberFormat(float f, int m) {
        return String.format("%."+m+"f",f);
    }

    private static Float[] splitnum(float num, int count) {
        Random random = new Random();
        float numtemp = num;
        float sum = 0;
        LinkedList<Float> nums = new LinkedList<Float>();
        nums.add(0f);
        while (true) {
            float nextFloat = numberFormatFloat(
                    (random.nextFloat()*num*2f)/(float)count,
                    2);
            if (numtemp - nextFloat >= 0) {
                sum = numberFormatFloat(sum + nextFloat, 2);
                nums.add(sum);
                numtemp -= nextFloat;
            } else {
                nums.add(num);
                return nums.toArray(new Float[0]);
            }
        }
    }

    private static float numberFormatFloat(float f, int m) {
        String strfloat = numberFormat(f,m);
        return Float.parseFloat(strfloat);
    }

    static class Counter implements Runnable {

        private final TextView view;
        private Float[] nums;
        private long pertime;

        private int i = 0;

        Counter(TextView view,Float[] nums,long time) {
            this.view = view;
            this.nums = nums;
            this.pertime = time/nums.length;
        }

        @Override
        public void run() {
            if (i>nums.length-1) {
                view.removeCallbacks(Counter.this);
                return;
            }
            view.setText(numberFormat(nums[i++],2));
            view.removeCallbacks(Counter.this);
            view.postDelayed(Counter.this, pertime);
        }
    }
}
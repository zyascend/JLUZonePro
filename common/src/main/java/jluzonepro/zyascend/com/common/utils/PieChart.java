package jluzonepro.zyascend.com.common.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;


public class PieChart extends View {


    public void setTitles(String[] titles) {
        this.titles = titles;
        invalidate();
    }

    private String[] titles;
    private float singlePadding;
    private Context mContext;

    public void setDatas(double[] datas) {
        this.datas = datas;
        invalidate();
    }



    public void setMainCount(int mainCount) {
        this.mainCount = getMainCount(mainCount);
        invalidate();
    }

    private int getMainCount(int score) {
        if (100>= score && 90 <= score){
            return 0;
        }else if (90> score && 80 <= score){
            return 1;
        }else if (80> score && 70 <= score){
            return 2;
        }else if (70> score && 60 <= score){
            return 3;
        }else {
            return 4;
        }

    }

    public void setNormalRadius(int normalRadius) {
        this.normalRadius = normalRadius;
        invalidate();
    }

    public void setOutRadius(int outRadius) {
        this.outRadius = outRadius;
        invalidate();
    }

    private double[] datas = new double[]{};
    private static final String TAG= "TAG_PieChart";
    private int mainCount;
    private int normalRadius = 200;
    private int outRadius = 210;

    private Paint piePaint;
    private Paint dividerPaint;
    private Paint textPaint;
    private PointF center;
    private RectF normalRecf;
    private RectF outRecf;


    public PieChart(Context context) {
        super(context);
        mContext = context;

        init();
    }

    public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        init();
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        piePaint.setStyle(Paint.Style.FILL);

        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setStyle(Paint.Style.STROKE);
        dividerPaint.setColor(Color.WHITE);
        dividerPaint.setStrokeWidth(7);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);


        center = new PointF();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.set(w/2,h/2);
        outRadius = w/4;
        normalRadius = outRadius-15;
        singlePadding = outRadius/4;

        outRecf = new RectF(-outRadius,-outRadius, outRadius, outRadius);
        normalRecf = new RectF(-normalRadius,-normalRadius,normalRadius,normalRadius);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * 5/7;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (datas == null || datas.length < 2){
            Log.d(TAG, "onDraw: 数据为空！！！");
            return;
        }
        canvas.save();
        canvas.translate(center.x-3*singlePadding,center.y);
        float startAngle = 0;
        for (int i = 0; i < datas.length; i++) {
            float swipeAngle = (float) (datas[i]/100*360);
            piePaint.setColor(getColor(i));
            if (i == mainCount){
                canvas.drawArc(outRecf,startAngle,swipeAngle,true,piePaint);
            }else {
                canvas.drawArc(normalRecf,startAngle,swipeAngle,true,piePaint);
            }
            startAngle += swipeAngle;
        }
        for (double data : datas) {
            float swipeAngle = (float) (data / 100 * 360);
            canvas.drawLine(0, 0, outRadius, 0, dividerPaint);
            canvas.rotate(swipeAngle);
        }
        piePaint.setColor(Color.WHITE);
        canvas.drawCircle(0,0,normalRadius/2,piePaint);
        canvas.restore();

        canvas.translate(center.x+2*singlePadding,center.y);


        if ( titles == null || titles.length<2){
            Log.d(TAG, "onDraw: titles == null");
            return;
        }
        int count = titles.length;
        float offsetY = (outRadius*2-2*singlePadding)/(count -1);
        float startY = -outRadius+singlePadding;
        float indicateRadius = 0.4f*singlePadding;
        textPaint.setTextSize(Sp2Px((int) (indicateRadius*3/5)));
        for (int i = 0; i < titles.length; i++) {
            piePaint.setColor(getColor(i));
            canvas.drawCircle(0,startY,indicateRadius,piePaint);
            canvas.drawText(titles[i],2*indicateRadius,startY+indicateRadius/2,textPaint);
            startY = startY + offsetY;
        }

    }

    private int getColor(int i) {
        switch (i){
            case 0:
                return Color.parseColor("#BF6913");
            case 1:
                return Color.parseColor("#6913BF");
            case 2:
                return Color.parseColor("#13BFBF");
            case 3:
                return Color.parseColor("#69BF13");
            case 4:
                return Color.parseColor("#BF1313");
            default:
                return Color.parseColor("#BF6913");
        }
    }

    public int Dp2Px(int dpi) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, mContext.getResources().getDisplayMetrics());
    }

    public int Sp2Px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, mContext.getResources().getDisplayMetrics());
    }
}

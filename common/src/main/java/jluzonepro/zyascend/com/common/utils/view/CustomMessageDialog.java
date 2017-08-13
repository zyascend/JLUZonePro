package jluzonepro.zyascend.com.common.utils.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;


import jluzonepro.zyascend.com.common.R;


/**
 * 封装两种dialog
 * 1.用于显示信息的dialog
 * 2.用与展示列表的dialog
 * Created by Administrator on 2017/3/26.
 */

public class CustomMessageDialog extends Dialog implements View.OnClickListener {


    private boolean removeCancel;

    private Context mContext;
    private String title;
    private String message;
    private Context context;
    private String positiveName;
    private String negativeName;
    private CustomDialogListener posiListener;
    private CustomDialogListener negaListener;
    private int resStyle;
    private View contentRes;
    private boolean cancelTouchout;


    private TextView titleTextView;
    private TextView btnPositive;
    private TextView btnNeg;
    private FrameLayout content;

    private CustomMessageDialog(Builder builder) {
        super(builder.context);
        mContext = builder.context;
        title = builder.title;
        message = builder.message;
        positiveName = builder.positiveName;
        negativeName = builder.negativeName;
        cancelTouchout = builder.cancelTouchout;
        posiListener = builder.posiListener;
        negaListener = builder.negaListener;
        negativeName = builder.negativeName;
        contentRes = builder.contentView;
        removeCancel = builder.removeCancel;
    }

    private CustomMessageDialog(Builder builder, int themeResId) {
        super(builder.context, themeResId);

        mContext = builder.context;
        title = builder.title;
        message = builder.message;
        positiveName = builder.positiveName;
        negativeName = builder.negativeName;
        cancelTouchout = builder.cancelTouchout;
        posiListener = builder.posiListener;
        negaListener = builder.negaListener;
        negativeName = builder.negativeName;
        resStyle = themeResId;
        contentRes = builder.contentView;
        removeCancel = builder.removeCancel;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.custom_dialog, null);
        setContentView(view);

        titleTextView = (TextView) view.findViewById(R.id.tv_title);
        btnPositive = (TextView) view.findViewById(R.id.btn_positive);
        btnNeg = (TextView) view.findViewById(R.id.btn_nage);
        content = (FrameLayout) view.findViewById(R.id.content);

        titleTextView.setText(title);
        btnPositive.setText(positiveName);

        if (removeCancel) btnNeg.setVisibility(View.GONE);
        else btnNeg.setText(negativeName);

        if (posiListener != null) btnPositive.setOnClickListener(this);
        if (negaListener != null) btnNeg.setOnClickListener(this);

        content.addView(contentRes);

        setCanceledOnTouchOutside(cancelTouchout);

    }

    @Override
    public void show() {
        super.show();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        //设置高宽
        lp.width = (int) (screenWidth * 0.75);
//        lp.height = (int) (lp.width * 0.70);
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_nage){
            if (negaListener != null) negaListener.onClick(v, this);
        }else if (id == R.id.btn_positive){
            if (posiListener != null) posiListener.onClick(v, this);
        }
    }

    public static final class Builder {

        private String title;
        private String message;
        private Context context;
        private String positiveName;
        private String negativeName;
        private CustomDialogListener posiListener;
        private CustomDialogListener negaListener;
        private int resStyle = -1;
        private boolean cancelTouchout = false;
        private View contentView;
        private boolean removeCancel;


        public Builder(Context context) {
            this.context = context;
        }


        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder setMessage(String msg) {
            this.message = msg;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder onPositiveClicked(String positiveName
                , CustomDialogListener listener) {
            this.posiListener = listener;
            this.positiveName = positiveName;
            return this;
        }

        public Builder onNagetiveClicked(String negativeName
                , CustomDialogListener listener) {
            this.negaListener = listener;
            this.negativeName = negativeName;
            return this;
        }

        public Builder setStyle(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            cancelTouchout = val;
            return this;
        }


        public CustomMessageDialog build() {
            if (resStyle != -1) {
                return new CustomMessageDialog(this, resStyle);
            } else {
                return new CustomMessageDialog(this);
            }
        }

        public Builder removeCancelButton(boolean removeCancel) {
            this.removeCancel = removeCancel;
            return this;
        }
    }

    public interface CustomDialogListener {
        void onClick(View v, CustomMessageDialog dialog);
    }
}

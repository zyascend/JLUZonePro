package jluzonepro.zyascend.com.common.share;

import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/10/22.
 */

public interface ShareContract {

    interface Presenter{
        void saveContent(ScrollView scrollView);
        void shareContent(ScrollView scrollView);



    }
    interface View{
        void onSuccess();
        void onFailed();
    }
}

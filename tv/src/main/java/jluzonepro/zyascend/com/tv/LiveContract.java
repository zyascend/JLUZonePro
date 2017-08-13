package jluzonepro.zyascend.com.tv;



import java.util.List;

import jluzonepro.zyascend.com.common.entity.LiveChannel;

/**
 *
 * Created by Administrator on 2017/3/22.
 */

public interface LiveContract {
    interface View{
        void onGetChannelList(List<LiveChannel> channels);
        void onFail();
    }
    interface Presenter{
        void fetchChannel();
    }

}

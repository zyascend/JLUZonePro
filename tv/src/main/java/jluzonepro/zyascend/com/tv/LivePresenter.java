package jluzonepro.zyascend.com.tv;

import java.util.List;

import jluzonepro.zyascend.com.common.base.BasePresenter;
import jluzonepro.zyascend.com.common.entity.LiveChannel;
import jluzonepro.zyascend.com.common.model.net.HttpManager;
import jluzonepro.zyascend.com.common.model.net.HttpManagerListener;

/**
 *
 * Created by Administrator on 2017/3/22.
 */

public class LivePresenter extends BasePresenter<LiveContract.View> implements LiveContract.Presenter {

    private HttpManager manager;
    public LivePresenter(){
        manager = HttpManager.getInstance();
    }

    @Override
    public void fetchChannel() {
        manager.getChannel(new HttpManagerListener.ChannelCallback() {
            @Override
            public void onSuccess(List<LiveChannel> channels) {
                mViewListener.onGetChannelList(channels);
            }

            @Override
            public void onFailure(Exception e) {
                mViewListener.onFail();
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
        manager.cancel();
    }
}

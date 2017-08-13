package jluzonepro.zyascend.com.score;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;

import jluzonepro.zyascend.com.common.base.BaseFragmentActivity;
import jluzonepro.zyascend.com.common.router.RouterUtils;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;


/**
 *
 * Created by Administrator on 2016/8/3.
 */
@Route(path = RouterUtils.SCORE_MAIN)
public class ScoreActivity extends BaseFragmentActivity {


    private Handler mHandler;
    private static final String TAG = "ScoreActivity";
    @Override
    protected void doOnCreate() {
        loadFragment();
        setToolbarTitle("成绩");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_score;
    }

    private void loadFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame,new ScoreFragment())
                .commit();
    }


    public void setHandler(Handler handler) {
        Log.d(TAG, "setHandler: ");
        this.mHandler = handler;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_score,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_statics){
            Message msg = mHandler.obtainMessage();
            msg.arg1 = -1;
            mHandler.sendMessage(msg);
        }
        return super.onOptionsItemSelected(item);
    }
}

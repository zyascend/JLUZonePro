package jluzonepro.zyascend.com.news.news;


import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import jluzonepro.zyascend.com.news.R2;
import jluzonepro.zyascend.com.common.base.BaseFragmentActivity;

import jluzonepro.zyascend.com.common.router.RouterUtils;
import jluzonepro.zyascend.com.news.R;


/**
 *
 * Created by Administrator on 2016/8/4.
 */
@Route(path = RouterUtils.JOB_MAIN)
public class NewsActivity extends BaseFragmentActivity {


    private static final String TAG = "TAG_NEwsActivty";
    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;
    private Handler mHandler;

    @Override
    protected void doOnCreate() {
        setToolbarTitle("最新通知");
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return NewsFragment.getInstance("jwc");
                    case 1:
                        return NewsFragment.getInstance("xiao");
                    default:
                        return NewsFragment.getInstance("jwc");
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "教务通知";
                    case 1:
                        return "校内通知";
                    default:
                        return "教务通知";
                }
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tab_layout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.news,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_filter){
            startFilter();
        }else {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void startFilter() {
        if (mHandler != null){
            Message message = mHandler.obtainMessage();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }
}

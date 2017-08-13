package jluzonepro.zyascend.com.news.job;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import jluzonepro.zyascend.com.common.base.BaseFragmentActivity;
import jluzonepro.zyascend.com.common.router.RouterUtils;
import jluzonepro.zyascend.com.news.R;
import jluzonepro.zyascend.com.news.R2;


/**
 *
 * Created by Administrator on 2016/10/20.
 */

@Route(path = RouterUtils.JOB_MAIN)
public class JobActivity extends BaseFragmentActivity {

    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void doOnCreate() {
        setToolbarTitle("就业资讯");
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return JobFragment.getInstance("xiaozhao");
                    case 1:
                        return JobFragment.getInstance("shixi");
                    default:
                        return JobFragment.getInstance("xiaozhao");
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
                        return "校园招聘";
                    case 1:
                        return "实习招聘";
                    default:
                        return "校园招聘";
                }
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tab_layout;
    }

}

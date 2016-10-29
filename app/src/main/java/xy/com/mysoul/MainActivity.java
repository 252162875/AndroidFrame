package xy.com.mysoul;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.tabs_main)
    TabLayout tabsMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTabs();
        initListener();
    }

    private void initListener() {
           /*下两行代码影响TabLayout图标的显示，所以下面自己处理了逻辑*/

//        tabs_main.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
//        tabs_main.setTabsFromPagerAdapter(contentAdapter);
        tabsMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView tabView = (ImageView) tab.getCustomView();
                int i = (int) tab.getTag();
                switch (i) {
                    case 0:
                        tabView.setImageResource(R.drawable.home_hover2x);
//                        mViewPager.setCurrentItem(0, false);// 让ViewPager切换到第一个页面
//                        BaseApplication.getInstance().setRootPosition(0);
//                        setSlidingMenuEnable(false);
                        break;
                    case 1:
                        tabView.setImageResource(R.drawable.community_hover2x);
//                        mViewPager.setCurrentItem(1, false);
//                        BaseApplication.getInstance().setRootPosition(1);
//                        setSlidingMenuEnable(false);
                        break;
                    case 2:
                        tabView.setImageResource(R.drawable.course_hover2x);
//                        mViewPager.setCurrentItem(2, false);
//                        BaseApplication.getInstance().setRootPosition(2);
//                        setSlidingMenuEnable(false);
                        break;
                    case 3:
                        tabView.setImageResource(R.drawable.mall_hover2x);
//                        mViewPager.setCurrentItem(3, false);
//                        BaseApplication.getInstance().setRootPosition(3);
//                        setSlidingMenuEnable(false);
                        break;
                    case 4:
                        tabView.setImageResource(R.drawable.mine_hover2x);
//                        mViewPager.setCurrentItem(4, false);
//                        BaseApplication.getInstance().setRootPosition(4);
//                        setSlidingMenuEnable(false);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView tabView = (ImageView) tab.getCustomView();
                int i = (int) tab.getTag();
                switch (i) {
                    case 0:
                        tabView.setImageResource(R.drawable.home_normal2x);
                        break;
                    case 1:
                        tabView.setImageResource(R.drawable.community_normal2x);
                        break;
                    case 2:
                        tabView.setImageResource(R.drawable.course_normal2x);
                        break;
                    case 3:
                        tabView.setImageResource(R.drawable.mall_normal2x);
                        break;
                    case 4:
                        tabView.setImageResource(R.drawable.mine_normal2x);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setTabs() {
        //设置tab模式，当前为系统默认模式
        tabsMain.setTabMode(TabLayout.MODE_FIXED);
        //添加tab选项卡
        TabLayout.Tab tab0 = tabsMain.newTab().setCustomView(initTabView(0)).setTag(0);
        TabLayout.Tab tab1 = tabsMain.newTab().setCustomView(initTabView(1)).setTag(1);
        TabLayout.Tab tab2 = tabsMain.newTab().setCustomView(initTabView(2)).setTag(2);
        TabLayout.Tab tab3 = tabsMain.newTab().setCustomView(initTabView(3)).setTag(3);
        TabLayout.Tab tab4 = tabsMain.newTab().setCustomView(initTabView(4)).setTag(4);
        tabsMain.addTab(tab0);
        tabsMain.addTab(tab1);
        tabsMain.addTab(tab2);
        tabsMain.addTab(tab3);
        tabsMain.addTab(tab4);
    }



    /**
     * 根据位置返回TAB的布局（初始化底下TABS）
     *
     * @param position
     * @return
     */
    public View initTabView(int position) {
        //首先为子tab布置一个布局
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_tab_item, null);
        ImageView iv = (ImageView) v.findViewById(R.id.iv_main_tab);
        switch (position) {
            case 0:
                iv.setImageResource(R.drawable.home_hover2x);
                break;
            case 1:
                iv.setImageResource(R.drawable.community_normal2x);
                break;
            case 2:
                iv.setImageResource(R.drawable.course_normal2x);
                break;
            case 3:
                iv.setImageResource(R.drawable.mall_normal2x);
                break;
            case 4:
                iv.setImageResource(R.drawable.mine_normal2x);
                break;
        }
        return iv;
    }

}
package me.alex.baseimageloaderdemo;

import android.view.View;

import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.FragmentLayout;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.FragmentChangeUtil;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.tabbar.Tab;
import com.kongzue.tabbar.TabBarView;
import com.kongzue.tabbar.interfaces.OnTabChangeListener;

import java.util.ArrayList;
import java.util.List;

import me.alex.baseimageloader.tools.AutoLoadTools;
import me.alex.baseimageloaderdemo.fragment.AutoLoadFragment;
import me.alex.baseimageloaderdemo.fragment.BaseImageViewFragment;
import me.alex.baseimageloaderdemo.fragment.CustomFragment;
import me.alex.baseimageloaderdemo.fragment.ImageFragment;

@DarkStatusBarTheme(true)
@Layout(R.layout.activity_main)
@FragmentLayout(R.id.frame)
public class MainActivity extends BaseActivity {

    @Override
    public void initViews() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(me, "ImageView", 0));
        tabs.add(new Tab(me, "自定义View", 0));
        tabs.add(new Tab(me, "加载至任意View", 0));
        tabs.add(new Tab(me, "自动加载", 0));

        TabBarView tabBar = findViewById(R.id.tabbar);
        tabBar.setTab(tabs);
        tabBar.setOnTabChangeListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(View v, int index) {
                changeFragment(index);
            }
        });
    }

    @Override
    public void initDatas(JumpParameter parameter) {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void initFragment(FragmentChangeUtil fragmentChangeUtil) {
        fragmentChangeUtil.addFragment(new ImageFragment());
        fragmentChangeUtil.addFragment(new BaseImageViewFragment());
        fragmentChangeUtil.addFragment(new CustomFragment());
        fragmentChangeUtil.addFragment(new AutoLoadFragment());
        changeFragment(0);
    }
}
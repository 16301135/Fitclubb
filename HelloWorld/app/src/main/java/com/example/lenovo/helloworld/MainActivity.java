package com.example.lenovo.helloworld;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.helloworld.fragment.OneFragment;
import com.example.lenovo.helloworld.fragment.ThreeFragment;
import com.example.lenovo.helloworld.fragment.TwoFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    //属于适配器控件
    private ViewPager mVp;
    private List<ImageView> mList ;
    private TextView tvTitle;
    private String[] titles;//标题数组
    private BottomTabBar mBottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);


        mBottomTabBar.init(getSupportFragmentManager())
                       .setImgSize(50,50)
                .setFontSize(12)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED, Color.BLUE)

        .addTabItem("首页",R.mipmap.home_selected,R.mipmap.home,OneFragment.class)
                       .addTabItem("课程",R.mipmap.list, TwoFragment.class)
                       .addTabItem("我的",R.mipmap.user,ThreeFragment.class)
                .isShowDivider(true)
                       .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener(){
                           @Override
                           public void onTabChange(int position, String name) {
                               Log.i("TGA","位置"+position+"选项卡"+name);
                           }
                       })
   ;

   }
}

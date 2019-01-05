package com.alex.doctor;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.alex.doctor.databinding.ActivityMainBinding;
import com.alex.doctor.fragment.ActiveFragment;
import com.alex.doctor.fragment.HomeFragment;
import com.alex.doctor.fragment.MineFragment;
import com.alex.doctor.fragment.PhotoFragment;
import com.alex.doctor.util.TabEntity;
import com.alex.doctor.util.Utils;

import java.util.ArrayList;

/**
 * Created by lj on 2018/2/9.
 * 主页，不同的fragment，其状态栏背景和文字颜色不一样
 */
public class ActivityMain extends AppCompatActivity {

    private String[] mTitles = {"", "", "", ""};
    private int[] mIconUnselectIds = {
            R.drawable.tab_icon_hone_default, R.drawable.tab_icon_active_default, R.drawable.tab_icon_photo_default, R.drawable.tab_icon_mine_default};
    private int[] mIconSelectIds = {
            R.drawable.tab_icon_hone_pre, R.drawable.tab_icon_active_pre, R.drawable.tab_icon_photo_pre, R.drawable.tab_icon_mine_pre};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private HomeFragment homeFragment;
    private ActiveFragment secondFragment;
    private PhotoFragment thirdFragment;
    private MineFragment fourFragment;
    private ActivityMainBinding bindingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Utils.getStatusBarHeight(this);
        super.onCreate(savedInstanceState);
        bindingView = DataBindingUtil.setContentView(ActivityMain.this, R.layout.activity_main);
        Utils.setStatusBar(this, false, false);
        //初始化frament
        initTab();
        initFragment(savedInstanceState);
        setUpdateParam();
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        bindingView.tabLayout.setTabData(mTabEntities);
        //点击监听
        bindingView.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
            secondFragment = (ActiveFragment) getSupportFragmentManager().findFragmentByTag("secondFragment");
            thirdFragment = (PhotoFragment) getSupportFragmentManager().findFragmentByTag("thirdFragment");
            fourFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag("fourFragment");
            currentTabPosition = savedInstanceState.getInt(Utils.HOME_CURRENT_TAB_POSITION);
        } else {
            homeFragment = new HomeFragment();
            secondFragment = new ActiveFragment();
            thirdFragment = new PhotoFragment();
            fourFragment = new MineFragment();

            transaction.add(R.id.fl_body, homeFragment, "homeFragment");
            transaction.add(R.id.fl_body, secondFragment, "secondFragment");
            transaction.add(R.id.fl_body, thirdFragment, "thirdFragment");
            transaction.add(R.id.fl_body, fourFragment, "fourFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        bindingView.tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                hideShowFragment(transaction, fourFragment, thirdFragment, secondFragment, homeFragment);
                Utils.setStatusTextColor(false, ActivityMain.this);
                break;
            //活动页
            case 1:
                hideShowFragment(transaction, homeFragment, thirdFragment, fourFragment, secondFragment);
                Utils.setStatusTextColor(true, ActivityMain.this);
                break;
            //相册
            case 2:
                hideShowFragment(transaction, homeFragment, fourFragment, secondFragment, thirdFragment);
                Utils.setStatusTextColor(true, ActivityMain.this);
                break;
            //我的
            case 3:
                hideShowFragment(transaction, homeFragment, secondFragment, thirdFragment, fourFragment);
                Utils.setStatusTextColor(false, ActivityMain.this);
                break;
            default:
                break;
        }

    }

    private void hideShowFragment(FragmentTransaction transaction, Fragment fragment1, Fragment fragment2, Fragment fragment3, Fragment fragment4) {
        transaction.hide(fragment1);
        transaction.hide(fragment2);
        transaction.hide(fragment3);
        transaction.show(fragment4);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
        if (bindingView.tabLayout != null) {
            outState.putInt(Utils.HOME_CURRENT_TAB_POSITION, bindingView.tabLayout.getCurrentTab());
        }
    }

    private void setUpdateParam() {
    }
}

package com.basemvp.hong.ui;


import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.basemvp.hong.ActivityManager;
import com.basemvp.hong.R;
import com.basemvp.hong.mvp.model.entity.TabEntity;
import com.basemvp.hong.ui.base.BaseActivity;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.ui.main.MainHomeFragment;
import com.basemvp.hong.ui.main.MainMineFragment;
import com.basemvp.hong.ui.main.MainProjectFragment;
import com.basemvp.hong.ui.main.MainSquareFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import butterknife.BindView;


@FConfig(value = R.layout.activity_main, hideToolbar = true)
public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.layout_main_container)
    FrameLayout frameLayout;

    List<Fragment> fragmentList;
    ArrayList<CustomTabEntity> mTabList;

    int currentIndex = 0;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initFragments(savedInstanceState);
        initTabLayout();
    }

    private void initTabLayout() {
        mTabList = new ArrayList<>();
        mTabList.add(new TabEntity(R.mipmap.ic_main_tab_home, R.mipmap.ic_main_tab_home_selected, "首页"));
        mTabList.add(new TabEntity(R.mipmap.ic_main_tab_mining, R.mipmap.ic_main_tab_mining_selected, "广场"));
        mTabList.add(new TabEntity(R.mipmap.ic_main_tab_wallet, R.mipmap.ic_main_tab_wallet_selected, "项目"));
        mTabList.add(new TabEntity(R.mipmap.ic_main_tab_my, R.mipmap.ic_main_tab_my_selected, "我的"));
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                currentIndex = position;
                selectedFragment(position);
                switch (position) {
                    case 0:
                    case 3:
                        ImmersionBar.with(MainActivity.this).statusBarDarkFont(true, 0.2f).init();
                        break;
                    case 2:
                    case 4:
                        ImmersionBar.with(MainActivity.this).init();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mTabLayout.setTabData(mTabList);
        mTabLayout.setCurrentTab(currentIndex);
        selectedFragment(currentIndex);
    }

    private void initFragments(Bundle savedInstanceState) {
        fragmentList = new ArrayList<>();
        if (savedInstanceState != null) {
            /*获取保存的fragment  没有的话返回null*/
            mHomeFragment = (MainHomeFragment) getSupportFragmentManager().getFragment(savedInstanceState, MAIN_HOME);
            mSquareFragment = (MainSquareFragment) getSupportFragmentManager().getFragment(savedInstanceState, MAIN_SQUARE);
            mProjectFragment = (MainProjectFragment) getSupportFragmentManager().getFragment(savedInstanceState, MAIN_PROJECT);
            mMineFragment = (MainMineFragment) getSupportFragmentManager().getFragment(savedInstanceState, MAIN_MINE);
            currentIndex = savedInstanceState.getInt("current_tab", 0);
        }
    }

    MainHomeFragment mHomeFragment;
    MainSquareFragment mSquareFragment;
    MainProjectFragment mProjectFragment;
    MainMineFragment mMineFragment;

    private static final String MAIN_HOME = "homeFragment";
    private static final String MAIN_SQUARE = "squareFragment";
    private static final String MAIN_PROJECT = "projectFragment";
    private static final String MAIN_MINE = "mineFragment";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        /*fragment不为空时 保存*/
        if (mHomeFragment != null) {
            getSupportFragmentManager().putFragment(outState, MAIN_HOME, mHomeFragment);
        }
        if (mSquareFragment != null) {
            getSupportFragmentManager().putFragment(outState, MAIN_SQUARE, mSquareFragment);
        }
        if (mProjectFragment != null) {
            getSupportFragmentManager().putFragment(outState, MAIN_PROJECT, mProjectFragment);
        }
        if (mMineFragment != null) {
            getSupportFragmentManager().putFragment(outState, MAIN_MINE, mMineFragment);
        }
        outState.putInt("current_tab", currentIndex);
    }


    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new MainHomeFragment();
                    transaction.add(R.id.layout_main_container, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                transaction.setMaxLifecycle(mHomeFragment, Lifecycle.State.RESUMED);
                break;
            case 1:
                if (mSquareFragment == null) {
                    mSquareFragment = new MainSquareFragment();
                    transaction.add(R.id.layout_main_container, mSquareFragment);
                } else {

                    transaction.show(mSquareFragment);
                }
                transaction.setMaxLifecycle(mSquareFragment, Lifecycle.State.RESUMED);
                break;
            case 2:
                if (mProjectFragment == null) {
                    mProjectFragment = new MainProjectFragment();
                    transaction.add(R.id.layout_main_container, mProjectFragment);
                } else {
                    transaction.show(mProjectFragment);
                }
                transaction.setMaxLifecycle(mProjectFragment, Lifecycle.State.RESUMED);
                break;
            case 3:
                if (mMineFragment == null) {
                    mMineFragment = new MainMineFragment();
                    transaction.add(R.id.layout_main_container, mMineFragment);
                } else {
                    transaction.show(mMineFragment);
                }
                transaction.setMaxLifecycle(mMineFragment, Lifecycle.State.RESUMED);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
            transaction.setMaxLifecycle(mHomeFragment, Lifecycle.State.STARTED);
        }
        if (mSquareFragment != null) {
            transaction.hide(mSquareFragment);
            transaction.setMaxLifecycle(mSquareFragment, Lifecycle.State.STARTED);
        }
        if (mProjectFragment != null) {
            transaction.hide(mProjectFragment);
            transaction.setMaxLifecycle(mProjectFragment, Lifecycle.State.STARTED);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
            transaction.setMaxLifecycle(mMineFragment, Lifecycle.State.STARTED);
        }
    }


    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long currentTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((currentTime - exitTime) > 2000) {
                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.getInstance().exitApp();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}

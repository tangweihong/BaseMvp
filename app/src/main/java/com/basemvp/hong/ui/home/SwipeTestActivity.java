package com.basemvp.hong.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.basemvp.hong.R;
import com.basemvp.hong.mvp.presenter.RequestPresenter;
import com.basemvp.hong.ui.base.BaseActivity;
import com.basemvp.hong.ui.base.SwipeRefreshActivity;
import com.basemvp.hong.ui.base.internal.FConfig;

@FConfig(value = R.layout.activity_swipe_test, title = "弹性上拉")
public class SwipeTestActivity extends SwipeRefreshActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setOnlyScrollBounce();
    }

    @Override
    public RequestPresenter getPresenter() {
        return null;
    }
}
package com.basemvp.hong;


import com.basemvp.hong.ui.base.BaseActivity;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.ui.home.NewsListActivity;


@FConfig(value = R.layout.activity_main,hideToolbar = true)
public class MainActivity extends BaseActivity {

    @Override
    protected void initView() {
        super.initView();
        startActivity(NewsListActivity.class);
    }
}

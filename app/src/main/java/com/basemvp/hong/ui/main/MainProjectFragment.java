package com.basemvp.hong.ui.main;

import com.basemvp.hong.R;
import com.basemvp.hong.ui.base.BaseFragment;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.ui.home.NewsListActivity;
import com.basemvp.hong.utils.XLog;

import butterknife.OnClick;

/**
 * Created by hong on 2021/10/18 10:52.
 */
@FConfig(value = R.layout.activity_test)
public class MainProjectFragment extends BaseFragment {

    @Override
    protected void initView() {
        super.initView();

    }

    @OnClick(R.id.tv_refresh)
    void onClick() {
         startActivity(NewsListActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.e("eeeee", "onResume");
    }

    @Override
    public void lazyData() {
        XLog.e("eeeee", "ProjectLazyData");
    }
}

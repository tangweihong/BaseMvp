package com.basemvp.hong.ui.main;

import com.basemvp.hong.R;
import com.basemvp.hong.ui.base.BaseFragment;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.ui.home.NewsListActivity;
import com.basemvp.hong.ui.home.SwipeTestActivity;
import com.basemvp.hong.utils.XLog;
import com.gyf.immersionbar.ImmersionBar;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hong on 2021/10/18 10:52.
 */
@FConfig(value = R.layout.activity_test)
public class MainSquareFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initView() {
        super.initView();
        ImmersionBar.setTitleBar(this, mToolbar);
    }


    @OnClick(R.id.tv_refresh)
    void onClick() {
        startActivity(SwipeTestActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.e("eeeee", "onResume");
    }

    @Override
    public void lazyData() {
        XLog.e("eeeee", "SquareLazyData");
    }
}

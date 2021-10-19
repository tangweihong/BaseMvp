package com.basemvp.hong.ui.main;

import com.basemvp.hong.R;
import com.basemvp.hong.adapter.MyFragmentStateAdapter;
import com.basemvp.hong.ui.base.BaseFragment;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.ui.fragment.TestFragment;
import com.basemvp.hong.utils.XLog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;

/**
 * Created by hong on 2021/10/18 10:52.
 */
@FConfig(value = R.layout.fragment_home)
public class MainHomeFragment extends BaseFragment {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_page)
    ViewPager2 mViewPage2;

    @Override
    protected void initView() {
        super.initView();
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        List<String> mList = new ArrayList<>();
        mList.add("策略库");
        mList.add("执行中");
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(this, fragments);
        mViewPage2.setAdapter(adapter);
//        mViewPage2.setUserInputEnabled(false);
//        mViewPage2.setPageTransformer(new ZoomOutPageTransformer());//设置页面切换的动画
        new TabLayoutMediator(mTabLayout, mViewPage2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mList.get(position));
            }
        }).attach();
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.e("eeeee", "onResume");
    }

    @Override
    public void lazyData() {
        XLog.e("eeeee", "HomeLazyData");
    }
}

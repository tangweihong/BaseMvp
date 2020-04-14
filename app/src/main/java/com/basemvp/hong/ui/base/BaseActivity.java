package com.basemvp.hong.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basemvp.hong.mvp.view.IBaseView;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by Hong on 2020/4/13 12:02
 * .
 */
public class BaseActivity extends RxAppCompatActivity implements IBaseView {
    FConfig fConfig;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fConfig = getClass().getAnnotation(FConfig.class);
        if (fConfig == null) {
            fConfig = BaseActivity.class.getAnnotation(FConfig.class);
        }
        setContentView(getConfigLayoutRes());
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initImmersionBar();
        initView();
        initData();
    }


    /**
     * get {@link FConfig#value()}
     *
     * @return layout id
     */
    protected int getConfigLayoutRes() {
        return fConfig.value();
    }

    /**
     * is toolbar set{@link BaseToolBarActivity#initToolbar()}
     */
    protected void initToolbar() {
    }

    /**
     * initialize Immersive status bar
     */
    protected void initImmersionBar() {
//        ImmersionBar.with(this).init();
    }

    /**
     * initialize view
     */
    protected void initView() {
    }

    /**
     * initialize data
     */
    protected void initData() {
    }

    /**
     * initialize presenter
     */
    protected void initPresenter() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void loading() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void loadingFinish() {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showLoading() {

    }
}

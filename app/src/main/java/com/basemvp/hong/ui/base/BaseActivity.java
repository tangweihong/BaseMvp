package com.basemvp.hong.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basemvp.hong.ui.base.internal.FConfig;

/**
 * Create by Hong on 2020/4/13 12:02
 * .
 */
public class BaseActivity extends AppCompatActivity {
    FConfig fConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fConfig = getClass().getAnnotation(FConfig.class);
        if (fConfig == null) {
            fConfig = BaseActivity.class.getAnnotation(FConfig.class);
        }
        setContentView(getConfigLayoutRes());
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
     * Initialize view
     */
    protected void initView() {
    }

    /**
     * initialize data
     */
    protected void initData() {
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
}

package com.basemvp.hong.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.basemvp.hong.ui.base.internal.FConfig;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by Hong on 2020/4/13 12:03.
 */
public class BaseFragment extends Fragment {
    FConfig fConfig;
    Unbinder unbinder;
    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fConfig = getClass().getAnnotation(FConfig.class);
        if (fConfig == null) {
            fConfig = BaseFragment.class.getAnnotation(FConfig.class);
        }
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
    }

    /**
     * initialize view
     * 初始化视图
     */
    protected void initView() {
    }

    /**
     * initialize data
     * 初始化数据
     */
    protected void initData() {
    }
}

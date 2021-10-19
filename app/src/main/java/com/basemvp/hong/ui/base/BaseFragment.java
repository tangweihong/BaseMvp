package com.basemvp.hong.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.basemvp.hong.mvp.view.IBaseView;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.utils.DialogUtils;
import com.basemvp.hong.utils.ToastUtils;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by Hong on 2020/4/13 12:03.
 */
public class BaseFragment extends RxFragment implements IBaseView {
    private FConfig fConfig;
    protected Context mContext;
    private Unbinder mUnbinder;
    private boolean isCreated;

    protected boolean isPageShow = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fConfig = getClass().getAnnotation(FConfig.class);
        if (fConfig == null) {
            fConfig = BaseFragment.class.getAnnotation(FConfig.class);
        }
        mContext = getActivity();
        isCreated = true;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getConfigLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);
        //初始化状态栏
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
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroyView() {
        isLoaded = false;
        super.onDestroyView();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


    @Override
    public void onGetData() {

    }

    boolean isLoaded = false;


    @Override
    public void onResume() {
        super.onResume();
        if (!isLoaded && !isHidden()) {
            lazyData();
            isLoaded = true;
        }
    }

    public void lazyData() {
    }

    @Override
    public void loadingFinish() {
        DialogUtils.dismiss();
    }


    @Override
    public void LoadingError(String msg, int code) {
        showToast(msg);
        loadingFinish();
    }

    /**
     * Show toast .
     *
     * @param s s
     */
    public void showToast(String s) {
        if (!TextUtils.isEmpty(s)) {
            ToastUtils.show(s);
        }
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog(true);
    }

    @Override
    public void showLoadingDialog(boolean isCancelable) {
        showLoadingDialog(isCancelable, "");
    }

    @Override
    public void showLoadingDialog(boolean isCancelable, String msg) {
        DialogUtils.showLoadingDialog(mContext, msg, isCancelable);
    }

}

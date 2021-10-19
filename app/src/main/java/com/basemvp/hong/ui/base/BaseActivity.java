package com.basemvp.hong.ui.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.basemvp.hong.R;
import com.basemvp.hong.common.Constants;
import com.basemvp.hong.mvp.view.IBaseView;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.utils.DialogUtils;
import com.basemvp.hong.utils.ScreenUtils;
import com.basemvp.hong.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.tencent.mmkv.MMKV;

import java.util.Locale;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by Hong on 2020/4/13 12:02
 * .
 */
public class BaseActivity extends RxAppCompatActivity implements IBaseView {
    protected FConfig fConfig;
    private Unbinder mUnBinder;
    protected Context mContext;
    //Toolbar
    protected Toolbar vToolbar;
    protected AppBarLayout vAppBarLayout;
    protected TextView vTitle;
    protected TextView vRightText;

    protected boolean isPageShow = true;
    protected MMKV mmkv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mmkv = MMKV.defaultMMKV();
        setLanguage();
        fConfig = getClass().getAnnotation(FConfig.class);
        if (fConfig == null) {
            fConfig = BaseActivity.class.getAnnotation(FConfig.class);
        }
        setContentView(getConfigLayoutRes());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initImmersion();

        if (!getConfigHideToolbar()) {
            setupActionBar();
        }
        initView(savedInstanceState);
        initData();
        onGetData();
    }

    /**
     * 语言设置
     */
    protected void setLanguage() {
        Locale myLocale = new Locale(mmkv.decodeString(Constants.LANGUAGE, "zh"));
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        res.updateConfiguration(conf, dm);
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected void initImmersion() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init();
    }


    /**
     * 初始化视图
     */
    protected void initView(Bundle savedInstanceState) {
    }

    /**
     * 初始化数据
     */
    protected void initData() {
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
     * get {@link FConfig#hideToolbar()}
     *
     * @return boolean
     */
    protected boolean getConfigHideToolbar() {
        return fConfig.hideToolbar();
    }


    /**
     * 设置Toolbar 信息
     */
    protected void setupActionBar() {
        vAppBarLayout = findViewById(R.id.appbar_layout);
        vToolbar = findViewById(R.id.toolbar);
       ImageView vBarBack = findViewById(R.id.bar_back);
        if (fConfig.navigationIcon() != 0) {
            vBarBack.setImageResource(fConfig.navigationIcon());
            vBarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        vTitle = (TextView) findViewById(R.id.bar_title);
        setTitle(fConfig.title() == 0 ? "" : getString(fConfig.title()));
        if (!TextUtils.isEmpty(fConfig.rightText())) {
            setRightText(fConfig.rightText(), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRightOnClickListener();
                }
            });
        }
        if (fConfig.rightImage() != 0) {
            setRightImage(fConfig.rightImage(), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRightOnClickListener();
                }
            });
        }
        ImmersionBar.setTitleBar(this, vToolbar);
    }

    protected void setRightOnClickListener() {

    }

    /**
     * set title text
     *
     * @param title
     */
    protected void setTitle(String title) {
        vTitle = (TextView) findViewById(R.id.bar_title);
        vTitle.setText(title);
    }

    public void setRightText(String text, View.OnClickListener onClickListener) {
        setRightText(text, R.color.font_text_color, onClickListener);
    }

    /**
     * set right text
     *
     * @param text            text
     * @param color           color
     * @param onClickListener onClickListener
     */
    public void setRightText(String text, int color, View.OnClickListener onClickListener) {
        vRightText = (TextView) findViewById(R.id.bar_right_text);
        vRightText.setVisibility(View.VISIBLE);
        vRightText.setText(text);
        vRightText.setTextColor(ContextCompat.getColor(this, color));
        vRightText.setOnClickListener(onClickListener);
    }

    /**
     * set right url
     *
     * @param url             url
     * @param onClickListener onClickListener
     */
    public void setRightImage(int url, View.OnClickListener onClickListener) {
        ImageView view = (ImageView) findViewById(R.id.bar_right);
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(onClickListener);
        Glide.with(this).load(url).into(view);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        isPageShow = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPageShow = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    /**
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * @param cls
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * @param cls
     * @param bundle
     */
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 动画跳转
     *
     * @param cls     目标activity
     * @param animIn  进入动画
     * @param animOut 退出动画
     */
    public void startActivityAnim(Class<?> cls, int animIn, int animOut) {
        startActivityAnim(cls, null, animIn, animOut);
    }

    public void startActivityAnim(Class<?> cls, Bundle bundle, int animIn, int animOut) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(animIn, animOut);
    }


    public void onGetData() {

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
        showLoadingDialog(false);
    }

    @Override
    public void showLoadingDialog(boolean isCancelable) {
        showLoadingDialog(isCancelable, "");
    }

    @Override
    public void showLoadingDialog(boolean isCancelable, String msg) {
        DialogUtils.showLoadingDialog(mContext, msg, isCancelable);
    }


    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                ScreenUtils.hideKeyboard(ev, view, BaseActivity.this);//调用方法判断是否需要隐藏键盘
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}

package com.basemvp.hong.ui.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.basemvp.hong.R;

/**
 * Create by Hong on 2020/4/14 10:19.
 */
public class BaseToolBarActivity extends BaseActivity{

    View baseView;
    View contentView;
    private int layoutResIDs;
    @Override
    protected void initToolbar() {


    }
    /**
     * custom base view toolbar add frame layout child view
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        baseView = LayoutInflater.from(this).inflate(R.layout.base_activity, null, false);
        this.layoutResIDs = layoutResID;
        contentView = getLayoutInflater().inflate(layoutResIDs, null, false);
        FrameLayout framelayout = baseView.findViewById(R.id.content_view);
        framelayout.addView(contentView);
        super.setContentView(baseView);

    }
}

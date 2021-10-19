package com.basemvp.hong.adapter;


import com.basemvp.hong.ui.base.BaseFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Created by hong on 2021/3/27 15:14.
 */
public class MyFragmentStateAdapter extends FragmentStateAdapter {
    private List<BaseFragment> fragments;


    public MyFragmentStateAdapter(@NonNull BaseFragment fragment, List<BaseFragment> list) {
        super(fragment);
        this.fragments = list;
    }
    public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, List<BaseFragment> list) {
        super(fragmentActivity);
        this.fragments = list;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

}

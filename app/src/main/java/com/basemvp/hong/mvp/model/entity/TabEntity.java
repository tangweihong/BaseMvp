package com.basemvp.hong.mvp.model.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hong on 2021/10/18 11:08.
 */
public class TabEntity implements CustomTabEntity {

    String title;
    int selectedIcon = 0;
    int unSelectedIcon = 0;

    public TabEntity(int unSelect, int select, String title) {
        this.title = title;
        this.selectedIcon = select;
        this.unSelectedIcon = unSelect;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}

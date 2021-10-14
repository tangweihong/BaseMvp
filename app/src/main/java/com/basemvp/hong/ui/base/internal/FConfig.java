package com.basemvp.hong.ui.base.internal;

import com.basemvp.hong.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by Hong on 2020/4/13 12:04.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FConfig {

    /**
     * @return 是否隐藏toolbar 默认false显示
     */
    boolean hideToolbar() default false;

    /**
     * @return layout res id
     */
    int value();

    /**
     * Toolbar 返回 Icon
     *
     * @return
     */
    int navigationIcon() default 0;

    /**
     * @return title 标题
     */
    String title() default "";

    /**
     * @return 右边文字
     */
    String rightText() default "";

    /**
     * @return 右边图片
     */
    int rightImage() default 0;
}

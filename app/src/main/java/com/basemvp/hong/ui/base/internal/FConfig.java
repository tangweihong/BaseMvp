package com.basemvp.hong.ui.base.internal;

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
     * layout resource id
     *
     * @return  layout res id
     */
    int value();

    /**
     * loading
     *
     * @return is need loading
     */
    boolean loading() default true;

    /**
     * title
     *
     * @return
     */
    String title() default "";

    /**
     * @return toolbar return res id
     */
    int navigationIcon() default 0;
}

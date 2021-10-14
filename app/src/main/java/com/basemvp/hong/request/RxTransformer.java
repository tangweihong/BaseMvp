package com.basemvp.hong.request;



import com.basemvp.hong.mvp.view.IBaseView;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxTransformer {

    /**
     * 无参数  仅做切换线程
     *
     * @param <T> 泛型
     * @return 返回Observable
     */
    public static <T> ObservableTransformer<T, T> transform() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 界面请求，不需要加载和隐藏loading时调用 使用RxLifeCycle
     * 传入view接口，Activity，Fragment等实现了view接口，Activity，Fragment继承于{@link com.trello.rxlifecycle2.components.support.RxAppCompatActivity}
     * 也就实现了bindToLifecycle方法
     *
     * @param view View
     * @param <T>  泛型
     * @return
     */
    public static <T> ObservableTransformer<T, T> transform(final IBaseView view) {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(view.bindToLifecycle());
    }


    /**
     * 界面请求，需要加载和隐藏loading时调用,使用RxLifeCycle
     * 传入view接口，Activity，Fragment等实现了view接口，Activity，Fragment继承于{@link com.trello.rxlifecycle2.components.support.RxAppCompatActivity}
     * 也就实现了bindToLifecycle方法
     *
     * @param view View
     * @param <T>  泛型
     * @return
     */
    public static <T> ObservableTransformer<T, T> transformWithLoading(final IBaseView view) {
        //隐藏进度条
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
//                    view.loading();//显示进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(2, TimeUnit.SECONDS)
                .doFinally(view::loadingFinish)
                .compose(view.bindToLifecycle());
    }
    /**
     * 界面请求，需要加载和隐藏loading时调用,使用RxLifeCycle
     * 传入view接口，Activity，Fragment等实现了view接口，Activity，Fragment继承于{@link com.trello.rxlifecycle2.components.support.RxAppCompatActivity}
     * 也就实现了bindToLifecycle方法
     *
     * @param view View
     * @param <T>  泛型
     * @return
     */
    public static <T> ObservableTransformer<T, T> transformWithLoadingDialog(final IBaseView view) {
        //隐藏进度条
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    view.showLoadingDialog();//显示进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(2, TimeUnit.SECONDS)
                .doFinally(view::loadingFinish)
                .compose(view.bindToLifecycle());
    }


}

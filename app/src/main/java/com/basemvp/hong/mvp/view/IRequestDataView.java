package com.basemvp.hong.mvp.view;

/**
 * IRequestDataView
 * Created by Hong on 2019/6/21.
 * Version:1
 */

public interface IRequestDataView<T> extends IBaseView {
	/**
	 * return data
	 * @param data
	 */
	void onFillData(T data);
}

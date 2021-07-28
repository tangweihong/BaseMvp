package com.basemvp.hong.mvp.view;

/**
 * IRequestDataView
 * Created by Lokiy on 2017/6/21.
 * Version:1
 */

public interface IRequestDataView<T> extends IBaseView {
	/**
	 * return data
	 * @param data
	 */
	void onFillData(T data);
}

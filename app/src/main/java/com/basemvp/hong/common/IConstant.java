/*
 *
 * Copyright (C) 2018 Hong(tangweihong1069@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  　　　　http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.basemvp.hong.common;

/**
 * ICons
 * Created by Lokiy on 2015/9/15.
 * Version:1
 */
public interface IConstant {

	/**
	 * 登录
	 */
	String BROADCAST_LOGIN = "com.hong.baseproject.BROADCAST_LOGIN";
	/**
	 * 退出登录
	 */
	String BROADCAST_LOGOUT = "com.hong.baseproject.BROADCAST_LOGOUT";
	/**
	 * 首页Tab显示new
	 */
	String BROADCAST_SHOW_BADGE = "com.hong.baseproject.BROADCAST_BADGE";
	/**
	 * 切换首页Tab
	 */
	String BROADCAST_SWITCH_TAB = "com.hong.baseproject.BROADCAST_SWITCH_TAB";
	/**
	 * 切换首页Tab
	 */
	String BROADCAST_REFRESH_DATA = "com.hong.baseproject.BROADCAST_REFRESH_DATA";
	double INTEREST_PER_SECOND = 365d * 24d * 3600d;
	long SECOND = 1000;
	long MINUTE = SECOND * 60;
	long HOUR = MINUTE * 60;
	long DAY = HOUR * 24;
	long MONTH = DAY * 30;
	long YEAR = MONTH * 12;
	int REQUEST_LOGIN 				= 0x000000FF;
	int REQUEST_REFRESH 			= 0x00000F05;//刷新
}

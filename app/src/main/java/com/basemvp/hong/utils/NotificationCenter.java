package com.basemvp.hong.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 观察者模式
 * 使用方法：在application中初始化：NotificationCenter.defaultCenter();
 * 在需要回调的地方 订阅观察者：
 * NotificationCenter.defaultCenter.addObserver(观察者对象, "观察者事件名字");
 * 在回调的界面里解除观察者：
 * NotificationCenter.defaultCenter.removeObserver(观察者对象, "观察者事件名字");
 * 发送全局事件，让所有已经订阅观察者事件的内容同时执行：
 * NotificationCenter.defaultCenter.postNotification("事件名称", 事件内容可以为null);
 * Created by yxt on 2016/10/28.
 */
public class NotificationCenter {
    public interface NotificationCenterObserver{
        void onReceive(String eventName, Object userInfo);
    }
    public static NotificationCenter defaultCenter;
    public static NotificationCenter defaultCenter(){
        if (defaultCenter== null){
            defaultCenter = new NotificationCenter();
        }
        return defaultCenter;
    }
    public Map<String,List<NotificationCenterObserver>> observerMap = new HashMap<String, List<NotificationCenterObserver>>();

    /**
     * 订阅观察者
     * @param who 谁来处理（观察者对象）
     * @param eventName 观察事件名字
     */
    public void addObserver(NotificationCenterObserver who,String eventName){
        if (observerMap.containsKey(eventName)){
            List<NotificationCenterObserver> list = observerMap.get(eventName);
            list.add(who);
        }
        else{
            List<NotificationCenterObserver> list = new ArrayList<NotificationCenterObserver>();
            list.add(who);
            observerMap.put(eventName, list);
        }
    }

    /**
     * 全局通知观察者事件（让所有已经订阅事件的页面同时受到消息）
     * @param eventName 被订阅的事件名字
     * @param userInfo  被订阅的事件传递过去的参数
     */
    public void postNotification(String eventName,Object userInfo){
        if (observerMap.containsKey(eventName)){
            List<NotificationCenterObserver> list = observerMap.get(eventName);
            for (int i=0;i<list.size();i++){
                NotificationCenterObserver notificationCenterObserver = list.get(i);
                notificationCenterObserver.onReceive(eventName, userInfo);
            }
        }
    }

    /**
     * 解除观察者事件（如果订阅的话 必须解除 否则容易引起内存泄漏）
     * @param who  观察者对象
     * @param eventName    要解除的观察者名字
     */
    public void removeObserver(NotificationCenterObserver who,String eventName) {
        // TODO Auto-generated method stub
        List<NotificationCenterObserver> list = observerMap.get(eventName);
        try {
            list.remove(who);
        }catch (Exception e){
        }
    }
}

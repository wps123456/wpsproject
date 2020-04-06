package com.wps.studyplatform.designpattern.singleton.sigleton;

public class SingletonHunger {
    /**
     * 单例模式-饿汉模式
     */
    //直接创建对象
    public static SingletonHunger singletonHunger = new SingletonHunger();
    //私有化构造函数
    private SingletonHunger(){
    }
    //返回对象实例
    public static SingletonHunger newInstance(){
        return singletonHunger;
    }
}

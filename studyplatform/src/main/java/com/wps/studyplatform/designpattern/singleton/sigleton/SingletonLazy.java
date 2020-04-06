package com.wps.studyplatform.designpattern.singleton.sigleton;

public class SingletonLazy {
    /**
     * 单例模式-懒汉模式，
     * 应用案例：访问次数，每次访问加1
     */
    private int count; //记录访问次数
    private static SingletonLazy singletonLazy = null;
    private SingletonLazy(){}
    public static SingletonLazy newInstance(){
        if(null == singletonLazy){
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}



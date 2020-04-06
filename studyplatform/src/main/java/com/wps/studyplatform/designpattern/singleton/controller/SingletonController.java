package com.wps.studyplatform.designpattern.singleton.controller;

import com.wps.studyplatform.designpattern.singleton.sigleton.SingletonLazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/single")
public class SingletonController {
    /**
     * 只用实例化对象一次，在整个容器中，只存在这个对象一个
     * 1、每一次set值
     * 2、如果在其他方法中应用到了这个对象，则直接可以获得之前set的值
     * @param id
     * @return
     */
    @GetMapping("/lazy/{id}")
    public int visit(@PathVariable int id){
        SingletonLazy singletonLazy=SingletonLazy.newInstance();
        singletonLazy.setCount(id);
        return id;
    }
    @GetMapping("/getVisitCount")
    public int getVisitCount () {
        SingletonLazy singletonLazy=SingletonLazy.newInstance();
        int visitCount=singletonLazy.getCount();
        return visitCount;
    }

}

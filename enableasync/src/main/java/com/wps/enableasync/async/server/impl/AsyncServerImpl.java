package com.wps.enableasync.async.server.impl;

import com.wps.enableasync.async.server.AsyncServer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * @Title AsyncServerImpl
 * @Description
 * @auther wps
 * @Date 2020/6/3014:45
 */
@Service
public class AsyncServerImpl implements AsyncServer {
    @Override
    @Async("asyncException")
    public String checkName() throws InterruptedException {

        System.out.println("=========开始进入休眠============");
        System.out.println("新的线程名"+Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("=========休眠结束============");
        return "true====";

    }

    @Override
    @Async("asyncException")
    public CompletableFuture<String> checkIP()  {
        System.out.println("=========开始进入休眠===========");
        System.out.println("新的线程名"+Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value="true";
        System.out.println("=========休眠结束===========");

        return completedFuture(value);
    }
}

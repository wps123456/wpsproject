package com.wps.enableasync.async.ccontroller;

import com.wps.enableasync.async.server.AsyncServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * @Title AsyncController
 * @Description
 * @auther wps
 * @Date 2020/6/3014:43
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncServer asyncServer;

    /**
     * 异步调用不用关心返回值
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/checkName")
    public String CheckName() throws InterruptedException {

        System.out.println("主线程名"+Thread.currentThread().getName());
        String value=asyncServer.checkName();
        System.out.println(value);
        return value;

    }

    /**
     * 异步执行，开启一个新线程去执行内容，同时主线程继续进行。
     * 最后将结果汇总，返回值
     * @return
     */
    @GetMapping("/checkIp")
    public CompletableFuture<String> checkIp() throws ExecutionException, InterruptedException {
        System.out.println("主线程名"+Thread.currentThread().getName());
        CompletableFuture<String> value1=asyncServer.checkIP().thenApplyAsync(re-> re+"test");
        CompletableFuture<String> value2=asyncServer.checkIP().thenApplyAsync(re-> re);
        return completedFuture(value1.get()+value2.get());


    }

}

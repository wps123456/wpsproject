package com.wps.enableasync.async.server;

import java.util.concurrent.CompletableFuture;

/**
 * @Title AsyncServer
 * @Description
 * @auther wps
 * @Date 2020/6/3014:43
 */
public interface AsyncServer {
    public String checkName() throws InterruptedException;
    public CompletableFuture<String> checkIP() ;
}

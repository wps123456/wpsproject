package com.wps.studyplatform.optional;

/**
 * @Title Demo1
 * @Description
 * @auther wps
 * @Date 2020/5/1419:21
 */
public class Demo1 {
    public static void main(String[] args) {
        String str = "10.121.69.228;node227.hde.com";

        String ip=str.split(";")[0];
        System.out.println(ip);


    }
}

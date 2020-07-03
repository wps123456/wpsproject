package com.wps.studyplatform.objectargs;

import java.util.Arrays;
import java.util.List;

/**
 * @Title ObjectOrgsController
 * @Description
 * @auther wps
 * @Date 2020/7/317:46
 */
public class ObjectOrgsController {
    public static void main(String[] args) {
        checkName("tett",1L);
       // checkName("name","tett",1L);
    }




    public static void checkName(Object... args){
        System.out.println(args.length);
        System.out.println(args);
        List sss=Arrays.asList(args);
        for (Object listTest : sss ) {
            System.out.println(listTest);

        }


    }
}

package com.wps.studyplatform.jsonexchange.controller;

import com.wps.studyplatform.jsonexchange.entity.Share;
import com.wps.studyplatform.jsonexchange.entity.SysUserEntity;
import com.wps.studyplatform.jsonexchange.utils.JsonUtils;

import java.util.HashMap;
import java.util.Optional;

/**
 * @Title JsonToEntity
 * @Description
 * @auther wps
 * @Date 2020/7/1419:19
 */
public class JsonToEntity {

    public static void main(String[] args) {
        //调用其他服务的接口时，返回的数据类型为：HashMap，其中保存的为<String,List>
        /**
         * 裁controoler层接收时可以使用Object，解决方法有两个
         * 1：使用JsonUtils.readValue转成HashMap对象
         *  Object a="";
         *  Optional<HashMap> shareOptional=JsonUtils.readValue(a.toString(),HashMap.class);
         * 2：强转成HashMap对象：
         * Object a="";
         * HashMap<String,Object> hashMap= (HashMap<String, Object>) a;
         *
         */


        String json="{\"type\":\"1\",\"title\":\"母亲节\",\"url\":\"\",\"content\":\"\",\"endTime\":\"\",\"questionModels\":[{\"title\":\"标题一\",\"type\":2,\"options\":[{\"option\":\"选项一\"},{\"option\":\"选项二\"}]}]}";
        Optional<Share> shareOptional=JsonUtils.readValue(json,Share.class);
        Share share=shareOptional.get();
        System.out.println(share);

        //只取出一部分值

       Optional<SysUserEntity> sysUserEntityOptional= JsonUtils.readValue(json,SysUserEntity.class);
       SysUserEntity sysUserEntity=sysUserEntityOptional.get();
       System.out.println(sysUserEntity);

    }
}

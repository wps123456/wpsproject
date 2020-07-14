package com.wps.studyplatform.jsonexchange.controller;

import com.wps.studyplatform.jsonexchange.entity.Share;
import com.wps.studyplatform.jsonexchange.entity.SysUserEntity;
import com.wps.studyplatform.jsonexchange.utils.JsonUtils;

import java.util.Optional;

/**
 * @Title JsonToEntity
 * @Description
 * @auther wps
 * @Date 2020/7/1419:19
 */
public class JsonToEntity {

    public static void main(String[] args) {

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

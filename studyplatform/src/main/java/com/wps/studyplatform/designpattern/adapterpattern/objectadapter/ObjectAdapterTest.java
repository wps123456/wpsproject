package com.wps.studyplatform.designpattern.adapterpattern.objectadapter;

import org.springframework.beans.factory.annotation.Autowired;

public class ObjectAdapterTest {

    public static void main(String[] args) {
        //多态
        ISiginSerevice siginSerevice=new SiginService();
        ObjectSiginForWebChatAdapter objectSiginForWebChatAdapter=new ObjectSiginForWebChatAdapter(siginSerevice);
        ResultMsg resultMsg=objectSiginForWebChatAdapter.loginForWechat("微信登录");
        ResultMsg resultMsg1= objectSiginForWebChatAdapter.loginISigin("正常登录","111");
        System.out.println(resultMsg);
    }
}

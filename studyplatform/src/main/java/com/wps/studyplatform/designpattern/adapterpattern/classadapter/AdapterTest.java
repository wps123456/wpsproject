package com.wps.studyplatform.designpattern.adapterpattern.classadapter;

/**
 * 适配器模式
 */
public class AdapterTest {
    public static void main(String[] args) {
        ISiginForWebChat iSiginForWebChat=new ClassSiginForWebChatAdapter();
        ResultMsg resultMsg=iSiginForWebChat.loginForWechat("微信登录");

        ISiginSerevice iSiginSerevice=new ClassSiginForWebChatAdapter();
        ResultMsg resultMsg1=iSiginSerevice.login("正常登录","111");

        System.out.println(resultMsg);

    }
}

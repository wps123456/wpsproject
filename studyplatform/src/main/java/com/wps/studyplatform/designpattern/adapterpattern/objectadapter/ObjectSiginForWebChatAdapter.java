package com.wps.studyplatform.designpattern.adapterpattern.objectadapter;

import org.springframework.beans.factory.annotation.Autowired;

public class ObjectSiginForWebChatAdapter implements ISiginForWebChat{

    private ISiginSerevice iSiginSerevice;
    public ObjectSiginForWebChatAdapter(ISiginSerevice iSiginSerevice){
        this.iSiginSerevice=iSiginSerevice;
    }
    public ResultMsg loginISigin(String username,String password){
           return iSiginSerevice.login(username,password);
    }

    @Override
    public ResultMsg loginForWechat(String openId) {

        return new ResultMsg("200","登录成功",openId);
    }
}

package com.wps.studyplatform.designpattern.adapterpattern.classadapter;

public class ClassSiginForWebChatAdapter extends SiginService implements ISiginForWebChat {
    @Override
    public ResultMsg loginForWechat(String openId) {
        return new ResultMsg("200","登录成功",openId);
    }
}

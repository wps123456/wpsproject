package com.wps.studyplatform.designpattern.adapterpattern.classadapter;

/**
 * @Description: 登录的具体实现
 * @Param:
 * @return:
 */
public class SiginService implements ISiginSerevice {
    /**
     * 登录的方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg login(String username,String password){
        return  new ResultMsg("200","登录成功",username);
    }
}

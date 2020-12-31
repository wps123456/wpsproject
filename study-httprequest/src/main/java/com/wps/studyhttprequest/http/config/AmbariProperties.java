package com.wps.studyhttprequest.http.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Title AmbariProperties
 * @Description
 * @auther wps
 * @Date 2020/12/3020:12
 */

/**
 * 注解的含义时将配置转成bean，
 */
@ConfigurationProperties(prefix = "ambari")
public class AmbariProperties {
    private String port;
    private String user;
    private String password;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

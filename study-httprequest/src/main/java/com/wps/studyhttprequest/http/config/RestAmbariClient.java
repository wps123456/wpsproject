package com.wps.studyhttprequest.http.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Title RestAmbariClient
 * @Description
 * @auther wps
 * @Date 2020/12/3020:04
 */
@Configuration
@EnableConfigurationProperties(AmbariProperties.class)
public class RestAmbariClient {

    private static final String baseUrl = "http://%s:%s:/v1/cluster/%s";

    @Autowired
    private RestTemplate restTemplate;

    private String amabriPort;
    private String ambariUser;
    private String ambariPassword;
    private String userInfo;

    public RestAmbariClient(AmbariProperties ambariProperties) {
        this.amabriPort = ambariProperties.getPort();
        this.ambariUser = ambariProperties.getUser();
        this.ambariPassword = ambariProperties.getPassword();
        this.userInfo = "Basic "+ new String(Base64.encodeBase64((this.ambariUser+":"+this.ambariPassword).getBytes()));
    }

    public String getHostInfo(String ip ,String clusterName,String jsonString,Boolean sendRequest){

        if (sendRequest) {
            String ambariUrl = String.format(baseUrl, ip, amabriPort, clusterName);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", this.userInfo);
            httpHeaders.set("X-Requested-By", "ambari");
            //无请求参数
            HttpEntity httpEntity = new HttpEntity(httpHeaders);
            //发起请求
            jsonString = restTemplate.exchange(ambariUrl, HttpMethod.GET, httpEntity, String.class).getBody();
        }
/*        {
            "href":"http://10.121.11.2",
                "items":[
            {
                "href":"http://10.121.22.2",
                    "Hosts":{
                "cluster_name":"test001",
                        "host_name":"test00000000",
                        "host_state":"ON"
            },
                "host_components":[
                {
                    "href":"http://10.121.22.2",
                        "HostRoles":{
                             "cluster_name":"test001",
                            "host_name":"test00000000",
                            "host_state":"ONwwwwww"
                }
                }
            ]
            }
    ]
        }*/
        //解析获取的请求内容，这里使用string接受，转成JSON对象，然后再解析
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        //将items解析出
        List items = (List) jsonObject.get("items");
        //items是由list组成,然后将获取出的一个元素装成map类型
        String hostName=null;
        for(int i=0;i<items.size();i++){
            Map<String,Object> clusterInfo = (Map<String, Object>) items.get(i);
            String href = clusterInfo.get("href").toString();
            List hostsInfo = (List) clusterInfo.get("host_components");
            for(int j=0;j<hostsInfo.size();j++){
                Map<String,Object> hrefClusterName = (Map<String, Object>) hostsInfo.get(j);
                Map<String,String> clusterNameInfo = (Map<String, String>) hrefClusterName.get("HostRoles");
                 hostName = clusterNameInfo.get("host_name");
            }
        }
        return hostName;

    }
}

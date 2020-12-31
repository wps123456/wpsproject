package com.wps.studyhttprequest.http.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wps.studyhttprequest.http.config.RestAmbariClient;
import com.wps.studyhttprequest.http.entity.PlatformEntity;
import com.wps.studyhttprequest.http.entity.ServerEntity;
import com.wps.studyhttprequest.http.service.HttpControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Title HttpControllerServiceImpl
 * @Description
 * @auther wps
 * @Date 2020/12/2821:41
 */
@Service
public class HttpControllerServiceImpl implements HttpControllerService {
    public static final Logger logger = LoggerFactory.getLogger(HttpControllerServiceImpl.class);

    @Autowired
    public RestTemplate restTemplate;

    @Value("${jsontext.json.url}")
    private String getJsonUrl;

    @Value("${jsontext.name.url}}")
    private String getNameUrl;

    @Value("${jsontext.ambari.url}")
    private String getAmbariUrl;

    @Autowired
    RestAmbariClient restAmbariClient;
    @Override
    public String getStringText() {
        String jsonString = restTemplate.getForObject(getJsonUrl,String.class);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        ServerEntity serverEntity = JSON.toJavaObject(jsonObject,ServerEntity.class);
        serverEntity.getInstance();
        logger.info("实例名称为{}",serverEntity.getInstance());
        return jsonString;
    }

    @Override
    public String getResult() {
        PlatformEntity platformEntity = new PlatformEntity("数据库","000");
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<PlatformEntity> request = new HttpEntity<>(platformEntity,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(getNameUrl,request,String.class);
        return responseEntity.getBody();
    }

    @Override
    public String getHostInfo() {
        String jsonString = restTemplate.getForObject(getAmbariUrl,String.class);
        String hostName = restAmbariClient.getHostInfo("10.121.10","test001",jsonString,false);
        return hostName;
    }
}

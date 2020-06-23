package com.wps.studyplatform.httpResponse.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
public class CheckLicenseAspect {
    @Autowired
    private RestTemplate restTemplate;
    @Pointcut("@annotation(com.wps.studyplatform.httpResponse.annotation.CheckLicense)")
    public void LicensePointCut(){

    }
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url="";
        HttpHeaders headers=new HttpHeaders();
        headers.add("Cookie",request.getHeader("Cookie"));
        HttpMethod method=HttpMethod.GET;
        HttpEntity<JSONObject> httpEntity=new HttpEntity<>(null,headers);
        ResponseEntity<Map> responseEntity=restTemplate.exchange(url,method,httpEntity,Map.class);
        Map<String, List> LicensesMap=responseEntity.getBody();
        List<Map<String,Object>> LicensesList=LicensesMap.get("Licenses");
        List<Map<String,Object>> sqlServerLicense=LicensesList.stream().filter(LicenseFeture->LicenseFeture.get("feature").equals("sql")).collect(Collectors.toList());


    }

}

package com.wps.studyjsontext.service.Impl;

import com.wps.studyjsontext.service.JsonControllerService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.ExecutionException;

/**
 * @Title JsonControllerServiceImpl
 * @Description
 * @auther wps
 * @Date 2020/12/2822:17
 */
@Service
public class JsonControllerServiceImpl implements JsonControllerService {
    @Override
    public String getJsonBody() {
        String jsonBody = getRegisterContent("type_name.json");
        return jsonBody;
    }

    @Override
    public String getAmbariBody() {
        String jsonBody = getRegisterContent("host_info.json");
        return jsonBody;
    }

    public static String getRegisterContent(String fileName){
        String jsonStr = "";
        try{
            Resource resource = new ClassPathResource(fileName);
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is,"UTF-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch=reader.read()) !=-1){
                sb.append((char) ch);
            }
            is.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        }catch (Exception e){
            return null;
        }
    }
}

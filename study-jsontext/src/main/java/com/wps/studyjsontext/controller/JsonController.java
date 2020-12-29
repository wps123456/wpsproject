package com.wps.studyjsontext.controller;

import com.wps.studyjsontext.entity.PlatformEntity;
import com.wps.studyjsontext.service.JsonControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title JsonController
 * @Description
 * @auther wps
 * @Date 2020/12/2822:15
 */
@RestController
@RequestMapping("/json")
public class JsonController {
    @Autowired
    private JsonControllerService jsonControllerService;

    @GetMapping("/getJson")
    public String getJson(){
        String jsonString = jsonControllerService.getJsonBody();
        return jsonString;

    }
    @PostMapping("/getName")
    public String getName(@RequestBody PlatformEntity platformEntity){
        String name = platformEntity.getName();
        return name;
    }

}

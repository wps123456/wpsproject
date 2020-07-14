package com.wps.studyplatform.jsonexchange.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @Title JsonUtils
 * @Description
 * @auther wps
 * @Date 2020/7/1419:28
 */
public class JsonUtils {
    private static final Logger logger=LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 避免重复的异常处理
     */
    public static <T>Optional<T> readValue(String json,Class<T> t){
        Optional<T> r;
        try {
            r=Optional.ofNullable(Json.mapper().readValue(json,t));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("失败",json);
            r=Optional.empty();
        }
        return r;
    }
    public static <T>Optional<T> readValue(JsonNode json, Class<T> t){
        Optional<T> r;
        try {
            r=Optional.ofNullable(Json.fromJson(json,t));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("失败",json);
            r=Optional.empty();
        }
        return r;
    }
    public static <T> T readValue(String json,TypeReference<T> typeReference){
        Optional<T> r;
        try {
            r=Optional.ofNullable(Json.mapper().readValue(json,typeReference));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("失败",json);
            r=Optional.empty();
        }
        return r.orElseThrow(()->new RuntimeException(""));
    }
    public static <T> T readValue(JsonNode json,TypeReference<T> typeReference){
        if (json==null){
            throw new RuntimeException("reeor");
        }
        return readValue(json.toString(),typeReference);
    }
}

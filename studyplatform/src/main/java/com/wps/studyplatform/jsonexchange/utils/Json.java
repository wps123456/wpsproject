package com.wps.studyplatform.jsonexchange.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @Title Json
 * @Description
 * @auther wps
 * @Date 2020/7/1419:34
 */
public class Json {
    private static final ObjectMapper defaultObjectMapper=newDefaultMapper();
    private static volatile ObjectMapper objectMapper=null;
    public static ObjectMapper newDefaultMapper(){
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return mapper;
    }

    public static ObjectMapper mapper(){
        if (objectMapper==null){
            return defaultObjectMapper;
        }else {
            return objectMapper;
        }
    }

    private static String generateJson(Object o, boolean prettyPrint,boolean escapeNonASSCII){

    }
    public static <A> A fromJson(JsonNode json, Class<A> t) {
        try {
            return mapper().treeToValue(json,t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

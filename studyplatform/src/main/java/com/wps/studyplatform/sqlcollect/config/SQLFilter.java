package com.wps.studyplatform.sqlcollect.config;

/**
 * @Title SQLFilter
 * @Description
 * @auther wps
 * @Date 2020/7/120:11
 */

import com.wps.studyplatform.exception.base.BaseException;
import com.wps.studyplatform.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * SQL过滤 防止注入
 */
public class SQLFilter {

    public static  String sqlInject(String str){
        if (StringUtils.isBlank(str)){
            return null;
        }
        str=StringUtils.replace(str,"'","");
        str=StringUtils.replace(str,"\"","");
        str=StringUtils.replace(str,";","");
        str=StringUtils.replace(str,"\\","");
        String retStr=str;
        //转换成小写
        str=str.toLowerCase();
        //非法字符
        String[] keywords={"master","truncate","insert","select","delete","update","declare","alert","drop"};
        //判断是否包含非法字符
        for (String keyword:keywords){
            if (str.contains(keyword)){
                throw new BaseException("包含非法字符");
            }
        }

        return StringUtil.humToLine2(retStr);
    }
}

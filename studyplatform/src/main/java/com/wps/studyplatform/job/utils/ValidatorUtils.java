package com.wps.studyplatform.job.utils;

import com.wps.studyplatform.exception.base.BaseException;
import org.apache.commons.lang3.StringUtils;

import javax.sql.rowset.BaseRowSet;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtils {
    private static Validator validator;
    static {
        validator= Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object 待校验对象
     * @param groups 待校验的组
     */
    public static void validateEntity(Object object,Class<?>... groups){
        Set<ConstraintViolation<Object>> constraintViolations=validator.validate(object,groups);
        if (!constraintViolations.isEmpty()){
            StringBuilder msg=new StringBuilder();
            for (ConstraintViolation<Object> constraint:constraintViolations){
                msg.append(constraint.getMessage()).append(",");
            }
            if (msg.length()>0){
                msg.setLength(msg.length()-1);
            }
            throw new BaseException("failcode",msg.toString());
        }
    }
    public static void isBlank(String str,String message){
        if (StringUtils.isBlank(str)){
            throw new BaseException("failcode",message);
        }
    }
    public static void isNull(Object object,String message){
        if (object==null){
            throw new BaseException("failcode",message);
        }
    }
}

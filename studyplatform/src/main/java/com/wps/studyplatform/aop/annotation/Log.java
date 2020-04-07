package com.wps.studyplatform.aop.annotation;

import com.wps.studyplatform.aop.enums.BusinessType;
import com.wps.studyplatform.aop.enums.OperatorType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 使用自定义log时，需要定义一个实体类，对应数据库中的表，用于保存记录日志
     */

    /**
     * 操作类型
     */
    public BusinessType businessType() default BusinessType.OTHER;
    /**
     * 模块
     */
    public String title() default "";
    /**
     * 访问的终端类型
     */
    public OperatorType operatorType() default OperatorType.MOBILE;
    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;


}


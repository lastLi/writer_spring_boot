package com.jiawang.core.annotations.request;

import com.jiawang.core.annotations.bean.components.JiaComponent;
import com.jiawang.eums.JiaRequestType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 14:29
 * 请求url,作用于类上和方法上
 * @author : LiJiWang
 */
@Documented
@JiaComponent
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@JiaMapping
public @interface JiaRequestMapping {

    /**
     *
     * @return 请求路径
     */
    String path() default "";

    /**
     *
     * @return 请求类型
     */
    JiaRequestType method() default JiaRequestType.GET;

}

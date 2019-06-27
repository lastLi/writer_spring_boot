package com.jiawang.core.annotations.request;

import com.jiawang.eums.JiaRequestType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 14:38
 *
 * @author : LiJiWang
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@JiaRequestMapping(method = JiaRequestType.POST)
public @interface JiaPostMapping {
    String value() default "";
}

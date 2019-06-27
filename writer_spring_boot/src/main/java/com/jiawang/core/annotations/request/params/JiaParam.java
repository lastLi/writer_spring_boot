package com.jiawang.core.annotations.request.params;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 15:11
 *
 * @author : LiJiWang
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JiaParam {

    /**
     *
     * @return 会去找指定参数名字的值
     */
    String value() ;

    /**
     *
     * @return 给这个参数一个默认的值
     */
    String defaultValue() default "";
}

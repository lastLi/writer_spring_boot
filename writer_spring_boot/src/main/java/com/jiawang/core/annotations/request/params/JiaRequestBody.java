package com.jiawang.core.annotations.request.params;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 15:14
 *
 * @author : LiJiWang
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JiaRequestBody {

    /**
     *
     * @return 是否必填，默认这个就是必填的
     */
    boolean required() default true;
}

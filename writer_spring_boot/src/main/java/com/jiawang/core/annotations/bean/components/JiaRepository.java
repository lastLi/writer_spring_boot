package com.jiawang.core.annotations.bean.components;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 13:44
 *
 * @author : LiJiWang
 */
@Documented
@JiaComponent
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JiaRepository {

    /**
     *
     * @return 名字
     */
    String value() default "";

}


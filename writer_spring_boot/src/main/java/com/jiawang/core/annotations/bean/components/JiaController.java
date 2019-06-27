package com.jiawang.core.annotations.bean.components;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 13:39
 * 控制类注解，通常用于http接口类上面
 * @author : LiJiWang
 */
@JiaComponent
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JiaController {
    /**
     *
     * @return 名字
     */
    String value() default "";
}

package com.jiawang.core.annotations.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 11:18
 * 包扫描，作用于类上面
 *
 * @author : LiJiWang
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JiaScannerPackage {

    /**
     * @return 需要扫描的包
     */
    String value() default "";


    /**
     *
     * @return 要排除的注解类们
     */
    Class<?>[] excludeAnnotation() default {};

    /**
     *
     * @return 要包扩的注解们
     */
    Class<?>[] includeAnnotation() default {};

}

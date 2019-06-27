package com.jiawang.core.annotations.start;

import com.jiawang.core.annotations.config.JiaScannerPackage;
import com.jiawang.core.annotations.config.JiaSpringBootConfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 17:38
 *
 * @author : LiJiWang
 */
@Documented
@JiaScannerPackage
@JiaSpringBootConfigure
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JiaSpringBoot {
}

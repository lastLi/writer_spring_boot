package com.jiawang.core.annotations.response;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 15:05
 *
 * @author : LiJiWang
 */
@Documented
@Target({ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JiaResponseBody {


}

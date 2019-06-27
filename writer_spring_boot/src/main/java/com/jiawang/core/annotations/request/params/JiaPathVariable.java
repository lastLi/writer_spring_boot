package com.jiawang.core.annotations.request.params;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 14:56
 * 获取路径上面的参数
 * @author : LiJiWang
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JiaPathVariable {

    /**
     *
     * @return 路径参数，如果写的化，必须和mapping表达式的名字对应，不写的化，那么你的参数名字必须对应他和mapping表达式的名字
     */
    String value() default "";
}

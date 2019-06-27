package com.jiawang.util;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 16:08
 * 注解工具类
 *
 * @author : LiJiWang
 */
public class AnnotationUtils {

    /**
     * 递归判断注解是否存在，适用于组合注解。
     * 判断这个类是否有这个注解，如果没有就去这个类上的注解里去找注解，找到了为true，否则false。
     *
     * @param clazz           初始类,(要去递归的注解)
     * @param annotationClass 要找的注解
     * @return 成功 true，否则false
     */
    public static boolean recursiveJudgmentAnnotation(Class<?> clazz,
                                                      Class<? extends Annotation> annotationClass) throws Exception {

        if (clazz.isAnnotationPresent(annotationClass)) {
            return true;
        }

        Set<Annotation> collectSet = Arrays.stream(clazz.getAnnotations()).
                // 排除元注解，避免无限递归
                        filter(iem -> !(iem instanceof Documented)).
                        filter(iem -> !(iem instanceof Target)).
                        filter(iem -> !(iem instanceof Retention))
                .collect(Collectors.toSet());
        //对注解进行遍历
        for (Annotation annotation : collectSet) {
            // 获取注解列表的某个元素转成class
            clazz = annotation.annotationType();
            // 如果这个class不等于我要找的那个注解，那么递归找这个注解里的注解
            if (!clazz.equals(annotationClass)) {
                // 如果你等于true，那么就返回true呗
                if (recursiveJudgmentAnnotation(clazz, annotationClass)) {
                    return true;
                }
            } else {
                // 如果匹配了就返回true
                return true;
            }
        }
        // 如果都遍历完了还没有找到，那么就返回false
        return false;
    }
}

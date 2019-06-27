package com.jiawang.eums;

import java.util.Arrays;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 16:23
 *
 * @author : LiJiWang
 */
public enum JiaAnnotationType {


    /**
     * 普通组件全名
     */
    COM_JIAWANG_CORE_ANNOTATIONS_BEAN_COMPONENTS_JIACOMPONENT,


    /**
     * 控制组件全名
     */
    COM_JIAWANG_CORE_ANNOTATIONS_BEAN_COMPONENTS_JIACONTROLLER,


    /**
     * 数据组件全名
     */
    COM_JIAWANG_CORE_ANNOTATIONS_BEAN_COMPONENTS_JIAREPOSITORY,

    /**
     * 业务组件全名
     */

    COM_JIAWANG_CORE_ANNOTATIONS_BEAN_COMPONENTS_JIACSERVICE,

    /**
     * 复合控制组件全名
     */
    COM_JIAWANG_CORE_ANNOTATIONS_RESPONSE_JIARESTCONTROLLER;


    /**
     * 根据注解名字匹配
     *
     * @param annotationName 注解名字
     * @return 匹配成功就是true，否则false
     */
    public static boolean match(String annotationName) {

        String replace = annotationName.toUpperCase().replace(".", "_");

        return Arrays.stream(JiaAnnotationType.values()).anyMatch(item -> item.name().equals(replace));
    }

}

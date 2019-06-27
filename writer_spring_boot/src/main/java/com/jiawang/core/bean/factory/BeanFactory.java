package com.jiawang.core.bean.factory;

import com.jiawang.util.BeanUtils;

import java.util.Map;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 15:54
 *  bean工厂
 *
 * @author : LiJiWang
 */
public class BeanFactory {

    private static Map<String, Object> beanNameMap = null;

    /**
     * 添加初始集合缓存
     *
     * @param map 集合
     */
    public static void addMap(Map<String, Object> map) {
        BeanFactory.beanNameMap = map;
    }


    /**
     * 根据名称获取bean
     *
     * @param beanName bean的名称
     * @return 结果
     */
    public Object getBeanByName(String beanName) {
        return beanNameMap.get(beanName);
    }


    /**
     * 根据字节码获取bean
     *
     * @param classType 字节码对象
     * @param <T>       具体的实例
     * @return 返回这个实例
     * @throws Exception 可能无法转换
     */
    public static <T> T getBeanByType(Class<T> classType) throws Exception {
        String className = classType.getName();
        String beanName = BeanUtils.classToBeanName(className);
        return (T) beanNameMap.get(beanName);
    }


}

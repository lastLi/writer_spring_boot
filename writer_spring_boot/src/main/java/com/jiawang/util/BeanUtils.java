package com.jiawang.util;

import com.jiawang.core.ACtest;
import com.jiawang.core.annotations.bean.assembly.JiaAutowired;
import com.jiawang.core.annotations.bean.components.JiaComponent;
import com.jiawang.core.annotations.bean.components.JiaController;
import com.jiawang.core.annotations.bean.components.JiaRepository;
import com.jiawang.core.annotations.bean.components.JiaService;
import com.jiawang.core.annotations.response.JiaRestController;
import com.jiawang.core.bean.UrlKey;
import com.jiawang.eums.JiaAnnotationType;
import com.jiawang.exe.DuplicateBeanException;
import com.jiawang.exe.InjectionFailureException;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 19:01
 * bean的工具类
 *
 * @author : LiJiWang
 */
public class BeanUtils {
    /**
     * 存所有的全类名
     */
    private static List<String> javaNames = new ArrayList<>();
    /**
     * 存放所有的bean
     */
    private static Map<String, Object> beanNameMap = new HashMap<>();

    /**
     * 存放所有的url
     */
    private static Map<UrlKey, Method> requestMethod = new HashMap<>();

    /**
     * 根据class全名转成bean的name "java.lang.String" -> "string".
     *
     * @param fullName class的全名
     * @return 转成bean的name。
     */
    public static String classToBeanName(String fullName) {
        String beanName = null;
        String tempClassName = fullName.substring(fullName.lastIndexOf(".") + 1);
        String lowerClassName = tempClassName.toLowerCase();
        beanName = lowerClassName.substring(0, 1) + tempClassName.substring(1);
        return beanName;
    }

    /**
     * 根据类的全名，判断对方是否具备实例化的资格，如果对方没有那么返回null，有就返回true。
     *
     * @param className 类的全名
     * @return 成功返回object，否则null
     * @throws Exception 异常
     */
    private static Object collectBytecode(String className) throws Exception {

        Class<?> aClass = Class.forName(className);
        // 获取汇编指令的值，来判断这个类的修饰符和类型
        int modifiers = aClass.getModifiers();
        // 判断这是不是公共修饰符
        boolean isPublic = Modifier.isPublic(modifiers);

        // 判断这是不是一个静态的类
        boolean isStatic = Modifier.isStatic(modifiers);

        // 判断这是不是一个抽象的类
        boolean isAbstract = Modifier.isAbstract(modifiers);

        // 判断这是不是一个注解
        boolean isAnnotation = aClass.isAnnotation();

        // 判断这是不是一个枚举
        boolean isEnum = aClass.isEnum();

        // 判断这是不是个接口
        boolean isInterface = Modifier.isInterface(modifiers);

        // 必须是公共的，不是静态的，不是枚举，不是注解，不是接口，不能是抽象的，只能是class
        if (isPublic && !isAnnotation && !isEnum && !isInterface && !isAbstract) {
            boolean b = Arrays.stream(aClass.getAnnotations()).anyMatch(
                    item -> JiaAnnotationType.match(item.annotationType().getName())
            );
            if (b) {

                return aClass.newInstance();
            } else {
                return null;
            }
        } else {
            return null;
        }


    }

    /**
     * 根据路径得到这个文件夹下的所有文件名，然后进行拼接，得到全类名，最后进行保存。
     *
     * @param packagePath 要扫描包的路径
     * @return 全类名列表
     */
    private static List<String> packageScanner(String packagePath) {
        packagePath = packagePath.replace(".", "/");


        Class<BeanUtils> beanUtilsClass = BeanUtils.class;
        ClassLoader classLoader = beanUtilsClass.getClassLoader();

        String parentPath = Objects.requireNonNull(classLoader.getResource("")).getPath();

        //得到父亲
        File parentFile = new File(parentPath + packagePath);

        // 得到儿子
        File[] baseFiles = parentFile.listFiles();

        // 儿子们开始遍历
        if (baseFiles != null) {
            for (File file : baseFiles) {
                if (file.isDirectory()) {
                    String directoryName = file.getName();

                    packageScanner(packagePath + "/" + directoryName);

                } else {
                    // 获取当前文件名
                    String fileName = file.getName();
                    // 判断是否已class文件结束
                    if (fileName.endsWith(".class")) {
                        // 拼接成为java 文件名
                        String javaName = (packagePath + "." + fileName.substring(0, fileName.lastIndexOf("."))).replace("/", ".");

                        // 添加这个文件到集合中
                        javaNames.add(javaName);
                    }
                }
            }
        }
        return javaNames;
    }


    /**
     * 装备所有的bean
     *
     * @param packagePath 包扫描范围
     * @throws Exception 异常
     */
    public static void loadBeanAll(String packagePath) throws Exception {
        List<String> classNames = BeanUtils.packageScanner(packagePath);

        loadOrdinaryBean(classNames);

        injectionAttribute();
    }

    /**
     * 装配普通的bean
     *
     * @param classNames 类名列表
     * @throws Exception 异常
     */
    private static void loadOrdinaryBean(List<String> classNames) throws Exception {
        // 装背所有的普通Bean
        for (String className : classNames) {
            // 普通bean判断
            Object obj = collectBytecode(className);
            // 不为空
            if (obj != null) {
                // 获取这个bean的名字
                String beanName = null;
                Class<?> aClass = Class.forName(className);

                JiaComponent jiaComponent = aClass.getAnnotation(JiaComponent.class);
                JiaController jiaController = aClass.getAnnotation(JiaController.class);
                JiaService jiaService = aClass.getAnnotation(JiaService.class);
                JiaRepository jiaRepository = aClass.getAnnotation(JiaRepository.class);
                JiaRestController jiaRestController = aClass.getAnnotation(JiaRestController.class);
                if (jiaComponent != null) {
                    beanName = jiaComponent.value();
                }
                if (jiaController != null) {
                    beanName = jiaController.value();
                }
                if (jiaService != null) {
                    beanName = jiaService.value();
                }
                if (jiaRepository != null) {
                    beanName = jiaRepository.value();
                }
                if (jiaRestController != null) {
                    JiaComponent annotation = jiaRestController.getClass().getAnnotation(JiaComponent.class);
                    beanName = jiaRestController.value();
                    Field fieldValue = annotation.getClass().getField("value");
                    fieldValue.setAccessible(true);
                    fieldValue.set(annotation, beanName);
                }
                // 如果是空，那么肯定没写，我就得帮他转换一下
                if ("".equals(beanName)) {
                    beanName = classToBeanName(className);
                    // 不准存入重复的
                    if (beanNameMap.get(beanName) != null) {
                        throw new DuplicateBeanException("我找到了多个" + beanName + "这里只能存一个" + beanName);
                    } else {
                        beanNameMap.put(beanName, obj);
                    }
                }
            }

        }
    }


    /**
     * 给bean注入属性
     *
     * @throws IllegalAccessException 有可能无法注入
     */
    private static void injectionAttribute() throws IllegalAccessException {
        // 给bean注入属性
        for (Map.Entry<String, Object> stringObjectEntry : beanNameMap.entrySet()) {
            Object name = stringObjectEntry.getValue();
            String className = name.getClass().getName();
            Field[] declaredFields = name.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                // 类型全类名
                String fieldTypeName = declaredField.getType().getName();
                // 类型注入不成功，按照名字进行注入
                String fieldName = declaredField.getName();
                // 是否有自动注入的注解
                if (declaredField.isAnnotationPresent(JiaAutowired.class)) {

                    //获取值
                    Object typeValue = beanNameMap.get(fieldTypeName);

                    // 先按类型注入
                    if (typeValue != null) {
                        declaredField.setAccessible(true);
                        declaredField.set(name, typeValue);
                        return;
                    } else {
                        // 类型注入不成功，按照名字进行注入
                        Object nameValue = beanNameMap.get(fieldName);
                        if (beanNameMap.get(fieldName) != null) {
                            declaredField.setAccessible(true);
                            declaredField.set(name, nameValue);
                            return;
                        }
                        throw new InjectionFailureException("在" + className + "这个Bean里" + "我无法找到这个属性名为" + fieldName + "所装载的名称和类型，抱歉自动注入失败");
                    }


                }
                //名称注入开始
                if (declaredField.isAnnotationPresent(Resource.class)) {
                    // 先按名称注入
                    Object nameValue = beanNameMap.get(fieldName);
                    if (nameValue != null) {
                        declaredField.setAccessible(true);
                        declaredField.set(name, nameValue);
                        return;
                    } else {
                        Object typeName = beanNameMap.get(fieldTypeName);
                        if (typeName != null) {
                            declaredField.setAccessible(true);
                            declaredField.set(name, typeName);
                            return;
                        }
                        throw new InjectionFailureException("在" + className + "这个Bean里" + "我无法找到这个属性名为" + fieldName + "所装载的名称和类型，抱歉自动注入失败");
                    }
                }
            }
        }
    }

    /**
     * 装配url
     */
    public static void storageUrl() {
        // 遍历bean
        for (Map.Entry<String, Object> stringObjectEntry : beanNameMap.entrySet()) {
            Class<?> aClass = stringObjectEntry.getValue().getClass();


        }
    }

    public static void main(String[] args) throws Exception {
        boolean b = AnnotationUtils.recursiveJudgmentAnnotation(ACtest.class, Resource.class);
        System.out.println(b);
    }


}

package com.jiawang.exe;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 16:41
 * 如果对方在一个bean上面标注了多个组件注解那么我就出来说话了
 * @author : LiJiWang
 */
public class MultipleComponentsException extends RuntimeException {
    public MultipleComponentsException() {
        super();
    }

    public MultipleComponentsException(String message) {
        super(message);
    }

    public MultipleComponentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleComponentsException(Throwable cause) {
        super(cause);
    }

    protected MultipleComponentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

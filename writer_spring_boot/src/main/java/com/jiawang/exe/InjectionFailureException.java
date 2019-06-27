package com.jiawang.exe;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 23:15
 *
 * @author : LiJiWang
 */
public class InjectionFailureException extends RuntimeException {
    public InjectionFailureException() {
        super();
    }

    public InjectionFailureException(String message) {
        super(message);
    }

    public InjectionFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectionFailureException(Throwable cause) {
        super(cause);
    }
}

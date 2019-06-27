package com.jiawang.exe;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 22:14
 * 如果有重复的bean，那么我就告诉你
 * @author : LiJiWang
 */
public class DuplicateBeanException extends RuntimeException {

    public DuplicateBeanException() {
        super();
    }

    public DuplicateBeanException(String message) {
        super(message);
    }

    public DuplicateBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateBeanException(Throwable cause) {
        super(cause);
    }

    protected DuplicateBeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

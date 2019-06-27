package com.jiawang.core.bean;

import com.jiawang.eums.JiaRequestType;

/**
 * 项目名称  : writer_spring_boot
 * 创建者   :  黎家望
 * 创建时间 :  2019-06 0:08
 * url的封装key
 * @author : LiJiWang
 */
public class UrlKey {

    /**
     * 请求方式
     */
    private JiaRequestType jiaRequestType;

    /**
     * 请求路径
     */
    private String urlPath;

    public UrlKey() {
    }

    public UrlKey(JiaRequestType jiaRequestType, String urlPath) {
        this.jiaRequestType = jiaRequestType;
        this.urlPath = urlPath;
    }

    public JiaRequestType getJiaRequestType() {
        return jiaRequestType;
    }

    public void setJiaRequestType(JiaRequestType jiaRequestType) {
        this.jiaRequestType = jiaRequestType;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UrlKey)) {
            return false;
        }

        UrlKey urlKey = (UrlKey) o;

        if (getJiaRequestType() != urlKey.getJiaRequestType()) {
            return false;
        }
        return getUrlPath().equals(urlKey.getUrlPath());
    }

    @Override
    public int hashCode() {
        int result = getJiaRequestType().hashCode();
        result = 31 * result + getUrlPath().hashCode();
        return result;
    }
}

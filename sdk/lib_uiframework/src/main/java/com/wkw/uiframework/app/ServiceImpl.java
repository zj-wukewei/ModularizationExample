package com.wkw.uiframework.app;

public final class ServiceImpl {
    private String key;
    private Object cacheObject;

    public ServiceImpl(String key, Object cacheObject) {
        this.key = key;
        this.cacheObject = cacheObject;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getCacheObject() {
        return cacheObject;
    }

    public void setCacheObject(Object cacheObject) {
        this.cacheObject = cacheObject;
    }
}

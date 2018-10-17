package com.wkw.uiframework.app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceLoader;

public final class MrServiceLoader {
    private volatile static MrServiceLoader instance;

    private HashMap<String, Object> cacheServiceLoader = new HashMap<>();


    private MrServiceLoader() {
    }

    public static MrServiceLoader getInstance() {
        if (instance == null) {
            synchronized (MrServiceLoader.class) {
                if (instance == null) {
                    instance = new MrServiceLoader();
                }
            }
        }
        return instance;
    }


    public <S> S getService(Class<S> service) {
        String name = service.getName();
        if (!cacheServiceLoader.containsKey(name)) {
            Iterator<S> iterator = ServiceLoader.load(service).iterator();

            cacheServiceLoader.put(name, iterator.next());
        }
        return (S) cacheServiceLoader.get(name);
    }



}

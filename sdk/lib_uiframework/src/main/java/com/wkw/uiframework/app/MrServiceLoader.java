package com.wkw.uiframework.app;

import java.util.HashMap;
import java.util.ServiceLoader;

public final class MrServiceLoader {
    private volatile static MrServiceLoader instance;
    private HashMap<String, ServiceImpl> mServiceHashMap = new HashMap<>();


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


    public <S> S getService(Class<S> service, String key) {
        String name = service.getName() + key;
        if (!mServiceHashMap.containsKey(name)) {
            createServiceImpl(service);
        }

        ServiceImpl service1mpl = mServiceHashMap.get(name);
        if (service1mpl != null) {
            return (S) service1mpl.getCacheObject();
        }
        return null;
    }

    public <S> S getService(Class<S> service) {
        return getService(service, "");
    }

    private <S> void createServiceImpl(Class<S> service) {

        ServiceLoader<S> serviceLoader = ServiceLoader.load(service);
        for (S s : serviceLoader) {
            String key = service.getName();
            IAutoServiceKey iAutoServiceKey = s.getClass().getAnnotation(IAutoServiceKey.class);
            if (iAutoServiceKey != null) {
                key = key + iAutoServiceKey.value();
            }
            mServiceHashMap.put(key, new ServiceImpl(key, s));
        }
    }

}

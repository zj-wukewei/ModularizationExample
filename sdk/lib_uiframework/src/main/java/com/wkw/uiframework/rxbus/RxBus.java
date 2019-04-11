package com.wkw.uiframework.rxbus;

import android.text.TextUtils;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * @author GoGo on 2019-04-07.
 */
public class RxBus {
    private static final String DEFAULT_TAG = "__DEFAULT_TAG";
    private volatile static RxBus instance;
    private final PublishRelay<Event> bus = PublishRelay.create();

    private void RxBus() {

    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public void post(Object data) {
        post(DEFAULT_TAG, data);
    }

    public void post(String tag, Object data) {
        try {
            bus.accept(new Event(tag, data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public <T> Flowable<T> toObservable(Class<T> eventType) {
        return toObservable(DEFAULT_TAG, eventType);
    }


    public <T> Flowable<T> toObservable(String tag, Class<T> eventType) {
        return bus.toFlowable(BackpressureStrategy.BUFFER)
                .filter(event -> TextUtils.equals(tag, event.tag))
                .map(it -> it.data)
                .flatMap(data -> Flowable.just(data).ofType(eventType));
    }

    public static class Event {
        public String tag;
        public Object data;

        public Event(String tag, Object data) {
            this.tag = tag;
            this.data = data;
        }
    }
}

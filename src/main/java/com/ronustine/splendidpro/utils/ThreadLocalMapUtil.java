package com.ronustine.splendidpro.utils;

import com.google.common.collect.Maps;

import java.util.Map;

public class ThreadLocalMapUtil {

    private final static ThreadLocal<Object> THREAD_LOCAL_HOLDER = new ThreadLocal<Object>();

    public static Object get(String key) {
        Map<String,Object> map = (Map<String,Object>)THREAD_LOCAL_HOLDER.get();
        if (null != map){
            return map.get(key);
        }else{
            return null;
        }
    }

    public static void set(String key ,Object value) {
        Map<String,Object> map = (Map<String,Object>)THREAD_LOCAL_HOLDER.get();
        if (null == map){
            map = Maps.newHashMap();
        }
        map.put(key,value);
        THREAD_LOCAL_HOLDER.set(map);
    }

    public static Map<String, Object> getMap() {
        return (Map<String,Object>)THREAD_LOCAL_HOLDER.get();
    }

    public static void clear() {
        THREAD_LOCAL_HOLDER.remove();
    }

}

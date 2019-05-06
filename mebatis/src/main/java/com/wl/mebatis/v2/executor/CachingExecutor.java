package com.wl.mebatis.v2.executor;

import com.wl.mebatis.v2.cache.CacheKey;

import java.util.HashMap;
import java.util.Map;

public class CachingExecutor implements Executor {

    //委派
    private Executor delegate;

    private static final Map<Integer, Object> cache = new HashMap<>();

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T findOne(String sql, Object[] args, Class<T> clazz) {
        CacheKey cacheKey = new CacheKey();
        cacheKey.update(sql);
        cacheKey.update(jsonStr(args));
        if (cache.containsKey(cacheKey.getCode())) {
            //如果命中缓存的话
            System.out.println("命中缓存");
            return (T) cache.get(cacheKey.getCode());
        } else {
            //调用简单的执行器
            T t = delegate.findOne(sql, args, clazz);
            //保存缓存
            cache.put(cacheKey.getCode(), t);
            return t;
        }
    }

    private Object jsonStr(Object[] args) {

        StringBuffer sb = new StringBuffer();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                sb.append(arg + ",");
            }
        }
        String str = sb.toString();
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}

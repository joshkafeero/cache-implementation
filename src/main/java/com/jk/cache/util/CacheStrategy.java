package com.jk.cache.util;

public interface CacheStrategy {
    public Object put(String key, Object object);
    public Object remove(String key);
}

package com.jk.cache.util;

import com.jk.cache.model.CacheNode;

import java.util.List;
import java.util.Map;

public class CacheStore {
    private Map<String, Object> cacheMap;
    private LRUCache            lruCache;
    private Integer             size;
    private Integer             maxSize;

    public CacheStore(Map<String, Object> cacheMap) {
        this.cacheMap = cacheMap;
        this.lruCache = new LRUCache(cacheMap);
    }

    public int size() {
        return cacheMap.size();
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Map<String, Object> getCacheMap() {
        return cacheMap;
    }

    public Object put(String key, Object object) {
        CacheNode cacheNode = lruCache.addNode(object);
        cacheMap.put(key, cacheNode);
        return object;
    }

    public Object get(String key) {
        CacheNode cacheNode = (CacheNode)cacheMap.get(key);
        return cacheNode.getObject();
    }

    public Object remove(String key) {
        CacheNode cacheNode = (CacheNode) cacheMap.get(key);
        CacheNode removedObject = (CacheNode) cacheMap.remove(key);
        lruCache.removeNode(cacheNode);

        return removedObject;
    }

}

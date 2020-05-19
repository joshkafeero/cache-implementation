package com.jk.cache.service;

import com.jk.cache.model.CacheNode;
import com.jk.cache.util.CacheStore;

import java.util.*;

public class CacheServiceImpl implements CacheService {

    private CacheStore cacheStore;
    private Integer size = 0;

    public CacheServiceImpl() {
        Map<String, Object> cacheMap = new HashMap<>();
        cacheStore = new CacheStore(cacheMap);
    }

    public CacheServiceImpl(CacheStore cacheStore) {
        this.cacheStore = cacheStore;
        this.size = cacheStore.size();
    }

    @Override
    public String putItem(Object object) {
        size++;
        String key = getKey();
        cacheStore.put(key, object);
        return key;
    }

    @Override
    public List<String> putItems(Collection<Object> objects) {
        List<String> keys = new ArrayList<>();
        for (Object newItem:objects) {
            String key = putItem(newItem);
            keys.add(key);
        }
        return keys;
    }

    @Override
    public Object fetchItem(String key) {
        Object object = cacheStore.get(key);
        return object;
    }

    @Override
    public List<Object> fetchItems(List<String> keys) {
        return null;
    }

    @Override
    public Boolean removeItem(String cacheKey) {
        size--;
        cacheStore.remove(cacheKey);
        return true;
    }

    @Override
    public Boolean removeItems(Collection<String> cacheKeys) {
        for(String key: cacheKeys) {
            cacheStore.remove(key);
            size--;
        }
        return true;
    }

    @Override
    public List<Object> displayItems() {
        List<Object> items = new ArrayList<>();
        Map<String, Object> cacheMap = cacheStore.getCacheMap();
        for (Map.Entry<String, Object> entry : cacheMap.entrySet()) {
            CacheNode cacheNode = (CacheNode) entry.getValue();
            items.add(cacheNode.getObject());
        }
        return items;
    }

    @Override
    public Integer size() {
        return size;
    }

    private String getKey() {
        return UUID.randomUUID().toString();
    }
}

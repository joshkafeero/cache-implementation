package com.jk.cache.service;

import java.util.Collection;
import java.util.List;

public interface CacheService {

    public String       putItem(Object object);                    // returns cache key
    public List<String> putItems(Collection<Object> objects);      // returns the cache keys

    public Object       fetchItem(String key);
    public List<Object> fetchItems(List<String> keys);

    public Boolean removeItem(String cacheKey);               // returns true if success false if not
    public Boolean removeItems(Collection<String> cacheKeys); // returns true if successful false if not

    public List<Object> displayItems();                    // list of the objects in the cache

    public Integer size();                                 // size of the objects (should change this to
                                                           // to be actual memory size not just count
}

package com.jk.cache.util;

import com.jk.cache.model.CacheNode;

import java.util.Map;

public class LRUCache implements CacheStrategy {
    private Map<String, Object> cacheMap;
    private CacheNode head;
    private CacheNode tail;

    public LRUCache(Map<String, Object> cacheMap) {
        this.cacheMap = cacheMap;
    }

    @Override
    public Object put(String key, Object object) {
        return null;
    }

    public Object remove(String key) {
        return null;
    }

    public synchronized CacheNode addNode(Object object) {

        CacheNode newCacheNode = new CacheNode(object);
        if (head == null) {
            head = newCacheNode;
            tail = newCacheNode;
        } else {
            head.setPrevious(newCacheNode);
            newCacheNode.setNext(head);
            head = newCacheNode;
        }

        return newCacheNode;
    }

    public synchronized Boolean removeNode(CacheNode cacheNode) {
        CacheNode nodeToDelete = cacheNode;
        if (head == null && tail == null) {
            return false;
        } else {
            CacheNode previous = nodeToDelete.getPrevious();
            CacheNode next = nodeToDelete.getNext();
            if(previous != null)
                previous.setNext(nodeToDelete.getNext());
            if(next != null)
                next.setPrevious(previous);
            return true;
        }
    }

    public CacheNode removeTail() {
        CacheNode newTail = tail.getPrevious();
        newTail.setNext(null);
        tail = newTail;
        return newTail;
    }

    public String putItem(Object object) {

        return "newly created";
    }

    ;

}



package com.jk.cache.model;

public class CacheNode {
    private Object object;
    private CacheNode previous = null;
    private CacheNode next = null;

    public CacheNode(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public CacheNode getPrevious() {
        return previous;
    }

    public void setPrevious(CacheNode previous) {
        this.previous = previous;
    }

    public CacheNode getNext() {
        return next;
    }

    public void setNext(CacheNode next) {
        this.next = next;
    }
}

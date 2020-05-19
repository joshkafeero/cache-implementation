package com.jk.cache.util;

import com.jk.cache.model.CacheNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LRUCacheTest {

    private LRUCache subject;

    @BeforeEach
    public void setup() {
        Map<String, Object> cacheMap = new HashMap<>();
        this.subject = new LRUCache(cacheMap);
    }

    @Test
    void put() {
//        subjectLRU.put(key, cacheEntry)

    }

    @Test
    void remove() {
    }

    @Test
    void addNode_EmptyCache() {
        Object newObject = new String("new object");
        CacheNode newNode = subject.addNode(newObject);

        assertTrue(newNode.getNext() == null);
        assertTrue(newNode.getPrevious() == null);
    }


    @Test
    void addNode_NonEmptyCache() {
        Object firstObject = new String("non-empty cache object");
        CacheNode firstNode = subject.addNode(firstObject);

        Object secondObject = new String("second object");
        CacheNode secondNode = subject.addNode(secondObject);

        assertTrue(firstNode.getPrevious().equals(secondNode));
        assertTrue(secondNode.getNext().equals(firstNode));
    }

    @Test
    void removeNode_Middle() {
        Object firstObject = new String("first object");
        Object secondObject = new String("second object");
        Object thirdObject = new String("third object");

        CacheNode firstNode = subject.addNode(firstObject);
        CacheNode secondNode = subject.addNode(secondObject);
        CacheNode thirdNode = subject.addNode(thirdObject);

        subject.removeNode(secondNode);

        assertTrue(firstNode.getPrevious().equals(thirdNode));
        assertTrue(thirdNode.getNext().equals(firstNode));

    }

    @Test
    void removeNode_Tail() {
        Object firstObject = new String("first object");
        Object secondObject = new String("second object");

        CacheNode firstNode = subject.addNode(firstObject);
        CacheNode secondNode = subject.addNode(secondObject);

        subject.removeTail();
        assertNull(secondNode.getNext());
    }
}
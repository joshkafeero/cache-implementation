package com.jk.cache.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceImplTest {

    private CacheService subject;

    @BeforeEach
    void setUp() {
        subject = new CacheServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void putItem_StartWithEmptyCache() {
        subject = new CacheServiceImpl();
        Object toStore = new String("Object to store");
        String key = subject.putItem(toStore);

        // The count must be one and key allocated.
        assertTrue(subject.size() == 1);
        assertNotNull(key);

        Object fromCache = subject.fetchItem(key);
        assertNotNull(fromCache);
        assertEquals(toStore, fromCache);
    }

    @Test
    void putItem_StartWithNonEmptyCache() {
        Object firstObject = new String("First Object");
        String firstKey = subject.putItem(firstObject);
        Integer firstSize = subject.size();

        Object secondObject = new String ("Second object");
        String secondKey = subject.putItem(secondObject);
        Integer secondSize = subject.size();
        assertNotEquals(firstKey, secondKey);
        assertTrue(firstSize < secondSize);
    }


    @Test
    void putItems_EmptyQueue() {
        subject = new CacheServiceImpl();
        List<Object> objectsTosave = doCreateObjectsToSave(5);

        List<String> keys = subject.putItems(objectsTosave);

        assertTrue(subject.size() == objectsTosave.size());
        assertTrue(keys.size() == subject.size());

    }

    @Test
    void putItems_NonEmptyCache() {
        // Make cache non-empty
        Object firstObject = new String("To make cache non-empty");
        subject.putItem(firstObject);
        List<Object> objectsTosave = doCreateObjectsToSave(5);

        List<String> keys = subject.putItems(objectsTosave);
        assertTrue(subject.size() != objectsTosave.size());
        assertTrue(keys.size() != subject.size());
    }


    @Test
    void removeItem() {
        String objectToSave = new String("Object to save");
        String key = subject.putItem(objectToSave);
        Integer firstSize = subject.size();

        Boolean isRemoveSuccessful = subject.removeItem(key);
        assertTrue(isRemoveSuccessful);
        assertTrue(firstSize != subject.size());
    }

    @Test
    void removeItem_EmptyCache() {
        assertTrue(subject.size() == 0);
        Boolean isRemoveUnsuccessful = subject.removeItem("dummyString");
        assertTrue(isRemoveUnsuccessful);
    }

    @Test
    void removeItems() {
        int numOfObjectsToSave = 5;
        List<Object> objectToSave = doCreateObjectsToSave(numOfObjectsToSave);
        List<String> keys = subject.putItems(objectToSave);

        assertTrue(subject.size() == numOfObjectsToSave);
        Boolean removeSucceeded = subject.removeItems(keys);

        assertTrue(removeSucceeded);
        assertTrue(subject.size() == 0);
    }

    @Test
    void displayItems() {
        List<Object> objectsToSave = doCreateObjectsToSave(5);
        subject.putItems(objectsToSave);

        List<Object> objectsDisplayed = subject.displayItems();
        assertTrue(objectsDisplayed.size() == objectsToSave.size());
        assertEquals(objectsToSave, objectsToSave);

    }

    private List<Object> doCreateObjectsToSave(int numToCreate) {
        // Load many objects after cache is non-empty
        List<Object> objectsTosave = new ArrayList<>();
        for (int i = 0; i < numToCreate; i++) {
            objectsTosave.add(new String("object_to_save" + 1));
        }
        return objectsTosave;
    }
}
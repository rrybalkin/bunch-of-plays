package com.rrybalkin.cache;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Roman Rybalkin
 * 08.10.16
 */
public class CacheTest {

    @Test
    public void testDeletionOfOldElements() {
        Cache<Integer> cache = createCache(5);

        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.put("four", 4);
        cache.put("five", 5);
        // here cache is full and should start pushing out LRU elements
        cache.put("six", 6);
        cache.put("seven", 7);

        Assert.assertNull(cache.get("one"));
        Assert.assertNull(cache.get("two"));
        Assert.assertTrue(3 == cache.get("three"));
        Assert.assertTrue(4 == cache.get("four"));
        Assert.assertTrue(5 == cache.get("five"));
        Assert.assertTrue(6 == cache.get("six"));
        Assert.assertTrue(7 == cache.get("seven"));
    }

    @Test
    public void testDeletionOfLeastRecentElement() {
        Cache<Integer> cache = createCache(5);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.put("four", 4);
        cache.put("five", 5);

        // let it be three the least recent used
        cache.get("one");
        cache.get("two");
        cache.get("four");
        cache.get("five");

        cache.put("six", 6);
        Assert.assertNull(cache.get("three"));
        Assert.assertTrue(1 == cache.get("one"));
        Assert.assertTrue(2 == cache.get("two"));
        Assert.assertTrue(4 == cache.get("four"));
        Assert.assertTrue(5 == cache.get("five"));
    }

    private Cache<Integer> createCache(int size) {
        return new LRUCacheBasedOnLinkedList<>(size);
    }
}

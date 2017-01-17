package com.rrybalkin.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MRUCacheBasedOnPriorityQueue<T> implements Cache<T> {
    private PriorityQueue<Element<T>> elements;
    private Map<String, Element<T>> dictionary;
    private int maxSize;

    public MRUCacheBasedOnPriorityQueue(int size) {
        this.maxSize = size;
        elements = new PriorityQueue<>(size);
        dictionary = new HashMap<>(size);
    }

    @Override
    public void put(String key, T value) {
        if (elements.size() == maxSize) {
            pushLRUElement();
        }
        Element<T> element = new Element<>(key, value);
        elements.offer(element);
        dictionary.put(key, element);
    }

    private void pushLRUElement() {
        Element<T> lruElement = elements.poll();
        dictionary.remove(lruElement.getKey());
    }

    @Override
    public T get(String key) {
        Element<T> element = dictionary.get(key);
        if (element == null) return null;
        element.usedRecently();
        return element.getData();
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    class Element<D> implements Comparable<Element<D>> {
        String key;
        D data;
        long lastUsageTime;

        public Element(String key, D data) {
            this.key = key;
            this.data = data;
            this.lastUsageTime = System.nanoTime();
        }

        public String getKey() {
            return this.key;
        }

        public D getData() {
            return data;
        }

        public void usedRecently() {
            this.lastUsageTime = System.nanoTime();
        }

        @Override
        public int compareTo(Element<D> o) {
            return -1 * Long.compare(lastUsageTime, o.lastUsageTime);
        }

        @Override
        public String toString() {
            return "[" + data + "(" + lastUsageTime + ")]";
        }
    }
}

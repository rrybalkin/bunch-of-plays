package com.rrybalkin.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheBasedOnLinkedList<T> implements Cache<T> {
    private Map<String, Element<T>> dictionary;
    private int maxSize;
    private Element<T> head, tail;

    public LRUCacheBasedOnLinkedList(int size) {
        this.maxSize = size;
        dictionary = new HashMap<>(size);
    }

    @Override
    public void put(String key, T value) {
        if (dictionary.size() == maxSize) {
            pushLRUElement();
        }
        Element<T> element = new Element<>(key, value);
        dictionary.put(key, element);
        addNewElement(element);
    }

    private void pushLRUElement() {
        Element<T> lruElement = head;
        dictionary.remove(lruElement.getKey());
        head.remove();
        head = (Element<T>) head.next;
    }

    private void addNewElement(Element<T> element) {
        if (head == null && tail == null) {
            // first insert
            head = element;
            tail = element;
        } else {
            element.moveAfter(tail);
            tail = element;
        }
    }

    @Override
    public T get(String key) {
        Element<T> element = dictionary.get(key);
        if (element == null) return null;
        recentlyUsed(element);
        return element.getData();
    }

    private void recentlyUsed(Element<T> usedElement) {
        if (head == usedElement) {
            head = (Element<T>) usedElement.next;
        }
        usedElement.moveAfter(tail);
        tail = usedElement;
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    class Node {
        Node prev, next;

        public void moveAfter(Node to) {
            remove();
            this.next = to.next;
            to.next = this;
            this.prev = to;
        }

        public Node remove() {
            if (prev != null && next != null) {
                prev.next = next;
                next.prev = prev;
            } else if (prev != null) {
                prev.next = null;
            } else if (next != null) {
                next.prev = null;
            }
            return this;
        }
    }

    class Element<D> extends Node {
        String key;
        D data;

        public Element(String key, D data) {
            this.key = key;
            this.data = data;
        }

        public String getKey() {
            return this.key;
        }

        public D getData() {
            return data;
        }
    }
}

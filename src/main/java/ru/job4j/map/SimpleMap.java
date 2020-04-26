package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Entry> {

    private int capacity;

    private int size;

    private static final int DEFAULT_CAPACITY = 16;

    public static final float LOAD_FACTOR = 0.75f;

    private int modCount;

    private Entry<K, V>[] table;

    public SimpleMap() {
        this.table = new Entry[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    public SimpleMap(int size) {
        this.capacity = size;
        this.table = new Entry[capacity];
    }

    public boolean put(K key, V value) {
        grow();
        int h = hash(key);
        if (!(table[h] == null || ((key == table[h].key) || table[h].key.hashCode() == key.hashCode() && key.equals(table[h].key)))) {
            return false;
        }
        table[h] = new Entry<>(key, value, h);
        size++;
        modCount++;
        return true;
    }

    public V remove(K key) {
        int h = hash(key);
        if (table[h] == null || !(key == null || table[h].key.hashCode() == key.hashCode() && key.equals(table[h].key))) {
            return null;
        }
        V v = table[h].value;
        table[h] = null;
        modCount++;
        size--;
        return v;
    }

    public V get(K key) {
        int h = hash(key);
        if (table[h] == null || !(key == null || table[h].key.hashCode() == key.hashCode() && key.equals(table[h].key))) {
            return null;
        }
        return table[h].value;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry> iterator() {
        return new Iterator<>() {

            private int index;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean found = false;
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        found = true;
                        break;
                    }
                }
                return found;
            }

            @Override
            public Entry next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++];
            }
        };
    }

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() & (capacity - 1);
    }

    private void grow() {
        if (((float) size / capacity) > LOAD_FACTOR) {
            int prevCapacity = capacity;
            capacity = capacity << 1;
            Entry<K, V>[] newTable = new Entry[capacity];
            for (int i = 0; i < prevCapacity; i++) {
                if (table[i] != null) {
                    int h = hash(table[i].key);
                    newTable[h] = new Entry<>(table[i].key, table[i].value, h);
                }
            }
            table = newTable;
        }
    }

    public static class Entry<K, V> {

        private K key;
        private V value;
        private int hash;

        private Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}

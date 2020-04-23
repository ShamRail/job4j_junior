package ru.job4j.list;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container = new Object[10];

    private int position;

    private int modCount;

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) container[index];
    }

    public void add(T model) {
        if (position == container.length) {
            container = Arrays.copyOf(container, 2 * position);
        }
        container[position++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int index;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[index++];
            }
        };
    }
}

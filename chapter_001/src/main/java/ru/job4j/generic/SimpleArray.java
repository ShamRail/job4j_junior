package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container = new Object[100];

    private int position;

    public void add(T model) {
        container[position++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
        container[index] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) container[index];
    }

    public T remove(int index) {
        Objects.checkIndex(index, position);
        T value = (T) container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        position--;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int index;

            @Override
            public boolean hasNext() {
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

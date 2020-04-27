package ru.job4j.set;

import ru.job4j.generic.SimpleArray;
import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray<T> array = new SimpleArray<>();

    public void add(T data) {
        if (contains(data)) {
            return;
        }
        array.add(data);
    }

    public boolean contains(T data) {
        boolean result = false;
        for (T el : array) {
            if (el.equals(data)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}

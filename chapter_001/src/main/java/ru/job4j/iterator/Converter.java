package ru.job4j.iterator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> its) {
        return new Iterator<>() {

            private Iterator<Integer> current = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                while (its.hasNext() && !current.hasNext()) {
                    current = its.next();
                }
                return current.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return current.next();
            }
        };
    }
}

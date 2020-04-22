package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int lastIndex;
    private int evenIndex;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        evenIndex = -1;
        for (int i = lastIndex; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                evenIndex = i;
                break;
            }
        }
        return evenIndex != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastIndex = evenIndex + 1;
        return data[evenIndex];
    }

}

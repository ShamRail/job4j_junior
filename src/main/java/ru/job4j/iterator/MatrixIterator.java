package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private int row;
    private int col;
    private int[][] data;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        return row < data.length && col < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int elem = data[row][col++];
        if (col == data[row].length) {
            row++;
            col = 0;
        }
        return elem;
    }
}

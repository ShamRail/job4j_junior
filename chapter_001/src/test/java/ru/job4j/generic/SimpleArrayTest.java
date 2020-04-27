package ru.job4j.generic;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenGetByValidIndex() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        assertThat(array.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetByInvalidIndex() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        array.get(1);
    }

    @Test
    public void whenSetByValidIndex() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(0);
        array.set(0, 1);
        assertThat(array.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetByInvalidIndex() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        array.set(1, 2);
    }

    @Test
    public void whenRemoveByValidIndex() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(0);
        array.add(1);
        array.add(2);
        array.remove(1);
        assertThat(array.get(0), is(0));
        assertThat(array.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveByInvalidIndex() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        array.remove(1);
    }

    @Test
    public void validIteratorTest() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        Iterator<Integer> it = array.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNSEE() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        Iterator<Integer> it = array.iterator();
        it.next();
        it.next();
    }

}
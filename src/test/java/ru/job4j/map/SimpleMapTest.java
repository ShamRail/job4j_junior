package ru.job4j.map;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAddAndGet() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        assertThat(map.get(1), is("1"));
    }

    @Test
    public void whenAddAndRemove() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        assertThat(map.remove(1), is("1"));
        assertThat(map.size(), is(0));
    }

    @Test
    public void whenAddBySameKeyThenUpdate() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        map.put(1, "2");
        assertThat(map.get(1), is("2"));
    }

    @Test
    public void whenMapGrowThenValuesMustSave() {
        SimpleMap<Integer, String> map = new SimpleMap<>(1);
        map.put(1, "1");
        map.put(2, "2");
        assertThat(map.get(1), is("1"));
        assertThat(map.get(2), is("2"));
        assertThat(map.size(), is(2));
    }

    @Test
    public void whenKeyNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(null, "1");
        assertThat(map.get(null), is("1"));
    }

    @Test
    public void whenRemoveByNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(null, "1");
        assertThat(map.remove(null), is("1"));
    }

    @Test
    public void whenUpdateByNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(null, "1");
        map.put(null, "2");
        assertThat(map.get(null), is("2"));
    }

    @Test
    public void iteratorTest() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        Iterator<SimpleMap.Entry> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next().getKey(), is(3));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        Iterator<SimpleMap.Entry> it = map.iterator();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        Iterator<SimpleMap.Entry> it = map.iterator();
        it.next();
        map.put(2, "2");
        it.next();
    }

    @Test
    public void whenKeyHashcodeNegative() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(-1, "-1");
        assertThat(map.get(-1), is("-1"));
    }

}
package ru.job4j.kiss;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class MaxMinTest {

    @Test
    public void whenMin() {
        Assert.assertThat(
                MaxMin.min(List.of(5, 4, 3, 2, 1), Comparator.naturalOrder()),
                Is.is(1)
        );
    }

    @Test
    public void whenMax() {
        Assert.assertThat(
                MaxMin.max(List.of(1, 2, 3, 4, 5), Comparator.naturalOrder()),
                Is.is(5)
        );
    }

}
package ru.job4j.design.isp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleMenuTest {

    @Test
    public void whenUnOrderedOut() {
        StubAction stub = new StubAction();
        SimpleMenu<String> menu = new SimpleMenu<>("A", stub);
        menu.add("A", "B", stub);
        menu.add("B", "C", stub);
        menu.add("A", "D", stub);
        String out = menu.unOrdered();
        String expect = String.format(
                "%s%n%s%n%s%n%s%n",
                "stub",
                "- stub",
                "-- stub",
                "- stub"
        );
        Assert.assertEquals(expect, out);
    }

    @Test
    public void whenOrderedOut() {
        StubAction stub = new StubAction();
        SimpleMenu<String> menu = new SimpleMenu<>("A", stub);
        menu.add("A", "B", stub);
        menu.add("B", "C", stub);
        menu.add("A", "D", stub);
        String out = menu.ordered();
        String expect = String.format(
                "%s%n%s%n%s%n%s%n",
                "stub",
                "1. stub",
                "1.1. stub",
                "2. stub"
        );
        Assert.assertEquals(expect, out);
    }

}
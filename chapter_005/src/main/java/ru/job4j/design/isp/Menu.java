package ru.job4j.design.isp;

public interface Menu<K extends Comparable<K>> extends Printable, CRUD<K, Action> {

}

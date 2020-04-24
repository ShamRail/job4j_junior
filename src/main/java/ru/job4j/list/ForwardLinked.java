package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {

    private int size;

    private int modCount;

    private Node head;

    public void addFirst(T value) {
        if (head == null) {
            head = new Node(value);
            size++;
            modCount++;
            return;
        }
        Node node = new Node(value);
        node.next = head;
        head = node;
        size++;
        modCount++;
    }

    public void addLast(T value) {
        if (head == null) {
            head = new Node(value);
            size++;
            modCount++;
            return;
        }
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = new Node(value, null);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        int position = 0;
        Node current = head;
        while (position != index) {
            current = current.next;
            position++;
        }
        return (T) current.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Node current = head;

            private int index;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = (T) current.value;
                current = current.next;
                index++;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node next;
        Node(T value) {
            this.value = value;
        }
        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

}

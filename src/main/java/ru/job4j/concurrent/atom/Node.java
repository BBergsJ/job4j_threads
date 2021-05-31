package ru.job4j.concurrent.atom;

public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
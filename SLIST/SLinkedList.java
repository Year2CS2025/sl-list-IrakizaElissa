package SLIST;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLinkedList<T> implements Iterable<T> {

    // Inner Node class (no need for separate generic <T>)
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public SLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public void addFirst(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

        if (tail == null) { // if list was empty
            tail = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node newNode = new Node(data);

        if (head == null) { // empty list
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode; // IMPORTANT
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        T data = head.data;
        head = head.next;
        size--;

        if (head == null) { // list became empty
            tail = null;
        }

        return data;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        if (head.next == null) { // only one element
            T data = head.data;
            head = null;
            tail = null;
            size--;
            return data;
        }

        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }

        T data = tail.data;
        current.next = null;
        tail = current;
        size--;
        return data;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }

    public boolean contains(T data) {
        Node current = head;

        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void display() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    // Reverse the list in-place
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }

        Node prev = null;
        Node current = head;
        Node next;

        tail = head; // old head becomes new tail

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
    }

    // Remove only consecutive duplicates
    public void deleteConsecutiveDuplicates() {
        if (head == null || head.next == null) {
            return;
        }

        Node current = head;

        while (current.next != null) {
            if (current.data.equals(current.next.data)) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }

        tail = current; // update tail
    }

    // Two lists are equal if same size and same elements in same order
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SLinkedList<?> other = (SLinkedList<?>) obj;

        if (this.size != other.size) {
            return false;
        }

        Node current1 = this.head;
        SLinkedList<?>.Node current2 = other.head;

        while (current1 != null) {
            if (!current1.data.equals(current2.data)) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        return true;
    }
}
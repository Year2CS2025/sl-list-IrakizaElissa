//package SLIST;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLinkedList<T> implements Iterable<T>{
    class Node<T>{
        T data;
        Node<T> next;
        Node(T data){
            this.data=data;
            next=null;
        }

    }

    private Node<T> head;
    private Node<T> tail;
    private int size=0;
    public SLinkedList(){
        head=null;
        size=0;

}
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

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
    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        tail=newNode;
        size++;
    }
    public void addLast(T data){
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail=newNode;
        } else {
            tail.next = newNode;
        }
        size++;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public T deleteFirst(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        if (head == null) {
            tail = null; // If the list becomes empty, set tail to null
        }
        return data;
    }
    public T deleteLast(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (head.next == null) { // Only one element
            T data = head.data;
            head = null;
            tail = null;
            size--;
            return data;
        }
        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        T data = tail.data;
        current.next = null;
        tail = current;
        size--;
        return data;
    }
    public void display(){
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    public boolean contains(T data){
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public void clear(){
        head = null;
        tail=null;
        size=0;
    }
    public T getFirst(){
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }
    public T getLast(){
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }
     public void remove(T e) {
    if (head == null) return;

    if (head.data.equals(e)) {
        head = head.next;
        size--;
        if (head == null) tail = null;
        return;
    }

    Node<T> current = head;

    while (current.next != null) {
        if (current.next.data.equals(e)) {
            current.next = current.next.next;
            size--;
            if (current.next == null) {
                tail = current;
            }
            return;
        }
        current = current.next;
    }
}


    public void reverse(){
      Node<T> prev = null;
    Node<T> current = head;
    Node<T> next = null;

    tail = head; 

    while (current != null) {
        next = current.next;   
        current.next = prev;   
        prev = current;        
        current = next;        
    }

    head = prev;
    }
    public void deleteConsecutiveDuplicates(){
       if (head == null) return;

    Node<T> current = head;

    while (current.next != null) {
        if (current.data.equals(current.next.data)) {
            current.next = current.next.next;
            size--;
        } else {
            current = current.next;
        }
    }

    tail = current;   
        
       
    }
  
 @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof SLinkedList)) return false;

    SLinkedList<?> other = (SLinkedList<?>) obj;

    if (this.size != other.size) return false;

    Iterator<T> it1 = this.iterator();
    Iterator<?> it2 = other.iterator();

    while (it1.hasNext() && it2.hasNext()) {
        if (!it1.next().equals(it2.next())) {
            return false;
        }
    }

    return true;
}




}
package list;

import interfaces.List;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {

        private T element;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            element = e;
            next = n;
        }

        public T getElement() {
            return element;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }

    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        Node<E> curr = tail.getNext();
        for (int j = 0; j < i; j++, curr = curr.getNext());
        return curr.getElement();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i > size) {
            return;
        }
        if (i == 0) {
            addFirst(e);
            return;
        }

        Node<E> curr = tail.getNext();
        for (int j = 0; j < i - 1; j++, curr = curr.getNext());
        Node<E> newNode = new Node<>(e, curr.getNext());
        curr.setNext(newNode);
        size++;
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i > size) {
            return null;
        }

        if (i == 0) {
            return removeFirst();
        }
        Node<E> curr = tail.getNext();
        for (int j = 0; j < i - 1; j++, curr = curr.getNext());

        Node<E> tmp = curr.getNext();
        curr.setNext(tmp.getNext());
        size--;
        return tmp.getElement();
    }

    public void rotate() {
        if(tail != null) {
            tail = tail.getNext();
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;
        Node<E> prev = null;

        @Override
        public boolean hasNext() {
            return prev != tail;
        }

        @Override
        public E next() {
            curr = curr.getNext();
            prev = curr;
            return curr.getElement();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (size == 1) {
            E element = tail.getElement();
            tail = null;
            size--;
            return element;
        }
        Node<E> head = tail.getNext();
        tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (size == 1) {
            E element = tail.getElement();
            tail = null;
            size--;
            return element;
        }

        Node<E> curr = tail.getNext();
        while (curr.getNext() != tail) {
            curr = curr.getNext();
        }

        E element = tail.getElement();
        curr.setNext(tail.getNext());
        tail = curr;
        size--;
        return element;
    }

    @Override
    public void addFirst(E e) {
        if(isEmpty()) {
            tail = new Node<E>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> n = new Node<E>(e, tail.getNext());
            tail.setNext(n);
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.getNext();
            sb.append(curr.getElement());
            if (curr != tail) {
                sb.append(", ");
            }
        } while(curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for(int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}

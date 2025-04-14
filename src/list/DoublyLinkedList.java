package list;

import interfaces.List;

import java.util.Iterator;
import java.util.LinkedList;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<>(null,null, null);
        tail = new Node<>(null, head, null);
        head.setNext(tail);
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newNode = new Node<>(e, pred, succ);
        pred.setNext(newNode);
        succ.setPrev(newNode);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (isEmpty() || i >= size) {
            return null;
        }
        Node<E> curr = head.getNext();
        for (int j = 0; j < i; j++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    @Override
    public void add(int i, E e) {
        if (i == 0) {
            addFirst(e);
        }
        Node<E> curr = head.getNext();

        for (int j = 0; j < i - 1; j++) {
            curr = curr.getNext();
        }
        addBetween(e, curr, curr.getNext());
        size++;
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i > size) {
            return null;
        }

        if (i == 0) {
            return removeFirst();
        } else {
            Node<E> curr = head.getNext();
            for (int j = 0; j < i - 1; j++) {
                curr = curr.getNext();
            }

            Node<E> temp = curr.getNext();
            curr.setNext(temp.getNext());
            temp.getNext().setPrev(curr);
            size--;
            return temp.getElement();
        }
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.getNext();

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E element = curr.getElement();
            curr = curr.getNext();
            return element;
        }
        // TODO
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        Node<E> succ = n.getNext();
        Node<E> prev = n.getPrev();

        succ.setPrev(prev);
        prev.setNext(succ);
        size--;
        return n.getElement();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }

        Node<E> curr = head.getNext();
        return curr.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        Node<E> curr = tail.getPrev();
        return curr.getElement();
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return remove(head.getNext());
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return remove(tail.getPrev());
    }

    @Override
    public void addLast(E e) {
        addBetween(e, tail.getPrev(), tail);
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, head, head.getNext());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.getNext();
        while (curr != tail) {
            sb.append(curr.getElement());
            curr = curr.getNext();
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void reverseInplace() {
        if (head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }
        Node<E> curr = head.getNext();
        Node<E> temp = null;
        Node<E> oldHead = head;

        while (curr != null) {
            temp = curr.getPrev();
            curr.setPrev(curr.getNext());
            curr.setNext(temp);
            curr = curr.getPrev();
        }

        head = tail;
        tail = oldHead;
    }

    public static void main(String [] args) {
        Integer [] arr = {1,2,3,4,5,6,7,8,9};
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        for(Integer i : arr) dl.addLast(i);
        System.out.println("forward list: " + dl);
        dl.reverseInplace();
        System.out.println("reverse list: " + dl);
    }
}

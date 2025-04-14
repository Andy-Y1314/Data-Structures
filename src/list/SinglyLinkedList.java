package list;

import interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        // TODO
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods
        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> node) {
            next = node;
        }

    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)

    /**
     * The last node of the list
     */
    private Node<E> tail = null;               // last node of the list (or null if empty)

    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() { }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        Node<E> curr = head;
        if (position == 0) {
            return head.getElement();
        }

        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {
        Node<E> curr = head;

        if (position == 0) {
            addFirst(e);
        }

        if (position > size) {
            return;
        }

        for (int i = 0; i < position - 1; i++) {
            curr = curr.getNext();
        }
        Node<E> newNode = new Node<E>(e, curr.getNext());
        curr.setNext(newNode);
        size++;
    }


    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, head);
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<E> curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
        }
        size++;
    }

    @Override
    public E remove(int position) {
        if (position == 0) {
            return removeFirst();
        }
        if (position >= size) {
            return null;
        }

        Node<E> curr = head;

        for (int i = 0; i < position - 1; i++) {
            curr = curr.getNext();
        }
        Node<E> nextNode = curr.getNext();
        curr.setNext(nextNode.getNext());
        nextNode.setNext(null);
        size--;
        return nextNode.getElement();
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = head.getElement();
        head = head.getNext();
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            return null;

        if (size == 1) {
            E element = head.getElement();
            head = null;
            size--;
            return element;
        }

        Node<E> prev = head;
        Node<E> curr = head.getNext();
        while (curr.getNext() != null) {
            curr = curr.getNext();
            prev = curr;
        }

        prev.setNext(null);
        size--;
        return curr.getElement();
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E element = curr.getElement();
            curr = curr.getNext();
            return element;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            curr = curr.getNext();
            if (curr != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //Week 6 lab
    public void reverse() {
        Node<E> curr = head;
        Node<E> prev = null;
        Node<E> next;

        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    //Week 6 lab
    public SinglyLinkedList<E> recursiveCopy() {
        SinglyLinkedList<E> list = new SinglyLinkedList<>();
        list.head = copy(head);
        return list;
    }

    //Week 6 lab
    private Node<E> copy(Node<E> current) {
        if (current == null) {
            return null;
        }
        Node<E> newNode = new Node<>(current.getElement(),null);
        newNode.next = copy(current.getNext());

        return newNode;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);
    }
}

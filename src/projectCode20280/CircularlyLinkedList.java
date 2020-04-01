package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private int size = 0; // number of nodes in list

    private Node<E> tail = null; // tail of list

    public CircularlyLinkedList() { } // constructs an initially empty list

    private class Node<E> {

        private E element;  // reference to the element stored at this node

        /** A reference to te next element in the list */
        private Node<E> next; // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e  the element to be stored
         */
        public Node(E e) {
            element = e;
        }

        // Accessor methods
        public E getElement() { return element; }

        public Node<E> getNext() { return next; }

        // Modifier methods
        public void setNext(Node<E> n) { next = n; }
    }

    /**
     * Creates an iterator
     */
    private class CircularLinkedListIterator implements Iterator<E>
    {
        private Node<E> current;

        private int count = 0;

        public CircularLinkedListIterator() {
            current = tail.getNext();
        }

        public E next() {
            if(!hasNext()) throw new RuntimeException("No such element");

            E res = current.getElement();
            current = current.getNext();
            count++;
            return res;
        }

        public boolean hasNext() {
            return !isEmpty() && count < size();
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {

        if (isEmpty()) { // if list is empty
            throw new RuntimeException("Index does not exist");
        }

        if (i == 0) { // if element is head
            return tail.getNext().getElement();
        }

        Node<E> cur = tail.getNext();

        int count = 0;
        while (cur != tail && count != i) { // walk through list until correct index is found
            cur = cur.next;
            count++;
        }

        if(count == i) { // return element at given index
            return cur.getElement();
        } else {
            throw new RuntimeException("Index doesn't exist");
        }
    }

    @Override
    public void add(int i, E e) {

        Node<E> newNode = new Node<E>(e);

        if (isEmpty() && i != 0) { // if list is empty and index is not head
            throw new RuntimeException("Index does not exist");
        }

        if (i == 0) { // if element is to be new head
            addFirst(e);
        }

        Node<E> cur = tail.getNext();
        Node<E> prev = tail;

        int count = 0;
        while (cur != tail && count != i) { // walk through list until correct index is found
            prev = cur;
            cur = cur.next;
            count++;
        }

        if(count == i && cur == tail) { // if index is the last element
            addLast(e);
        } else if (cur == tail && count < i){ // if index is too large
            throw new RuntimeException("Index does not exist");
        } else if (cur != tail) { // insert new element
            prev.next = newNode;
            newNode.next = cur;
            size++;
        }
    }

    @Override
    public E remove(int i) {

        Node<E> deleted = tail; // element that will be returned

        if (isEmpty()){
            throw new RuntimeException("cannot delete");
        }

        if (i == 0) { // if first element is element to be deleted
            return removeFirst();
        }

        Node<E> cur = tail.getNext();
        Node<E> prev = tail;

        int count = 0;
        while (cur != tail && count != i) { // walk through list until correct index is found
            prev = cur;
            cur = cur.next;
            count++;
        }

        if (cur == tail) {
            throw new RuntimeException("cannot delete");
        }

        deleted = cur;
        // delete cur node
        prev.next = cur.next;
        size--;

        return deleted.element;
    }

    /**
     * Removes an element from the start of the list.
     *
     * @return element deleted
     */
    @Override
    public E removeFirst() {
        Node<E> deleted = tail.getNext(); // store element to be deleted
        tail.setNext(deleted.getNext()); // unlink deleted
        size--;

        return deleted.getElement();
    }

    /**
     * Removes an element from the end of the list.
     *
     * @return element deleted
     */
    @Override
    public E removeLast() {
        if (size == 0) {
            throw new RuntimeException("No such element");
        } else if (size == 1) {
            Node<E> deleted = tail;
            tail = null;
            size--;
            return deleted.getElement();
        } else {
            Node<E> cur = tail.getNext();
            Node<E> prev = tail;

            while (cur != tail) { // walk through list until correct index is found
                prev = cur;
                cur = cur.next;
            }

            Node<E> deleted = cur;

            // delete cur node
            prev.setNext(cur.getNext());
            size--;

            return deleted.getElement();
        }
    }

    /**
     * @return iterator over list
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }

    /**
     *  Adds element to start of the list
     *
     * @param e  Element to be added
     */
    @Override
    public void addFirst(E e) {
        Node<E> newest = new Node<>(e);
        if (size == 0) { // if list is empty create new tail/head
            tail = newest;
            tail.setNext(tail);
        } else {
            newest.setNext(tail.getNext()); // add new node to head
            tail.setNext(newest);
        }
        size++;
    }

    /**
     *  Adds element to end of the list
     *
     * @param e  Element to be added
     */
    @Override
    public void addLast(E e) {
        Node<E> newest = new Node<>(e);
        if (size == 0) { // if list is empty create new tail node
            tail = newest;
            tail.setNext(tail);
        } else {
            newest.setNext(tail.getNext()); // add node as tail
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public void rotate() {
        // TODO (maybe doesn't work?)
        tail = tail.getNext();
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

    public String toString() {
        String result = "[";

        Node<E> cur = tail.getNext();

        boolean first = true;
        while((cur != tail.getNext() || first) && !isEmpty()){
            first = false;
            result += cur.getElement();

            if(cur.getNext() != tail.getNext()){
                result += ", ";
            }
            cur = cur.getNext();
        }
        return result + "]";
    }
}

package projectCode20280;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private int size = 0; // number of elements in the Linked List

    /** Sentinel node at the beginning of the list */
    private Node<E> header;             // header sentinel

    /** Sentinel node at the end of the list */
    private Node<E> trailer;            // trailer sentinel

    /** Constructs a new empty list */
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, null, header);
        header.setNext(trailer);
    }

    private static class Node<E> {

        private E element;  // reference to the element stored at this node

        /** A reference to te next element in the list */
        private Node<E> next; // reference to the subsequent node in the list

        /** A reference to te preceding element in the list */
        private Node<E> prev; // reference to the previous node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e  the element to be stored
         * @param n  reference to a node that should follow the new node
         * @param p  reference to the previous node
         */
        public Node(E e, Node<E> n, Node<E> p) {
            element = e;
            next = n;
            prev = p;
        }

        // Accessor methods
        public E getElement() { return element; }

        public Node<E> getNext() { return next; }

        public Node<E> getPrev() { return prev; }

        // Modifier methods
        public void setNext(Node<E> n) { next = n; }

        public void setPrev(Node<E> p) { prev = p; }
    }

    /**
     * Creates an iterator
     */
    private class DoublyLinkedListIterator implements Iterator<E>
    {
        private Node<E> current;

        public DoublyLinkedListIterator() {
            current = header.getNext();
        }

        public E next() {
            if(!hasNext()) throw new RuntimeException("No such element");

            E res = current.getElement();
            current = current.getNext();
            return res;
        }

        public boolean hasNext() {
            return current != trailer;
        }
    }

    /** Adds an element to the Linked List between the given nodes.
     * The given nodes should neighbour each other prior to the call.
     *
     * @param e the new element to add
     * @param predecessor node just before the location where the new node will be inserted
     * @param successor node just before the location where the new node will be inserted
     */
    private void addBetween(E e, Node<E> successor, Node<E> predecessor) {
        // create and link a new node
        Node<E> newest = new Node<>(e, successor, predecessor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() { // if header's next element is trailer then list is empty
        return header.getNext() == trailer;
    }

    /**
     * Finds element at given index
     *
     * @param i index of element
     * @return element at given index
     */
    @Override
    public E get(int i) {

        if (isEmpty()) { // if list is empty
            throw new RuntimeException("index does not exist");
        }

        Node<E> cur = new Node<>(header.getNext().getElement(), header.getNext().getNext(), header);

        int count = 0;
        while (cur != trailer && count != i) { // walk through list until correct index is found
            cur = cur.next;
            count++;
        }

        if(count == i) { // return element at given index
            return cur.getElement();
        } else {
            throw new RuntimeException("Index doesn't exist");
        }
    }

    /**
     * Adds a given element at a given index
     *
     * @param i index of element
     * @param e element to be added
     */
    @Override
    public void add(int i, E e) {

        if (isEmpty() && i != 0) { // if list is empty and index is not head
            throw new RuntimeException("index does not exist");
        }

        if (i == 0) { // if element is to be new head
            addFirst(e);
            return;
        }

        Node<E> cur = header.getNext();

        int count = 0;
        while (cur != trailer && count != i) { // walk through list until correct index is found
            cur = cur.next;
            count++;
        }

        if(count == i && cur == trailer) { // if index is the last element
            addLast(e);
        } else if (cur == trailer && count < i){ // if index is too large
            throw new RuntimeException("Index does not exist");
        } else if (cur != trailer) { // insert new element
            addBetween(e, cur, cur.prev);
        }
    }

    /**
     * Removes the node at the given index and returns its element.
     *
     * @param i index of the element to remove
     * @return removed element
     */
    @Override
    public E remove(int i) {

        Node<E> deleted; // this will save the element that gets deleted

        if (isEmpty()){
            throw new RuntimeException("cannot delete");
        }

        Node<E> cur = header.getNext();

        int count = 0;
        while (cur != trailer && count != i) { // walk through list until correct index is found
            cur.prev = cur;
            cur = cur.next;
            count++;
        }

        if (cur == trailer) {
            throw new RuntimeException("cannot delete");
        }

        Node<E> predecessor = cur.getPrev();
        Node<E> successor = cur.getNext();
        deleted = cur;

        // delete cur node
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;

        return deleted.element;
    }

    /**
     * @return iterator over list
     */
    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    /**
     * Removes the node at start of the list
     *
     * @return removed element
     */
    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        return remove(0);
    }

    /**
     * Removes the node at end of the list
     *
     * @return removed element
     */
    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        return remove(size() - 1);
    }

    /**
     * Adds an element to the front of the list
     * @param e the new element in the list
     */
    @Override
    public void addFirst(E e) {
        addBetween(e, header.getNext(), header);
    }

    /**
     * Adds an element to the end of the list
     * @param e the new element in the list
     */
    @Override
    public void addLast(E e) {
        addBetween(e, trailer, trailer.getPrev());
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for(Integer e: ll) {
            System.out.println("value: " + e);
        }
    }

    public String toString() {
        String result = "[";

        Node<E> cur = header.getNext();

        while(cur != trailer){
            result += cur.getElement();

            if(cur.getNext() != trailer){
                result += ", ";
            }
            cur = cur.getNext();
        }
        return result + "]";
    }


}

package projectCode20280;
import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private int size = 0; // number of elements in the Linked List

    private Node<E> head; // node at the front of the Linked List

    private static class Node<E> {

        private E element;  // reference to the element stored at this node

        private Node<E> next; // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e  the element to be stored
         * @param n  reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
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
    private class LinkedListIterator implements Iterator<E>
    {
        private Node<E> current;

        public LinkedListIterator() {
            current = head;
        }

        public E next() {
            if(!hasNext()) throw new RuntimeException("No such element");

            E res = current.getElement();
            current = current.getNext();
            return res;
        }

        public boolean hasNext() {
            return current != null;
        }
    }

    /**
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * gets the element at index i
     *
     * @param i, index of element to return
     *
     * @return element at specified index
     */
    @Override
    public E get(int i) {

        if (isEmpty()) { // if list is empty
            throw new RuntimeException("Index does not exist");
        }

        if (i == 0) { // if element is head
            return head.getElement();
        }

        Node<E> cur = head;

        int count = 0;
        while (cur.getNext() != null && count != i) { // walk through list until correct index is found
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
     * remove element at index i
     *
     * @param i, index of new element
     * @param e, data of new node
     */
    @Override
    public void add(int i, E e) {

        Node<E> newNode = new Node<E>(e, null);

        if (isEmpty() && i != 0) { // if list is empty and index is not head
            throw new RuntimeException("Index does not exist");
        }

        if (i == 0) { // if element is to be new head
            addFirst(e);
        }

        Node<E> cur = head;
        Node<E> prev = null;

        int count = 0;
        while (cur != null && count != i) { // walk through list until correct index is found
            prev = cur;
            cur = cur.next;
            count++;
        }

        if(count == i && cur == null) { // if index is the last element
            addLast(e);
        } else if (cur == null && count < i){ // if index is too large
            throw new RuntimeException("Index does not exist");
        } else if (cur != null) { // insert new element
            prev.next = newNode;
            newNode.next = cur;
            size++;
        }
    }

    /**
     * remove element at index i
     *
     * @param i, index of element to delete
     *
     * @return deleted element
     */
    @Override
    public E remove(int i) {

        Node<E> deleted = head; // element that will be returned

        if (isEmpty()){
            throw new RuntimeException("cannot delete");
        }

        if (i == 0) { // if head is element to be deleted
            head = head.next;
            size--;
            return deleted.element;
        }

        Node<E> cur = head;
        Node<E> prev = null;

        int count = 0;
        while (cur != null && count != i) { // walk through list until correct index is found
            prev = cur;
            cur = cur.next;
            count++;
        }

        if (cur == null) {
            throw new RuntimeException("cannot delete");
        }

        deleted = cur;
        // delete cur node
        prev.next = cur.next;
        size--;

        return deleted.element;
    }

    /**
     * @return iterator over list
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * @return size of list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes the element from the start of the list.
     *
     * @return element deleted
     */
    @Override
    public E removeFirst() {

        Node<E> deleted = head; // element that will be returned

        if (isEmpty()){
            throw new RuntimeException("cannot delete");
        } else {
            head = head.next; // unlink head
            size--;
        }
        return deleted.element;
    }

    /**
     * Removes an element from the end of the list.
     *
     * @return element deleted
     */
    @Override
    public E removeLast() {

        Node<E> deleted; // element that will be returned

        if (isEmpty()){
            throw new RuntimeException("cannot delete");
        } else {

            Node<E> cur = head; // start traversal at head
            Node<E> prev = null;

            while (cur.getNext() != null) { // traverse List to the last node
                prev = cur;
                cur = cur.next;
            }

            deleted = cur;

            // delete cur node
            prev.next = cur.next;
            size--;
        }
        return deleted.element;
    }


    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    @Override
    public void addFirst(E e) {
        head = new Node<E>(e, head); // create and link a new node
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    @Override
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null); // node will become tail
        Node<E> last = head;
        if(last == null) {
            head = newest;
        } else {
            while (last.getNext() != null) { // traverse List to the last node
                last = last.next;
            }
            last.setNext(newest); // new node after current tail
            size++;
        }
    }

    /**
     * @return the first element in the list
     */
    public E first() {
        if(isEmpty()) {
            return null;
        } else {
            return get(0);
        }
    }
    
    public static void main(String[] args) {
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
        for (String s : alphabet) {
            sll.addFirst(s);
            sll.addLast(s);
        }
        System.out.println(sll.toString());

        sll.removeFirst();
        System.out.println(sll.toString());

        sll.removeLast();
        System.out.println(sll.toString());

        sll.remove(2);
        System.out.println(sll.toString());

        for (String s : sll) {
            System.out.print(s + ", ");
        }
    }

    public String toString() {
        String result = "[";

        Node<E> cur = head;

        while(cur != null){
            result += cur.getElement();

            if(cur.getNext() != null){
                result += ", ";
            }
            cur = cur.getNext();
        }
        return result + "]";
    }
}

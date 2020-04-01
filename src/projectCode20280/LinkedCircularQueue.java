package projectCode20280;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

    Node<E> back;

    Node<E> front;

    private int size;

    public LinkedCircularQueue() {
        back = front = null;
        size = 0;
    }

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

    public static void main(String[] args) {
        // Intialising an array queue
        LinkedCircularQueue<Integer> que = new LinkedCircularQueue<>();

        // enqueue method to insert data
        que.enqueue(10);
        que.enqueue(20);
        que.enqueue(30);
        que.enqueue(40);

        System.out.println(que);

        // remove() method : to get head
        System.out.println("Head element removed: " + que.dequeue());

        System.out.println("The array is now: "+ que);

        que.enqueue(21);
        que.enqueue(22);
        que.enqueue(23);
        System.out.println("After 3 more enqueue: "+ que);

        que.dequeue();
        que.dequeue();
        que.dequeue();
        System.out.println("After 3 deque: "+ que);
    }

    /**
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if stack is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds given element to end of queue
     *
     * @param e element to be added to queue
     */
    @Override
    public void enqueue(E e) {
        Node<E> newest = new Node<>(e);
        newest.next = front; // make new end node point to front of queue
        if(isEmpty()) {
            front = back = newest; // if list is empty point back and front to only node
        } else {
            back.next = newest; // make new node back of queue
            back = newest;
        }
        size++;
    }

    /**
     * Returns but does not delete the front element
     *
     * @return element at front of list
     */
    @Override
    public E first() {
        return front.getElement();
    }

    /**
     * Returns but and deletes the front element
     *
     * @return deleted element from front of queue
     */
    @Override
    public E dequeue() {
        Node<E> deleted = front;

        if(isEmpty()) {
            return null;
        } else {
            back.setNext(front.next); // unlink front
            front = front.next;
            size--;

            return deleted.element;
        }
    }

    public String toString() {
        String result = "[";

        Node<E> cur = back.getNext();

        boolean first = true;
        while((cur != back.getNext() || first) && !isEmpty()){
            first = false;
            result += cur.getElement();

            if(cur.getNext() != back.getNext()){
                result += ", ";
            }
            cur = cur.getNext();
        }
        return result + "]";
    }
}

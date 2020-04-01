package projectCode20280;

public class LinkedQueue<E> implements Queue<E> {

    Node<E> front; // front of queue

    Node<E> rear; // back of queue

    private int size;

    public LinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    private static class Node<E> {

        private E element;  // reference to the element stored at this node

        /** A reference to the subsequent node in the queue */
        private Node<E> next; // reference to the next node

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
    }

    public static void main(String[] args) {
        // Intialising an array queue
        LinkedQueue<Integer> que = new LinkedQueue<>();

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
        Node<E> newest = new Node<>(e, null);

        if(isEmpty()) {     // if queue was empty then point front and rear to new node
            front = rear = newest;
        } else {
            rear.next = newest; // add new node to end of the queue
            rear = newest;
        }
        size++;
    }

    /**
     * Returns but does not remove first element in the queue
     *
     * @return first element in the queue
     */
    @Override
    public E first() {
        return front.element;
    }

    /**
     * Removes and returns first element from queue
     *
     * @return first element in the queue
     */
    @Override
    public E dequeue() {

        Node<E> deleted;
        if(isEmpty()) {
            return null;
        } else {
            deleted = front;
            front = front.next; // unlink front element
            size--;
        }

        if(front == null) { // if list is now empty make rear point to null
            rear = null;
        }
        return deleted.element;
    }

    public String toString() {
        String result = "[";

        Node<E> cur = front;

        while(cur != null){
            result += cur.element;

            if(cur.next != null){
                result += ", ";
            }
            cur = cur.next;
        }
        return result + "]";
    }
}

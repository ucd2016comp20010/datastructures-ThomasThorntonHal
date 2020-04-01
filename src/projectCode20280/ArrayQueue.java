package projectCode20280;

public class ArrayQueue<E> implements Queue<E> {

    public static final int CAPACITY = 1000;    // maximum size of the stack

    private E[] data;                           // generic array for data in stack

    private int front = 0;                      // index of top element in stack

    private int rear = -1;                      // index of top element in stack

    private int size = 0;                       // number of elements in the array

    private int maxSize;                        // given size (defaults to CAPACITY)

    public ArrayQueue() {
        this(CAPACITY);                         // constructs stack with default capacity
        maxSize = CAPACITY;
    }

    @SuppressWarnings({"unchecked"})
    public ArrayQueue(int capacity) {           // constructs stack with given capacity
        data = (E[]) new Object[capacity];      // safe cast; compiler may give warning
        maxSize = capacity;
    }

    public static void main(String[] args) {
        // Intialising an array queue
        ArrayQueue<Integer> que = new ArrayQueue<>(10);

        // enqueue method to insert data
        que.enqueue(10);
        que.enqueue(20);
        que.enqueue(30);
        que.enqueue(40);
        que.enqueue(50);

        // first() method : to get head
        System.out.println("\nHead element : " + que.first());

        System.out.println("\n" + que);

        // push() method :
        que.enqueue(300);
        que.enqueue(400);
        que.enqueue(500);

        System.out.println("\n" + que);

        // remove() method : to get head
        System.out.println("Head element remove : " + que.dequeue());

        System.out.println("The array after dequeue: "+ que);

        que.enqueue(24);
        que.enqueue(25);
        que.enqueue(26);
        System.out.println("After 3 further enqueue: "+ que);

        System.out.println("First: " + que.first());

        System.out.print("\nQueue overflow expected: ") ;que.enqueue(3500);
    }

    /**
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if array is empty
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
        if (size() == maxSize) { // if queue is full
            System.out.println("Queue overflow");
            System.exit(-1);
        } else {
            rear = (front + size()) % maxSize; // rear is incremented and loops if reaches end of array
            data[rear] = e;
            size++;
        }

    }

    /**
     * Returns but does not remove first element in the queue
     *
     * @return first element in the queue
     */
    @Override
    public E first() {
        return data[front];
    }

    /**
     * Removes and returns first element from queue
     *
     * @return first element in the queue
     */
    @Override
    public E dequeue() {

        if( isEmpty( ) ) {
            return null; // if there's noting to deque do nothing
        } else {
            E deleted = data[front];

            front = (front + 1) % maxSize; // increment front and loop if necessary
            size--;

            return deleted;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = front; i != rear; i = (i + 1) % maxSize) {
            sb.append(data[i].toString());
            if(i != rear){
                sb.append(",");
            }
        }
        sb.append(data[rear].toString());
        sb.append(']');
        return sb.toString();
    }
}

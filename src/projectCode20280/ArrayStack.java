package projectCode20280;

public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000;    // maximum size of the stack

    private E[] data;                           // generic array for data in stack

    private int t = -1;                         // index of top element in stack

    private int maxSize;                        // given size (defaults to CAPACITY)

    public ArrayStack() {
        this(CAPACITY);                         // constructs stack with default capacity
        maxSize = CAPACITY;
    }

    @SuppressWarnings({"unchecked"})
    public ArrayStack(int capacity) {           // constructs stack with given capacity
        data = (E[]) new Object[capacity];      // safe cast; compiler may give warning
        maxSize = capacity;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.push(70);
        stack.push(80);
        stack.push(90);
        stack.push(100);
        stack.push(110);

        System.out.println("stack size: " + stack.size());
        System.out.println(stack);

        System.out.println( stack.pop() );
        System.out.println( stack.pop() );
        System.out.println( stack.pop() );

        System.out.println( stack );

    }

    /**
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return (t + 1);
    }

    /**
     * @return true if stack is empty
     */
    @Override
    public boolean isEmpty() {
        return (t == -1);
    }

    /**
     *  Adds new element to the top of the stack
     *
     * @param e element to be added to stack
     */
    @Override
    public void push(E e) {
        if(size() == maxSize) {
            System.out.println("Stack overflow");
            System.exit(-1);
        } else {
            t++;
            data[t] = e;
        }
    }

    /**
     *  Returns but does not remove element at top of stack
     *
     * @return element at top of stack
     */
    @Override
    public E top() {
        return data[t];
    }

    /**
     * Removes and returns the top element from the stack
     *
     * @return element that was on top of stack
     */
    @Override
    public E pop() {
        if(isEmpty()) { // check if list is empty
            return null;
        } else {
            E value = data[t]; // save value to be deleted
            t--;
            return value;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < size() ;i++) {
            sb.append(data[i].toString());
            if(i < size() - 1){
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}

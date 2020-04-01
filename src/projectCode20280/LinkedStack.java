package projectCode20280;

public class LinkedStack<E> implements Stack<E> {

    /** Storage for elements in stack */
    private SinglyLinkedList<E> list = new SinglyLinkedList<>(); // an empty list

    public LinkedStack() { } // new stack relies on the initially empty list

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LinkedStack<Integer> stack = new LinkedStack<>();

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

        System.out.println( "pop: " + stack.pop() );
        System.out.println( "pop: " + stack.pop() );
        System.out.println( "pop: " + stack.pop() );

        System.out.println( stack );

    }

    /**
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * @return true if stack is empty
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Adds element to top of the stack
     *
     * @param e element to be added
     */
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    /**
     * Returns but does not delete the top element on the stack
     *
     * @return top element of the stack
     */
    @Override
    public E top() {
        return list.first();
    }

    /**
     * Returns and deletes top element form the stack
     *
     * @return deleted element
     */
    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public String toString() {
        String result = "[";

        for(int i = 0; i < size(); i++){
            result += list.get(size() - i - 1); /* this toString method prints the array backwards
                                                    just to keep consistency with ArrayStack */
            if(i < size() - 1){
                result += ", ";
            }
        }
        return result + "]";
    }
}

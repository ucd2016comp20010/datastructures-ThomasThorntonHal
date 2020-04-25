package projectCode20280;

import java.util.Iterator;

public class LinkedDeque<E> implements Deque<E> {

	DoublyLinkedList<E> deque = new DoublyLinkedList<>();

	public static void main(String[] args) {
		LinkedDeque<String> DEque = new LinkedDeque<>();

		DEque.addLast("Element 1 (Tail)");
		DEque.addFirst("Element 2 (Head)");
		DEque.addLast("Element 3 (Tail)");

		System.out.println(DEque + "\n");

		// Iterate through the queue elements
		System.out.println("Standard Iterator");
		Iterator iterator = DEque.iterator();
		while (iterator.hasNext())
			System.out.println("\t" + iterator.next());

		System.out.println("\nFist element: " + DEque.first());
		System.out.println("Last element: " + DEque.last());

		System.out.println("\nRemove First element: " + DEque.removeFirst());
		System.out.println("Remove Last element: " + DEque.removeLast());

		System.out.println("\nCurrent queue: " + DEque);
	}

	/**
	 * @return number of elements in queue
	 */
	@Override
	public int size() {
		return deque.size();
	}

	/**
	 * @return true if queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return deque.isEmpty();
	}

	/**
	 * Returns but does not remove element at start of queue
	 *
	 * @return element at start of queue
	 */
	@Override
	public E first() {
		return deque.get(0);
	}

	/**
	 * Returns but does not remove element at end of queue
	 *
	 * @return element at end of queue
	 */
	@Override
	public E last() {
		return deque.get(size() - 1);
	}

	/**
	 * Adds element to start of queue
	 *
	 * @param e element added to start of queue
	 */
	@Override
	public void addFirst(E e) {
		deque.addFirst(e);

	}

	/**
	 * Adds element to end of queue
	 *
	 * @param e element added to end of queue
	 */
	@Override
	public void addLast(E e) {
		deque.addLast(e);

	}

	/**
	 * Removes the node at start of the queue
	 *
	 * @return removed element
	 */
	@Override
	public E removeFirst() {
		return deque.removeFirst();
	}

	/**
	 * Removes the node at end of the queue
	 *
	 * @return removed element
	 */
	@Override
	public E removeLast() {
		return deque.removeLast();
	}

	/**
	 * @return iterator over queue
	 */
	public Iterator<E> iterator() {
		return deque.iterator();
	}

	public String toString() {
		return deque.toString();
	}
}

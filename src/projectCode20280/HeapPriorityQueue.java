package projectCode20280;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

  ArrayList<Entry<K, V>> heap = new ArrayList<>();

  /** Creates an empty priority queue based on the natural ordering of its keys. */
  public HeapPriorityQueue() { super(); }

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the priority queue
   */
  public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

  /**
   * Creates a priority queue initialized with the respective
   * key-value pairs.  The two arrays given will be paired
   * element-by-element. They are presumed to have the same
   * length. (If not, entries will be created only up to the length of
   * the shorter of the arrays)
   * @param keys an array of the initial keys for the priority queue
   * @param values an array of the initial values for the priority queue
   */
  public HeapPriorityQueue(K[] keys, V[] values) {
    super();
    for (int j=0; j < Math.min(keys.length, values.length); j++)
      heap.add(new PQEntry<>(keys[j], values[j]));
    heapify();
  }

  // protected utilities
  protected int parent(int j) {
    return (j -1)/2;
  }

  protected int left(int j) {
    return (2*j + 1);
  }

  protected int right(int j) {
    return (2*j + 2);
  }

  protected boolean hasLeft(int j) {
    return left(j) < heap.size();
  }

  protected boolean hasRight(int j) {
    return right(j) < heap.size();
  }

  /** Exchanges the entries at indices i and j of the array list. */
  protected void swap(int i, int j) {
    Entry<K, V> old = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, old);
  }

  /** Moves the entry at index j higher, if necessary, to restore the heap property. */
  protected void upheap(int j) {
    while(j > 0) {
      int p = parent(j);
      if(compare(heap.get(j), heap.get(p)) >= 0) {
        // finished
        break;
      }
      swap(j, p); // swap position j with its parent
      j = p; // continue the upheap from the position of the parent
    }
  }

  /** Moves the entry at index j lower, if necessary, to restore the heap property. */
  protected void downheap(int j) {
    while(left(j) < heap.size()) {
      int left = left(j);
      int right = right(j);

      int child = left;
      if(right < heap.size() && compare(heap.get(left), heap.get(right)) > 0) {
        child = right;
      }
      if(compare(heap.get(child), heap.get(j)) >= 0) {
        break; // heap property is restored - we're done
      }
      swap(j, child);
      j = child;
    }
  }

  /** Performs a bottom-up construction of the heap in linear time. */
  protected void heapify() {
    int startIndex = parent(size() - 1); // start at PARENT of last entry
    for (int j=startIndex; j >= 0; j--) // loop until processing the root
      downheap(j);
  }

  // public methods

  /**
   * Returns the number of items in the priority queue.
   * @return number of items
   */
  @Override
  public int size() { return heap.size(); }

  /**
   * Returns (but does not remove) an entry with minimal key.
   * @return entry having a minimal key (or null if empty)
   */
  @Override
  public Entry<K,V> min() {
    return heap.get(0);
  }

  /**
   * Inserts a key-value pair and return the entry created.
   * @param key     the key of the new entry
   * @param value   the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  @Override
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
    Entry<K, V> n = new PQEntry<K, V>(key, value);

    heap.add(n);
    upheap(heap.size() - 1);

    return n;
  }

  /**
   * Removes and returns an entry with minimal key.
   * @return the removed entry (or null if empty)
   */
  @Override
  public Entry<K,V> removeMin() {
    if(heap.isEmpty()) {
      return null;
    }
    Entry<K, V> old = heap.get(0);
    swap(0, heap.size() - 1);
    heap.remove(heap.size() - 1);
    downheap(0);

    return old;
  }

  /** Used for debugging purposes only */
  private void sanityCheck() {
    for (int j=0; j < heap.size(); j++) {
      int left = left(j);
      int right = right(j);

      if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
        Entry<K, V> e_left = heap.get(left);
        Entry<K, V> e_right = heap.get(right);
        Entry<K, V> e_j = heap.get(j);
        // System.out.println("Invalid left child relationship");
        System.out.println("Invalid left <" + e_left + ", " + e_j + ", " + e_right + ">");
      }
      if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
        Entry<K, V> e_left = heap.get(left);
        Entry<K, V> e_right = heap.get(right);
        Entry<K, V> e_j = heap.get(j);
        // System.out.println("Invalid right child relationship");
        System.out.println("Invalid right <" + e_left + ", " + e_j + ", " + e_right + ">");
      }
    }
  }

  public String toString() {
    return heap.toString();
  }

  public String toBinaryTreeString() {
    LinkedBinaryTree< Entry<K, V> > bt = new LinkedBinaryTree<>();
    bt.createLevelOrder(heap);
    BinaryTreePrinter<Entry<K, V>> btp = new BinaryTreePrinter<>(bt);

    return btp.print();
  }

  public static void main(String[] args) {
    // HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(new MaxComparator<>());


    // pq.insert(0, 0);
    // Integer[] arr = new Integer[] {20, 6, 7, 8, 12, 10, 11, 25, 21, 17, 19, 18};
    Random rnd = new Random();
    int n = 32;
    // java.util.List<Integer> arr = rnd.ints(1, 1000).limit(n).distinct().boxed().collect(Collectors.toList());
    // Integer[] arr = new Integer[] {432, 590, 429, 422, 497, 333, 989, 475, 484, 46, 752, 491, 564, 54, 683, 239, 496, 777, 680, 332, 355, 901, 724, 599, 834, 78, 349, 578, 144, 318, 33, 623};
    Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    Integer[] arr2 = new Integer[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue(arr, arr2);

        /*
        for(Integer i : arr) {
            pq.insert(i, 0);
        }

        System.out.println(pq.toBinaryTreeString());
        pq.sanityCheck();

        System.out.println(pq.min());
        System.out.println(pq.removeMin());
        System.out.println(pq.toBinaryTreeString());
        pq.sanityCheck();

         */

    System.out.println(pq.toBinaryTreeString());
    pq.sanityCheck();

    System.out.println("======");
    while(!pq.isEmpty()) {
      pq.removeMin();
      System.out.println(pq.toBinaryTreeString());
    }
  }
}

package projectCode20280;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplayTreeMap<K,V> extends TreeMap<K,V> {

	protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();


	/** Constructs an empty map using the natural ordering of keys. */
	public SplayTreeMap() { super(); }

	/**
	 * Constructs an empty map using the given comparator to order keys.
	 * @param comp comparator defining the order of keys in the map
	 */

	//public SplayTreeMap(DefaultComparator<K> comp) { super(comp); }

	/** Utility used to rebalance after a map operation. */
	private void splay(Position<Entry<K,V>> p) {
		while(!isRoot(p)) {
			Position<Entry<K,V>> parent = parent(p);
			Position<Entry<K,V>> gparent = parent(parent);
			if (gparent == null) rotate(p);									//zig
			else if ((parent == left(gparent)) == (p == left(gparent))) {	//zig zig
				rotate(parent);
				rotate(p);
			} else {														//zig zag
				rotate(p);
				rotate(p);
			}
		}
	}

	/** Overrides the TreeMap rebalancing hook that is called after a node access. */
	@Override
	protected void rebalanceAccess(Position<Entry<K,V>> p) {
		if (isExternal(p)) p = parent(p);
		if (p != null) splay(p);
	}

	/** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	@Override
	protected void rebalanceInsert(Position<Entry<K,V>> p) { splay(p); }

	/** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	@Override
	protected void rebalanceDelete(Position<Entry<K,V>> p) {
		if (!isRoot(p)) splay(parent(p));
	}

	public static void main(String[] args) {
		SplayTreeMap<Integer, Integer> treeMap = new SplayTreeMap<>();

		//treeMap.put(0, 0);
		//treeMap.put(-1, 0);
		//treeMap.put(1, 0);

		Integer[] arr = new Integer[] {44, 17, 88, 8, 32, 65, 97, 28, 54, 82, 93, 21, 29, 76, 80};
		for(Integer i : arr) treeMap.put(i, i);
		//System.out.println("tree map " + treeMap.toString());


        /*
        Random rnd = new Random();
        int n = 16;
        java.util.List<Integer> rands = rnd.ints(1, 1000).limit(n).distinct().boxed().collect(Collectors.toList());

        for(Integer i : rands) {
            treeMap.put(i, i);
        }
        */
		System.out.println("tree entries: " + treeMap.entrySet());

		BinaryTreePrinter<Entry<Integer, Integer>> btp = new BinaryTreePrinter<>(treeMap.tree);
		System.out.println(btp.print());

		treeMap.remove(88);

		System.out.println(btp.print());
		System.out.println("tree entries after removal: " + treeMap.entrySet());
		System.out.println("Get(32) " + treeMap.get(32));
		System.out.println("First Entry (expect 8): " + treeMap.firstEntry());
		System.out.println("Last Entry (expect 97): " + treeMap.lastEntry());
		System.out.println("Floor Entry 20 (expect 17): " + treeMap.floorEntry(20));
		System.out.println("Lower Entry 82 (expect 80): " + treeMap.lowerEntry(82));
		System.out.println("Ceiling Entry 82 (expect 82): " + treeMap.ceilingEntry(82));
		System.out.println("Ceiling Entry 60 (expect 65): " + treeMap.ceilingEntry(60));
		System.out.println("Higher Entry 82 (expect 93): " + treeMap.higherEntry(82));
		System.out.println(treeMap.entrySet());

	}
}
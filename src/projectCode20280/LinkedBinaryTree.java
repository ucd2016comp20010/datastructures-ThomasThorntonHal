package projectCode20280;

import java.util.ArrayList;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    /** Nested static class for a binary tree node. */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() throws IllegalStateException { return element; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }
        public Node<E> getParent() { return parent; }

        // modifiers
        public void setElement(E e) { element = e; }
        public void setLeft(Node<E> n) { left = n; }
        public void setRight(Node<E> n) { right = n; }
        public void setParent(Node<E> n) { parent = n; }

        public String toString() {
            return new StringBuilder("").append(element).toString();
        }
    }

    /** Factory function to create a new node storing element e. */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    /** The root of the binary tree */
    protected Node<E> root = null;     // root of the tree

    /** The number of nodes in the binary tree */
    private int size = 0;              // number of nodes in the tree

    // constructor
    /** Constructs an empty binary tree. */
    public LinkedBinaryTree() { }      // constructs an empty binary tree

    // nonpublic utility
    /**
     * Verifies that a Position belongs to the appropriate class, and is
     * not one that has been previously removed. Note that our current
     * implementation does not actually verify that the position belongs
     * to this particular list instance.
     *
     * @param p   a Position (that should belong to this tree)
     * @return    the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;       // safe cast
        if (node.getParent() == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)
    /**
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p    A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> n = validate(p);
        return n.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> n = validate(p);
        return n.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> n = validate(p);
        return n.getRight();
    }

    // update methods supported by this class
    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e   the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if(root != null) {
            throw new IllegalStateException("root already exists");
        }
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e){
        //recursively add from root
        root = addRecursive(root, e);
        ++size;
    }

    //recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e){
        if(p == null) {
            return createNode(e, null, null, null);
        }

        if((Integer) e < (Integer) p.element) { // find the correct location for the new element
            p.left = addRecursive(p.left, e);
        } else if ((Integer) e > (Integer) p.element) {
            p.right = addRecursive(p.right, e);
        } else {
            return p;
        }

        return p;
    }


    /**
     * Creates a new left child of Position p storing element e and returns its Position.
     *
     * @param p   the Position to the left of which the new element is inserted
     * @param e   the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("Left child of p already exists");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);;
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its Position.
     *
     * @param p   the Position to the right of which the new element is inserted
     * @param e   the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("Right child of p already exists");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);;
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced element.
     *
     * @param p   the relevant Position
     * @param e   the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> n = validate(p);
        E old = n.getElement();
        n.setElement(e);
        return old;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p   a leaf of the tree
     * @param t1  an independent tree whose structure becomes the left child of p
     * @param t2  an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> pos = validate(p);
        if(isInternal(p))
            throw new IllegalArgumentException("Node is not a leaf");

        // add left and right trees to given node
        if(pos.left == null){
            pos.left = t1.root;
        }
        if(pos.right == null){
            pos.right = t2.root;
        }
        else {
            System.out.print("is not leaf");
        }

        size = size + t1.size() + t2.size();
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p   the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> pos = validate(p);
        Node<E> parent = ((Node<E>) p).parent; // parent of given node
        if (pos.left != null && pos.right != null) // if p has 2 children throw error
            throw new IllegalArgumentException("Too many children");
        Node<E> child = (pos.getLeft() != null ? pos.getLeft() : pos.getRight() );
        if(pos == root) {
            root = child;
        } else {
            if (child == null) { // if p has no children it can be unlinked from its parent
                if (parent.getLeft() == p) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                pos.setParent(null);
            } else {
                if (parent.getLeft() == p) {
                    if (pos.left != null) { // replace parents child with deleted nodes child
                        parent.setLeft(pos.left);
                    } else {
                        parent.setLeft(pos.right);
                    }
                } else {
                    if (pos.left != null) { // replace parents child with deleted nodes child
                        parent.setRight(pos.left);
                    } else {
                        parent.setRight(pos.right);
                    }
                }
            }
        }
        pos.setParent(null);
        pos.setLeft(null);
        pos.setRight(null);
        size--;
        return pos.element;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int count = 0;
        for(Position<E> p : positions()) {
            if(count == 0){
                count++;
            } else {
                sb.append(", ");
            }
            sb.append(p.getElement());
        }
        sb.append("]");
        return sb.toString();
    }

    public void createLevelOrder(ArrayList<E> arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(ArrayList<E> arr, Node<E> p, int i) {
        if(i < arr.size()) {
            Node<E> n = createNode(arr.get(i), p, null, null);
            n.left = createLevelOrderHelper(arr, n.left, 2*i + 1);
            n.right = createLevelOrderHelper(arr, n.right, 2*i + 2);
            size++;
            return n;
        }
        return p;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        if(i < arr.length) {
            Node<E> n = createNode(arr[i], p, null, null);
            n.left = createLevelOrderHelper(arr, n.left, 2*i + 1);
            n.right = createLevelOrderHelper(arr, n.right, 2*i + 2);
            size++;
            return n;
        }
        return p;
    }

    public static void main(String [] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        //Direct construction of the tree
        Position<Integer> root = bt.addRoot(12);
        Position<Integer> p1 = bt.addLeft(root, 25);
        Position<Integer> p2 = bt.addRight(root, 31);

        Position<Integer> p3 = bt.addLeft(p1, 58);
        bt.addRight(p1, 36);

        Position<Integer> p5 = bt.addLeft(p2, 42);
        bt.addRight(p2, 90);

        Position<Integer> p4 = bt.addLeft(p3, 62);
        bt.addRight(p3, 75);


        LinkedBinaryTree<Integer> vt = new LinkedBinaryTree<Integer>();

        Position<Integer> root2 = vt.addRoot(112);
        Position<Integer> v1 = vt.addLeft(root2, 125);
        Position<Integer> v2 = vt.addRight(root2, 131);

        Position<Integer> v3 = vt.addLeft(v1, 158);
        vt.addRight(v1, 136);

        Position<Integer> v5 = vt.addLeft(v2, 142);
        vt.addRight(v2, 190);

        Position<Integer> v4 = vt.addLeft(v3, 162);
        vt.addRight(v3, 175);



        // for(int i : arr) {
        //     bt.insert(i);
        // }

        //level order construction
       /* Integer [] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
        bt.createLevelOrder(arr);
        */

        System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
        System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
        System.out.println("bt postorder: " + bt.size() + " " + bt.postorder());

        System.out.println("bt height: " +  bt.height(bt.root()));
        System.out.println("root depth: " +  bt.depth(bt.root()));
        Position<Integer> p6 = bt.addLeft(p4, 2);
        System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
        System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
        System.out.println("bt postorder: " + bt.size() + " " + bt.postorder());
        System.out.println("bt height: " +  bt.height(bt.root()));
        System.out.println("\nRemoved: " + bt.remove(p4));
        System.out.println("new bt inorder: " + bt.size() + " " + bt.inorder());
        System.out.println("new bt height: " +  bt.height(bt.root()));

        System.out.println("vt inorder: " + vt.size() + " " + vt.inorder());
        // System.out.println("62 depth: " +  bt.depth(p4));
        // System.out.println("42 depth: " +  bt.depth(p5));

        LinkedBinaryTree<Integer> newTree = new LinkedBinaryTree<Integer>();
        Position<Integer> rootNew = newTree.addRoot(1000);
        newTree.attach(rootNew, bt, vt);
        System.out.println("newTree inorder: " + newTree.size() + " " + newTree.inorder());
    }
}

package Lab3;

import Lab3.LabFiles.SimpleSet;

/**
 * Created by jesper on 2016-02-09.
 */
public class SplayTreeSet<E> implements SimpleSet {


    public class Node<E> {
        private E element;

        private Node<E> leftChild, rightChild;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this.element = e;
            leftChild = rightChild = null;
        }
    }

    private int size;
    public Node<E> root;

    public SplayTreeSet() {
        size = 0;
        root = null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean add(Comparable x) {
        if (root == null) {
            root = new Node(x);
            size++;
            return true;
        }
        return recursiveAdd(root, x);
    }

    private boolean recursiveAdd(Node<E> node, Comparable x) {
        if (node == null) {
            node = new Node(x);
            size++;
            return false;
        }
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            return recursiveAdd(node.leftChild, x);
        }
        if(comp > 0) {
            return recursiveAdd(node.rightChild, x);
        }
        return false;
    }

    @Override
    public boolean remove(Comparable x) {
        return recursiveRemove(root, null, x);
    }

    private boolean recursiveRemove(Node<E> node, Node<E> parent, Comparable x) {
        if (node == null) {
            return false;
        }
        int comp = x.compareTo(node.element);
        if (comp > 0) {
            return recursiveRemove(node.rightChild, node, x);

        }
        if (comp < 0) {
            return recursiveRemove(node.leftChild, node, x);
        }
        if (node.leftChild == null && node.rightChild == null) {

        }
        if (node.leftChild == null && node.rightChild == null) { //no children
            changeParentLink(parent, node, null);
        } else if (node.leftChild != null && node.rightChild == null) { // only left child
            changeParentLink(parent, node, node.leftChild);
        } else if (node.leftChild == null && node.rightChild != null) { // only right child
            changeParentLink(parent, node, node.rightChild);
        } else {
            E newValue = changeCurrentNode(node.leftChild, node);
            node.element = newValue;
        }
        return true;
    }

    /**
     * Recursively searches for the largest descendant
     * @param newNode root of the subtree currently being searched
     * @param parent parent of newNode
     * @return the largest value in the subtree
     */
    private E changeCurrentNode(Node<E> newNode, Node<E> parent) {
        if (newNode.rightChild == null) {
            if (newNode.leftChild == null) {
                changeParentLink(parent,newNode, null);
            }else {
                changeParentLink(parent, newNode, newNode.leftChild);
            }
            return newNode.element;
        }
        return changeCurrentNode(newNode.rightChild, newNode);
    }

    private void changeParentLink(Node<E> parent, Node<E> oldChild, Node<E> newChild) {
        if(parent == null) {
            root = newChild;
        }else  if (parent.leftChild == oldChild) {
            parent.leftChild = newChild;
        } else {
            parent.rightChild = newChild;
        }
    }

    @Override
    public boolean contains(Comparable x) {
        if(root == null) {
            return false;
        }
        return recursiveContains(root, x);
    }

    private boolean recursiveContains(Node<E> node, Comparable x) {
        if (node == null) {
            return false;
        }
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            return recursiveContains(node.leftChild, x);
        }
        if (comp > 0) {
            return recursiveContains(node.rightChild, x);
        }
        if (node.element.equals(x)){
            return true;
        }
        return false;
    }

    private void zig() {
        rightRotation(root);
    }

    private void zigzig(Node<E> node) {
        rightRotation(node.leftChild);
        rightRotation(node);
    }
    private void zag(Node<E> node) {
        leftRotation(node.leftChild);
        rightRotation(node);
    }

    private void rightRotation(Node<E> node) {
        Node<E> temp = node.leftChild;
        node.leftChild = temp.rightChild;
        temp.rightChild = node;
        node = temp;
    }

    private void leftRotation(Node<E> node) {
        Node<E> temp = node.rightChild;
        node.rightChild = temp.leftChild;
        temp.leftChild = node;
        node = temp;
    }

}

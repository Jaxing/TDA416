package Lab3;


import Lab3.LabFiles.*;
/**
 * Created by jesper on 2016-02-09.
 */
public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E>{

    public class Node<E> {
        private E element;

        private Node<E> next;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this.element = e;
            next = null;
        }
    }
    private int size;
    public Node<E> head;

    public SortedLinkedListSet() {
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(E x) {
        int currentSize = size();
        head = addRecursive(head, x);
        if (currentSize == size()) {
            return false;
        }
        return true;
    }

    private Node<E> addRecursive(Node<E> node, E x) {
        if (node == null) {
            System.out.println("Add last");
            node = new Node<E>(x);
            size++;
            return node;
        }
        int comp = node.element.compareTo(x);
        if (comp > 0) { //insert before node
            System.out.println("Insert");
            Node newNode = new Node<E>(x);
            newNode.next = node;
            size++;
            return newNode;
        }
        if (comp == 0) {
            System.out.println("false");
            return node;
        }
        node.next = addRecursive(node.next, x);
        return node;
    }

    @Override
    public boolean remove(E x) {
        int currentSize = size();
        head = removeRecursive(head, x);
        if(currentSize == size()) {
            System.out.println("false");
            return false;
        }
        return true;
    }

    private Node<E> removeRecursive(Node<E> node, E x) {
        if (node == null) {
            System.out.println("Nothing to remove");
            return null;
        }
        int comp = node.element.compareTo(x);
        if (comp > 0) {
            System.out.println("Object dosen't exist");
            return null;
        }
        if (comp == 0) {
            System.out.println("removed node");
            size--;
            return node.next;
        }
        node.next = removeRecursive(node.next, x);
        return node;
    }

    @Override
    public boolean contains(E x) {
        return containsRecursive(head, x);
    }

    private boolean containsRecursive(Node<E> node, E x) {
        if (node == null) {
            return false;
        }
        int comp = node.element.compareTo(x);
        if (comp > 0) {
            return false;
        }
        if(comp == 0) {
            return true;
        }
        return containsRecursive(node.next, x);
    }
}

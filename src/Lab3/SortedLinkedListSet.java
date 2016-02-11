package Lab3;


import Lab3.LabFiles.*;
/**
 * Created by jesper on 2016-02-09.
 */
public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet{

    public class Node<E> {
        private E element;

        private Node next;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this.element = e;
            next = null;
        }
    }
    private int size;
    public Node head;

    public SortedLinkedListSet() {
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(Comparable x) {
        if (head == null) {
            head = new Node(x);
            size++;
            return true;
        }
        return addRecursive(head, x);
    }

    private boolean addRecursive(Node node, Comparable x) {
        if(node == null) {
            node = new Node(x);
            size++;
            return  true;
        }
        if (x.compareTo(node.element) >= 0) {
            Node prev = node;
            Node next = node.next;
            Node newNode = new Node(x);
            prev.next = newNode;
            newNode.next = next;
            size++;
            return true;
        }
        return addRecursive(node.next, x);
    }

    @Override
    public boolean remove(Comparable x) {
        if (head.next == null) {
            return false;
        }
        if (head.element.equals(x)) {
            if (head.next == null) {
                head = null;
                return true;
            }
            head = head.next;
            return true;
        }
        return removeRecursive(head, x);
    }

    private boolean removeRecursive(Node node, Comparable x) {
        if (node.next == null) {
            return false;
        }
        if (node.next.element.equals(x)) {
            if (node.next == null) {
                node = null;
                return true;
            }
            node = node.next;
            return true;
        }
        return removeRecursive(node.next, x);
    }

    @Override
    public boolean contains(Comparable x) {
        return containsRecursive(head, x);
    }

    private boolean containsRecursive(Node node, Comparable x) {
        if (node == null) {
            return false;
        }
        int comp = x.compareTo(node.element);
        if (comp > 0) {
            return false;
        }
        if(comp == 0) {
            return true;
        }
        return containsRecursive(node.next, x);
    }

    @Override
    public String toString() {
        String returnString = "";
        Node currentNode = head;
        for (int i = 0; i < size(); i++){
            returnString = currentNode.element + "\t";
            currentNode = currentNode.next;
        }
        return  returnString;
    }
}

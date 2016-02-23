package Lab3;

import Lab3.LabFiles.SimpleSet;

/**
 * Created by jesper on 2016-02-09.
 */
public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {


    public class Node<E> {
        private E element;

        private Node<E> leftChild, rightChild, parent;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this.element = e;
            leftChild = rightChild = parent = null;
        }

        public E getData() {
            return element;
        }
        public Node<E> getLeft() {
            return leftChild;
        }
        public Node<E> getRight() {
            return rightChild;
        }
    }

    private int size;
    private int level;
    public Node<E> root;

    public SplayTreeSet() {
        size = 0;
        root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E x) {
        int currentSize = size();

        return recursiveAdd(root, null, x);
        /*root = recursiveAdd(root, null,x);

        if (currentSize == size()) {
            return false;
        }
        return true;*/
    }

    private boolean recursiveAdd(Node<E> node, Node<E> parent, E x) {
        if (node == null) {
            node = new Node<E>(x);
            node.parent = parent;
            size++;
            if (node.parent != null) {
                int comp = node.parent.element.compareTo(node.element);
                if (comp < 0) {
                    node.parent.rightChild = node;
                } else {
                    node.parent.leftChild = node;
                }
            }
            splay(node);
            return true;
        }
        int comp = node.element.compareTo(x);
        if (comp < 0) {
            return recursiveAdd(node.rightChild, node, x);
        }
        if (comp > 0) {
            return recursiveAdd(node.leftChild, node, x);
        }
        splay(node);
        return false;
    }

    @Override
    public boolean remove(Comparable x) {
        int currentSize = size();
        root = recursiveRemove(root, null, x);
        if (currentSize == size()) {
            return false;
        }
        return true;
    }

    private Node<E> recursiveRemove(Node<E> node, Node<E> parent, Comparable x) {
        if (node == null) {
            return null;
        }
        int comp = x.compareTo(node.element);
        if (comp > 0) {
            node.rightChild = recursiveRemove(node.rightChild, node, x);
            if (node.element == x) {
                if (node.rightChild == null) {
                    return zag(node);
                } else {
                    return zag(node.rightChild);
                }
            }
        }
        if (comp < 0) {
            node.leftChild = recursiveRemove(node.leftChild, node, x);
            if (node.element == x) {
                if (node.leftChild == null) {
                    return zig(node);
                } else {
                    return zig(node.leftChild);
                }
            }
        }
        if (node.leftChild == null && node.rightChild == null) { //no children
            System.out.println("------" + 0 + "-------");
            BTreePrinter.printNode(parent.parent);
            changeParentLink(parent, node, null);
            BTreePrinter.printNode(parent.parent);
        } else if (node.leftChild != null && node.rightChild == null) { // only left child
            System.out.println("------" + node.leftChild.element + "-------");
            BTreePrinter.printNode(parent.parent);
            changeParentLink(parent, node, node.leftChild);
            BTreePrinter.printNode(parent.parent);
        } else if (node.leftChild == null && node.rightChild != null) { // only right child
            System.out.println("------" + node.rightChild.element + "-------");
            BTreePrinter.printNode(parent.parent);
            changeParentLink(parent, node, node.rightChild);
            BTreePrinter.printNode(parent.parent);
        } else {
            E newValue = changeCurrentNode(node.leftChild, node);
            node.element = newValue;
        }
        size --;
        return node;
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
            } else {
                changeParentLink(parent, newNode, newNode.leftChild);
            }
            return newNode.element;
        }
        return changeCurrentNode(newNode.rightChild, newNode);
    }

    private void changeParentLink(Node<E> parent, Node<E> oldChild, Node<E> newChild) {
        if (parent == null) {
            root = newChild;
        } else  if (parent.leftChild == oldChild) {
            parent.leftChild = newChild;
        } else {
            parent.rightChild = newChild;
        }
    }

    @Override
    public boolean contains(Comparable x) {
        return recursiveContains(root, x);
    }

    private boolean recursiveContains(Node<E> node, Comparable x) {
        if (node == null) {
            return false;
        }
        int comp = x.compareTo(node.element);
        if (comp < 0) {
            boolean result = recursiveContains(node.leftChild, x);
            //node = zag(node.leftChild);
            return result;
        }
        if (comp > 0) {
            return recursiveContains(node.rightChild, x);
        }
        return true;
    }


    public Node<E> zig(Node<E> node) {
        rightRotation(node);
        //node =
        return node;
    }

    public Node<E> zigzig(Node<E> node) {
        BTreePrinter.printNode(node.parent);
        zig(node.parent);
        BTreePrinter.printNode(node.parent);
        //node.parent
        zig(node);
        BTreePrinter.printNode(node.parent);
        //        node =
        return node;
    }
    public Node<E> zigzag(Node<E> node) {
        BTreePrinter.printNode(node.parent);
        zag(node);
        BTreePrinter.printNode(node.parent);
        //node.parent
        zig(node);
        BTreePrinter.printNode(node.parent);
        //node =
        return node;
    }

    public Node<E> zag(Node<E> node) {
        leftRotation(node);
        //node =
        return node;
    }
    public Node<E> zagzag(Node<E> node) {
        BTreePrinter.printNode(node.parent);
        zag(node.parent);
        BTreePrinter.printNode(node.parent);
        //node.parent =
        zag(node);
        BTreePrinter.printNode(node.parent);
        //node =
        return node;
    }
    public Node<E> zagzig(Node<E> node) {
        BTreePrinter.printNode(node.parent);
        zig(node);
        BTreePrinter.printNode(node.parent);
        //node.parent =
        zag(node);
        BTreePrinter.printNode(node.parent);
        //node =
        return node;
    }
    private Node<E> rightRotation(Node<E> node) {
        Node<E> tmp = node.parent;
        if (tmp != null) {
            Node<E> tmpParent = tmp.parent;
            tmp.leftChild = node.rightChild;
            node.parent = tmpParent;
            node.rightChild = tmp;
            tmp.parent = node;
            changeParentLink(tmpParent, tmp, node);
        }// else node is root

        /*Node<E> temp = node.leftChild;
        Node<E> tempParent = node.parent;
        node.leftChild = temp.rightChild;
        temp.parent = tempParent;
        temp.rightChild = node;
        node.parent = temp;
        changeParentLink(tempParent, node, temp);*/
        return node;
    }

    private Node<E> leftRotation(Node<E> node) {
        Node<E> tmp = node.parent;
        if (tmp != null) {
            Node<E> tmpParent = tmp.parent;
            tmp.rightChild = node.leftChild;
            node.parent = tmpParent;
            node.leftChild = tmp;
            tmp.parent = node;
            changeParentLink(tmpParent, tmp, node);
        }

        return node;

        /*Node<E> temp = node.rightChild;
        Node<E> tempParent = node.parent;
        node.rightChild = temp.leftChild;
        temp.parent = tempParent;
        temp.leftChild = node;
        node.parent = temp;
        changeParentLink(tempParent, node, temp);
        return temp;*/
    }

    private void  splay (Node<E> splayNode) {
        while (splayNode.parent != null) {
            Node<E> splayParent = splayNode.parent;
            if (splayParent.parent != null) {
                Node<E> splayGrandParent = splayParent.parent;

                if (splayGrandParent.leftChild == splayParent) {
                    if (splayParent.leftChild == splayNode) {
                        zigzig(splayNode);
                        //splayNode =
                    } else {
                        zigzag(splayNode);
                        //splayNode =
                    }
                } else {
                    if (splayParent.rightChild == splayNode) {
                        //zagzag
                         zagzag(splayNode);
                        //splayNode =
                    } else {
                        //zagzig
                        zagzig(splayNode);
                        //splayNode =
                    }
                }
            } else {
                if (splayParent.rightChild == splayNode) {
                    //zag
                    zag(splayNode);
                    //splayNode =
                } else {
                    //zig
                    zig(splayNode);
                    //splayNode =
                }
            }
        }
        root = splayNode;
    }

}

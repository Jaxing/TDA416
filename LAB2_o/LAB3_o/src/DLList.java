/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {
    public class Node {
        public E elt;
        private Node prev, next;

        Node() {
            this.elt = null;
            prev = next = null;
        }

        Node(E elt) {
            this.elt = elt;
            prev = next = null;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setNext(Node next){this.next = next;}

        public void setPrev(Node prev){ this.prev = prev;}

        public boolean hasNext(){
            if(next != null){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * first and last nodes in list, null when list is empty
     */
    private Node first, last;

    DLList() {
        first = last = null;
    }


    /**
     * inserts an element at the beginning of the list
     *
     * @param e the new element value
     * @return the node holding the added element
     */
    public Node addFirst(E e) {
        Node newNode = new Node(e);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setPrev(null);
            newNode.setNext(first);
            first.setPrev(newNode);
            first = newNode;
        }
        return newNode;
    }

    /**
     * inserts an element at then end of the list
     *
     * @param e the new element
     * @return the node holding the added element
     */
    public Node addLast(E e) {
        Node newNode = new Node(e);
        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setPrev(last);
            newNode.setNext(null);
            last = newNode;
        }
        return newNode;
    }

    /**
     * @return the node of the list's first element, null if list is empty
     */
    public Node getFirst() {
        return first;
    }

    /**
     * @return the node of the list's last element, null if list is empty
     */
    public Node getLast() {
        return last;
    }

    /**
     * inserts a new element after a specified node
     *
     * @param e the new element
     * @param l the node after which to insert the element, must be non-null and a node belonging to this list
     * @return the node holding the inserted element
     */
    public Node insertAfter(E e, Node currentNode) {
        if (currentNode == last) {
            return addLast(e);
        }
        Node newNode = new Node(e);
        Node nextNode = currentNode.getNext();

        currentNode.setNext(newNode);
        newNode.setPrev(currentNode);
        nextNode.setPrev(newNode);
        newNode.setNext(nextNode);

        return newNode;
    }


    /**
     * inserts a new element before a specified node
     *
     * @param e the new element
     * @param l the node before which to insert the element, must be non-null and a node belonging to this list
     * @return the node holding the inserted element
     */
    public Node insertBefore(E e, Node currentNode) {
        if(currentNode == first){
            return addFirst(e);
        }
        Node newNode = new Node(e);
        Node prevNode = currentNode.getPrev();

        prevNode.setNext(newNode);
        newNode.setNext(currentNode);
        newNode.setPrev(prevNode);
        currentNode.setNext(newNode);

        return newNode;
    }

    /**
     * removes an element
     *
     * @param l then node containing the element that will be removed, must be non-null and a node belonging to this list
     */
    public void remove(Node l) {
        Node currentNode = l;
        Node prevNode = currentNode.getPrev();
        Node nextNode = currentNode.getNext();
        if(nextNode != null){
            nextNode.setPrev(prevNode);
        }
        if(prevNode != null) {
            prevNode.setNext(nextNode);
        }
    }
}

import java.util.ArrayDeque;
/**
 * Created by Oskar on 15-Feb-16.
 * 
 * Each Node knows its parent and left and right buddy.
 *
 */
public class SplayTreeSet<T> implements SimpleSet<Integer> {
    private int size;
    private SplayTreeNode<Integer> topNode;


    public SplayTreeSet(){
        topNode = null;
        this.size = 0;
    }

    public String toString(){
        if(size == 0){
            return "emtpy";
        }
        String tree = "";
        ArrayDeque<SplayTreeNode<Integer>> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(topNode);

        SplayTreeNode<Integer> printNode;
        int iterator = 0;
        while(!nodeQueue.isEmpty()){
            int top = -1;
            printNode = nodeQueue.poll();
            if(tree != ""){ tree += "\n"; }
            tree += printNode.toString();
            if(printNode.getLeft() != null){
                nodeQueue.add(printNode.getLeft());
            }
            if(printNode.getRight() != null){
                nodeQueue.add(printNode.getRight());
            }
            if(iterator == 10){
                System.out.println("printing took too long..");
                break;
            }
            iterator++;
        }
        return tree;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(Integer x) {
        if(this.contains(x)){
            return false;
        }
        System.out.println("--adding " +x);
        SplayTreeNode<Integer> newNode = addNodeAtNull(x);
        if(newNode == topNode){
            return true;
        }else{
            System.out.print("\t--splaying--");
            splay(newNode);
            System.out.println("\t--splaying done--");
            System.out.println("tree:\n" +this.toString());
            return true;
        }

    }

    @Override
    public boolean remove(Integer x) {
        SplayTreeNode<Integer> extinctNode = getNode(x);
        if(extinctNode == null){
            return false;
        }
        System.out.println("removing " +x);
        Integer extinctValue = extinctNode.getValue();
        SplayTreeNode<Integer> swapNode = new SplayTreeNode<Integer>(extinctNode);

        //swap value with left/rightmost in subtree
        if(swapNode.hasRight()){ //get the leftmost in the right subtree
            swapNode = swapNode.getRight();
            while(swapNode.hasLeft()){
                swapNode = swapNode.getLeft();
            }
            //swap values
            extinctNode.setValue(swapNode.getValue());
            swapNode.setValue(extinctValue);
            
            //remove the swapped node.
            if(swapNode.hasRight()){
                swapNode.getTop().setRight(swapNode.right);
                swapNode.right.setTop(swapNode.getTop());
            } else{
                swapNode.removeTopLink();
            }
        }else if(swapNode.hasLeft()){ //get the rightmost in the left subtree
            swapNode = swapNode.getLeft();
            while(swapNode.hasRight()){
                swapNode = swapNode.getRight();
            }
            //swap values
            extinctNode.setValue(swapNode.getValue());
            swapNode.setValue(extinctValue);
            
            //remove the swapped node.
            if(swapNode.hasLeft()){
                swapNode.getTop().setLeft(swapNode.left);
                swapNode.left.setTop(swapNode.getTop());
            } else{
                swapNode.removeTopLink();
            }
         }else if(extinctNode.hasTop()){
            if(extinctNode.isLeftChild()){
                extinctNode.getTop().clearLeft();
            }else if(extinctNode.isRightChild()){
                extinctNode.getTop().clearRight();
            }else{
                //happens to be here when the node is missing children.
                System.out.println("found child without parents..");
            }
            swapNode = extinctNode;
        }else{
            size=0;
            topNode = null;
            return true;
        }
         size--;
        System.out.println("tree:\n" +this.toString()); 
        splay(swapNode.getTop());
        System.out.println("tree:\n" +this.toString()); 
       
        return true;
    }

    @Override
    public boolean contains(Integer x) {
        if(getNode(x) != null){
            return true;
        }else{
            return false;
        }
    }

    private SplayTreeNode<Integer> getNode(Integer x){
        SplayTreeNode<Integer> comprareNode = topNode;
        while(comprareNode != null){
           if(comprareNode.getValue().equals(x)){
                return comprareNode;
           } else if(comprareNode.getValue()>x && comprareNode.hasLeft()){
                comprareNode = comprareNode.getLeft();
           } else if(comprareNode.getValue()<x && comprareNode.hasRight()){
                comprareNode = comprareNode.getRight();
           } else{
                return null;
           }
        }
        return null;
    }

    private void splay(SplayTreeNode<Integer> splayNode){
        if(splayNode == null){
            System.out.print("- got the head.. -");
            return;
        }
        while(splayNode.hasTop()){
              System.out.print("s1");
            if(splayNode.getTop() == topNode){
                System.out.print("- Zig! -");
                zig(splayNode);
            }else if(splayNode.getTop().hasTop()){
                SplayTreeNode<Integer> parent = splayNode.getTop();
                SplayTreeNode<Integer> gParent = parent.getTop();
                if(splayNode.isRightChildTo(parent) && parent.isRightChildTo(gParent)){
                    System.out.print("- zigZig! -");
                    zigZig(splayNode,parent,gParent,false);
                }else if(splayNode.isLeftChildTo(parent) && parent.isLeftChildTo(gParent)){
                    System.out.print("- zigZig! -");
                    zigZig(splayNode,parent,gParent,true);
                }else if(splayNode.getTop() != topNode){
                    if(splayNode.isRightChild() && parent.isLeftChild()){
                        System.out.print("- zigZag! -");
                        zigZag(splayNode,parent,gParent,true);
                    }else if(splayNode.isLeftChild() && parent.isRightChild()){
                        System.out.print("- zigZag! -");
                        zigZag(splayNode,parent,gParent,false);
                    }
                }
            }
        }//while
    }

    /* Translation between these trees:
    *        t        x
    *      /  \      / \
    *     x   C     A   t
    *   /  \           / \
    *  A    B         B   C
    */
    private void zig(SplayTreeNode<Integer> splayNode){
        SplayTreeNode<Integer> oldTop = new SplayTreeNode<>(0);
        oldTop.setValue(topNode.getValue());

        if(splayNode.isLeftChildTo(topNode)){ //starting with the left tree
            oldTop.left = splayNode.getRight();
            splayNode.right = oldTop;
            oldTop.right = topNode.getRight();

        }else if(splayNode.isRightChildTo(topNode)){ //starting with the right tree
            oldTop.right = splayNode.getLeft();
            splayNode.left = oldTop;
            oldTop.left = topNode.getLeft();
        }
        if(oldTop.hasLeft()){
            oldTop.left.top = oldTop;
        }
        if(oldTop.hasRight()){
            oldTop.right.top = oldTop;
        }

        oldTop.top = splayNode;
        splayNode.top = null;
        topNode = splayNode;
    }

    private void zigZig(SplayTreeNode<Integer> splayNode, SplayTreeNode<Integer> parent, SplayTreeNode<Integer> gParent, boolean left){
        if(left){
            gParent.setLeft(parent.getRight());
            parent.setLeft(splayNode.getRight());
            parent.setRight(gParent);
            gParent.setTop(parent);

            splayNode.setRight(parent);
            parent.setTop(splayNode);
        }else{
            gParent.setRight(parent.getLeft());
            parent.setRight(splayNode.getLeft());
            parent.setLeft(gParent);
            gParent.setTop(parent);

            splayNode.setLeft(parent);
            parent.setTop(splayNode);
        }
        splayNode.clearTop();
        topNode = splayNode;
    }

    private void zigZag(SplayTreeNode<Integer> splayNode, SplayTreeNode<Integer> parent, SplayTreeNode<Integer> gParent, boolean left){
        gParent.setTop(splayNode);
        parent.setTop(splayNode);
        if(left){
            gParent.setLeft(splayNode.getRight());
            if(gParent.hasLeft()){
                gParent.left.setTop(gParent);
            }
            parent.setRight(splayNode.getLeft());
            if(parent.hasRight()){
                parent.right.setTop(parent);
            }
            splayNode.setLeft(parent);
            splayNode.setRight(gParent);
        }else{
            gParent.setRight(splayNode.getLeft());
            if(gParent.hasRight()){
                gParent.right.setTop(gParent);
            }
            parent.setLeft(splayNode.getRight());
            if(parent.hasLeft()){
                parent.left.setTop(parent);
            }
            splayNode.setLeft(gParent);
            splayNode.setRight(parent);

        }
        splayNode.clearTop();
        topNode = splayNode;
    }


    private SplayTreeNode<Integer> addNode(SplayTreeNode<Integer> parent, Integer x, boolean left){
        SplayTreeNode<Integer> newChild = new SplayTreeNode<Integer>(x);
        if(parent == null){
            topNode = newChild;
            System.out.println("new top!");
        }
        else{
            newChild.top = parent;
            if(left){
                parent.setLeft(newChild);
            }else{
                parent.setRight(newChild);
            }
        }
        this.size++;
        return newChild;
    }

    private SplayTreeNode<Integer> addNodeAtNull(Integer x){
       if(size == 0){
            return addNode(null,x,false);
        }else{
            SplayTreeNode<Integer> comprareNode = topNode;
            while(comprareNode!=null){
                if(comprareNode.getValue() < x){
                    if(comprareNode.hasRight()){
                        comprareNode = comprareNode.right; 
                    }
                    return addNode(comprareNode,x,false); //adding
                }else if(comprareNode.getValue() >= x){
                    if(comprareNode.hasLeft()){
                        comprareNode = comprareNode.left;
                    }
                    return addNode(comprareNode,x,true); //adding
                    
                }              
            }//while
            System.out.println("Hueston, we have a problem...");
            return null;
        }
        
    }//splayTreeNode
}

import java.lang.Comparable;
import java.util.ArrayDeque;
/**
 * Created by Oskar on 15-Feb-16.
 * 
 * Each Node knows its parent and left and right buddy.
 *
 */
public class SplayTreeSet<T extends Comparable<? super T>> implements SimpleSet<T> {
    private int size;
    private SplayTreeNode<T> topNode;


    public SplayTreeSet(){
        topNode = null;
        this.size = 0;
    }

    /*public String toString(){
        if(size == 0){
            return "emtpy";
        }
        String tree = "";
        ArrayDeque<SplayTreeNode<Integer>> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(topNode);

        SplayTreeNode<Integer> printNode;
        while(!nodeQueue.isEmpty()){
            printNode = nodeQueue.poll();
            tree += "\n" +printNode.toString();
            if(printNode.getLeft() != null){
                nodeQueue.add(printNode.getLeft());
            }
            if(printNode.getRight() != null){
                nodeQueue.add(printNode.getRight());
            }
        }
        return tree;
    }
    */

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(T x) {
        if(this.getNode(x) != null ){
            return false;
        }
        SplayTreeNode<T> newNode = addNodeFindNull(x);
        if(newNode == topNode){
            return true;
        }else{
            splay(newNode);
            return true;
        }

    }

    @Override
    public boolean remove(T x) {
        if(size == 0){
            return false;
        }
        SplayTreeNode<T> extinctNode = getNode(x);
        if(extinctNode == null){
            return false;
        }
        SplayTreeNode<T> extinctNodeParent = extinctNode.getTop();
        if(this.killOneChildParent(extinctNode)){
            splay(extinctNodeParent);
            return true;
        }

        //twoChildedParent:
        T extinctValue = extinctNode.getValue();
        SplayTreeNode<T> swapNode = new SplayTreeNode<T>(extinctNode);
        if(swapNode.hasRight()){ //get the leftmost in the right subtree
            swapNode = swapNode.getRight();
            while(swapNode.hasLeft()){
                swapNode = swapNode.getLeft();
            }
        }else if(swapNode.hasLeft()){ //get the rightmost in the left subtree
            swapNode = swapNode.getLeft();
            while(swapNode.hasRight()){
                swapNode = swapNode.getRight();
            }
        }
        //swap values
        extinctNode.setValue(swapNode.getValue());
        swapNode.setValue(extinctValue);
        
        this.killOneChildParent(swapNode); 
        this.splay(extinctNodeParent);
        return true;
    }

    private boolean killOneChildParent(SplayTreeNode<T> parent){
        switch (parent.countChildren()){
            case 0:
                if(parent == topNode){
                    topNode = null;
                    break;
                }
                parent.clearTopLink();
                break;
            case 1:
                SplayTreeNode<T> childNode = parent.getChild();
                SplayTreeNode<T> gParent = parent.getTop();
                if(gParent == null){
                    connectLeft(null,childNode);
                    break;
                }
                switch(gParent.getChildSide(parent)){
                    case "left":
                        connectLeft(parent.getTop(),childNode);
                    break;
                    case "right":
                        connectRight(parent.getTop(),childNode);
                    break;
                }
                break;
            default:
                return false;
        }
        this.size--;
        return true;
    } 

    @Override
    public boolean contains(T x) {
        SplayTreeNode<T> searchedNode = getNode(x);
        if(searchedNode == null){
            return false;
        }else{
            this.splay(searchedNode);
            return true;
        }
    }

    private SplayTreeNode<T> getNode(T x){
        SplayTreeNode<T> comprareNode = topNode;
        while(comprareNode != null){
            int comp = comprareNode.getValue().compareTo(x);
           if(comp == 0){
                return comprareNode;
           } else if(comp > 0 && comprareNode.hasLeft()){
                comprareNode = comprareNode.getLeft();
           } else if(comp < 0 && comprareNode.hasRight()){
                comprareNode = comprareNode.getRight();
           } else{
                return null;
           }
        }
        return null;
    }

    private void splay(SplayTreeNode<T> splayNode){
        if(splayNode == null){
            return;
        }
        //printOut("\nxx Splaying:\n" +this.toString());
        while(splayNode.hasTop()){
            if(splayNode.getTop() == topNode){
                zig(splayNode);
            }else if(splayNode.getTop().hasTop()){
                SplayTreeNode<T> parent = splayNode.getTop();
                SplayTreeNode<T> gParent = parent.getTop();
                if(splayNode.isRightChildTo(parent) && parent.isRightChildTo(gParent)){
                    zigZig(splayNode,parent,gParent,false);
                }else if(splayNode.isLeftChildTo(parent) && parent.isLeftChildTo(gParent)){
                    zigZig(splayNode,parent,gParent,true);
                }else if(splayNode.getTop() != topNode){
                    if(splayNode.isRightChild() && parent.isLeftChild()){
                        zigZag(splayNode,parent,gParent,true);
                    }else if(splayNode.isLeftChild() && parent.isRightChild()){
                        zigZag(splayNode,parent,gParent,false);
                    }
                }else{
                }
            }
        }//while
        //printOut("\nxx Done:\n" +this.toString());
    }

    /* Translation between these trees:
    *        t        x
    *      /  \      / \
    *     x   C     A   t
    *   /  \           / \
    *  A    B         B   C
    */
    private void zig(SplayTreeNode<T> splayNode){
        SplayTreeNode<T> oldTop = new SplayTreeNode(0);
        oldTop.setValue(topNode.getValue());

        if(splayNode.isLeftChildTo(topNode)){ //starting with the left tree
            connectLeft(oldTop,splayNode.getRight());
            connectRight(splayNode,oldTop);
            connectRight(oldTop,topNode.getRight());

        }else if(splayNode.isRightChildTo(topNode)){ //starting with the right tree
            connectRight(oldTop,splayNode.getLeft());
            connectLeft(splayNode,oldTop);
            connectLeft(oldTop,topNode.getLeft());
        }
        splayNode.clearTopLink();
        topNode = splayNode;
    }

    private void zigZig(SplayTreeNode<T> splayNode, SplayTreeNode<T> parent, SplayTreeNode<T> gParent, boolean left){
        SplayTreeNode<T> theElite = gParent.getTop();
        if(left){
            connectLeft(gParent,parent.getRight());
            connectLeft(parent,splayNode.getRight());
            connectRight(parent,gParent);
            connectRight(splayNode,parent);
        }else{
            connectRight(gParent,parent.getLeft());
            connectRight(parent,splayNode.getLeft());
            connectLeft(parent,gParent);
            connectLeft(splayNode,parent);
        }

        if(theElite != null){
            insertTheElite(theElite,gParent,splayNode);
        }else{
            splayNode.clearTopLink();
            topNode = splayNode;
        }
    }

    private void zigZag(SplayTreeNode<T> splayNode, SplayTreeNode<T> parent, SplayTreeNode<T> gParent, boolean left){
        SplayTreeNode<T> theElite = gParent.getTop();
        if(left){
            connectLeft(gParent,splayNode.getRight());
            connectRight(parent,splayNode.getLeft());
            
            connectLeft(splayNode,parent);
            connectRight(splayNode,gParent);
        }else{
            connectRight(gParent,splayNode.getLeft());
            connectLeft(parent,splayNode.getRight());

            connectLeft(splayNode,gParent);
            connectRight(splayNode,parent);
        }
        if(theElite != null){
            insertTheElite(theElite,gParent,splayNode);
        }else{
            splayNode.clearTopLink();
            topNode = splayNode;
        }
    }

    private void insertTheElite(SplayTreeNode<T> theElite, SplayTreeNode<T> gParent, SplayTreeNode<T> splayNode){
        switch(theElite.getChildSide(gParent)){
            case "left":
                connectLeft(theElite,splayNode);
                break;
            case "right":
                connectRight(theElite,splayNode);
                break;
            default:
        }
    }


    private SplayTreeNode<T> createNewNode(SplayTreeNode<T> parent, T x, boolean left){
        SplayTreeNode<T> newChild = new SplayTreeNode<T>(x);
        if(parent == null){
            topNode = newChild;
        }
        else{
            if(left){
                connectLeft(parent,newChild);
            }else{
                connectRight(parent,newChild);
            }
        }
        this.size++;
        return newChild;
    }

    private SplayTreeNode<T> addNodeFindNull(T x){
       if(size == 0){
            return createNewNode(null,x,false);
        }else{
            SplayTreeNode<T> comprareNode = topNode;
            while(comprareNode!=null){
                int comp = comprareNode.getValue().compareTo(x);
                if(comp < 0){
                    if(comprareNode.hasRight()){
                        comprareNode = comprareNode.right; 
                    }else{
                        return createNewNode(comprareNode,x,false); //adding
                    }
                }else if(comp > 0){
                    if(comprareNode.hasLeft()){
                        comprareNode = comprareNode.left;
                    }else{
                        return createNewNode(comprareNode,x,true); //adding
                    }
                }              
            }//while
            return null;
        }
    }//splayTreeNode



    private void connectLeft(SplayTreeNode<T> newParent, SplayTreeNode<T> newChild){
        if(newParent == null){
            topNode = newChild;
        }else{
            newParent.setLeft(newChild);
        }
        if(newChild != null){
            newChild.setTop(newParent);
        }

    }
    private void connectRight(SplayTreeNode<T> newParent, SplayTreeNode<T> newChild){
        if(newParent == null){
            topNode = newChild;
        }
        else{
            newParent.setRight(newChild);
        }
        if(newChild != null){
            newChild.setTop(newParent);
        } 
    }

    private void printOut(String text,boolean newLine){
        String t = text;
        if(newLine){
            t += "\n";
        }
        //System.out.print(t);
    }

    private void printOut(String text){
        printOut(text,false);
    }

}//end of class

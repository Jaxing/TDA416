/*
* Created by Oskar
* Each Node knows its parent and left and right buddy.
*/

public class SplayTreeNode<E>{
	private E value;
	public SplayTreeNode<E> left,right,top;


	public SplayTreeNode(E value){
		this.value = value;
		left = null;
		right = null;
		top = null;
	}

	public SplayTreeNode(SplayTreeNode<E> copy){
		this.value = copy.getValue();
		left = copy.getLeft();
		right = copy.getRight();
		top = copy.getTop();
	}

	public E getValue(){
		return this.value;
	}

	public void setValue(E newValue){
		this.value = newValue;
	}

	public SplayTreeNode<E> getTop(){
		return top;
	}
	public SplayTreeNode<E> getLeft(){
		return left;
	}
	public SplayTreeNode<E> getRight(){
		return right;
	}

	public boolean hasTop(){
		if(top != null){
			return true;
		}
		return false;
	}
	public boolean hasLeft(){
		if(left != null){
			return true;
		}
		return false;
	}
	public boolean hasRight(){
		if(right != null){
			return true;
		}
		return false;
	}

	public void setTop(SplayTreeNode<E> newTop){
		this.top = newTop;
	}
	public void setLeft(SplayTreeNode<E> newLeft){
		this.left = newLeft;	
	}
	public void setRight(SplayTreeNode<E> newRight){
		this.right = newRight;
	}
	
	public void clearTop(){
		this.top = null;
	}
	public void clearLeft(){
		this.left = null;
	}
	public void clearRight(){
		this.right = null;
	}

	public boolean isLeftChild(){
		if(this.getTop().getLeft() == this){
			return true;
		}else{
			return false;
		}
	}
	public boolean isRightChild(){
		if(this.getTop().getRight() == this){
			return true;
		}else{
			return false;
		}
	}


	public boolean isLeftChildTo(SplayTreeNode<E> parent){
		if(parent != null && parent.getLeft() == this){
			return true;
		}
		return false;
	}

	public boolean isRightChildTo(SplayTreeNode<E> parent){
		if(parent != null && parent.getRight() == this){
			return true;
		}
		return false;
	}

	public String toString(){
		E topVal = null;
		E leftVal = null;
		E rightVal = null;

		if(top != null){
			topVal = top.getValue();
		}
		if(left != null){
			leftVal = left.getValue();
		}
		if(right != null){
			rightVal = right.getValue();
		}

		return "this=" +value +"\tt:" +topVal +"\tl:" +leftVal +"\tr:" +rightVal;
	}

	public void removeTopLink(){
		if(!this.hasTop()){
			return;
		}
		if(this.isLeftChild()){
            this.getTop().clearLeft();
        }else if(this.isLeftChild()){
            this.getTop().clearRight();
        }
        this.top = null;
	}
}
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
	public int countChildren(){
		int count = 0;
		if(this.hasRight()){
			count++;
		}
		if(this.hasLeft()){
			count++;
		}
		return count;
	}

	public SplayTreeNode<E> getChild(){
		if(this.countChildren() != 1){
			return null;
		}
		if(hasLeft()){
			return left;
		}else if(hasRight()){
			return right;
		}
		return null;
	}

	public String getChildSide(){
		if(this.hasLeft() && !this.hasRight()){
			return "left";
		}else if(this.hasRight() && !this.hasLeft()){
			return "right";
		}else{
			System.out.println("XXX getChildSide: hasTwoChildren!");
			return null;
		}
	}

	public String getChildSide(SplayTreeNode<E> child){
		if(child.isLeftChildTo(this)){
			return "left";
		}else if(child.isRightChildTo(this)){
			return "right";
		}
		else{
			return "none";
		}
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
	
	public void clearTopLink(){
		if(this.hasTop()){
			if(this.isRightChild()){
				this.top.clearRightLink();
			}else if(this.isLeftChild()){
				this.top.clearLeftLink();
			}
			setTop(null);
		}
	}
	public void clearLeftLink(){
		if(this.hasLeft()){
			this.left.setTop(null);
		}
		this.setLeft(null);
	}
	public void clearRightLink(){
		if(this.hasRight()){
			this.right.setTop(null);
		}
		this.setRight(null);
	}

	public boolean isLeftChild(){
		if(!this.hasTop()){
			return false;
		}
		if(this.getTop().getLeft() == this){
			return true;
		}else{
			return false;
		}
	}
	public boolean isRightChild(){
		if(!this.hasTop()){
			return false;
		}
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
}
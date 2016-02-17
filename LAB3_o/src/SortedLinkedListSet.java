import java.util.LinkedList;

/**
 * Created by Oskar on 15-Feb-16.
 *
 * smallest objects in front.
 */
public class SortedLinkedListSet<T> implements SimpleSet<Integer> {
    private int size;
    private DLList<Integer> list;

    public SortedLinkedListSet(){
        list = new DLList<>();
        this.size = 0;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public boolean add(Integer x) {
        if(this.contains(x)){
            return false; //cannot add a value which exists
        } else if(this.size == 0){ //emtpy?
            list.addFirst(x);
            this.size++;
            return true;
        } else if(list.getFirst().elt > x){ //fisrt?
            list.addFirst(x);
            this.size++;
            return true;
        }else if(list.getLast().elt < x){ //last?
            list.addLast(x);
            this.size++;
            return true;
        }else {
            DLList.Node iterator = list.getFirst();
            for(int i=0; i< this.size-1 && iterator.hasNext(); i++){
                if ((Integer) iterator.getNext().elt > x) {
                    list.insertAfter(x, iterator);
                    this.size++;
                    return true;
                }
                iterator = iterator.getNext();
            }
            return false;
        }
    }

    @Override
    public boolean remove(Integer x) {
        DLList.Node iterator = list.getFirst();
        while(iterator != null){
            if(iterator.elt.equals(x)){
                list.remove(iterator);
                this.size--;
                return true;
            }else if(iterator.hasNext()) {
                iterator = iterator.getNext();
            }else{
                break;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Integer x) {
        if (this.size == 0) {
            return false;
        } else {
            DLList.Node iterator = list.getFirst();
            while (iterator != null) {
                if (iterator.elt.equals(x)) {
                    return true;
                } else if (iterator.hasNext()) {
                    iterator = iterator.getNext();
                } else {
                    break;
                }
            }
            return false;
        }
    }
}

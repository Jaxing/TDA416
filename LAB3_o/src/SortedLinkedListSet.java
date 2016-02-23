import java.util.LinkedList;
/**
 * Created by Oskar on 15-Feb-16.
 *
 * ascending linked list
 */
public class SortedLinkedListSet<T> implements SimpleSet<Integer> {
    private int size;
    private DLList<Integer> list;

    public SortedLinkedListSet(){
        list = new DLList<Integer>();
        this.size = 0;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public boolean add(Integer x) {
        if(this.size == 0){ //emtpy?
            list.addFirst(x);
            this.size++;
            return true;
        }
        else if(this.contains(x)){
            return false; //cannot add a value which exists
        }else{
            DLList<Integer>.Node iterator = list.getFirst();
            for(int i=0; i < this.size; i++){
                if(iterator.getElt() > x){
                    list.insertBefore(x, iterator);
                    this.size++;
                    return true;
                }else if(iterator.getElt() < x && this.size-1 == i){
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
        DLList<Integer>.Node iterator = list.getFirst();
        while(iterator != null){
            if(iterator.getElt().equals(x)){
                list.remove(iterator);
                this.size--;
                return true;
            }
            iterator = iterator.getNext();
        }
        return false;
    }

    @Override
    public boolean contains(Integer x) {
        if (this.size > 0) {
            DLList<Integer>.Node iterator = list.getFirst();
            while (iterator != null && iterator.getElt() <= x ) {
                if (iterator.getElt().equals(x)) {
                    return true;
                }
                iterator = iterator.getNext();  
            }
        }
        return false;
    }

    /*@Override
    public String toString(){
        String text = "[";
        DLList<Integer>.Node iterator = list.getFirst();
        while (iterator != null) {
            text += iterator.getElt().toString();
            text += ",";
            iterator = iterator.getNext();
        }
        return text + "] #" +size;
    }*/
}

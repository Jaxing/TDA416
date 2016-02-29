import java.util.LinkedList;
import java.lang.Comparable;
/**
 * Created by Oskar on 15-Feb-16.
 *
 * ascending linked list
 */
public class SortedLinkedListSet<T extends Comparable<? super T>> implements SimpleSet<T> {
    private int size;
    private DLList<T> list;

    public SortedLinkedListSet(){
        list = new DLList<T>();
        this.size = 0;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public boolean add(T x) {
        if(this.size == 0){ //emtpy?
            list.addFirst(x);
            this.size++;
            return true;
        }else{
            DLList<T>.Node iterator = list.getFirst();
            for(int i=0; i < this.size; i++){
                int cmp = iterator.getElt().compareTo(x);
                if(cmp > 0){
                    list.insertBefore(x, iterator);
                    this.size++;
                    return true;
                }else if(cmp == 0){
                    return false; //already in the list!
                }
                iterator = iterator.getNext();
            }
            list.addLast(x);
            this.size++;
            return true;
        }
    }

    @Override
    public boolean remove(T x) {
        DLList<T>.Node iterator = list.getFirst();
        while(iterator != null){
            int cmp = iterator.getElt().compareTo(x);
            if(cmp > 0){
                return false;
            }

            if(cmp == 0){
                list.remove(iterator);
                this.size--;
                return true;
            }
            iterator = iterator.getNext();
        }
        return false; 
    }

    @Override
    public boolean contains(T x) {
        if (this.size > 0) {
            DLList<T>.Node iterator = list.getFirst();
            while (iterator != null) {
                int cmp = iterator.getElt().compareTo(x);
                if(cmp > 0){
                    return false;
                }
                if (cmp == 0) {
                    return true;
                }
                iterator = iterator.getNext();  
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String text = "[";
        DLList<T>.Node iterator = list.getFirst();
        while (iterator != null) {
            text += iterator.getElt().toString();
            text += ",";
            iterator = iterator.getNext();
        }
        return text + "] #" +size;
    }
}

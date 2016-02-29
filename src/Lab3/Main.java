package Lab3;


/**
 * Created by jesper on 2016-02-09.
 */
public class Main {
    public static void main(String[] args) {
        SortedLinkedListSet<Integer> listSet = new SortedLinkedListSet<Integer>();
        listSet.add(new Integer(4));
        listSet.add(new Integer(8));
        listSet.add(new Integer(7));
        listSet.add(new Integer(3));
        listSet.add(new Integer(2));
        listSet.add(new Integer(5));
        listSet.add(new Integer(9));
        listSet.add(new Integer(6));
        System.out.print(listSet.toString());
    }
}

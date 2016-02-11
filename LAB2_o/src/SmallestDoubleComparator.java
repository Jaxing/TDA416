import java.util.Comparator;

/**
 * Created by Oskar on 08-Feb-16.
 */
public class SmallestDoubleComparator implements Comparator<Double> {

   @Override
    public int compare(Double o1, Double o2) { //smallest number is better.
        if(o1<o2){
            return 1;
        } else if(o1 > o2){
            return -1;
        }else {
            return 0;
        }
    }

}

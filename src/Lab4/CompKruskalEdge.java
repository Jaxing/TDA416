package Lab4;

import Lab4.LabFiles.Edge;

import java.util.Comparator;

/**
 * Created by jesper on 2016-02-29.
 */
public class CompKruskalEdge<E extends Edge> implements Comparator<E> {
    @Override
    public int compare(E o1, E o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return Double.compare(o1.getWeight(),o2.getWeight());
    }
}

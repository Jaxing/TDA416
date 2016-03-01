package Lab4;

import Lab4.LabFiles.Edge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representation of a path in a graph.
 * The path consists of a number of edges,
 * each edge know which nodes it goes from and to.
 * The path also have a startNode and an endNode.
 */
public class Path<E extends Edge> {
    private List<E> edges;
    private int from ,to;

    public Path(int from, int to) {
        this.from = from;
        this.to = to;
        edges = new ArrayList();
    }


    public Path(int to, Path<E> old) {
        this.from = old.from;
        this.to = to;
        edges = new ArrayList<E>();
        for (E e : old.edges) {
            this.add(e);
        }
    }

    public boolean add(E e) {
        if (edges == null || !edges.contains(e)) {
            edges.add(e);
            return true;
        }
        return false;
    }

    public double getWeight() {
        double weight = 0;

        for (Edge e : edges) {
            weight = e.getWeight() + weight;
        }
        return weight;
    }

    public boolean containsEdge(E e) {
        return edges.contains(e);
    }

    public boolean containsNode(int node) {
        for (E e : edges) {
            if (e.from == node) {
                return true;
            }
        }
        return false;
    }

    public int getTo() {
        return to;
    }

    public Iterator<E> iterator(){
        return edges.iterator();
    }
}

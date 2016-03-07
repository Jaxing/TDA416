package Lab4;


import Lab4.LabFiles.*;
import javafx.scene.Parent;

import java.util.*;

public class DirectedGraph<E extends Edge> {

    private Map<Integer,List<E>> listOfEdges;

	public DirectedGraph(int noOfNodes) {
        listOfEdges = new HashMap<Integer, List<E>>(noOfNodes);
        for (int i = 0; i < noOfNodes; i++) {
            listOfEdges.put(i, new ArrayList<E>());
        }
	}

	public void addEdge(E e) {
        listOfEdges.get(e.from).add(e);
	}

	public Iterator<E> shortestPath(int from, int to) {
        PriorityQueue<Path> pq = new PriorityQueue<Path>(new CompDijkstrasPath<Path>());
        List<Integer> visitedNodes = new ArrayList<Integer>();
        int currentNode = from;
        Path<E> currentPath;
        pq.add(new Path(from, from));


        while (true) {
            while (true) {
                if (pq.size() == 0) { //Tree not 'sammanhängade'
                    return null;
                }
                currentPath = pq.poll();
                currentNode = currentPath.getTo();
                if (!visitedNodes.contains(currentNode)) {
                    break;
                }
            }
            if (currentNode == to) {
                return currentPath.iterator();
            }
            visitedNodes.add(currentNode);
            for (E e : listOfEdges.get(currentNode)) {
                if (e.from == currentNode) {
                    Path newPath = new Path<E>(e.to, currentPath);
                    newPath.add(e);
                    pq.add(newPath);
                }
            }
        }

        /*Path shortestPath = new Path(from, from);
        List<Path> allPaths;
        List<E> edgesFromFrom = new ArrayList<E>();
        for (E e : listOfEdges) {
            if (e.from == from) {

            }
        }*/
	}

    /*public Iterator<E> minimumSpanningTree() {
        return null;
    }*/
	public Iterator<E> minimumSpanningTree() {
        Map<Integer,List<E>> subsets = new HashMap<Integer, List<E>>();
        PriorityQueue<E> pq = new PriorityQueue<E>(new CompKruskalEdge<E>());
        E currentEdge = null;
        for (List<E> eList : listOfEdges.values()) {
            for (E e : eList) {
                subsets.put(e.from, new ArrayList<E>());
                subsets.put(e.to, new ArrayList<E>());
                pq.add(e);
            }
        }
        while (!pq.isEmpty()) {
            while (!pq.isEmpty()) {
                currentEdge = pq.poll();
                if (subsets.get(currentEdge.from) != subsets.get(currentEdge.to)) {
                    break;
                }
            }
            if (!pq.isEmpty()) {
                List<E> list1 = subsets.get(currentEdge.from);
                List<E> list2 = subsets.get(currentEdge.to);
                if (list1.size() > list2.size()) {
                    list1.addAll(list2);
                    list1.add(currentEdge);
                    if (!list2.isEmpty()) {
                        for (E e : list2) {
                            subsets.put(e.from, list1);
                            subsets.put(e.to, list1);
                        }
                    } else {
                        subsets.put(currentEdge.to, list1);
                    }
                } else {
                    list2.addAll(list1);
                    list2.add(currentEdge);
                    if (!list1.isEmpty()) {
                        for (E e : list1) {
                            subsets.put(e.from, list2);
                            subsets.put(e.to, list2);
                        }
                    } else {
                        subsets.put(currentEdge.from, list2);
                    }
                }
            }
        }
        return subsets.get(currentEdge.from).iterator();
        //Kruskals
	}



}
  

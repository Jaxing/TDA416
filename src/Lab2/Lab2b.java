package Lab2;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Lab2b {
    private static class NodeCompare implements Comparator<DLList<Point>.Node> {

        @Override
        public int compare(DLList<Point>.Node o1, DLList<Point>.Node o2) {
           if(o1.prev == null || o1.next == null) {
               return 1;
           }
           if(o2.prev == null || o2.next == null) {
                return -1;
           }
            int out = (int) (valueMesure(o1)*100 - valueMesure(o2)*100);
           return out;
        }
    }

  public static double[] simplifyShape(double[] poly, int k) {
      DLList<Point> list = new DLList<Point>();
      PriorityQueue<DLList<Point>.Node> queue = new PriorityQueue<DLList<Point>.Node>(new NodeCompare());
      DLList<Point>.Node currentNode;
      double[] newPoly = new double[k*2];

      for (int i = 0; i < poly.length; i +=2 ){
         list.addLast(new Point(poly[i], poly[i+1]));
      }
      currentNode = list.getFirst();

      while (currentNode != null) {
          queue.add(currentNode);
          currentNode = currentNode.getNext();
      }

      while (queue.size() > k) {
          DLList<Point>.Node node = queue.poll();
          DLList<Point>.Node prevNode = node.getPrev();
          DLList<Point>.Node nextNode = node.getNext();
          list.remove(node);
          queue.remove(prevNode);
          queue.remove(nextNode);
          queue.add(prevNode);
          queue.add(nextNode);

      }
      currentNode = list.getFirst();
      int index = 0;
      while (currentNode != null) {
          newPoly[index] = currentNode.elt.getX();
          newPoly[index + 1] = currentNode.elt.getY();
          index += 2;
          currentNode = currentNode.getNext();
      }

      return newPoly;

  }

  public static double valueMesure(DLList<Point>.Node node) {
      double l1, l2, l3;

        l1 = node.elt.distanceTo(node.getPrev().elt);
        l2 = node.elt.distanceTo(node.getNext().elt);
        l3 = node.getPrev().elt.distanceTo(node.getNext().elt);

        return l1 + l2 - l3;
  }

  public static double distance(double x1, double y1, double x2, double y2) {
        double dX = Math.abs(x1 - x2);
        double dY = Math.abs(y1 - y2);

        return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
  }
}

import java.util.Comparator;
import java.util.PriorityQueue;

public class Lab2b{
      public static double[] simplifyShape(double[] poly, int k)
      {
          int nDots = poly.length/2;
          // lista med Noder i prioritetsordning [node1,node3,node2] lägst värde hos node1.
          // lista med Noder och deras koordinater [node1,node2,node3,node4,node5,node6];


          Comparator<DLList.Node> comp = new Comparator<DLList.Node>() {
              @Override
              public int compare(DLList.Node o1, DLList.Node o2) {
                  Double[] o1Value = ((Double[]) o1.elt);
                  Double[] o2Value = ((Double[]) o2.elt );
                  if(o1Value[2]  < o2Value[2] ){
                      return 1;
                  }else{
                      return -1;
                  }
              }

          };
          PriorityQueue<DLList.Node> prioQueue = new PriorityQueue<>(comp);

          DLList<Double[]> list = new DLList<>();
          for(int x=0; x<poly.length-1; x+=2){
              Double[] coords = new Double[3];
              coords[0]=poly[x];
              coords[1]=poly[x+1];
              if(x==0 || x == poly.length-3){
                  coords[2] = Double.MAX_VALUE;
              }
              list.addLast(coords);
          }

          DLList.Node node = list.getFirst();
          while( node != null && node != list.getLast()) {
              node = node.getNext();
              node = newTestValue(node);
              prioQueue.add(node);
          }

          //setup done

          while(nDots > k) {
              node = prioQueue.poll();
              DLList.Node prevNode = node.getPrev();
              DLList.Node nextNode = node.getNext();
              list.remove(node);
              prioQueue.remove(prevNode);
              prioQueue.remove(nextNode);

              prevNode = newTestValue(prevNode);
              nextNode = newTestValue(nextNode);

              prioQueue.add(prevNode);
              prioQueue.add(nextNode);
              nDots--;
          }
          return null;
      }
    private static DLList.Node newTestValue(DLList.Node node){
        Double testValue = testValue(node);
        Double[] nodeCoords = (Double[]) node.elt;
        nodeCoords[2] = testValue;
        node.elt = nodeCoords;

        return node;
    }


    private static double testValue(DLList.Node node) {
        DLList.Node nextNode = node.getNext();
        DLList.Node prevNode = node.getPrev();

        Double[] l = (Double[]) prevNode.elt;
        Double[] p = (Double[]) node.elt;
        Double[] r = (Double[]) nextNode.elt;

        double l1 = Math.sqrt( Math.pow(l[0] - p[0], 2) + Math.pow(l[1] - p[1], 2) ); //l1 = L-P
        double l2 = Math.sqrt( Math.pow(p[0] - r[0], 2) + Math.pow(p[1] - r[1], 2) ); //l2 = P-R
        double l3 = Math.sqrt( Math.pow(l[0] - r[0], 2) + Math.pow(l[1] - r[1], 2) ); //l3 = L-R

        System.out.print(l1+l2 +"\t");
        System.out.print(l3 + "\t");
        System.out.println(l1+l2-l3);

        return l1 + l2 - l3;
    }
}

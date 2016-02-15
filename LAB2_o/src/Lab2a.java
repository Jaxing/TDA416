import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;

//run: java Lab2 -k8 -h12 < fig1.txt

public class Lab2a {
  public static double[] simplifyShape(double[] poly, int k) {
    ArrayList<Double> newPoly = new ArrayList<Double>();

    for(int i=0; i < poly.length; i++){
      newPoly.add(poly[i]);
    }

    while(k < newPoly.size()/2) {
      double minValue = Double.MAX_VALUE;
      int minValuePos = 0;
      for (int x = 0; x < newPoly.size(); x+=2) {
        double value = testCoordinate(newPoly, x);
        if (value < minValue) {
          minValue = value;
          minValuePos = x;
        }
      }
      newPoly = removeIndex(newPoly, minValuePos);
      System.out.println("new point removed!");
    }

    double[] returnPoly = new double[newPoly.size()];
    for(int i=0; i<newPoly.size(); i++){
      returnPoly[i] = newPoly.get(i);
    }
    return returnPoly;
  }

  private static ArrayList<Double> removeIndex(ArrayList<Double> poly, int pos) {
    for(int i=pos; i < poly.size()-2; i++){
      poly.set(i,poly.get(i+2));
    }
    poly.remove(poly.size()-1);
    poly.remove(poly.size()-1);
    return poly;
  }

  private static double testCoordinate(ArrayList<Double> poly, int pos) {
      if(pos < 2 || pos >= poly.size()-2){
          System.out.println(pos +"cant do ends!");
          return Double.MAX_VALUE;
      }
    double lX = poly.get(pos - 2);
    double lY = poly.get(pos - 1);
    double pX = poly.get(pos + 0);
    double pY = poly.get(pos + 1);
    double rX = poly.get(pos + 2);
    double rY = poly.get(pos + 3);

    double l1 = Math.sqrt( Math.pow(lX - pX, 2) + Math.pow(lY - pY, 2) ); //l1 = L-P
    double l2 = Math.sqrt( Math.pow(pX - rX, 2) + Math.pow(pY - rY, 2) ); //l2 = P-R
    double l3 = Math.sqrt( Math.pow(lX - rX, 2) + Math.pow(lY - rY, 2) ); //l3 = L-R

    System.out.print(l1+l2 +"\t");
    System.out.print(l3 + "\t");
    System.out.println(l1+l2-l3);

    return l1 + l2 - l3;
  }
}
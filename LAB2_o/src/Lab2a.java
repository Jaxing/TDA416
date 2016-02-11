import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Lab2a {
  //while
  //antalet punkter är större än beräknat värdemåttet av varje intern punkt (ej ändpunkterna)tag bort den minst betydelsefullaend
  // whil

  public static double[] simplifyShape(double[] poly, int k) {
    double[] newPoly = poly;

    while(k < newPoly.length/2) {
      double minValue = Double.MAX_VALUE;
      int minValuePos = 0;
      for (int x = 0; x < newPoly.length; x+=2) {
        double value = testCoordinate(newPoly, x);
        if (value < minValue) {
          minValue = value;
          minValuePos = x;
        }
      }
      newPoly = removeIndex(newPoly, minValuePos);
      System.out.println("new point removed!");
    }

    return newPoly;
  }

  private static double[] removeIndex(double[] poly, int pos) {
    double[] newPoly = new double[poly.length-2];
    for(int i = 0; i < newPoly.length; i++){
      if(i < pos) {
        newPoly[i] = poly[i];
      }else {
        newPoly[i] = poly[i + 2];
      }
    }
    return newPoly;
  }

  private static double testCoordinate(double[] poly, int pos) {
      if(pos < 2 || poly.length-2 < pos){
          System.out.println("cant do ends!");
          return Double.MAX_VALUE;
      }
    double lX = poly[pos - 2];
    double lY = poly[pos - 1];
    double pX = poly[pos + 0];
    double pY = poly[pos + 1];
    double rX = poly[pos + 2];
    double rY = poly[pos + 3];

    double l1 = Math.sqrt( Math.pow(lX - pX, 2) + Math.pow(lY - pY, 2) ); //l1 = L-P
    double l2 = Math.sqrt( Math.pow(pX - rX, 2) + Math.pow(pY - rY, 2) ); //l2 = P-R
    double l3 = Math.sqrt( Math.pow(lX - rX, 2) + Math.pow(lY - rY, 2) ); //l3 = L-R

    System.out.print(l1+l2 +"\t");
    System.out.print(l3 + "\t");
    System.out.println(l1+l2-l3);

    return l1 + l2 - l3;
  }
}
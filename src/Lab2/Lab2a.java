package Lab2;

public class Lab2a {
  public static double[] simplifyShape(double[] poly, int k) {

      double[] newPoly = poly;
      while (newPoly.length/2 > k) {
          double minValueMeasure = Double.MAX_VALUE;
          int indexOfLeast = 0;

          for (int i = 2; i < newPoly.length-2; i = i +2) {
              double l1, l2, l3;
              double valueMeasure;

              l1 = distance(newPoly[i-2], newPoly[i-1], newPoly[i], newPoly[i+1]);
              l2 = distance(newPoly[i], newPoly[i+1], newPoly[i+2], newPoly[i+3]);
              l3 = distance(newPoly[i-2], newPoly[i-1], newPoly[i+2], newPoly[i+3]);

              valueMeasure = l1 + l2 - l3;

              if(minValueMeasure > valueMeasure) {
                  minValueMeasure = valueMeasure;
                  indexOfLeast = i;
              }
          }
          newPoly = removePoint(newPoly, indexOfLeast);
      }
      return newPoly;
  }
  public static double distance(double x1, double y1, double x2, double y2) {
      double dX = Math.abs(x1 -x2);
      double dY = Math.abs(y1 - y2);

      return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
  }
    public static double[] removePoint(double[] poly, int k) {
        double[] newPoly;
        if(poly.length > 1) {
            newPoly = new double[poly.length - 2];
        } else{
            newPoly = new double[0];
        }

        for (int i = 0; i < k; i++) {
            newPoly[i] = poly[i];
        }
        for (int i = k; i < newPoly.length; i++) {
            newPoly[i] = poly[i+2];
        }
        return newPoly;
    }
}


package Lab1;



/**
 * Created by jesper on 2016-01-20.
 */
public class MySqrt {

    public static double mySqrtLoop(double sqr, double eps) {
        double mean;
        double high;
        double low;


        if(sqr < 0) {
            return Double.NaN;
        } else if(sqr < 1) {
            high = 1;
            low = sqr;
        } else {
            high = sqr;
            low = 1;
        }

        mean = (high + low)/2;

        while (Math.abs(mean*mean - sqr) >= eps) {
            if(mean*mean > sqr) {
                high = mean;        //Search [low,mean]
            } else {
                low = mean;         //Search [mean,high]
            }
            mean = (high + low)/2;
        }
        return mean;
    }

    public static double mySqrtRecurse(double sqr, double eps) {
            if(sqr < 0) {
                return Double.NaN;
            }
            if(sqr < 1) {
                return help(sqr, eps, sqr, 1);
            }

            return help(sqr, eps, 1, sqr);
    }

    private static double help(double sqr, double eps, double low, double high) {
        double mean = (low + high) / 2;
        if(Math.abs(mean*mean - sqr) >= eps) {
            if(mean*mean > sqr) {                   // if mean^2 is greater than sqr then the root of sqr is smaller
                                                    // than mean, we search the interval [1,mean]
                return help(sqr, eps, low, mean);
            } else {                                // else we the root is lager than mean, we search the interval [mean,sqr]
                return help(sqr, eps, mean, high);
            }
        }

        return mean;
    }

    /*public static void main(String[] args) {
        double eps = 0.000001;

        System.out.println("Recursive: \nTest1 : " + (Math.abs(Math.pow(MySqrt.mySqrtRecurse(1, eps), 2) - 1) < eps));
        System.out.println("Test2 : " + (Math.abs(Math.pow(MySqrt.mySqrtRecurse(0.5, eps), 2) - 0.5) < eps));
        System.out.println("Test3 : " + (Double.compare(MySqrt.mySqrtRecurse(-1, eps), Double.NaN) == 0));
        System.out.println("Test4 : " + (Math.abs(Math.pow(MySqrt.mySqrtRecurse(16, eps), 2) - 16) < eps));
        System.out.println("Test5 : " + (Math.abs(Math.pow(MySqrt.mySqrtRecurse(0, eps), 2) - 0) < eps)); //Doesn't


        System.out.println("Loop: \nTest1 : " + (Math.abs(Math.pow(MySqrt.mySqrtLoop(1, eps), 2) - 1) < eps));
        System.out.println("Test2 : " + (Math.abs(Math.pow(MySqrt.mySqrtLoop(0.5, eps), 2) - 0.5) < eps));
        System.out.println("Test3 : " + (Double.compare(MySqrt.mySqrtLoop(-1, eps), Double.NaN) == 0));
        System.out.println("Test4 : " + (Math.abs(Math.pow(MySqrt.mySqrtLoop(16, eps), 2) - 16) < eps));
        System.out.println("Test5 : " + (Math.abs(Math.pow(MySqrt.mySqrtLoop(0, eps), 2) - 0) < eps));
    }*/
}

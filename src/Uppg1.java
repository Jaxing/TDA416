/**
 * Created by jesper on 2016-01-20.
 */
public class Uppg1 {
    static double binarySqrt(double sqr, double eps) {
        double mean = (sqr + 1)/2;
        if(Math.abs(mean*mean - sqr) > eps) {     // if the distance between mean^2 and sqr is greater than eps
                                                // then mean is not considered the root of sqr
            help(sqr, eps, 1, sqr);
        }
        return mean;
    }

    static double help(double sqr, double eps, double low, double high) {
        double mean = (low + high) / 2;
        if(Math.abs(mean*mean - sqr) > eps) {
            if(mean*mean > sqr) {                   // if mean^2 is greater than sqr then the root of sqr is smaller
                                                    // than mean, we search the interval [1,mean]
                return help(sqr, eps, 1, mean);
            } else {                                // else we the root is lager than mean, we search the interval [mean,sqr]
                return help(sqr, eps, mean, sqr);
            }
        }

        return mean;
    }
}

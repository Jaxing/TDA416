package com.company;

import java.lang.Math;

/**
 * Created by Oskar on 21-Jan-16.
 */
public class MySqrt {


    public static double mySqrtLoop(double x, double eps) {
        double Ymin = 0;
        double Ymax = 0;
        double Ymid = 0;
        double YmidSq = 0; //Ymid Squared

        //Algorithm
        while(true) {
            Ymid = (Ymin + Ymax) / 2;
            YmidSq = Math.pow(Ymid, 2); //Assign Ymid Squared
            if (Math.abs(YmidSq - x) <= eps) {
                break; //done! Ymid is good enaught
            } else if (YmidSq - x < 0) {
                Ymin = Ymid; //Look in the top half
            } else if (YmidSq - x >= 0) {
                Ymax = Ymid; //Look in the bottom half

            }
        }
        return 0;
    }
    //public static double mySqrtRecurse(double x, double eps){}
    public static void main(String [] args){
        double eps = Math.pow(10,-6);

    }
}

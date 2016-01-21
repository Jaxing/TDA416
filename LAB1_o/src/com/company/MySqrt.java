package com.company;

import java.lang.Math;

/**
 * Created by Oskar on 21-Jan-16.
 */
public class MySqrt {


    public static double mySqrtLoop(double x, double eps) {
        double Ymin,Ymax,Ymid,YmidSq;

        //Set initial interval based on the value of x.
        if(x<0){ //no real root exists
            return Double.NaN;
        } else if(0 <= x && x <= 1){ //root must be within [x,1]
            Ymin = x;
            Ymax = 1;
        } else{ //(x>1) root must be within [1,x]
            Ymin = 1;
            Ymax = x;
        }

        //Algorithm
        while(true) {
            Ymid = (Ymin + Ymax) / 2;
            YmidSq = Math.pow(Ymid, 2); //Assign Ymid Squared
            if (Math.abs(YmidSq - x) <= eps) {
                break; //done! Ymid is good enaught
            } else if (YmidSq > x) {
                Ymin = Ymid; //Look in the top half
            } else if (YmidSq < x) {
                Ymax = Ymid; //Look in the bottom half
            }
        }
        return Ymid;
    }
    //public static double mySqrtRecurse(double x, double eps){}
    public static void main(String [] args){
        double eps = Math.pow(10,-6);
        double x = 9;

        System.out.println("Ymid:" +mySqrtLoop(x,eps));

    }
}

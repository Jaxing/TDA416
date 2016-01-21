package com.company;

import java.lang.Math;

/**
 * Created by Oskar on 21-Jan-16.
 */
public class MySqrt {
    public static double mySqrtLoop(double x, double eps) {
        double Ymin, Ymax, Ymid, YmidSq;

        //Set initial interval based on the value of x.
        if (x < 0) { //no real root exists
            return Double.NaN;
        } else if (0 <= x && x <= 1) { //root must be within [x,1]
            Ymin = x;
            Ymax = 1;
        } else { //(x>1) root must be within [1,x]
            Ymin = 1;
            Ymax = x;
        }

        while (true) {       //Algorithm
            Ymid = (Ymin + Ymax) / 2;
            YmidSq = Math.pow(Ymid, 2); //Assign Ymid Squared
            if (Math.abs(YmidSq - x) <= eps) {
                break; //done! Ymid is good enaught
            } else if (YmidSq < x) {
                Ymin = Ymid; //Look in the top half
            } else { //(YmidSq > x)
                Ymax = Ymid; //Look in the bottom half
            }
        }
        return Ymid;
    }

    public static double mySqrtRecurse(double x, double eps){
        double Ymin, Ymax;
        //Set initial interval based on the value of x.
        if (x < 0) { //no real root exists
            return Double.NaN;
        } else if (0 <= x && x <= 1) { //root must be within [x,1]
            Ymin = x;
            Ymax = 1;
        } else { //(x>1) root must be within [1,x]
            Ymin = 1;
            Ymax = x;
        }
        return mySqrtRecurseHelp(Ymin,Ymax,x,eps); //return yMid
    }

    private static Double mySqrtRecurseHelp(double Ymin, double Ymax, double x,double eps){
        double Ymid = (Ymin+Ymax) / 2;
        double YmidSq = Math.pow(Ymid, 2); //Calc Ymid Squared

        if (Math.abs(YmidSq-x) <= eps) {
            return Ymid;
        } else if (YmidSq < x) {
            return mySqrtRecurseHelp(Ymid,Ymax,x,eps); //look in the top half
        } else { //(YmidSq > x)
            return mySqrtRecurseHelp(Ymin,Ymid,x,eps); //look in the bottom half
        }
    }


    public static void main(String [] args){
        double eps = Math.pow(10,-6);
        System.out.println("non recursive:");
        checkValue(-1,eps,false);
        checkValue(0,eps,false);
        checkValue(0.5,eps,false);
        checkValue(1,eps,false);
        checkValue(9,eps,false);

        System.out.println("\nrecursive:");
        checkValue(-1,eps,true);
        checkValue(0,eps,true);
        checkValue(0.5,eps,true);
        checkValue(1,eps,true);
        checkValue(9,eps,true);
    }

    private static void checkValue(double x,double eps,boolean recursive){
        double diff;
        if(recursive){
            diff = Math.pow(mySqrtRecurse(x,eps),2)-x;
        }else {
            diff = Math.pow(mySqrtLoop(x, eps), 2)-x;
        }
        System.out.println("x=" +x +"\tYmid^2-x=" +diff +"\teps:" +eps +"\tdiff<eps:" +(diff<eps));
    }
}

package Lab3;

import Lab3.LabFiles.SimpleSet;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by jesper on 2016-02-15.
 */
public class TestSetCorrectness {
    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int n3 = Integer.parseInt(args[2]);
        int n4 = Integer.parseInt(args[3]);

        SimpleSet<Integer> set;
        if(n1 == 1)
            set = new SortedLinkedListSet<Integer>();
        else
            set = new SplayTreeSet<Integer>();
        Set<Integer> cmpSet = new HashSet<Integer>();

        Random rand = new Random();

        while (n2-- > 0) {
            for (int i = n3; i > 0; i--) {
                int random = rand.nextInt(n4);
                boolean trueValue, myValue;
                System.out.println("----Add " + random);
                myValue = set.add(random);
                trueValue = cmpSet.add(random);
                System.out.println("value "+ myValue + " " + (n3 - i) + " size " + set.size());

                BTreePrinter.printNode(((SplayTreeSet) set).root);

                if (myValue != trueValue) {
                    System.out.println("Add failed because of wrong value at " + (n3-i) + " iteration");
                    return;
                }
                if (set.size() != cmpSet.size()) {
                    System.out.println("Add failed because of wrong size");
                    return;
                }
                if (set.getClass() == SplayTreeSet.class && ((SplayTreeSet<Integer>)set).root.getData() != random && trueValue) {
                    System.out.println("Add failed because of wrong splay " + ((SplayTreeSet<Integer>) set).root.getData() + " " + random + " at " + (n3-i) + " iteration");
                    return;
                }
            }
            for (int i = n3; i > 0; i--) {
                int random = rand.nextInt(n4);
                boolean trueValue, myValue;
                myValue = set.contains(random);
                trueValue = cmpSet.contains(random);
                System.out.println("----Contains " + random + " " + myValue +"----");

                BTreePrinter.printNode(((SplayTreeSet)set).root);
                if (myValue != trueValue) {
                    System.out.println("Contains failed");
                    return;
                }
            }
            BTreePrinter.printNode(((SplayTreeSet)set).root);
            for (int i = n4; i > 0; i--) {
                int random = rand.nextInt(n4);
                boolean trueValue, myValue;
                myValue = set.remove(random);
                trueValue = cmpSet.remove(random);
                //System.out.println("----Remove " + random + " " + myValue +"----");
                //BTreePrinter.printNode(((SplayTreeSet) set).root);
                if (myValue != trueValue) {
                    System.out.println("Remove failed");
                    return;
                }
            }
        }
    }


}

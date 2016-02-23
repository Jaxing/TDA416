
/**
 * java TestSetSpeed 1 1000
 */
import java.util.Random;

class TestSetSpeed {
 public static void main(String[] args) {

  
  final int implnumber = Integer.parseInt(args[0]);

  SimpleSet<Integer> set = 
   implnumber == 1 ? new SortedLinkedListSet<Integer>() : new SplayTreeSet<Integer>();
  System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
  System.out.println("Measuring speed of " + (implnumber == 1 ? "SortedLinkedListSet" : "SplayTreeSet"));
  System.out.println("This will take 10-20 seconds...");


  final int size = Integer.parseInt(args[1]);

  final int bound = 2 * size;

  Random rand = new Random();

  while (set.size() < size) {
   set.add(new Integer(rand.nextInt(bound)));
  }


  long ops = 0;
  long startTime = System.currentTimeMillis();
  long niter = 1;
  long endTime = startTime;

  while (endTime - startTime <= 100) {
   for (long i = 0; i < niter; i++) {
    set.contains(new Integer(rand.nextInt(bound)));
    set.contains(new Integer(rand.nextInt(bound)));
    ops += 2;

    boolean done = false;
    while (!done) {
     done = set.add(new Integer(rand.nextInt(bound)));
     ops++;
    }
    
    done = false;
    while (!done) {
     done = set.remove(new Integer(rand.nextInt(bound)));
     ops++;
    }
   }
   endTime = System.currentTimeMillis();
   niter *= 2;
  }
  
  System.out.println("time: " + (double)(endTime - startTime) / 1000.0 + "s");
  System.out.println("operations: " + ops);
  System.out.println("time/operation: " + (double)(endTime - startTime) * 1e6 / (double)ops + "ns");
 }
}

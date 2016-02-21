import java.util.Random;
import java.util.HashSet;

public class TestSetCorrectness{
	public static void main(String[] args){
		  final int test = Integer.parseInt(args[0]);
		  final int nLoops = Integer.parseInt(args[1]);
		  final int nOps = Integer.parseInt(args[2]);
		  final int randomRoof = Integer.parseInt(args[3]);

		  Random rand = new Random();
		  int nErrors = 0;
		 
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(" Beginning test...");  
		for(int i = 0; i < nLoops; i++){
	  	 		SimpleSet<Integer> set = null;
			switch (test){
				case 1:
					set = new SortedLinkedListSet<>();
				break;
				case 2:
					set = new SplayTreeSet<>();
				break;
				default:
					System.out.println("wrong input..");
					System.exit(1);
			}
			HashSet<Integer> javaSet = new HashSet<>();

			for(int ops = 0; ops < nOps; ops++){
				int testNumber = new Integer(rand.nextInt(randomRoof));
				int testCase = new Integer(rand.nextInt(4));

				switch (testCase){
					case 0:
						System.out.print("+ Testing size:");
						int correctSize = javaSet.size();
						int testedSize = set.size();
						if(correctSize == testedSize){
							System.out.print(" correct!");
						}else{
							System.out.print(" error, got:" +testedSize +", sould be:"+correctSize);
							nErrors++;
						}
						System.out.print("\n");
					break;
					case 1:
						System.out.print("+ Testing add");
						boolean correctValue = javaSet.add(testNumber);
						boolean testedValue = set.add(testNumber);
						if(correctValue == testedValue){
							System.out.print(" correct!\n");
						}else{
							System.out.print(" error, got:" +testedValue +", sould be:"+correctValue);
							nErrors++;
						}
						System.out.print("\n");
					break;
					case 2:
						System.out.print("+ Testing remove");
						boolean correctRemove = javaSet.remove(testNumber);
						boolean testedRemove = set.remove(testNumber);
						if(correctRemove == testedRemove){
							System.out.print(" correct!\n");
						}else{
							System.out.print(" error, got:" +testedRemove +", sould be:"+correctRemove);
							nErrors++;
						}
						System.out.print("\n");
					break;
					case 3:
						System.out.print("+ Testing contains");
						boolean correctContains = javaSet.contains(testNumber);
						boolean testedContains = set.contains(testNumber);
						if(correctContains == testedContains){
							System.out.print(" correct!\n");
						}else{
							System.out.print(" error, got:" +testedContains +", sould be:"+correctContains);
							nErrors++;
						}
						System.out.print("\n");
					break;
				}
			}

		} //big for loop
		System.out.println("+++++ Test Done: nErrors:" + nErrors);

		
	}

}
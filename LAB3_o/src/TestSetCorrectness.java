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
		testLoop:
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
							System.out.println(" error, got:" +testedSize +", sould be:"+correctSize);
							System.out.println(set.toString());
							System.out.println(set.size());
							return;
						}
						System.out.print("\n");
					break;
					case 1:
						System.out.print("+ Testing add:" +testNumber);
						boolean correctValue = javaSet.add(testNumber);
						boolean testedValue = set.add(testNumber);
						if(correctValue == testedValue && javaSet.size() == set.size()){
							System.out.print(" correct!");
						}else{
							System.out.println(" error, got:" +testedValue +", sould be:"+correctValue);
							System.out.println(set.toString());
							System.out.println(set.size());
							return;
						}
						System.out.print("\n");
					break;
					case 2:
						System.out.print("+ Testing remove:" +testNumber);
						boolean correctRemove = javaSet.remove(testNumber);
						boolean testedRemove = set.remove(testNumber);
						if(correctRemove == testedRemove && javaSet.size() == set.size()){
							System.out.print(" correct!");
						}else{
							System.out.println(" error, got:" +testedRemove +", sould be:"+correctRemove);
							System.out.println(set.toString());
							System.out.println(set.size());
							return;
						}
						System.out.print("\n");
					break;
					case 3:
						System.out.print("+ Testing contains:" +testNumber);
						boolean correctContains = javaSet.contains(testNumber);
						boolean testedContains = set.contains(testNumber);
						if(correctContains == testedContains){
							System.out.print(" correct!");
						}else{
							System.out.println(" error, got:" +testedContains +", sould be:"+correctContains);
							System.out.println(set.toString());
							System.out.println(set.size());
							return;
						}
						System.out.print("\n");
					break;
				}
			}

		} //big for loop
		System.out.println("+++++ Test Done: nErrors:" + nErrors);

		
	}

}
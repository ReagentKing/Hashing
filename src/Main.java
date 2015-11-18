import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Linear Probing:");
		
		linearFill(60);
		linearFill(80);
		
		System.out.println("Quadratic Probing:");
		
		quadFill(60);
		quadFill(80);
		
	}
	public static void linearFill(int percent){
		
		int numToMake = 0;
		int numAdded = 0;
		double [] hashtable = new double [1019];
		
		if (percent == 60){
			numToMake = 611;
		}
		
		else{
			numToMake = 815;
		}
		
		ArrayList<Double> valList = new ArrayList<Double>(numToMake);
		
		while(numAdded<numToMake){
			
			double itm = genRanNum();
			
			if(!valList.contains(itm)){
				valList.add(itm);
				//System.out.println("added " + itm + " to list of values to hash");
				numAdded++;
				
			}
		}
		
		for(int i=0;i<numToMake;i++){
			//System.out.println("iteration: " + i);
			int loc = hash(valList.get(i));
			//System.out.println("location: " + loc);
			boolean added = false;
			
			while(added == false){
				
				if(hashtable[loc]==0){
					hashtable[loc] = valList.get(i);
					added = true;
				}
				
				else{
					if((loc+1) >= 1019){
						loc = 0;
					}
					else{
						loc++;
					}
				}
			}
		}
		
		LinearSearch(hashtable);
		
	}
	
	public static void LinearSearch(double [] hshtble){
		
		int [] numProbes = new int [10001];
		boolean [] foundArr = new boolean [100001];
		int numfound = 0;
		
		for(int i=1;i<=10000;i++){
			
			//System.out.println("Searching for : " + i);
			boolean found = false;
			int loc = i%1019;
			
			while(found == false){

				if(hshtble[loc]==0){
					break;
				}
				
				numProbes[i]++;
				
				if(hshtble[loc]!=i){
					
					if((loc+1)>=1019){
						loc=0;
					}
					
					else{
						loc++;
					}
				}
				
				else{
					
					numProbes[i]++;
					found = true;
					foundArr[i] = true;
					numfound++;
					//System.out.printf("%d: found : num probes required: %d\n", i,numProbes[i]+1);
				}
			}
		}
		int numNotFound = 0;
		int totalNumProbesFound = 0;
		int totalNumProbesNot = 0;
		double avgProbeFound = 0;
		double avgProbeNot = 0;
		
		for(int i=0;i<numProbes.length;i++){
				if(foundArr[i] == true){
					totalNumProbesFound+=numProbes[i];
				}
				else{
					totalNumProbesNot+=numProbes[i];
					numNotFound++;
				}
		}
		
		avgProbeFound = (double)totalNumProbesFound/(double)numfound;
		avgProbeNot = (double)totalNumProbesNot/(double)numNotFound;
		
		System.out.println("Total number successful searches: " + numfound);
		//System.out.println("Total number of probes for successful: " + totalNumProbesFound);
		System.out.println("Average number of probes per successful search: " + avgProbeFound);
		//System.out.println("Total number of probes for unsuccessful: " + totalNumProbesNot);
		System.out.println("Average number of probes per unsuccessful search: " + avgProbeNot);
	}
	
	public static void quadFill(int percent){
		
		int numToMake = 0;
		int numAdded = 0;
		
		double [] hashtable = new double [1019];
		
		if (percent == 60){
			numToMake = 611;
		}
		
		else{
			numToMake = 815;
		}
		
		ArrayList<Double> valList = new ArrayList<Double>(numToMake);
		
		while(numAdded<numToMake){
			
			double itm = genRanNum();
			
			if(!valList.contains(itm)){
				valList.add(itm);
				//System.out.println("added " + itm + " to list of values to hash");
				numAdded++;
				
			}
		}
		
		for(int i=0;i<numToMake;i++){
			//System.out.println("iteration: " + i);
			int loc = hash(valList.get(i));
			int newloc = loc;
			int modifier = 1;
			int addOrsub = 1;
			//System.out.println("location: " + loc);
			boolean added = false;
			
			while(added == false){
				
				if(hashtable[newloc]==0){
					hashtable[newloc] = valList.get(i);
					added = true;
				}
				else{
					if(addOrsub == 1){
						//System.out.println("addition");
						newloc = loc +(modifier*modifier);
						//System.out.println("Next loc initially: " + newloc);
						if(newloc>=1019){
							//System.out.println("changed");
							newloc -= 1019;
						}
						addOrsub = 2;
						//System.out.println("added: location to look next is " + newloc);
					}
					else{
						//System.out.println("subtraction");
						newloc = loc - (modifier*modifier);
						//System.out.println("Next loc initially: " + newloc);
						if(newloc<0){
							//System.out.println("changed");
							newloc += 1019;
						}
						modifier++;
						addOrsub = 1;
						//System.out.println("subtracted: location to look next is " + newloc);
					}
				}
			}
				

		}
		
		quadSearch(hashtable);
		
	}
	
	public static void quadSearch(double [] hshtble){
		int [] numProbes = new int[10001];
		boolean [] foundArr = new boolean[10001];
		int numfound = 0;
		
		for(int i=1;i<=10000;i++){
			int modifier = 1;
			int addOrsub = 1;
			int loc = hash(i);
			int newloc = loc;
			boolean found = false;

			while(found == false &&  modifier < 509){
				if(hshtble[newloc]==0){
					break;
				}
				if(hshtble[newloc]!=i){
					numProbes[i]++;
					if(addOrsub == 1){
						//System.out.println("addition");
						newloc = loc +(modifier*modifier);
						//System.out.println("next loc initially " + newloc);
						if(newloc>=1019){
							//System.out.println("changed");
							newloc =fixIndex(newloc);
						}
						addOrsub = 2;
						//System.out.println("added: location to look next is " + newloc);
					}
					else{
						//System.out.println("subtraction");
						newloc = loc - (modifier*modifier);
						//System.out.println("next loc initially " + newloc);
						if(newloc<0){
							//System.out.println("changed");
							newloc=fixIndex(newloc);
						}
						modifier++;
						addOrsub = 1;
						//System.out.println("subtracted: location to look next is " + newloc);
					}
				}
				else{
					numProbes[i]++;
					found = true;
					numfound++;
					foundArr[i] = true;
					//System.out.printf("%d: found : num probes required: %d\n", i,numProbes[i]+1);
				}
			}
		}
		int numNotFound = 0;
		int totalNumProbesFound = 0;
		int totalNumProbesNot = 0;
		double avgProbeFound = 0;
		double avgProbeNot = 0;
		
		for(int i=0;i<numProbes.length;i++){
				if(foundArr[i] == true){
					totalNumProbesFound+=numProbes[i];
				}
				else{
					totalNumProbesNot+=numProbes[i];
					numNotFound++;
				}
		}
		
		avgProbeFound = (double)totalNumProbesFound/(double)numfound;
		avgProbeNot = (double)totalNumProbesNot/(double)numNotFound;
		
		System.out.println("Total number successful searches: " + numfound);
		//System.out.println("Total number of probes for successful: " + totalNumProbesFound);
		System.out.println("Average number of probes per successful search: " + avgProbeFound);
		//System.out.println("Total number of probes for unsuccessful: " + totalNumProbesNot);
		System.out.println("Average number of probes per unsuccessful search: " + avgProbeNot);
	}
	
	public static int fixIndex(int idx){
		if(idx>=1019){
			while(idx>1018){
				idx-=1019;
			}
		}
		else{
			while(idx<0){
				idx+=1019;
			}
		}
		return idx;
	}
	
	public static double genRanNum(){
		
		Random r = new Random();
		int ranVal = r.nextInt(10000) + 1;
		
		return ranVal;
		
	}
	
	public static int hash(double key){
		int retVal = (int) (key%1019);
		//System.out.println("key: " + retVal);
		return retVal;
	}
	
}

import java.util.ArrayList;
 
public class Scheduler {
 
    private Assignment[] assignmentArray;
    private Integer[] C;
    private Double[] max;
    private ArrayList<Assignment> solutionDynamic;
    private ArrayList<Assignment> solutionGreedy;
 
    /**
     * @throws IllegalArgumentException when the given array is empty
     */
    public Scheduler(Assignment[] assignmentArray) throws IllegalArgumentException {
        // Should be instantiated with an Assignment array
        // All the properties of this class should be initialized here
    	if(assignmentArray.length==0||assignmentArray==null) {
    		throw new IllegalArgumentException();
    	}
    		this.assignmentArray=assignmentArray;
        	
    		//all arrays and arraylists are initialized here
        	C=new Integer[assignmentArray.length];
        	max=new Double[assignmentArray.length];
        	solutionDynamic = new ArrayList<Assignment>();
        	solutionGreedy = new ArrayList<Assignment>();
        	
        	//C is filled after this method
        	this.calculateC();
       
        	//max array was firstly filled zeros, I use 0 ,because when the calculateMax is called,the program can be aware of index of max are changed or not changed  
        	for(int i = 0 ; i < max.length ; i++) {
        		max[i]=0.0;
        	}
         	this.calculateMax(assignmentArray.length-1);
 
     	
    }
 
    /**
     * @param index of the {@link Assignment}
     * @return Returns the index of the last compatible {@link Assignment},
     * returns -1 if there are no compatible assignments
     */
    private int binarySearch(int index) {
    	/*
    	 * start time of index
    	 * it is converted to integer to compare other times
    	 */
    	String[] startArr=assignmentArray[index].getStartTime().split(":");
    	String temp1= startArr[0]+startArr[1];
    	int startTime=Integer.parseInt(temp1);
    	
    	/*
    	 * this is finish time of firts index of assignmentArray
    	 */
    	String[] finishArr1= assignmentArray[0].getFinishTime().split(":"); 
    	String temp2= finishArr1[0]+finishArr1[1];
    	int finishTime1=Integer.parseInt(temp2);
    	
    	//firstly compare with first index
    	 if(startTime< finishTime1) {
             return -1;
         }
    	 
 
     	int lo = 0;
        int hi = assignmentArray.length-1;
 
         while (lo <= hi) {
             int mid=(hi+lo)/2;
             
            String[] finishArr2= assignmentArray[mid].getFinishTime().split(":"); 
            
          String temp3 = finishArr2[0]+finishArr2[1];
       
          	int finishTime3=Integer.parseInt(temp3);
 
            if (startTime<finishTime3) {
                 hi = mid - 1;
                 
            } else if (startTime>=finishTime3) {
            	lo = mid + 1;
            	// there is a differences between finding a exact element and closest element
            	//if starttime is later on finishtime we exchange lo but, we dont know whether lo+1 index is compatible with starttime or not.
            	//so i compare them to understand whether they overlap eachother or not
            	String[] finishArr3= assignmentArray[mid+1].getFinishTime().split(":");
              	String temp4 =finishArr3[0]+finishArr3[1]; 
              	
              	int finishTime4=Integer.parseInt(temp4);
            	
              	
            	if(startTime<finishTime4) {
            		 return mid;
            	}
                 
             } else {
                 return mid;
             }
         }
         return lo;
    }
    	
    
 
 
    /**
     * {@link #C} must be filled after calling this method
     */
    //binarysearch called here and C is filled here
    private void calculateC() {
    		for(int i = 0 ; i<C.length ; i++) {
    			C[i]=this.binarySearch(i);
    		}
    }
 
 
    /**
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    // findsolution method is called here than, this method returns solutiondynamic arraylist
    public ArrayList<Assignment> scheduleDynamic() {
        this.findSolutionDynamic(assignmentArray.length-1);
        return solutionDynamic;
    }
 
    /**
     * {@link #solutionDynamic} must be filled after calling this method
     */
    private void findSolutionDynamic(int i) {
    	//if i is -1, program understand that, it is not overlapping anything so return nothing
    		if(i==-1) {
    			return;
    		}
    		if(i == 0) {
    			//I examined 0 cases alone because, if i equals 0 than i cant compare max[i] and max[i-1], it gives index exception
    			System.out.printf("findSolutionDynamic(%d)\n",i);
    			System.out.printf("Adding %s to the dynamic schedule\n",assignmentArray[0]);
    			solutionDynamic.add(assignmentArray[0]);
    			return;
    		}
    		System.out.printf("findSolutionDynamic(%d)\n",i);
    			
    		if(max[i]>max[i-1]) {
    			//that means it is better to include assignment i.
    			System.out.printf("Adding %s to the dynamic schedule\n",assignmentArray[i]);
    			findSolutionDynamic(C[i]);
    			solutionDynamic.add(assignmentArray[i]);
    		}
    		else {
    			//that means it is worse to include assignment i. so program don't include i
    			findSolutionDynamic(i-1);
    		}
    }
 
    /**
     * {@link #max} must be filled after calling this method
     */
    private Double calculateMax(int i) {
    	//-1 means it is not compitable anything.
        if(i==-1) {
            return 0.0;
        }
        //this condition is controlling whether indexes of max were calculated or not 
        else if(max[i]!=0.0) {
            if(i==0) {
                System.out.println("calculateMax(0): Zero");
            }
            else {
            System.out.printf("calculateMax(%d): Present\n",i);
            }
            return max[i];
        }
        else {
            if(i==0) {
                System.out.println("calculateMax(0): Zero");
            }
            else {
            System.out.printf("calculateMax(%d): Prepare\n",i);
            }
            //it controlls two cases, first case is with i , other case is without i
            double number1=assignmentArray[i].getWeight()+calculateMax(C[i]);
            double number2=calculateMax(i-1);
        
            max[i]=number2>number1 ? number2 : number1;
            return number2>number1 ? number2 : number1;
 
        }
 
 
    }
 
    /**
     * {@link #solutionGreedy} must be filled after calling this method
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleGreedy() {
    	//it instantly catch first element of array and add the arraylist
    	System.out.printf("Adding %s to the greedy schedule\n",assignmentArray[0]);
    	solutionGreedy.add(assignmentArray[0]);
    	
    	String[] finishTimeOfZeroArr=assignmentArray[0].getFinishTime().split(":");
    	Integer finishTime0=Integer.parseInt(finishTimeOfZeroArr[0]+finishTimeOfZeroArr[1]);
    	
    
    	//then i found the assignments which are overlapping with first index and each other by using this method
    	for(int i = 1 ; i < assignmentArray.length ; i++) {
    		String[] startTimeOfIArr=assignmentArray[i].getStartTime().split(":");
        	Integer startTimeI=Integer.parseInt(startTimeOfIArr[0]+startTimeOfIArr[1]);
        	
        	if(startTimeI>=finishTime0) {
        		System.out.printf("Adding %s to the greedy schedule\n",assignmentArray[i]);
            	solutionGreedy.add(assignmentArray[i]);
            	
            	String[] finishTimeOfi=assignmentArray[i].getFinishTime().split(":");
            	finishTime0=Integer.parseInt(finishTimeOfi[0]+finishTimeOfi[1]);
            	
            	
        	}
        	
        	
    	}
    	return solutionGreedy;
    }
}
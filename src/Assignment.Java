public class Assignment implements Comparable<Assignment> {
    private String name;
    private String start;
    private int duration;
    private int importance;
    private boolean maellard;
 
    /*
        Getter methods
     */
    public String getName() {
        return name;
    }
 
    public String getStartTime() {
        return start;
    }
 
    public int getDuration() {
        return duration;
    }
 
    public int getImportance() {
        return importance;
    }
 
    public boolean isMaellard() {
        return maellard;
    }
 
    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    
    /*start was seperated two parts by usins split method
     * then these two part merge without ":" signs in string
     * then it is converted to integer and adding duration*100
     * after that it is converted to string replacing ":" middle
     */
    public String getFinishTime() {
    	String[] finishArr = start.split(":");
    	
    	Integer finishI= Integer.parseInt(finishArr[0]+finishArr[1])+duration*100;
    	
    	String finishStr=String.valueOf(finishI);
    	if(finishStr.length()==3) {
    		String finishTemp="";
    		finishTemp = "0"+""+finishStr.charAt(0)+":"+finishStr.charAt(1)+""+finishStr.charAt(2);
    		return finishTemp;
    	}
    	else {
    		String finishTemp="";
    		finishTemp = finishStr.charAt(0)+""+finishStr.charAt(1)+":"+finishStr.charAt(2)+""+finishStr.charAt(3);
    		return finishTemp;
    	}
    	
    	
  
    }
 
    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    
    // if maellard true than return 1001*importance/duration if not it returns 11*importance/duration
    public double getWeight() {
        return (double)(importance*(maellard ? 1001:1))/duration;
    }
 
    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    
    /*
     * I defined this method for Array.Sort method
     * */
    @Override
    public int compareTo(Assignment a) {
        if(this.getFinishTime().compareTo(a.getFinishTime())==0) {
        	return 0;
        }
        else {
        	return (this.getFinishTime().compareTo(a.getFinishTime())>0 ? +1 : -1);
        }
    }
 
    /**
     * @return Should return a string in the following form:
     * Assignment{name='Refill vending machines', start='12:00', duration=1, importance=45, maellard=false, finish='13:00', weight=45.0}
     */
    @Override
    public String toString() {
        return String.format("Assignment{name='%s', start='%s', duration=%d, importance=%d, maellard=%b, finish='%s', weight=%.1f}",
        		name,start,duration,importance,maellard,getFinishTime(),getWeight());
    }
}
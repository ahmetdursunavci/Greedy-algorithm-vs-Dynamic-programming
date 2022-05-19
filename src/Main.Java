import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.*;
public class Main {
    /**
     * Propogated {@link IOException} here
     * {@link #readFile} and {@link #writeOutput} methods should be called here
     * A {@link Scheduler} instance must be instantiated here
     */
    public static void main(String[] args) throws IOException {
    	
    	Assignment[] ass=readFile(args[0]);
    	Arrays.sort(ass);
    	Scheduler sch = new Scheduler(ass);
    	
    	ArrayList<Assignment> dynArr=sch.scheduleDynamic();
    	ArrayList<Assignment> grdArr=sch.scheduleGreedy();
    	
    	writeOutput("solution_dynamic.json",dynArr);
    	writeOutput("solution_greedy.json",grdArr);
    	
    }

    /**
     * @param filename json filename to read
     * @return Returns a list of {@link Assignment}s obtained by reading the given json file
     * @throws FileNotFoundException If the given file does not exist
     */
    private static Assignment[] readFile(String filename) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(filename);
        Assignment[] assignments = gson.fromJson(reader, Assignment[].class);
        return assignments;
    }

    /**
     * @param filename  json filename to write
     * @param arrayList a list of {@link Assignment}s to write into the file
     * @throws IOException If something goes wrong with file creation
     */
    private static void writeOutput(String filename, ArrayList<Assignment> arrayList) throws IOException {
    		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    		Writer write=new FileWriter(filename);
    		gson.toJson(arrayList,write);
    		write.close();
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
 
import com.google.gson.*;
public class Main {
    /**
     * Propogated {@link IOException} here
     * {@link #readFile} and {@link #writeOutput} methods should be called here
     * A {@link Scheduler} instance must be instantiated here
     */
    public static void main(String[] args) throws IOException {
    	
    	Assignment[] ass=readFile(args[0]);
    	Arrays.sort(ass);
    	Scheduler sch = new Scheduler(ass);
    	
    	ArrayList<Assignment> dynArr=sch.scheduleDynamic();
    	ArrayList<Assignment> grdArr=sch.scheduleGreedy();
    	
    	writeOutput("solution_dynamic.json",dynArr);
    	writeOutput("solution_greedy.json",grdArr);
    	
    }
 
    /**
     * @param filename json filename to read
     * @return Returns a list of {@link Assignment}s obtained by reading the given json file
     * @throws FileNotFoundException If the given file does not exist
     */
    private static Assignment[] readFile(String filename) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(filename);
        Assignment[] assignments = gson.fromJson(reader, Assignment[].class);
        return assignments;
    }
 
    /**
     * @param filename  json filename to write
     * @param arrayList a list of {@link Assignment}s to write into the file
     * @throws IOException If something goes wrong with file creation
     */
    private static void writeOutput(String filename, ArrayList<Assignment> arrayList) throws IOException {
    		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    		Writer write=new FileWriter(filename);
    		gson.toJson(arrayList,write);
    		write.close();
    }
}
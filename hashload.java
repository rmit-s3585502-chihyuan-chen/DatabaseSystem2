import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * @Date 04/05/2018
 * @author Chih-Yuan Chen s3585502
 * @Description: read heap file and transfer it into hash file
 * @Version 1.0
 **/

public class hashload {

	public static void main(String[] args) {
		try {
		dbquery db=new dbquery();//use the dbquery class to run the methods
		long startTime = 0;//create the start time variable
		long endTime=0;//create the end time variable
		int pageSize;// set the default pageSize
		ArrayList<HashTable> HashTable=new ArrayList<HashTable>();
	    pageSize = Integer.parseInt(args[0]); 
	    final int recordsNumber=2024670;
		int tableSize=1024 -1;
		double occupacy=0.995;
		int bucketSize=(int) (( recordsNumber /occupacy) / tableSize);
		Calculate calculator=new Calculate(0,0,startTime,endTime);//use the calculate class to run the timing method 
	    System.out.println("Loading and Creating....");
        calculator.setStatrTime();//set start time
	    db.readHeap(pageSize,HashTable, tableSize, bucketSize);//launch the process of reading the heap file and output the hash file
	    calculator.SetEndTime();//set the end time
	    calculator.CalculateTime3();//call the method to print out the time information
	    System.out.println("Successfully creat the hashFile!!");
	}catch(Exception e) {
		System.err.println("Input error!! Please input the correct pagesize");
	}
	}
	}
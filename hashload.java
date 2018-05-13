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
		dbquery db=new dbquery();
		long startTime = 0;
		long endTime=0;
		int pageSize;// set the default pageSize
		ArrayList<HashTable> HashTable=new ArrayList<HashTable>();
	    pageSize = Integer.parseInt(args[0]); 
	    final int recordsNumber=2024670;
		int tableSize=1024 -1;
		double occupacy=0.995;
		int bucketSize=(int) (( recordsNumber /occupacy) / tableSize);
		Calculate calculator=new Calculate(0,0,startTime,endTime);
	    System.out.println("Loading and Creating....");
        calculator.setStatrTime();
	    db.readHeap(pageSize,HashTable, tableSize, bucketSize);
	    calculator.SetEndTime();
	    calculator.CalculateTime3();
	    System.out.println("Successfully creat the hashFile!!");
	}
	}
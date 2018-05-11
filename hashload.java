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
		//hashload hashload=new hashload();
		dbquery db=new dbquery();
		long startTime = 0;
		long endTime=0;
		int pageIndex = 0;//set the default pageIndex
		int pageSize;// set the default pageSize
		ArrayList<HashTable> hashTableList=new ArrayList<HashTable>();
	    pageSize = Integer.parseInt(args[0]); 
	    final int recordsAmount=2024670;
		int tableSize=1024 -1;
		double rate=0.995;
		int bucketSize=(int) (( recordsAmount / rate ) / tableSize);
		Calculate calculator=new Calculate(0,0,startTime,endTime);
	    System.out.println("Loading and Creating....");
        calculator.setStatrTime();
	    db.readHeap(pageSize, hashTableList, tableSize, bucketSize);
	    calculator.SetEndTime();
	    calculator.CalculateTime3();
	    System.out.println("Successfully creat the hashFile!!");
	}
	}
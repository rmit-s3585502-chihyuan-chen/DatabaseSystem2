import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * @Author: Chih-Yuan Chen s3585502
 * @Description: running the searching querry
 * @Date: Created on 04/05/2018
 * @Version 1.0
 */
public class hashquery {

	public static void main(String[] args) {
		dbquery search= new dbquery();//use the dbquery class to run the methods
		try {
			int length = args.length; // Store the page size
			int pagesizeindex = length - 1; // set the position of page size
			int pageSize = Integer.parseInt(args[pagesizeindex]); //set the page size from input
			final int recordsNumber=2024670;
			int tableSize=1024 -1;
			double occupacy=0.995;
			int bucketSize=(int) (( recordsNumber /occupacy) / tableSize);
			String text = args[0]; // Store the query text
			ArrayList<Hash> recordNO=new ArrayList<Hash>();
			//get the full query text
			for (int i = 1; i < pagesizeindex; i++) {
				text = text + " " + args[i];
			}
			long startTime = 0;//create the start time variable
			long endTime=0;//create the end time variable
			Calculate calculator=new Calculate(0,0,startTime,endTime);//use the calculate class to run the timing method 
			System.out.println("Searching......");
			calculator.setStatrTime();//set start time
			search.searchHash(text, recordNO, pageSize, tableSize, bucketSize); //launch the process of searching
			calculator.SetEndTime();//set the end time
			calculator.CalculateTime4();//call the method to print out the time information
		} catch (Exception e) {
			System.err.println("Input Eroor!! Please Check again");
		}
	}
	}
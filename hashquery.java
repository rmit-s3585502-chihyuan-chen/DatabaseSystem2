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
		dbquery search= new dbquery();
		try {
			int length = args.length; // Store the page size
			int pagesizeindex = length - 1; // set the position of page size
			int pageSize = Integer.parseInt(args[pagesizeindex]); //set the page size from input
			final int recordsNumber=2024670;
			int tableSize=1024 -1;
			double occupacy=0.995;
			int bucketSize=(int) (( recordsNumber /occupacy) / tableSize);
			String text = args[0]; // Store the target text
			ArrayList<Hash> recordNO=new ArrayList<Hash>();
			// Get all the content of text
			for (int i = 1; i < pagesizeindex; i++) {
				text = text + " " + args[i];
			}
			long startTime = 0;
			long endTime=0;
			Calculate calculator=new Calculate(0,0,startTime,endTime);
			calculator.setStatrTime();
			search.searchHash(text, recordNO, pageSize, tableSize, bucketSize); // Implementation of searching
			calculator.SetEndTime();
			calculator.CalculateTime4();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
		}
	}
	}
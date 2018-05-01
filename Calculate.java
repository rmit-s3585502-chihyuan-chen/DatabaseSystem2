import java.io.File;

import java.io.FileWriter;
import java.util.*;

/**
 * @Author: Chih-Yuan Chen s3585502
 * @Description: calculate the time,numbers of page and record
 * @Date: Created on 21/03/2018
 * @Version 1.0
 */
public class Calculate {
	int pageNumber; //set the pagenumber's data type
	int recordNumber;// set the recordnumber's data type 
	long startTime ;// set the start time's data type
	long endTime;// set the end time's data type
	String[] data= new String[3]; //set up the array to store time,numbers of page and record
	public Calculate(int pageNumber,int recordNumber,long startTime,long endTime){ //the constructor of calculate
		this.pageNumber=pageNumber;
		this.recordNumber=recordNumber;
		this.startTime=startTime;
		this.endTime=endTime;
		
	}
	public void setStatrTime(){// set strt time
		startTime+=System.currentTimeMillis();
	}
	public void SetEndTime(){//set end time
		endTime+=System.currentTimeMillis();
	}
	public void CalculatePage(){ //calculate the number of page
		pageNumber++;
	}
	public int getPageNumber(){//get the number of page
		return pageNumber;
		
	}
	public void CalculateRecord(){ //calculate the number of record 
		recordNumber++;
	}
	public int getRecordNumber(){ //get the number of record
		return recordNumber;
		
	}
	public void CalculateTime(){ //calculate the time and output the page number ,record number and time 
		System.out.println("The number of milliseconds to create the heap file:"+Long.toString(endTime-startTime));
		
		}
	public void CalculateTime2(){ //calculate the total time taken to do all the search opera-tions in milliseconds 
		System.out.println("The total time taken to do all the search opera-tions in milliseconds:"+(Long.toString(endTime-startTime)));
		
		}
	public void printPageNumbers(){ //printout the number of pages
		System.out.println("The number of pages used:"+pageNumber);
	}
	public void printRecordNumbers(){ //printout the number of records
		System.out.println("The number of records loaded:"+recordNumber);
	}
	public void outputStdout(){ //output the data which contains the time.number of pages and records.
		 File saveFile=new File("stdout");
		    try
		    {
		      FileWriter fwriter=new FileWriter(saveFile);
		     for(int i=0;i<data.length;i++){
             fwriter.write(data[i]+"\n");
		     }
		      fwriter.close();
		    }
		    catch(Exception e)
		    {
		     System.err.println("Cannot output stdout");;
		    }
		  }
	}


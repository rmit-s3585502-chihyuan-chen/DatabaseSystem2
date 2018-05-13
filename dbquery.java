import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: Chih-Yuan Chen s3585502
 * @Description: running the searching querry and support the hashquery
 * @Date: Created on 04/05/2018
 * @Version 2.0
 */ 
public class dbquery {
	
	private static final String PageSize = null;

	public static void main(String[] args) {
		dbquery dbquery = new dbquery();//initialize the dbquery
		FileInputStream input;//set the file input stream
		File file; //set file varable
		int pageIndex = 0;//set the default pageIndex
		int pageSize;// set the default pageSize
		String text = args[0];
		pageSize = Integer.parseInt(args[1]); 
		long startTime = 0;
		long endTime=0;
		Calculate calculator=new Calculate(0,0,startTime,endTime);
		calculator.setStatrTime();//start timing
		searchHeap(text,pageSize);
		calculator.SetEndTime();
        calculator.CalculateTime2();
	}
	public static void searchHeap(String text,int pageSize) {
	FileInputStream input= readFile(pageSize);
		try {
			
			byte[] readFile = new byte[pageSize];//set the array to store heap file's data
			int count = 0;
			while (input.read(readFile, 0, pageSize) != -1) {
				int recoreNumber = getRecordNumber(readFile);//set the recoreNumber
				if (recoreNumber <= 0)//fill the empty value
					System.out.println("");
				ArrayList<Integer> recordIndex = getIndex(readFile, recoreNumber);//set the recordIndex
				ArrayList<Record> records = getRecord(readFile, recordIndex);//set the record
				for (int i = 0; i<records.size(); i++) {//search and retrieve data
					
					if (records.get(i).getField().get(1).getContent().contains(text)) {
					System.out.print("BUSINESS NAMES:"+" ");
						for (int n = 1; n < 9; n++) {
				
							System.out.print(records.get(i).getField().get(n).getContent()+" ");
					}
					int r =recordIndex.get(recoreNumber);
					System.out.print(" "+"PageNumber:"+count);
					System.out.print(" " +"RecordNumber:"+r);
					System.out.println("");
					}					
				}
				
				count++;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Cannot read the Heap File");
		}
	}

	public static void searchHeap2(String text,int pageSize,int pageNumber,int record) {
		FileInputStream input= readFile(pageSize);
			try {
				int offset=pageNumber*pageSize;
				byte[] readFile = new byte[pageSize];//set the array to store heap file's data
				input.skip(offset);
				input.read(readFile, 0, pageSize);
				int recoreNumber1 = getRecordNumber(readFile);//set the recoreNumber
				if (recoreNumber1 <= 0)//fill the empty value
					System.out.println("");
				ArrayList<Integer> recordIndex = getIndex(readFile, recoreNumber1);//set the recordIndex
				ArrayList<Record> records = getRecord(readFile, recordIndex);//set the recor
				for(int i=0;i<records.size();i++) {
					if(records.get(i).getField().get(1).getContent().equals(text)) {
						System.out.print("BUSINESS NAMES:"+" ");
						for (int n = 1; n < 9; n++) {
							
							System.out.print(records.get(i).getField().get(n).getContent()+" ");
					}
						System.out.print(" "+"PageNumber:"+pageNumber);
						System.out.print(" " +"RecordNumber:"+record);
						System.out.println("");
					}
				}
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Cannot read the Heap File");
			}
		}
	public static FileInputStream readFile(int pageSize)
	{
		FileInputStream fileInputStream=null;
		File file = new File("heap."+pageSize);			
		try {
			fileInputStream = new FileInputStream(file); //read heap file
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find the heap file");
			System.exit(0); //stop the application and exit
			}  
			return fileInputStream;
		}
	
	
    public void readHeap(int pageSize,ArrayList<HashTable> HashTable,int tableSize,int bucketSize) {
    	String BNname = null;
		for(int i=0;i<tableSize;i++)
		{
			HashTable hashTable=new HashTable(new ArrayList<Hash>());
			HashTable.add(hashTable);
		}
    	FileInputStream input= readFile(pageSize); //set the file input stream
		try {
		byte[] readFile = new byte[pageSize];//set the array to store heap file's data
		int count = 0;
		while (input.read(readFile, 0, pageSize) != -1) {
			
			int recoreNumber = getRecordNumber(readFile);//set the recoreNumber
			if (recoreNumber <= 0)//fill the empty value
				System.out.println("");
			ArrayList<Integer> recordIndex = getIndex(readFile, recoreNumber);//set the recordIndex
			ArrayList<Record> records = getRecord(readFile, recordIndex);//set the record
		
			for (int i = 0; i < records.size(); i++) {//retrieve data
						BNname = new String(records.get(i).getField().get(1).getContent());//get BNNAME
						int hashnumber=BNname.hashCode();
						int hashcode=Hashcode(hashnumber, tableSize);//get hash code
						int recordNumber2=recordIndex.get(recoreNumber);//get recordNumber
						if(HashTable.get(hashcode).getIndexlist().size()<bucketSize) { //if bucket is not full
							HashTable.get(hashcode).insert(BNname, count, recordNumber2); //insert elements into hash table
						}
						else
						{
							while(HashTable.get(hashcode).getIndexlist().size()>=bucketSize) //if bucket does not have available space
							{
								if(hashcode!=tableSize-1)
								{
									hashcode++; //insert the data into next bucket
								}
								else
								{
									hashcode=0; 
								}
							}
							HashTable.get(hashcode).insert(BNname, count, recordNumber2); //insert the elements into hash table
						}
					}
			count++;		
		}
		input.close();
		outHashFile(tableSize,pageSize,HashTable);
    }catch(Exception e) {
    System.err.println("Cannot read the heap file");	
    }
    }
    
	public void outHashFile(int tableSize,int pageSize, ArrayList<HashTable> HashTable) throws IOException {
	 BufferedWriter writeHash = null;
		for(int i=0;i<tableSize;i++)
		{
			int Index=i;
			File hashFile=new File("hash"+Index+"."+pageSize);
			try {
				writeHash=new BufferedWriter(new FileWriter(hashFile));
				for(int j=0;j<HashTable.get(Index).getIndexlist().size();j++)
				{
					writeHash.write(HashTable.get(Index).getIndexlist().get(j).getBN_NAME());
					writeHash.write("\t"+HashTable.get(Index).getIndexlist().get(j).getPageNumber());
					writeHash.write("\t"+HashTable.get(Index).getIndexlist().get(j).getRecordNumber());
					writeHash.newLine();
					}
			} catch (Exception e) {
				System.err.println("Cannot write the Hash File");
			}finally {
				writeHash.flush();
			}
		}
		writeHash.close();
		}
		
    
    public void searchHash(String text,ArrayList<Hash>recordNo,int pageSize,int tableSize,int bucketSize) throws IOException {
    int hashnumber=text.hashCode();
    int textHashcode= Hashcode(hashnumber,tableSize);	
    recordNo=getRecord(pageSize,textHashcode,text,bucketSize,tableSize);
    if(recordNo.size()!=0) {
    	int pageNumber=0;
    	int record=0;
    	for(int i=0;i<recordNo.size();i++) {
    		pageNumber=recordNo.get(i).getPageNumber();
    		record=recordNo.get(i).getRecordNumber();
    		searchHeap2(text, pageSize, pageNumber,record);
    	}
    }
    else {
    	System.out.println("Cannot find the target2");
    	System.exit(0);
    }
    }
    
    public int Hashcode(int hashnumber, int tableSize) {
		int hashcode = Math.abs(hashnumber)%tableSize;
		return hashcode;
	}
    
    
    
    
	public static int getRecordNumber(byte[] readFile) {//get the record number 
		byte[] temp = new byte[4];
		System.arraycopy(readFile, 0, temp, 0, 4);

		return changeToInt(temp);

	}

	
	public static ArrayList<Hash>getRecord(int pageSize,int textHashcode,String text,int bucketSize,int tableSize) throws IOException{
	ArrayList<Hash> recordNo=new ArrayList<Hash>();
	int recordNumber=bucketSize;
	while(recordNumber>=bucketSize) {
		String row="";
		recordNumber=0;
		File file=new File("hash"+textHashcode+"."+pageSize);
		BufferedReader reading=new BufferedReader(new FileReader(file));
		while((row=reading.readLine())!=null) {
			recordNumber++;
			String[]collection=row.split("\t");
			if(collection[0].equals(text)) {
				 recordNo.add(new Hash(collection[0],Integer.valueOf(collection[1]),Integer.valueOf(collection[2])));
			}
		}
		if(textHashcode==tableSize) {
			textHashcode=0;
		}
		textHashcode++;
		reading.close();	
	}
	return recordNo;
	}
	
	
	public static ArrayList<Integer> getIndex(byte[] readFile, int recordNumber) { //get the record index
		ArrayList<Integer> getTheRecordIndex = new ArrayList();
		byte[] temp = new byte[4];
		for (int i = 0; i < recordNumber+1; i++) {
			System.arraycopy(readFile, 4 + 4 * i, temp, 0, 4);
			getTheRecordIndex.add(changeToInt(temp));
		}
		return getTheRecordIndex;

	}

	public static ArrayList<Record> getRecord(byte[] readFile, ArrayList<Integer> getIndex) {//get the record
		ArrayList<Record> showRecord = new ArrayList();

		for (int i = 0; i < getIndex.size() - 1; i++) {
			int indexLength = ((getIndex.get(i + 1)) - getIndex.get(i));
			byte[] temp = new byte[indexLength];
			System.arraycopy(readFile, getIndex.get(i), temp, 0, indexLength);

			ArrayList<Integer> fieldIndex = getFieldIndex(temp);
			Record record = new Record(getField(temp, fieldIndex));
			showRecord.add(record);

		}
		return showRecord;
	}

	public static ArrayList<Field> getField(byte[] readFile, ArrayList<Integer> getFieldIndex) {//get the field
		ArrayList<Field> getField = new ArrayList<>();
		for (int i = 0; i < getFieldIndex.size() - 1; i++) {
			int FieldIndexLength = ((getFieldIndex.get(i + 1)) - getFieldIndex.get(i));
			if(FieldIndexLength<0)
				System.out.println("");
			byte[] temp = new byte[FieldIndexLength];
			System.arraycopy(readFile, getFieldIndex.get(i), temp, 0, FieldIndexLength);
			String field = new String(temp);
			getField.add(new Field("String", field));
		}
		byte[] temp = new byte[8];
		System.arraycopy(readFile, getFieldIndex.get(8), temp, 0, 8);
		getField.add(new Field("String", Long.toString(changeToLong(temp))));

		return getField;
	}

	public static ArrayList<Integer> getFieldIndex(byte[] readFile) { //get the field index
		ArrayList<Integer> getFieldIndex = new ArrayList();
		byte[] temp = new byte[4];
		for (int i = 0; i < 9; i++) {
			System.arraycopy(readFile, 4 * i, temp, 0, 4);
			getFieldIndex.add(changeToInt(temp));
		}
		return getFieldIndex;

	}

	public static int changeToInt(byte[] binaryFile) {//transfer the data type
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result <<= 8;
			result |= (binaryFile[i] & 0xff);
		}
		return result;
	}

	public static long changeToLong(byte[] binaryFile) {//transfer the data type
		long result = 0;
		for (int i = 0; i < 8; i++) {
			result <<= 8;
			result |= (binaryFile[i] & 0xff);
		}
		return result;
	}
}

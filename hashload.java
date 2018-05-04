import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;



public class hashload {

	public static void main(String[] args) {
		int pageIndex = 0;//set the default pageIndex
		int pageSize;// set the default pageSize
		hashload hashload=new hashload();
		Hashtable table=new Hashtable();
		int tableSize=1023;
		FileInputStream input;//set the file input stream
		File file; //set file varable
		try {
			pageSize = Integer.parseInt(args[0]); // Get the pagesize
			String filePath = ("heap." + args[0]); // Get the file name
			file = new File(filePath); //set the file to load file from the path
			input = new FileInputStream(file);//input the file
			byte[] readFile = new byte[pageSize];
			int count=0;
			File hash =new File("hash."+ args[0]) ;
			FileOutputStream fos=new FileOutputStream(hash);
			ObjectOutputStream hashout= new ObjectOutputStream(fos);
			while (input.read(readFile, 0, pageSize) != -1) {
		        count++;
				int recoreNumber = hashload.getRecordNumber(readFile);//set the recoreNumber
				if (recoreNumber <= 0)//fill the empty value
					System.out.println("");
				ArrayList<Integer> recordIndex = hashload.getIndex(readFile, recoreNumber);//set the recordIndex
				ArrayList<Record> records = hashload.getRecord(readFile, recordIndex);//set the record
			
				
				int hashcode;
				for (int i = 0; i < records.size(); i++) {//search and retrieve data
					
					
					String Bn=(records.get(i).getField().get(1).getContent());								
				    hashcode=Math.abs((Bn.hashCode()%2097151));
				 table.put(records.get(i).getField().get(1).getContent(),Integer.toString(count));
				
				}
				
				
			}
			hashout.writeObject(table);
           
			Enumeration ee = table.keys();
			String key = null;
			while(ee.hasMoreElements()) {
				key = (String)ee.nextElement();
				Object obj = table.get(key);
				System.out.println(key+"->"+obj+"\n");
			}	
			hashout.flush();
			
				hashout.close();
             
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Cannot read the Heap File");
		}
		
	}
	
	public int getRecordNumber(byte[] readFile) {//get the record number 
		byte[] temp = new byte[4];
		System.arraycopy(readFile, 0, temp, 0, 4);

		return changeToInt(temp);

	}

	public ArrayList<Integer> getIndex(byte[] readFile, int recordNumber) { //get the record index
		ArrayList<Integer> getTheRecordIndex = new ArrayList();
		byte[] temp = new byte[4];
		for (int i = 0; i < recordNumber+1; i++) {
			System.arraycopy(readFile, 4 + 4 * i, temp, 0, 4);
			getTheRecordIndex.add(changeToInt(temp));
		}
		return getTheRecordIndex;

	}

	public ArrayList<Record> getRecord(byte[] readFile, ArrayList<Integer> getIndex) {//get the record
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

	public ArrayList<Field> getField(byte[] readFile, ArrayList<Integer> getFieldIndex) {//get the field
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

	public ArrayList<Integer> getFieldIndex(byte[] readFile) { //get the field index
		ArrayList<Integer> getFieldIndex = new ArrayList();
		byte[] temp = new byte[4];
		for (int i = 0; i < 9; i++) {
			System.arraycopy(readFile, 4 * i, temp, 0, 4);
			getFieldIndex.add(changeToInt(temp));
		}
		return getFieldIndex;

	}

	public int changeToInt(byte[] binaryFile) {//transfer the data type
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result <<= 8;
			result |= (binaryFile[i] & 0xff);
		}
		return result;
	}

	public long changeToLong(byte[] binaryFile) {//transfer the data type
		long result = 0;
		for (int i = 0; i < 8; i++) {
			result <<= 8;
			result |= (binaryFile[i] & 0xff);
		}
		return result;
	}
	
	
}


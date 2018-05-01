import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: Chih-Yuan Chen s3585502
 * @Description: write out and convert the content into binary
 * @Date: Created on 21/03/2018
 * @Version 1.0
 */
public class OutputHeap {
	DataOutputStream os;//build the output stream

	public OutputHeap(String fileName) {//the constructor of outputHeap and output the file
		try {
			os = new DataOutputStream(new FileOutputStream(fileName)); 
		} catch (IOException e) {
			System.err.println("Cannot output file");
		}
	}

	public void writeField(Field field) { //according to the data type to write string into binary or long into file
		try {
			if (field.getType().equals("String")) {
				writeBinary(field.getContent().getBytes("UTF-8"));
			} else {
				os.writeLong(Long.valueOf(field.getContent()));
			}
		} catch (IOException e) {
			System.err.println("Cannot write String into file");
		}
	}
	public void writeInt(int index) {//write the index into file
		try {
			os.writeInt(index);
		} catch (IOException e) {
			System.err.println("Cannot write Int into file");
		}

	}

	public void writeShort(Short shortValue) { //writ the short into file
		try {
			os.writeShort(shortValue);
		} catch (IOException e) {
			System.err.println("Cannot write Short into file");
		}
	}

	public void writeBinary(byte[] bytes) { //write the byte into file
		try {
			os.write(bytes);
		} catch (IOException e) {
			System.err.println("Cannot write bytes into file");
		}
	}
}
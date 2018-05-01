import java.util.ArrayList;

/**
 * @Author: Chih-Yuan Chen s3585502
 * @Description: Setting up the page's attributes to store records and set its
 *               length.
 * @Date: Created on 21/03/2018
 * @Version 1.0
 */
public class Page {
	private int Length; // page's length
	private String Content; // page's content
	private ArrayList<Record> recordList;// store records

	public Page() { // sent the default length for page
		Length = 8;
		this.recordList = new ArrayList<>();
	}

	public ArrayList<Record> getRecord() { // get records
		return recordList;
	}

	public void add(Record record) {// add record and calculate the length
		this.recordList.add(record);
		Length += record.getLength() + 4;
	}

	public int getLength() { // get page's length
		return Length;
	}

	public void setLength(int Length) {// set page's length
		this.Length = Length;
	}

	public String getContent() {// get page's content
		return Content;
	}

	public void setContent(String Content) {// set page's content
		this.Content = Content;
	}
}

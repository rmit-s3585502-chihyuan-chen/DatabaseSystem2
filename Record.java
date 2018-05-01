import java.util.ArrayList;

/**
 * @Author: Chih-Yuan Chen s3585502
 * @Description: Setting up the record's attributes to store fields and calculate its length
 * @Date: Created on 21/03/2018
 * @Version 1.0
 */


public class Record {
	private int Length; //record's length
    private String Content; //record's content
    private ArrayList<Field> fieldList; //store fields
    

    public Record(ArrayList<Field> fieldList){ //set the record which contains fields and calculate its length
    		this.fieldList = fieldList;
    		calLength();
    		
    }
    
    public ArrayList<Field> getField(){ //get fields
    	return fieldList;
    }
    private void calLength(){ //calculate record's length
	Length=36;
    	for(int i=0;i<fieldList.size();i++){
		Length+= fieldList.get(i).getLength();
	}
}
    
    
    public Record(int Length, String Content) {//The constructor of record
        this.Length = Length;
        this.Content = Content;
    }

    public int getLength() {// get record's length
        return Length;
    }
    
    public void setLength(int Length) { //set record's length
    		this.Length = Length;
    }

    public String getContent() { //get record's content
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}

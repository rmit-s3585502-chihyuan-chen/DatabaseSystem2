/**
 * @Author: Chih-Yuan Chen s3585502
 * @Description: Setting up the field's attributes and calculate its length
 * @Date: Created on 21/03/2018
 * @Version 1.0
 */
public class Field {
	 private int Length;         //field's length
	    private String Content;  //field's content
	    private String Type;     //field's type

	    public Field(String Type, String  Content) {  //constructor of field
	        this.Content = Content; 
	        this.Type = Type;
	        calLength();
	    }
	    
	    private void calLength(){//accord the type to calculate the length
	    		if(Type.equals("String")){
	    			Length = Content.getBytes().length;
	    		}
	    		else if(Type.equals("long")){
	    			Length = 8;
	    		}
	    		
	    }

	    public int getLength() { //get field's length
	        return Length;
	    }
	    
	    public void setLength(int Length) { //set field's length
	    		this.Length = Length;
	    }

	    public String getContent() { //get field's content
	        return Content;
	    }

	    public void setContent(String Content) { //set field's content
	        this.Content = Content;
	    }

	    public String getType() { //get field's type
	        return Type;
	    }

	    public void setType(String Type) {//set field's type
	        this.Type = Type;
	      
	    }


}

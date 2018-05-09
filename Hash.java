/**
 * @Date 09/05/2018
 * @author Chih-Yuan Chen s3585502
 * @Description: The constructor of hash.
 * @Version 1.0
 **/
public class Hash {
String BN_NAME;
int pageNumber;
int recordNumber;

public Hash(String BN_NAME,int pageNumber,int recordNumber) {
	this.BN_NAME=BN_NAME;
	this.pageNumber=pageNumber;
	this.recordNumber=recordNumber;
}
public String getBN_NAME(String BN_NAME) {
	return BN_NAME;
	
}

public int getPageNumber(int pageNumber) {
	return pageNumber;
	
}

public int getRecordNumber(int recordNumber) {
	return recordNumber;
	
}
public void setBN_NAME(String BN_NAME) {
	this.BN_NAME=BN_NAME;
}

public void setPageNumber(int pageNumber) {
	this.pageNumber=pageNumber;
}
public void setRecordNumber(int recordNumber) {
	this.recordNumber=recordNumber;
}

}

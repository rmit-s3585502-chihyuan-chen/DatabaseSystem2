
public class Item {
String BN;
int count;
	public Item(String BN,int count) {
		setBN(BN);
		setCount(count);
	}
	public void setBN(String BN) { this.BN = BN; }
	 
	   //set name method
	   public void setCount(int count) { this.count = count; }

public String getBN(int count) { 
	this.count = count;
	return BN;
}
public int getCount(String BN) { 
	this.BN = BN;
	return count;
}
}
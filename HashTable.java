import java.util.ArrayList;

/**
 * @Date 07/05/2018
 * @author Chih-Yuan Chen s3585502
 * @Description: The constructor of hash table.
 * @Version 1.0
 **/

public class HashTable {
private ArrayList<Hash> indexList=new ArrayList<Hash>();
	
	public HashTable(ArrayList<Hash> hashIndexList)
	{
		this.setIndexlist(hashIndexList);
	}


	public void insert(String BN_NAME,int pageNumber,int recordNumber)
	{
		Hash Index=new Hash(BN_NAME,pageNumber,recordNumber);
		indexList.add(Index);
	}

	public ArrayList<Hash> getIndexlist() {
		return indexList;
	}


	public void setIndexlist(ArrayList<Hash> indexList) {
		this.indexList = indexList;
	}

}

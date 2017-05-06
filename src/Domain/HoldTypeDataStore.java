package Domain;

import java.util.HashMap;

public class HoldTypeDataStore {
	
	private static HashMap<String, HoldType> holdTypeHash = new HashMap<String, HoldType>();
	static{
		holdTypeHash.put("past due", new HoldType("past due", "copy not returned on time"));
	}
	
	public static void addHoldType(HoldType ht){
		if(holdTypeHash.containsKey(ht.getHoldTypeName())==false)
			holdTypeHash.put(ht.getHoldTypeName(), ht);
	}
	
	public static HoldType fetchHoldType(String holdTypeName){
		return holdTypeHash.get(holdTypeName);
	}
}

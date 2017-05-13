package Domain;

import java.util.HashMap;

public class CopyDataStore {
	private static HashMap<String, Copy> copyHash;
	
	static{
		copyHash = new HashMap<String, Copy>();
		loadInitialCopies();
	}

	private static void loadInitialCopies() {

		Copy c1 = new Copy("C001", TitleDataStore.fetchTitle("T001"));
		Copy c2 = new Copy("C002", TitleDataStore.fetchTitle("T002"));
		Copy c3 = new Copy("C003", TitleDataStore.fetchTitle("T001"));
		Copy c4 = new Copy("C004", TitleDataStore.fetchTitle("T002"));
		
		Copy copyNotForSale = new Copy("C399", TitleDataStore.fetchTitle("T399"));
		copyHash.put(c1.getCopyID(), c1);
		copyHash.put(c2.getCopyID(), c2);
		copyHash.put(c3.getCopyID(), c3);
		copyHash.put(c4.getCopyID(), c4);
		copyHash.put(copyNotForSale.getCopyID(), copyNotForSale);
		
		
	}
	
	public static void addCopy(Copy c) {
		if(copyHash.containsKey(c.getCopyID())==false)
			copyHash.put(c.getCopyID(), c);
	}
	
	public static Copy fetchCopy(String copyId) throws UnknownCopy{
		Copy c = copyHash.get(copyId);
		if (c!=null)
			return c;
		else
			throw new UnknownCopy();

	}
	
	public static void removeCopy(String copyID){
		copyHash.remove(copyID);
	}
	
	public static String copyIds(){
		String result = "";
		for(String key: copyHash.keySet())
			result+="Copy ID: " + key+"\n";
		return result;
	}
}

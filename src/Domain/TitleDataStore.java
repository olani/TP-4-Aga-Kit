package Domain;

import java.util.HashMap;
import java.util.UUID;

public class TitleDataStore {

	private static HashMap<String, Title> titleHash;
	
	static{
		titleHash= new HashMap<String, Title>();
		initalTitleLoad();
	}

	private static void initalTitleLoad() {
		
		titleHash.put("T001", new Title("T001", "Applying UML Patterns and Design", 149.99));
		titleHash.put("T002", new Title("T002", "Clean Code", 29.99));
		titleHash.put("T399", new Title("T399", "CC Not For Sale", 0.0));
	}
	
	public static Title fetchTitle(String titleID){
		return titleHash.get(titleID);
	}
	
	public static void addTitle(Title t){
		if(titleHash.containsKey(t.getTitleID())==false)
			titleHash.put(t.getTitleID(), t);
	}
	
}

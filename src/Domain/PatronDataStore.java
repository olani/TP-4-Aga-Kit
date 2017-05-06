package Domain;

import java.util.HashMap;

public class PatronDataStore {

	private static HashMap<String, Patron> patronHash;
	
	static {
		patronHash = new HashMap<String, Patron>();
		loadInitialPatrons();
		}
	
	
	private static void loadInitialPatrons(){
		patronHash.put("1", new Patron("olani", "1"));
		patronHash.put("2", new Patron("Dave", "2"));
		Patron pWithHold = new Patron("Patron with Hold", "399");
		pWithHold.addHold("past due");
		patronHash.put("399", pWithHold);
	}
	
	public void addPatron(Patron p) {
		if(patronHash.containsKey(p.getPatronId())==false)
			patronHash.put(p.getPatronId(), p);
	}
	
	public static Patron fetchPatron(String patronId)throws UnknownPatron{
		Patron answer = patronHash.get(patronId);
		if (answer!=null)
		return answer;
		else
			throw new UnknownPatron();
	}
	
	public static String patronIds(){
		String result ="";
		for(String key: patronHash.keySet())
            result += "Patron ID: " + key + "\tHas hold: " + patronHash.get(key).hasHold()+"\n";
		return result;
	}
}

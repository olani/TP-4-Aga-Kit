package Domain;

import java.util.ArrayList;

public class Patron
{
	private String name;
	private String patronID;
	private ArrayList<Copy> copiesOut;
	private ArrayList<Hold> accountHoldList;

	public Patron(String n, String id)
	{
		this.name = n;
		this.patronID = id;
		this.copiesOut = new ArrayList<Copy>();
		this.accountHoldList = new ArrayList<Hold>();
	}

	public boolean checkCopyOut(Copy c)
	{
		c.setOutTo(this);
		copiesOut.add(c);
		return true;
	}

	public boolean checkCopyIn(Copy c)
	{
		c.setOutTo(null);
		if (copiesOut.contains(c))
		{
			copiesOut.remove(c);
			c.setOutTo(null);
			return true;
		}
		else
			return false;
	}

	public String getPatronId(){
		return patronID;
	}
	
	public String getHolds(){
		String holds = "";
		for(Hold h: accountHoldList)
			holds += h.getHoldTypeName();
		return holds;
	}
	
	public boolean hasHold(){
		return accountHoldList.size()>0;
	}
	
	public void addHold(String holdTypeName){
		
		accountHoldList.add(new Hold(holdTypeName));
	}

}

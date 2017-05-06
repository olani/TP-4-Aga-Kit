package Domain;

import java.util.Date;



public class Hold {
	private HoldType holdType;
	private Date holdEffectiveDate;
	
	public Hold(String holdTypeName) throws InvalidHoldType{
		MakeHold(holdTypeName);
	}
	
	public String getHoldTypeName(){
		return holdType.getHoldTypeName();
	}
	
	private void MakeHold(String holdTypeName){
		holdType=findHoldTypeForHoldTypeName(holdTypeName);
		holdEffectiveDate = new Date();
	}

	private HoldType findHoldTypeForHoldTypeName(String htName){
		HoldType result = HoldTypeDataStore.fetchHoldType(htName);
		if (result==null)
			throw  new InvalidHoldType();
		else
			return result;
	}
	public class InvalidHoldType extends RuntimeException{
		
	}
	
	public Date getHoldEffectiveDate(){
		return holdEffectiveDate;
	}

}

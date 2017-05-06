package Domain;

public class HoldType {

	
	private String holdTypeName;
	private String holdDescription;
	
	public HoldType(String holdTypeName, String holdDescription) {
		// TODO Auto-generated constructor stub
		this.holdTypeName= holdTypeName;
		this.holdDescription = holdDescription;
	}

	public String getHoldTypeName() {
		return holdTypeName;
	}

	public String getHoldDescription() {
		return holdDescription;
	}
	
	
}

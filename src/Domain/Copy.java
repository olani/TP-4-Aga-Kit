package Domain;


public class Copy
{
	private String copyID;
	private Patron outTo;
	private Title  copyTitle;

	// following generated in Eclipse Source menu

	public Patron getOutTo()
	{
		return outTo;
	}

	public void setOutTo(Patron outTo)
	{
		this.outTo = outTo;
	}

	public String getCopyID()
	{
		return copyID;
	}
	

	public Copy(String cid, Title t)
	{
		copyID = cid;
		copyTitle = t;
	}


	public double getPrice() {
		return copyTitle.getPrice();
	}
	
	public boolean isForSale(){
		
		return copyTitle.isForSale();
	}

}

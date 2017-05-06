package Domain;


public class Title {
	private String titleID;
	private String bookTitle;
	private double price = 0.0;
	
	public Title(String titleID, String bookTitle, double price){
		this.titleID = titleID;
		this.bookTitle = bookTitle;
		this.price = price;
		
	}

	public String getTitleID() {
		return titleID;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public boolean isForSale(){
		return price>0.9;
	}
}

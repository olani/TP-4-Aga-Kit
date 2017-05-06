package Domain;

import java.util.ArrayList;


public class SaleControl {

	private ArrayList<Copy> copiesEntered;
	private Patron currentPatron;
	private double totalPrice=0.0;
	private Transaction currentTransaction;
	
	public SaleControl() {
		
	}
	
	public void startSale(String patronID){
		Patron p = PatronDataStore.fetchPatron(patronID);
		this.currentPatron = p;
		copiesEntered = new ArrayList<Copy> ();
		currentTransaction= new Transaction("Sale");
	}
	
	public void addCopyForSale(String copyId){

		Copy copy = CopyDataStore.fetchCopy(copyId);
		if (copy.isForSale()){
			if (isCopyEnteredForCurrentSale(copy)==false){
				copiesEntered.add(copy);
				totalPrice += copy.getPrice();
			}
		}
		else
			throw new CopyNotForSale();
	}
	
	public boolean isCopyEnteredForCurrentSale(Copy c){
		return copiesEntered.contains(c);
	}
	public double getTotalPrice(){
		return (totalPrice*100)/100.0;
	}

	public void completeSale() {
		for(Copy c:copiesEntered){
			String copyID = c.getCopyID();
			CopyDataStore.removeCopy(copyID);
		}
		currentTransaction.setAssociatedCopiesList(copiesEntered);
		currentTransaction.endTransaction();	
	}

	public String receipt(){
		String receipt="No Copies entered yet";
		if(countOfCopiesEntered()>0){
			receipt =recieptHeader();
			for(Copy c: copiesEntered)
				receipt+= c.getCopyID()+ "\t"+ c.getPrice()+"\n";
			receipt+="Total Price:" + getTotalPrice();
		}
		return receipt;
	}
	
	private String recieptHeader(){
		String header="TransactionID:" + currentTransaction.getTransactionID()+
				"\nTransaction Type: " + currentTransaction.getTransactionType()
				+"\nCopies\t" + "Unit Price\n";
		return header;
	}
	
	public int countOfCopiesEntered(){
		return copiesEntered.size();
	}
	
	public Patron getCurrentPatron() {
		
		return currentPatron;
	}
	
	public class CopyNotForSale extends RuntimeException{
		
	}


}

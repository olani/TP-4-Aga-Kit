package Domain;

import java.util.ArrayList;



public class CheckOutControl {

	private ArrayList<Copy> copiesEntered;
	private Patron currentPatron;
	private Transaction currentTransaction;
	
	public CheckOutControl(){
		
	}
	

	public void startCheckOut(String patronID){
		Patron p = PatronDataStore.fetchPatron(patronID);
		if (p.hasHold())
			throw new AccountHasHold();
		else
			initializeCheckOut(p);
	}
	
	private void initializeCheckOut(Patron p){
		this.currentPatron = p;	
		currentTransaction = new Transaction("CheckOut");
		copiesEntered = new ArrayList<Copy>();
	}
	
	public void addForCheckOut(String copyId){
			Copy copy = CopyDataStore.fetchCopy(copyId);
			if(copy.getOutTo()==null){
				if(isCopyEnteredForCurrentCheckOut(copy)==false)
					copiesEntered.add(copy);
			}
			else
				throw new CopyAlreadyCheckedOut();
	}
	
	public boolean isCopyEnteredForCurrentCheckOut(Copy c){
		return copiesEntered.contains(c);
	}

	public void removeFromCopiesEntered(Copy c){
		if (copiesEntered.size()>0)
			copiesEntered.remove(c);
	}
	
	public void clearCopiesEntered(){
		copiesEntered.clear();
	}
	
	public void completeCheckOut(){
		for(Copy c:copiesEntered)
			currentPatron.checkCopyOut(c);
		currentTransaction.setAssociatedCopiesList(copiesEntered);
		currentTransaction.endTransaction();
	}
	
	public int countOfCopiesEntered(){
		return copiesEntered.size();
	}
	
	public Patron getCurrentPatron() {
		
		return currentPatron;
	}
	
	public String receipt(){
		String receipt="No Copies entered yet";
		if(countOfCopiesEntered()>0){
			receipt =recieptHeader();
			for(Copy c: copiesEntered)
				receipt+= c.getCopyID()+"\n";
		}
		return receipt;
	}
	
	private String recieptHeader(){
		String header="TransactionID:" + currentTransaction.getTransactionID()+
				"\nTransaction Type: " + currentTransaction.getTransactionType()
				+"\nCopies entered\n";
		return header;
	}
	

}

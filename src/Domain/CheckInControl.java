package Domain;

import java.util.ArrayList;


public class CheckInControl {
	private ArrayList<Copy> copiesEntered;
	private Patron currentPatron;
	private Transaction currentTransaction;
	
	public CheckInControl(){
		
	}
	
	public void startCheckIn(String patronID){
		Patron p = PatronDataStore.fetchPatron(patronID);
		initializeCheckIn(p);
	}
	
	public void initializeCheckIn(Patron p){
		this.currentPatron = p;	
		currentTransaction = new Transaction("CheckIn");
		copiesEntered = new ArrayList<Copy> ();
	}
	
	public void addForCheckIn(String copyId){

		Copy copy = CopyDataStore.fetchCopy(copyId);
			if(copy.getOutTo()!=null){
				if(isCopyEnteredForCurrentSale(copy)==false)
					copiesEntered.add(copy);
			}
			else
				throw new CopyHasNotBeedCheckedOut();
	}
	
	public boolean isCopyEnteredForCurrentSale(Copy c){
		return copiesEntered.contains(c);
	}
	
	public void completeCheckIn(){
		for(Copy c:copiesEntered)
			currentPatron.checkCopyIn(c);
		currentTransaction.endTransaction();
		currentTransaction.setAssociatedCopiesList(copiesEntered);
	}
	
	public int countOfCopiesEntered(){
		return copiesEntered.size();
	}
	
	public void removeFromCopiesEntered(Copy c){
		if (copiesEntered.size()>0)
			copiesEntered.remove(c);
	}
	
	public void clearCopiesEntered(){
		copiesEntered.clear();
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
	
	public Patron getCurrentPatron() {
		
		return currentPatron;
	}
	
	private String recieptHeader(){
		String header="TransactionID:" + currentTransaction.getTransactionID()+
				"\nTransaction Type: " + currentTransaction.getTransactionType()
				+"\nCopies entered\n";
		return header;
	}
	public class CopyHasNotBeedCheckedOut extends RuntimeException{
		
	}
	
}

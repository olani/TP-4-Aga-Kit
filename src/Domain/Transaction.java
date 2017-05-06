package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Transaction {

	


	private String transactionId;
	private String transactionType;
	private Date startTime;
	private Date endTime;
	private ArrayList<Copy> associatedCopiesList;
	
	public Transaction(String transactionType) {
		beginTransaction(transactionType);
	}
	
	public void setAssociatedCopiesList(ArrayList<Copy> copiesList){
		associatedCopiesList = copiesList;
	}
	
	public void beginTransaction(String transactionType) {
		transactionId = UUID.randomUUID().toString();
		this.transactionType = transactionType;
		startTime = new Date();
	}
	
	public void endTransaction(){
		endTime = new Date();
		TransactionLogs.addTransaction(this);
	}
	
	public String getTransactionID(){
		return transactionId;
	}
	
	public String logDetail(){
		String log = "";
		for(Copy c: associatedCopiesList){
			log += "Transaction Id:\t " + transactionId + "\t Transaction Type : " + transactionType 
					+"\t Copy ID: " + c.getCopyID()
					+"\t Start time: "+ startTime.toString() + "\t end time: "+endTime.toString() + "\n";
		}
		return log;
	}
	public String getTransactionType(){
		return transactionType;
	}
}

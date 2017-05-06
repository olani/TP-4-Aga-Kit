package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TransactionLogs {

	private static ArrayList<Transaction> transactionLogList = new ArrayList<Transaction>();
	
	public static void addTransaction(Transaction t){
		transactionLogList.add(t);
	}
	
	public static String allLogs(){
		String result = "";
		for(Transaction t: transactionLogList)
			result += t.logDetail();
		return result;
	}
	
	public static int numberOfTransactions(){
		return transactionLogList.size();
	}
	
	public static void cleanLogs(){
		transactionLogList = null;
		transactionLogList = new ArrayList<Transaction>();
	}
}

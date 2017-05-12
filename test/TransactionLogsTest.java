import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Domain.Copy;
import Domain.Transaction;
import Domain.TransactionLogs;

public class TransactionLogsTest {
	TransactionLogs tLogs;
	@Before
	public void setUp() throws Exception {
		tLogs = new TransactionLogs();
	}

	@Test
	public void youCanAddTransactionToLogs(){
		Transaction t  = new Transaction("CheckOut");
		assertEquals(0, tLogs.numberOfTransactions());
		tLogs.addTransaction(t);
		
		assertEquals(1, tLogs.numberOfTransactions());
		
	}
	
	@Test
	public void testTransactionLogDetails(){
		Transaction t  = new Transaction("CheckOut");
		t.setAssociatedCopiesList(new ArrayList<Copy>());
		t.endTransaction();
		tLogs.addTransaction(t);
		
		assertEquals(t.logDetail(),tLogs.allLogs());
	}
	


	@After
	public void clearTransactionLog(){
		TransactionLogs.cleanLogs();
	}

}

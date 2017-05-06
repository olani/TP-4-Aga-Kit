import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

}

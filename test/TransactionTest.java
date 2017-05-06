import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Domain.CheckInControl;
import Domain.Copy;
import Domain.CopyDataStore;
import Domain.Transaction;
import Domain.TransactionLogs;

public class TransactionTest {

	Transaction transaction;
	ArrayList<Copy> copyList;
	@Before
	public void setUp() throws Exception {
		transaction = new Transaction("CheckIn");
		copyList = new ArrayList<Copy>();
		copyList.add(CopyDataStore.fetchCopy("C001"));
		copyList.add(CopyDataStore.fetchCopy("C002"));
	}

	@Test
	public void test() {
	
		//TransactionLogs.print();
		
		transaction.beginTransaction("CheckIn");
		transaction.setAssociatedCopiesList(copyList);
		transaction.endTransaction();
		transaction.logDetail();
		
	}

}

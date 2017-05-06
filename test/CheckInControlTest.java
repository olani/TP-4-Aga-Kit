import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Domain.CheckInControl;
import Domain.CheckOutControl;
import Domain.Copy;
import Domain.CopyDataStore;
import Domain.PatronDataStore;
import Domain.TransactionLogs;

public class CheckInControlTest {

	CheckInControl checkInControl;
	CheckOutControl checkOutControl;
	@Before
	public void setUp() throws Exception {
		checkInControl = new CheckInControl();
	}

	@Test(expected = Domain.UnknownPatron.class)
	public void startingCheckInForUknownPatronResultsInUknownPatronException(){
		checkInControl.startCheckIn("1098");

	}

	@Test (expected = Domain.UnknownCopy.class)
	public void tryingToAddUknownCopiesForCheckInRaisesUknownCopyError() {
		checkInControl.addForCheckIn("C0011");
	}
	
	@Test
	public void afterCheckInIsCompleted_OutToInformationForCopiesAreUpdated(){
		
		checkOutControl = new CheckOutControl();
		checkOutControl.startCheckOut("1");
		checkOutControl.addForCheckOut("C003");
		checkOutControl.addForCheckOut("C004");
		checkOutControl.completeCheckOut();
		
		checkInControl.startCheckIn("1");
		checkInControl.addForCheckIn("C003");
		
		checkInControl.addForCheckIn("C004");
		
		checkInControl.completeCheckIn();
		
		Copy c1 = CopyDataStore.fetchCopy("C003");
		Copy c2 = CopyDataStore.fetchCopy("C004");

		assertNull(c1.getOutTo());
		assertNull(c2.getOutTo());

	}
	
	@After
	public void clearTransactionLog(){
		TransactionLogs.cleanLogs();
	}
	
}

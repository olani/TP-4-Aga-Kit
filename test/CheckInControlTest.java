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
import Domain.CheckInControl.CopyHasNotBeedCheckedOut;

public class CheckInControlTest {

	CheckInControl checkInControl;
	CheckOutControl checkOutControl;
	@Before
	public void setUp() throws Exception {
		checkInControl = new CheckInControl();
		checkOutControl = new CheckOutControl();
		checkOutControl.startCheckOut("1");
		checkOutControl.addForCheckOut("C003");
		checkOutControl.addForCheckOut("C004");
		checkOutControl.completeCheckOut();
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
	public void checkInControlCopyKnowsCurrentPatron(){
		checkInControl.startCheckIn("2");
		assertEquals("2", checkInControl.getCurrentPatron().getPatronId());
	}
	@Test
	public void afterCheckInIsCompleted_OutToInformationForCopiesAreUpdated(){

		checkInControl.startCheckIn("1");
		checkInControl.addForCheckIn("C003");
		
		checkInControl.addForCheckIn("C004");
		
		checkInControl.completeCheckIn();
		
		Copy c1 = CopyDataStore.fetchCopy("C003");
		Copy c2 = CopyDataStore.fetchCopy("C004");

		assertNull(c1.getOutTo());
		assertNull(c2.getOutTo());

	}
	@Test
	public void copiesCanBeRemovedFromItemsEnteredDuringTransaction(){
		Copy c3 = CopyDataStore.fetchCopy("C003");

		
		checkInControl.startCheckIn("1");
		checkInControl.addForCheckIn("C003");
		
		checkInControl.addForCheckIn("C004");
		checkInControl.removeFromCopiesEntered(c3);

		checkInControl.completeCheckIn();

		assertEquals(1, checkInControl.countOfCopiesEntered());

	}
	@Test
	public void checkInCanClearAllCopiesEntered(){
		checkOutControl.startCheckOut("1");
		checkOutControl.addForCheckOut("C001");
		checkOutControl.completeCheckOut();
		checkInControl.startCheckIn("1");
		checkInControl.addForCheckIn("C001");
		assertEquals(1, checkOutControl.countOfCopiesEntered());
		checkInControl.clearCopiesEntered();
		assertEquals(0, checkInControl.countOfCopiesEntered());
	}
	@Test(expected=CopyHasNotBeedCheckedOut.class)
	public void canNotCheckInCopyIfItWasNotCheckedOut(){
		checkInControl.startCheckIn("1");
		checkInControl.addForCheckIn("C001");
	}
	
	@Test
	public void ifNoItemsEnteredNoReceiptIsGenerated(){
		checkInControl.startCheckIn("1");
		assertEquals(0,checkInControl.countOfCopiesEntered());
		assertEquals("No Copies entered yet", checkInControl.receipt());
	}
	
	@Test
	public void receiptShowsIsProvidedWithTranasactionTypeAndCopiesEntered(){

		checkInControl.startCheckIn("1");
		
		checkInControl.addForCheckIn("C003");
		checkInControl.receipt();
		String receipt ="\nTransaction Type: CheckIn\nCopies entered\nC003\n";

		assertEquals(true, checkInControl.receipt().endsWith(receipt));
		checkInControl.completeCheckIn();
		
		

	}
	
	@After
	public void clearTransactionLog(){
		TransactionLogs.cleanLogs();
		CopyDataStore.fetchCopy("C001").setOutTo(null);
		CopyDataStore.fetchCopy("C002").setOutTo(null);
		CopyDataStore.fetchCopy("C003").setOutTo(null);
		CopyDataStore.fetchCopy("C004").setOutTo(null);
	}
	
}

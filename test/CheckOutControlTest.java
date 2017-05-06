import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Domain.CheckOutControl;
import Domain.Copy;
import Domain.CopyDataStore;
import Domain.Patron;
import Domain.PatronDataStore;
import Domain.Title;
import Domain.TransactionLogs;

public class CheckOutControlTest {
	CheckOutControl checkOutControl;
	Copy copy;
	Patron patron;
	
	@Before
	public void setUp() throws Exception {
		checkOutControl = new CheckOutControl();
		
		copy = new Copy("C001", new Title("T001", "The key to success", 9.99));
		patron = new Patron("olani", "1");
	}


	
	@Test
	public void afterAddingCopy_TheCopyShoulBeInEnteredCopiesList() {
		checkOutControl.startCheckOut("1");
		checkOutControl.addForCheckOut("C001");
		checkOutControl.addForCheckOut("C002");

		assertEquals(2, checkOutControl.countOfCopiesEntered());
	}
	

	@Test (expected = Domain.UnknownCopy.class )
	public void tryingToAddUknownCopiesForCheckoutRaisesUknownCopyError() {
		checkOutControl.addForCheckOut("C0011");
	}
	
	@Test(expected = Domain.UnknownPatron.class)
	public void startingCheckOutForUknownPatronResultsInUknownPatronException(){
		checkOutControl.startCheckOut("1098");

	}
	
	@Test
	public void afterCheckOutIsCompleted_OutToInformationForCopiesAreUpdated(){
		checkOutControl.startCheckOut("1");
		checkOutControl.addForCheckOut("C001");
		
		checkOutControl.addForCheckOut("C002");
		
		checkOutControl.completeCheckOut();
		
		Copy c1 = CopyDataStore.fetchCopy("C001");
		Copy c2 = CopyDataStore.fetchCopy("C002");
		
		assertEquals(c1.getOutTo().getPatronId(), "1");
		assertEquals(c2.getOutTo().getPatronId(), "1");

	}
	
	@After
	public void clearTransactionLog(){
		TransactionLogs.cleanLogs();
	}
	


}

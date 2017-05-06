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
		checkOutControl.completeCheckOut();
	}
	

	@Test (expected = Domain.UnknownCopy.class )
	public void tryingToAddUknownCopiesForCheckoutRaisesUknownCopyError() {
		checkOutControl.addForCheckOut("C0011");
		checkOutControl.completeCheckOut();
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
	
	@Test
	public void copiesCanBeRemovedFromItemsEnteredDuringTransaction(){
		Copy c3 = CopyDataStore.fetchCopy("C003");
		Copy c4 = CopyDataStore.fetchCopy("C004");
		
		checkOutControl.startCheckOut("1");
		checkOutControl.addForCheckOut("C003");
		
		checkOutControl.addForCheckOut("C004");
		checkOutControl.removeFromCopiesEntered(c3);

		checkOutControl.completeCheckOut();
		
		
		assertEquals(null, c3.getOutTo());
		assertEquals("1", c4.getOutTo().getPatronId());
		assertEquals(1, checkOutControl.countOfCopiesEntered());

	}
	
	@Test
	public void ifNoItemsEnteredNoReceiptIsGenerated(){
		checkOutControl.startCheckOut("1");
		assertEquals(0,checkOutControl.countOfCopiesEntered());
		assertEquals("No Copies entered yet", checkOutControl.receipt());
	}
	
	@Test
	public void receiptShowsIsProvidedWithTranasactionTypeAndCopiesEntered(){
		
		checkOutControl.startCheckOut("1");
		
		checkOutControl.addForCheckOut("C003");
		checkOutControl.receipt();
		String receipt ="\nTransaction Type: CheckOut\nCopies entered\nC003\n";

		assertEquals(true, checkOutControl.receipt().endsWith(receipt));
		checkOutControl.completeCheckOut();
		
		

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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Domain.CheckInControl;
import Domain.Copy;
import Domain.CopyDataStore;
import Domain.Patron;
import Domain.PatronDataStore;
import Domain.SaleControl;
import Domain.TransactionLogs;

public class SaleControlTest {

	SaleControl sale;
	Patron patron;
	@Before
	public void setUp() throws Exception {
		patron = PatronDataStore.fetchPatron("1");
		sale = new SaleControl();
	}

	@Test(expected = Domain.UnknownPatron.class)
	public void startingCheckOutForUknownPatronResultsInUknownPatronException(){
		sale.startSale("1098");

	}

	@Test (expected = Domain.UnknownCopy.class )
	public void tryingToAddUknownCopiesForCheckoutRaisesUknownCopyError() {
		sale.addCopyForSale("C0011");
	}

	@Test
	public void saleControlKnowsCurrentPatron(){
		sale.startSale("1");
		Patron currentPatron = sale.getCurrentPatron();
		assertEquals("1", currentPatron.getPatronId());
	}
	@Test
	public void totalPriceIsUpdated_afterAdditingCopiesForSale(){
		sale.startSale("1");
		sale.addCopyForSale("C001");
		
		sale.addCopyForSale("C002");
		
		
		double expectedTotal = sale.getTotalPrice();
		assertEquals(expectedTotal, 179.98, 0.00001);
		
	}
	@Test
	public void ifNoItemsEnteredNoReceiptIsGenerated(){
		sale.startSale("1");
		assertEquals(0,sale.countOfCopiesEntered());
		assertEquals("No Copies entered yet", sale.receipt());
	}
	
	@Test
	public void receiptShowsIsProvidedWithTranasactionTypeAndCopiesEntered(){
		
		sale.startSale("1");
		
		sale.addCopyForSale("C003");
		sale.receipt();
		String receipt ="\nTransaction Type: Sale\nCopies\tUnit Price\nC003\t149.99\nTotal Price:149.99";
		assertEquals(true, sale.receipt().endsWith(receipt));
		sale.completeSale();
		
		

	}
	@Test (expected = SaleControl.CopyNotForSale.class )
	public void tryingToSaleTitlesNotForSaleRaisesCopyNotForSaleError() {
		sale.addCopyForSale("C399");
	}
	
	@After
	public void clearTransactionLog(){
		TransactionLogs.cleanLogs();
	}
	
}

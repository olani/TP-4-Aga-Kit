import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Domain.Copy;
import Domain.CopyDataStore;
import Domain.Patron;
import Domain.PatronDataStore;
import Domain.TRLApplication;
import Domain.TitleDataStore;
import Domain.TransactionLogs;

public class TRLApplicationTest {

	private TRLApplication TRLApp;
	Copy copy1;
	Copy copy2;
	@Before
	public void setUp() throws Exception {
		TRLApp = new TRLApplication();
		copy1 = CopyDataStore.fetchCopy("C001");
		copy2 = CopyDataStore.fetchCopy("C002");
		copy2.setOutTo(PatronDataStore.fetchPatron("2"));
	}
	@After
	public void teardown() throws Exception {
		TransactionLogs.cleanLogs();
		Copy c1 = new Copy("C001", TitleDataStore.fetchTitle("T001"));
		Copy c2 = new Copy("C002", TitleDataStore.fetchTitle("T002"));
		Copy c3 = new Copy("C003", TitleDataStore.fetchTitle("T001"));
		Copy c4 = new Copy("C004", TitleDataStore.fetchTitle("T002"));
		
		Copy copyNotForSale = new Copy("C399", TitleDataStore.fetchTitle("T399"));
		CopyDataStore.addCopy(c1);
		CopyDataStore.addCopy(c2);
		CopyDataStore.addCopy(c3);
		CopyDataStore.addCopy(c4);
		CopyDataStore.addCopy(copyNotForSale);
	}

	@Test
	public void testCheckOutApp(){
		String input = "1\n1\nC001\n0\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		TRLApplication.startSelectAPP(scanner);
		assertEquals("1", copy1.getOutTo().getPatronId());
	}
	
	@Test
	public void testCheckIn(){
		Patron outTo = copy2.getOutTo();
		assertEquals("2",outTo.getPatronId());
		String input = "2\n2\nC002\n0\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		TRLApplication.startSelectAPP(scanner);
		outTo = copy2.getOutTo();
		assertNull(outTo);
	}
	
	@Test(expected =Domain.UnknownCopy.class)
	public void testSaleApp_CopyIsRemovedSuccessfully(){
		String input = "3\n1\nC001\n0\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		TRLApplication.startSelectAPP(scanner);
		CopyDataStore.fetchCopy("C001");
	}
	
	@Test
	public void testHelpMessage(){
		TRLApp.helpMessage();
	}
	

}

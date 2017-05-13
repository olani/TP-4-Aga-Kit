import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Domain.CheckOutApp;
import Domain.CopyDataStore;
import Domain.Patron;
import Domain.PatronDataStore;
import Domain.TransactionLogs;

public class CheckOutAppTest {
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void teardown() throws Exception {
		TransactionLogs.cleanLogs();
		CopyDataStore.fetchCopy("C001").setOutTo(null);
		CopyDataStore.fetchCopy("C002").setOutTo(null);
		CopyDataStore.fetchCopy("C003").setOutTo(null);
		CopyDataStore.fetchCopy("C004").setOutTo(null);
	}
	
	@Test
	public void testValidPatronAndCopyWorks(){
		CheckOutApp checkOut = new CheckOutApp();
		String input = "1\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
		Patron outToPatron = CopyDataStore.fetchCopy("C001").getOutTo();
		assertEquals("1", outToPatron.getPatronId());
	}
	@Test
	public void validPatronIDWithInvalidCopyDoesNotBreakSystem(){
		CheckOutApp checkOut = new CheckOutApp();
		String input = "1\nC00ad1\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
	}
	
	@Test
	public void appCanCheckOutMoreThan1CopyAtATime(){
		CheckOutApp checkOut = new CheckOutApp();
		String input = "1\nC001\nC002\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
	}
	@Test
	public void appCanIgnorInvalidCopiesAndWillNotBreak(){
		CheckOutApp checkOut = new CheckOutApp();
		String input = "1\nC00241\nC002\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
		Patron outToPatron = CopyDataStore.fetchCopy("C002").getOutTo();
		assertEquals("1", outToPatron.getPatronId());
		
	}
	
	@Test
	public void invalidPatronIDValidCopyDoesNotBreakSystem(){
		CheckOutApp checkOut = new CheckOutApp();
		String input = "adf1\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
	}
	
	@Test
	public void invalidPatronIDValidCopyCanNotCheckOutACopy(){
		CheckOutApp checkOut = new CheckOutApp();
		String input = "adf1\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
		Patron outToPatron = CopyDataStore.fetchCopy("C001").getOutTo();
		assertNull(outToPatron);
	}
	@Test
	public void appWillNotAllowCheckOutForCopyThatIsAlreadyOut(){
		CheckOutApp checkOut = new CheckOutApp();
		String input = "1\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
		
		Patron outToPatron = CopyDataStore.fetchCopy("C001").getOutTo();
		assertEquals("1", outToPatron.getPatronId());
		
		input = "2\nC001\n0";
		scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
		outToPatron = CopyDataStore.fetchCopy("C001").getOutTo();
		assertNotEquals("2", outToPatron.getPatronId());
		assertEquals("1", outToPatron.getPatronId());
	}
	
	@Test
	public void appWillNotAllowCheckOutForPatronWithHold(){
		CheckOutApp checkOut = new CheckOutApp();
		String input = "399\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkOut.doCheckOut(scanner);
		
		Patron outToPatron = CopyDataStore.fetchCopy("C001").getOutTo();
		assertNull(outToPatron);
		

	}
	

}

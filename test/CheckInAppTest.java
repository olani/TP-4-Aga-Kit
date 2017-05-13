import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Domain.CheckInApp;
import Domain.Copy;
import Domain.CopyDataStore;
import Domain.Patron;
import Domain.PatronDataStore;
import Domain.TransactionLogs;

public class CheckInAppTest {
	private Copy copy1;
	private Copy copy2;
	private CheckInApp checkIn;
	@Before
	public void setUp() throws Exception {
		checkIn= new CheckInApp();
		Patron p = PatronDataStore.fetchPatron("1");
		copy1 = CopyDataStore.fetchCopy("C001");
		copy2 = CopyDataStore.fetchCopy("C002");
		copy1.setOutTo(p);
		copy2.setOutTo(p);
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
		String input = "1\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
		Patron outToPatron = copy1.getOutTo();
		checkIn.doCheckIn(scanner);
		outToPatron = copy1.getOutTo();
		assertNull(outToPatron);
	}
	@Test
	public void validPatronIDWithInvalidCopyDoesNotBreakSystem(){
		String input = "1\nC00ad1\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkIn.doCheckIn(scanner);
	}
	@Test
	public void appCanCheckINMoreThan1CopyAtATime(){
		assertEquals("1", copy1.getOutTo().getPatronId());
		assertEquals("1", copy2.getOutTo().getPatronId());
		String input = "1\n"+copy1.getCopyID()+"\n"+copy2.getCopyID()+"\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkIn.doCheckIn(scanner);
		assertNull(copy1.getOutTo());
		assertNull(copy2.getOutTo());
		
	}
	@Test
	public void appCanIgnorInvalidCopiesAndWillNotBreak(){
		Patron outToPatron = copy2.getOutTo();
		assertEquals("1", outToPatron.getPatronId());
		String input = "1\nC00241\nC002\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkIn.doCheckIn(scanner);
		outToPatron = CopyDataStore.fetchCopy("C002").getOutTo();
		assertNull(outToPatron);
		
	}
	
	@Test
	public void invalidPatronIDValidCopyDoesNotBreakSystem(){

		String input = "adf1\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkIn.doCheckIn(scanner);
	}
	@Test
	public void invalidPatronIDCanNotCheckIn(){
		Patron outToPatron = copy1.getOutTo();
		assertEquals("1", outToPatron.getPatronId());
		String input = "adf1\n"+copy1.getCopyID()+"\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkIn.doCheckIn(scanner);
		outToPatron = CopyDataStore.fetchCopy("C001").getOutTo();
		assertEquals("1", outToPatron.getPatronId());
	}
	@Test
	public void appWillNotAllowCheckInForCopyThatIsNotOut(){
		copy1.setOutTo(null);

		assertNull(copy1.getOutTo());
		
		String input = "1\nC001\nC002\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		checkIn.doCheckIn(scanner);
		
		Patron p1 = copy1.getOutTo();
		Patron p2 = copy2.getOutTo();
		
		assertNull(p1);
		assertNull(p2);
	}
	

}

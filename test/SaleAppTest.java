import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Domain.Copy;
import Domain.CopyDataStore;
import Domain.SaleApp;
import Domain.TitleDataStore;
import Domain.TransactionLogs;

public class SaleAppTest {
	private SaleApp sale;
	private Copy copy1;
	private Copy copy2;
	@Before
	public void setUp() throws Exception {
		sale= new SaleApp();
		copy1 = CopyDataStore.fetchCopy("C001");
		copy2 = CopyDataStore.fetchCopy("C002");
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
	
	@Test(expected =Domain.UnknownCopy.class)
	public void testValidPatronAndCopyWorks_CopiesAreRemovedWhenSold(){
		String input = "1\nC001\n0";
		assertEquals(copy1, CopyDataStore.fetchCopy("C001"));
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
		sale.doSale(scanner);
		CopyDataStore.fetchCopy("C001");
		
	}
	
	@Test
	public void validPatronIDWithInvalidCopyDoesNotBreakSystem(){
		String input = "1\nC00ad1\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		sale.doSale(scanner);
	}
	
	@Test
	public void invalidPatronIDWithCalidCopyDoesNotBreakSystem_orRemoveCopy(){
		String input = "sf1\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		sale.doSale(scanner);
		assertEquals(copy1, CopyDataStore.fetchCopy("C001"));
	}
	
	@Test
	public void itemsNotForSaleDoesNotBreakSystem(){
		String input = "1\nC399\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		sale.doSale(scanner);
	}
	
	@Test(expected =Domain.UnknownCopy.class)
	public void systemCanHandleMultipleCopiesForSingleSale(){
		String input = "1\nC399\nC001\n0";
		Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes())); 
		sale.doSale(scanner);
		CopyDataStore.fetchCopy("C001");
	}
}

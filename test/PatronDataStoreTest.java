import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.Patron;
import Domain.PatronDataStore;

public class PatronDataStoreTest{
	PatronDataStore patronStore;
	@Before
	public void setUp() throws Exception {
		patronStore= new PatronDataStore();
	}
	
	@Test
	public void classCreationTest(){
		
	}

	@Test
	public void addPatronToPatronDataStore() {
		Patron p = new Patron("olani", "1");
		patronStore.addPatron(p);
	}
	
	@Test
	public void afterAddingPatron_fetchPatronShouldRetriveit() {
		Patron p3 = new Patron("Level", "3");
		Patron p4 = new Patron("ABC", "4");
		patronStore.addPatron(p3);
		patronStore.addPatron(p4);
		
		assertEquals(patronStore.fetchPatron("3"),p3);
		assertEquals(patronStore.fetchPatron("4"),p4);
	}
	
	@Test(expected=Domain.UnknownPatron.class)
	public void ifPatronDoesNotReturnPatronUnknownException(){
		
		patronStore.fetchPatron("03123");
	}

	@Test
	public void testListOfPatrons(){
		String expectedResult = "Patron ID: 1\tHas hold: false\n"
				+ "Patron ID: 2\tHas hold: false\n"
				+ "Patron ID: 399\tHas hold: true\n";
		assertEquals(expectedResult, patronStore.patronIds());
	}
}

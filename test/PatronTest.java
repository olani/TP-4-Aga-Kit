import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.Patron;

public class PatronTest {

	Patron patron;
	@Before
	public void setUp() throws Exception {
		patron = new Patron("tester1", "325");
	}

	@Test
	public void patronKnowsItsHold() {
		patron.addHold("past due");
		assertEquals("past due", patron.getHolds());
	}
	
	@Test
	public void patronKnowsIfitHasHold() {
		patron.addHold("past due");
		assertEquals(true, patron.hasHold());
		
		Patron pWithOutHold = new Patron("tester2", "2");
		assertEquals(false, pWithOutHold.hasHold());
	}

}

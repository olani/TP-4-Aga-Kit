import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.Hold;
import Domain.HoldType;
import Domain.HoldTypeDataStore;

public class HoldTest {

	private Hold hold;

	
	@Before
	public void setUp() throws Exception {
		HoldTypeDataStore.addHoldType(new HoldType("Past Due Date", "Cused by late returns"));
	}

	@Test
	public void aHoldisCreatedOnlyIfaValidHoldTypeIsInHoldTypeDataStore() {
		hold = new Hold("Past Due Date");
		assertEquals("Past Due Date", hold.getHoldTypeName());
		//System.out.println(hold.getHoldEffectiveDate().toString());
	}
	
	@Test(expected = Hold.InvalidHoldType.class)
	public void aHoldCanNotBeCreatedWithoutProperHoldTypeFromHoldTypeDataStore() throws Exception{
		hold = new Hold("Pastedasdf Due Date");
		assertEquals("Past Due Date", hold.getHoldTypeName());
	}

}

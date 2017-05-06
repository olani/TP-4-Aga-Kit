import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.HoldType;

public class HoldTypeTest {

	private HoldType holdType;
	@Before
	public void setUp() throws Exception {
		holdType = new HoldType("Over Due", "This hold is created when a patron did not return item on time");
	}

	@Test
	public void test() {
		
	}

}

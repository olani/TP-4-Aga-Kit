import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.Copy;
import Domain.Title;


public class CopyTest {

	Copy copy;
	Title title;
	@Before
	public void setUp() throws Exception {
		title = new Title("T001", "Applying UML Patterns and Design", 149.99);
		copy = new Copy("001", title);
		
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}

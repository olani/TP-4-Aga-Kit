import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.Title;

public class TitleTest {

	Title title;
	@Before
	public void setUp() throws Exception {
		title = new Title("T89", "Java Book", 99.99);
	}

	@Test
	public void titleShouldKnowItsOwnPrice() {
		assertEquals(99.99, title.getPrice(), 0.00);
	}

}

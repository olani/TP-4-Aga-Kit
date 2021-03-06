import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.Copy;
import Domain.CopyDataStore;
import Domain.Title;

public class CopyDataStoreTest {

	CopyDataStore copyStore;
	@Before
	public void setUp() throws Exception {
		copyStore= new CopyDataStore();
	}

	@Test
	public void afterAddingCopyToCopyDataStore_CopyShouldBeInCopyDataStore() {
		Copy c = new Copy("C005", new Title("T004", "Random Book Title", 0.9));
		copyStore.addCopy(c);
		
		Copy expectedCopyOfcInCopyDataStore = copyStore.fetchCopy("C005");
		assertEquals(expectedCopyOfcInCopyDataStore.getCopyID(), c.getCopyID());
		//copyStore.removeCopy("C005");
	}
	
	@Test(expected=Domain.UnknownCopy.class)
	public void ifCopyDoesNotExist_CopyNotRecognizedExceptionIsRaised(){
		
		copyStore.fetchCopy("03123");
	}
	@Test
	public void testCopyIds(){
		copyStore.copyIds();
	}

}

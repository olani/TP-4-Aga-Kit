import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Domain.HoldType;
import Domain.HoldTypeDataStore;

public class HoldTypeDataStoreTest {

	@Test
	public void afterAddingHoldTypeToHoldTypeStore_HoldTypeShouldBeInStore() {
		HoldType ht = new HoldType("Over Due","Cused by late returns");
		
		HoldTypeDataStore.addHoldType(ht);
		
		HoldType expectedHoldType = HoldTypeDataStore.fetchHoldType("Over Due");
		assertEquals(expectedHoldType.getHoldDescription(), ht.getHoldDescription());
	}
	@Test
	public void fethHoldTypeReturnsNull_WhenHoldTypeIsNotFound(){
		assertNull(HoldTypeDataStore.fetchHoldType("Random Hold Returning Null"));
	}

}

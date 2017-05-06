

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import Domain.Copy;
import Domain.Title;
import Domain.TitleDataStore;

public class TitleDataStoreTest {

	@Test
	public void afterAddingTitleToTitleDataStore_TitleShouldBeInTitleDataStore() {
		Title t = new Title("T004", "Clean Code", 29.99);
		TitleDataStore.addTitle(t);
		
		Title expectedCopyOftInTitleDataStore = TitleDataStore.fetchTitle("T004");
		assertEquals(expectedCopyOftInTitleDataStore.getTitleID(), t.getTitleID());
	}
	
	@Test
	public void ifTitleDoesNotExist_ReturnNull(){
		
		assertEquals(TitleDataStore.fetchTitle("03123"), null);
	}
}

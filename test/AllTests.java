import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CheckInControlTest.class, CheckOutControlTest.class, CopyDataStoreTest.class, CopyTest.class,
		HoldTest.class, HoldTypeDataStoreTest.class, HoldTypeTest.class, PatronDataStoreTest.class,
		SaleControlTest.class, TitleDataStoreTest.class, TitleTest.class, TransactionLogsTest.class,
		TransactionTest.class })
public class AllTests {

}

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CheckInControlTest.class, CheckOutControlTest.class, CopyDataStoreTest.class, CopyTest.class,
		HoldTest.class, HoldTypeDataStoreTest.class, HoldTypeTest.class, PatronDataStoreTest.class, TitleDataStoreTest.class, TitleTest.class, TransactionLogsTest.class,
		TransactionTest.class, PatronTest.class,CheckOutAppTest.class, CheckInAppTest.class, SaleAppTest.class, SaleControlTest.class, TRLApplicationTest.class })
public class AllTests {

}

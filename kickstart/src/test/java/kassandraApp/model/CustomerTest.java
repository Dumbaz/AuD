package kassandraApp.model;
import org.junit.Assert;
import org.junit.Test;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerTest {
	   
	
	@Autowired
	UserAccountManager userAccountManager;
	
	
	@Test
	public void getUserAccountTest() {
		UserAccount hans = userAccountManager.create("Hans", "Wurst");
		Customer customer = new Customer(hans,0);
		Assert.assertEquals(hans, customer.getUserAccount());
		
	}
	@Test
	public void getPayTest() {
		Customer myCustomer = new Customer(null, 6000);
		Assert.assertTrue(6000 == myCustomer.getPay());      
	}
	  
	@Test(expected=IllegalArgumentException.class)
		public void setNegativePayTest() {
		new Customer(null, -1);
	}
}
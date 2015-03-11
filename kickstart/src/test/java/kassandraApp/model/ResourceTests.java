package kassandraApp.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResourceTests{
	private Resource res;
	private LittleHall littleHall = LittleHall.getInstance();
	private int rentHall;
	
	//Setup
	@Before
	public void setup(){
		//Ressourcen
		res = new Resource("TestBühne", Store.getInstance(), "Stage", 100);
		
		//Räume
		rentHall = littleHall.getRent();
	}
	
	//Ressourcen: positiver Test
	@Test
	public void testResRent1(){
		res.setRent(100);
		Assert.assertTrue(100 == res.getRent());
	}
	
	//Ressourcen: negativer Test
	@Test(expected=IllegalArgumentException.class)
	public void testResRent2(){
		res.setRent(-10);		
	}
	
	//Räume: positiver Test
	@Test
	public void testRoomRent1(){
		littleHall.setRent(100);
		Assert.assertTrue(100 == res.getRent());
	}
	
	//Räume: negativer Test
	@Test(expected=IllegalArgumentException.class)
	public void testRoomRent2(){
		littleHall.setRent(-10);		
	}
	
	//Alles wieder zurücksetzen
	@After
	public void deleteTest(){
		res = null;
		littleHall.setRent(rentHall);
	}
}

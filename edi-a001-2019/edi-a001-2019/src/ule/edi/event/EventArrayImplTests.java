package ule.edi.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e;
	
	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}

	public EventArrayImplTests() {
		
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Before
	public void testBefore() throws Exception{
	    e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 10, 100);

	}
	
	@Test
	public void testSomething() throws Exception {
		
	    Assert.assertTrue(e.getNumberOfAvailableSeats()==110);
	    Assert.assertEquals(e.getNumberOfSilverSeats(), 100);
	    Assert.assertEquals(e.getNumberOfGoldSeats(),10);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
	}
	
	@Test
	public void testGetPriceGold() throws Exception{
		
		Event e1 = new EventArrayImpl("Event 1", parseLocalDate("24/02/2018 17:00:00"), 20, 200);
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("24/02/2018 17:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(100.0, e.getPriceGold(), 0.01);
		Assert.assertEquals(100.0, e1.getPriceGold(), 0.01);
		Assert.assertEquals(50.0, e2.getPriceGold(), 0.01);
	}
	
	@Test
	public void testSetPriceGold() throws Exception{
		
		e.setPriceGold(30.0);
		
		Assert.assertEquals(30.0,e.getPriceGold(), 0.01);

	}
	
	@Test
	public void testGetPriceSilver() throws Exception{
		
		Event e1 = new EventArrayImpl("Event 1", parseLocalDate("24/02/2018 17:00:00"), 20, 200);
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("24/02/2018 17:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(50.0, e.getPriceSilver(), 0.01);
		Assert.assertEquals(50.0, e1.getPriceSilver(), 0.01);
		Assert.assertEquals(10.0, e2.getPriceSilver(), 0.01);
	}
	
	@Test 
	public void testSetPriceSilver() throws Exception{
		
		e.setPriceSilver(21.0);
		
		Assert.assertEquals(21.0,e.getPriceSilver(), 0.01);

	}
	
	@Test
	public void testGetName() throws Exception{
		
		Event e1 = new EventArrayImpl("Event 1", parseLocalDate("24/02/2018 17:00:00"), 20, 200);
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("24/02/2018 17:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals("The Fabulous Five", e.getName());
		Assert.assertEquals("Event 1", e1.getName());
		Assert.assertEquals("Event 2", e2.getName());
		
	}
	
	@Test 
	public void testGetDate() throws Exception{
		Event e1 = new EventArrayImpl("Event 1", parseLocalDate("25/02/2018 17:00:00"), 20, 200);
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(parseLocalDate("24/02/2018 17:00:00"), e.getDate());
		Assert.assertEquals(parseLocalDate("25/02/2018 17:00:00"), e1.getDate());
		Assert.assertEquals(parseLocalDate("27/02/2018 18:00:00"), e2.getDate());
	
	}
	
	@Test
	public void getNumberOfAttendingChildrenOk() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(0, e.getNumberOfAttendingChildren());
		Assert.assertEquals(0, e2.getNumberOfAttendingChildren());
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 13),Type.GOLD);
		e2.sellSeat(1, new Person("Chen","10203040C", 13),Type.SILVER);
		e2.sellSeat(2, new Person("Kai","10203040B", 10),Type.SILVER);
		
		Assert.assertEquals(1, e.getNumberOfAttendingChildren());
		Assert.assertEquals(2, e2.getNumberOfAttendingChildren());
		
	}
	
	@Test
	public void getNumberOfAttendingChildrenWrongAge() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(0, e.getNumberOfAttendingChildren());
		Assert.assertEquals(0, e2.getNumberOfAttendingChildren());
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 30),Type.GOLD);
		e2.sellSeat(1, new Person("Chen","10203040C", 29),Type.SILVER);
		e2.sellSeat(2, new Person("Kai","10203040B", 23),Type.SILVER);
		
		Assert.assertEquals(0, e.getNumberOfAttendingChildren());
		Assert.assertEquals(0, e2.getNumberOfAttendingChildren());
		
	}
	
	@Test 
	public void getNumberOfAttendingAdultsOk() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(0, e.getNumberOfAttendingAdults());
		Assert.assertEquals(0, e2.getNumberOfAttendingAdults());
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 30),Type.GOLD);
		e2.sellSeat(1, new Person("Chen","10203040C", 29),Type.SILVER);
		e2.sellSeat(2, new Person("Kai","10203040B", 23),Type.SILVER);
		
		Assert.assertEquals(1, e.getNumberOfAttendingAdults());
		Assert.assertEquals(2, e2.getNumberOfAttendingAdults());
		
	}
	
	@Test
	public void getNumberOfAttendingAdultsWrongAge() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(0, e.getNumberOfAttendingAdults());
		Assert.assertEquals(0, e2.getNumberOfAttendingAdults());
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 13),Type.GOLD);
		e.sellSeat(2, new Person("Lisa","10203040D", 73),Type.GOLD);
		e2.sellSeat(1, new Person("Chen","10203040C", 10),Type.SILVER);
		e2.sellSeat(2, new Person("Kai","10203040B", 86),Type.SILVER);
		
		Assert.assertEquals(0, e.getNumberOfAttendingAdults());
		Assert.assertEquals(0, e2.getNumberOfAttendingAdults());
		
	}
	

	@Test
	public void testSellSeat1Adult() throws Exception{
		
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		e.sellSeat(1, new Person("Sonyeon","10203040A", 34),Type.GOLD);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
	  
	}
	
	@Test
	public void getNumberOfAttendingElderlyPeopleOk() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(0, e.getNumberOfAttendingElderlyPeople());
		Assert.assertEquals(0, e2.getNumberOfAttendingElderlyPeople());
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 72),Type.GOLD);
		e2.sellSeat(1, new Person("Chen","10203040C", 83),Type.SILVER);
		e2.sellSeat(2, new Person("Kai","10203040B", 90),Type.SILVER);
		
		Assert.assertEquals(1, e.getNumberOfAttendingElderlyPeople());
		Assert.assertEquals(2, e2.getNumberOfAttendingElderlyPeople());
		
	}
	
	@Test
	public void getNumberOfAttendingElderlyPeopleWrongAge() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(0, e.getNumberOfAttendingElderlyPeople());
		Assert.assertEquals(0, e2.getNumberOfAttendingElderlyPeople());
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 13),Type.GOLD);
		e.sellSeat(2, new Person("Lisa","10203040D", 2147483647),Type.GOLD);
		e2.sellSeat(1, new Person("Chen","10203040C", 56),Type.SILVER);
		e2.sellSeat(2, new Person("Kai","10203040B", 2147483647),Type.SILVER);
		
		Assert.assertEquals(0, e.getNumberOfAttendingElderlyPeople());
		Assert.assertEquals(0, e2.getNumberOfAttendingElderlyPeople());
		
	}
	@Test
	public void testGetNumberOfSoldSeats() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 13),Type.GOLD);
		e2.sellSeat(1, new Person("Chen","10203040C", 56),Type.SILVER);
		e2.sellSeat(2, new Person("Kai","10203040B", 68),Type.SILVER);
		e2.sellSeat(3, new Person("Sehun","10203040D", 23),Type.GOLD);
		
		Assert.assertEquals(1, e.getNumberOfSoldSeats());
		Assert.assertEquals(3, e2.getNumberOfSoldSeats());
	
	}
	
	@Test
	public void testGetNumberOfSoldGoldSeats() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 13),Type.GOLD);
		e2.sellSeat(1, new Person("Chen","10203040C", 56),Type.GOLD);
		e2.sellSeat(2, new Person("Kai","10203040B", 68),Type.SILVER);
		e2.sellSeat(3, new Person("Sehun","10203040D", 23),Type.GOLD);
		
		Assert.assertEquals(1, e.getNumberOfSoldGoldSeats());
		Assert.assertEquals(2, e2.getNumberOfSoldGoldSeats());
		
	}
	
	@Test
	public void testGetNumberOfSoldSilverSeats() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		e.sellSeat(1, new Person("Sonyeon","10203040A", 13),Type.SILVER);
		e2.sellSeat(1, new Person("Chen","10203040C", 56),Type.SILVER);
		e2.sellSeat(2, new Person("Kai","10203040B", 68),Type.GOLD);
		e2.sellSeat(3, new Person("Sehun","10203040D", 23),Type.SILVER);
		
		Assert.assertEquals(1, e.getNumberOfSoldSilverSeats());
		Assert.assertEquals(2, e2.getNumberOfSoldSilverSeats());
		
	}
	
	@Test
	public void testGetNumberOfSeats() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(110,e.getNumberOfSeats());
		Assert.assertEquals(60,e2.getNumberOfSeats());
		
	}
	
	@Test
	public void testGetNumberOfGoldSeats() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(10,e.getNumberOfGoldSeats());
		Assert.assertEquals(10,e2.getNumberOfGoldSeats());
		
	}
	
	@Test
	public void testGetNumberOfSilverSeats() throws Exception{
		Event e2 = new EventArrayImpl("Event 2", parseLocalDate("27/02/2018 18:00:00"),10 , 50, 50.0, 10.0);
		
		Assert.assertEquals(100,e.getNumberOfSilverSeats());
		Assert.assertEquals(50,e2.getNumberOfSilverSeats());
		
	}
	
	@Test 
	public void testGetNumberOfAvailableSeats() throws Exception{
		
		Event e1 = new EventArrayImpl("Event 1", parseLocalDate("25/02/2018 17:00:00"), 20, 200);
		
		e.sellSeat(1,new Person("Sonyeon","10203040A", 13),Type.SILVER);
		e.sellSeat(2, new Person("Sehun","10203040B", 21),Type.GOLD);
		e1.sellSeat(1,new Person("Wendy","10203040C", 51),Type.SILVER);
		
		Assert.assertEquals(108, e.getNumberOfAvailableSeats());
		Assert.assertEquals(219, e1.getNumberOfAvailableSeats());
		
	}
	
	
	@Test
	public void testGetSeatOk() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		
		e.sellSeat(1,p1,Type.SILVER);
		e.sellSeat(2,p2,Type.GOLD);
		
		
		Assert.assertEquals("{'Event':'The Fabulous Five', 'Position':1, 'Holder':{ NIF: 10203040A  Name : Sonyeon, Age:13}, 'Price':50.0}", e.getSeat(1, Type.SILVER).toString());
		Assert.assertEquals("{'Event':'The Fabulous Five', 'Position':2, 'Holder':{ NIF: 10203040B  Name : Sehun, Age:21}, 'Price':100.0}", e.getSeat(2, Type.GOLD).toString());
	}
	
	@Test
	public void testGetSeatNegativePos() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		
		e.sellSeat(-1,p1,Type.SILVER);
		e.sellSeat(-20,p2,Type.GOLD);
		
		
		Assert.assertEquals(null, e.getSeat(-1, Type.SILVER));
		Assert.assertEquals(null, e.getSeat(-20, Type.GOLD));
	}
	
	@Test
	public void testGetSeatTooBigPos() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		
		e.sellSeat(200,p1,Type.SILVER);
		e.sellSeat(130,p2,Type.GOLD);
		
		
		Assert.assertEquals(null, e.getSeat(200, Type.SILVER));
		Assert.assertEquals(null, e.getSeat(130, Type.GOLD));
	}
	
	@Test
	public void testRefundSeatOk() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		
		e.sellSeat(1,p1,Type.SILVER);
		e.sellSeat(2,p2,Type.GOLD);
		
		Assert.assertEquals(p1.toString(), e.refundSeat(1, Type.SILVER).toString());
		Assert.assertEquals(p2.toString(), e.refundSeat(2, Type.GOLD).toString());
		
		
	}
	
	@Test
	public void testRefundSeatNegativePos() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		
		e.sellSeat(-1,p1,Type.SILVER);
		e.sellSeat(-20,p2,Type.GOLD);
		
		Assert.assertEquals(null, e.refundSeat(-1, Type.SILVER));
		Assert.assertEquals(null, e.refundSeat(-20, Type.GOLD));
		
		
	}
	
	@Test
	public void testRefundSeatTooBigPos() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		
		e.sellSeat(200,p1,Type.SILVER);
		e.sellSeat(130,p2,Type.GOLD);
		
		Assert.assertEquals(null, e.refundSeat(200, Type.SILVER));
		Assert.assertEquals(null, e.refundSeat(130, Type.GOLD));
		
		
	}
	
	@Test
	public void testRefundSeatNotExist() throws Exception{
		
		Assert.assertEquals(null, e.refundSeat(1, Type.SILVER));
		Assert.assertEquals(null, e.refundSeat(2, Type.GOLD));
		
		
	}
	
	@Test
	public void testSellSeatOk() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		Person p3 = new Person("Kai","10203040C", 35);
		
		Assert.assertEquals(100, e.getNumberOfSilverSeats());
		Assert.assertEquals(10, e.getNumberOfGoldSeats());
		
		Assert.assertTrue(e.sellSeat(1,p1,Type.SILVER));
		Assert.assertTrue(e.sellSeat(2,p2,Type.GOLD));
		Assert.assertTrue(e.sellSeat(3,p3,Type.SILVER));
		
		Assert.assertNotNull(e.getSeat(1,Type.SILVER ));
		Assert.assertNotNull(e.getSeat(2,Type.GOLD ));
		Assert.assertNotNull(e.getSeat(3, Type.SILVER));
		
	}
	
	@Test
	public void testSellSeatNegativePos() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		Person p3 = new Person("Kai","10203040C", 35);
		
		
		Assert.assertFalse(e.sellSeat(-1,p1,Type.SILVER));
		Assert.assertFalse(e.sellSeat(-20,p2,Type.GOLD));
		Assert.assertFalse(e.sellSeat(-200,p3,Type.SILVER));
		
	}
	
	@Test
	public void testSellSeatTooBigPos() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		Person p3 = new Person("Kai","10203040C", 35);
		
		
		Assert.assertFalse(e.sellSeat(200,p1,Type.SILVER));
		Assert.assertFalse(e.sellSeat(300,p2,Type.GOLD));
		Assert.assertFalse(e.sellSeat(2389,p3,Type.SILVER));
		
	}
	
	@Test
	public void testSellSeatNotAvailable() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		Person p3 = new Person("Chanyeol","10203040C", 13);
		Person p4 = new Person("Chen","10203040D", 21);
		
		e.sellSeat(1, p1, Type.SILVER);
		e.sellSeat(1, p2, Type.GOLD);
		
		
		Assert.assertNotNull(e.getSeat(1,Type.SILVER ));
		Assert.assertNotNull(e.getSeat(1,Type.GOLD ));
		
		
		e.sellSeat(1, p3, Type.SILVER);
		e.sellSeat(1, p4, Type.GOLD);
		
		Assert.assertFalse(e.sellSeat(1,p3,Type.SILVER));
		Assert.assertFalse(e.sellSeat(1,p4,Type.GOLD));
		
	}
	
	@Test
	public void testSellSeatNotExist() throws Exception{
		
		Event e1 = new EventArrayImpl("Event 1", parseLocalDate("25/02/2018 17:00:00"), 0, 0);
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 21);
		
		Assert.assertEquals(0, e1.getNumberOfGoldSeats());
		Assert.assertEquals(0, e1.getNumberOfSilverSeats());
		
		Assert.assertFalse(e1.sellSeat(1,p1,Type.SILVER));
		Assert.assertFalse(e1.sellSeat(2,p2,Type.GOLD));
		
	}
	

	@Test
	public void testGetAvailableGoldSeatsList() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		
		Assert.assertTrue(ep.sellSeat(1, new Person("1010", "AA", 10), Configuration.Type.GOLD));
		Assert.assertEquals("[2]",ep.getAvailableGoldSeatsList().toString());
	}

	@Test
	public void testGetAvailableSilverSeatsList() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), Configuration.Type.SILVER),true);
		Assert.assertEquals("[2]",ep.getAvailableSilverSeatsList().toString() );					
	}

	
	@Test
	public void testGetPrice() throws Exception{
		
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Seat s1 = new Seat(ep, 1, Type.SILVER, p1);
		
		Assert.assertEquals(50.0, ep.getPrice(s1),0.01);
		
	}
	
	@Test
	public void testGetColletionEvent() throws Exception{
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 32);

		e.sellSeat(1, p1, Type.GOLD);
		e.sellSeat(2, p2, Type.SILVER);
		
		Assert.assertEquals(150.0,e.getCollectionEvent(),0.01);
		
	}
	
	@Test 
	public void testGetPosPersonGold(){
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 32);
		
		e.sellSeat(1, p1, Type.GOLD);
		e.sellSeat(2, p2, Type.GOLD);
		
		Assert.assertEquals(1, e.getPosPersonGold(p1));
		Assert.assertEquals(2, e.getPosPersonGold(p2));
		
	}
	
	@Test 
	public void testGetPosPersonSilver(){
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 32);
		
		e.sellSeat(1, p1, Type.SILVER);
		e.sellSeat(2, p2, Type.SILVER);
		
		Assert.assertEquals(1, e.getPosPersonSilver(p1));
		Assert.assertEquals(2, e.getPosPersonSilver(p2));
		
	}
	
	@Test
	public void testIsGold(){
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 32);
		
		e.sellSeat(1, p1, Type.GOLD);
		e.sellSeat(2, p2, Type.SILVER);
		
		Assert.assertTrue(e.isGold(p1));
		Assert.assertFalse(e.isGold(p2));
	
	}
	
	@Test
	public void testIsSilver(){
		
		Person p1 = new Person("Sonyeon","10203040A", 13);
		Person p2 = new Person("Sehun","10203040B", 32);
		
		e.sellSeat(1, p1, Type.GOLD);
		e.sellSeat(2, p2, Type.SILVER);
		
		Assert.assertFalse(e.isSilver(p1));
		Assert.assertTrue(e.isSilver(p2));
	
	}
	
	
	
	
}
	


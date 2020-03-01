package coffeemachine;

//import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;

public class OrderTest {
	
//	@Mock
//	PadDTO pad;
	
//	@Before
//	public void before() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	public void mockPadOrder() {
//		boolean a = pad.order("a");
//		Assertions.assertTrue(a);
//		Mockito.verify(pad, Mockito.times(1)).vrai();
//		Mockito.verify(pad, Mockito.times(0)).faux();
//		boolean b = pad.order("b");
//		Assertions.assertFalse(b);
//		Mockito.verify(pad, Mockito.times(1)).vrai();
//		Mockito.verify(pad, Mockito.times(1)).faux();
	}

	@Test
	public void orderTeaOneSugarOneStick() throws Exception {
		Drink d = new Drink("T:1:0");
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 tea with 1 sugar and a stick");
	}

	@Test
	public void orderChocolateNoSugarNoStick() throws Exception {
		Drink d = new Drink("H::");
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 chocolate with no sugar - and therefore no stick");
	}
	
	@Test
	public void orderCoffeeTwoSugarOneStick() throws Exception {
		Drink d = new Drink("C:2:0");
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 coffee with 2 sugars and a stick");
	}

	@Test
	public void message() throws Exception {
		Drink d = new Drink("M:message-content");
		Assertions.assertEquals(d.toString(), "message-content");
	}
}

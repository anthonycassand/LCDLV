package coffeemachine;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import coffeemachine.entities.Drink;
import coffeemachine.enums.EnumDrink;
import coffeemachine.services.DrinkMakerService;


public class OrderTest {
	
	DrinkMakerService dms;
	
	@Before
	public void before() {
		dms = new DrinkMakerService();
	}

	@Test
	public void orderTeaOneSugarOneStick() throws Exception {
		Drink d = dms.buyYouADrink("T:1:0", EnumDrink.T.getPrice());
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 tea with 1 sugar and a stick");
	}

	@Test
	public void orderExtraHotTeaOneSugarOneStick() throws Exception {
		Drink d = dms.buyYouADrink("Th:1:0", EnumDrink.T.getPrice());
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 extra hot tea with 1 sugar and a stick");
	}

	@Test
	public void orderTeaOneSugarOneStickNotEnoughMoney() throws Exception {
		Drink d = dms.buyYouADrink("T:1:0", (EnumDrink.T.getPrice() - 0.1f));
		Assertions.assertEquals(d.toString(), "not enough money given");
	}

	@Test
	public void orderChocolateNoSugarNoStick() throws Exception {
		Drink d = dms.buyYouADrink("H::", EnumDrink.H.getPrice());
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 chocolate with no sugar - and therefore no stick");
	}

	@Test
	public void orderExtraHotChocolateOneSugarAndAStick() throws Exception {
		Drink d = dms.buyYouADrink("Hh:1:0", EnumDrink.H.getPrice());
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 extra hot chocolate with 1 sugar and a stick");
	}

	@Test
	public void orderChocolateNoSugarNoStickNotEnoughMoney() throws Exception {
		Drink d = dms.buyYouADrink("H::", (EnumDrink.H.getPrice() - 0.1f));
		Assertions.assertEquals(d.toString(), "not enough money given");
	}
	
	@Test
	public void orderCoffeeTwoSugarOneStick() throws Exception {
		Drink d = dms.buyYouADrink("C:2:0", EnumDrink.C.getPrice());
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 coffee with 2 sugars and a stick");
	}
	
	@Test
	public void orderExtraHotCoffeeNoSugar() throws Exception {
		Drink d = dms.buyYouADrink("Ch::", EnumDrink.C.getPrice());
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 extra hot coffee with no sugar - and therefore no stick");
	}
	
	@Test
	public void orderCoffeeTwoSugarOneStickNotEnoughMoney() throws Exception {
		Drink d = dms.buyYouADrink("C:2:0", (EnumDrink.C.getPrice() - 0.1f));
		Assertions.assertEquals(d.toString(), "not enough money given");
	}
	
	@Test
	public void orderOrangeJuice() throws Exception {
		Drink d = dms.buyYouADrink("O::", EnumDrink.O.getPrice());
		Assertions.assertEquals(d.toString(), "Drink maker makes 1 orange juice with no sugar - and therefore no stick");
	}
	
	@Test
	public void orderOrangeJuiceNotEnoughMoney() throws Exception {
		Drink d = dms.buyYouADrink("O::", (EnumDrink.O.getPrice() - 0.1f));
		Assertions.assertEquals(d.toString(), "not enough money given");
	}

	@Test
	public void message() throws Exception {
		Drink d = dms.buyYouADrink("M:message-content", null);
		Assertions.assertEquals(d.toString(), "message-content");
	}

	@Test
	public void wrongMessage() throws Exception {
		Exception exception = null;
		try {
			dms.buyYouADrink("M:message-content:x:y", null);
		} catch (Exception e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
	}
}

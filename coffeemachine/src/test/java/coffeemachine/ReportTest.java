package coffeemachine;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ReportTest {

	private static final String TEA_MESSAGE = "T::";
	private static final String CHOCOLATE_MESSAGE = "H::";
	private static final String COFFEE_MESSAGE = "C::";
	private static final String ORANGE_MESSAGE = "O::";
	
	private static final Integer TEA_NUMBER = 15;
	private static final Integer CHOCOLATE_NUMBER = 12;
	private static final Integer COFFEE_NUMBER = 7;
	private static final Integer ORANGE_NUMBER = 3;
	
	private static final Map<String, Integer> drinksToGenerate = new HashMap<String, Integer>() {{
		put(TEA_MESSAGE, TEA_NUMBER);
		put(CHOCOLATE_MESSAGE, CHOCOLATE_NUMBER);
		put(COFFEE_MESSAGE, COFFEE_NUMBER);
		put(ORANGE_MESSAGE, ORANGE_NUMBER);
	}};
	
	@Test
	public void buyDrinksThenGenerateAReportTest() throws Exception {
		for (String message : drinksToGenerate.keySet()) {
			for (int i=0 ; i<drinksToGenerate.get(message) ; i++) {
				DrinkMakerService.buyYouADrink(message, 1.f);
			}
		}
		
		String actualReport = DrinkMakerService.generateReport();
		
		Assertions.assertTrue(actualReport.contains("tea sold : " + TEA_NUMBER));
		Assertions.assertTrue(actualReport.contains("chocolate sold : " + CHOCOLATE_NUMBER));
		Assertions.assertTrue(actualReport.contains("coffee sold : " + COFFEE_NUMBER));
		Assertions.assertTrue(actualReport.contains("orange juice sold : " + ORANGE_NUMBER));
		Assertions.assertTrue(actualReport.contains("total amount of money earned : 17,30 euros"));
	}
}

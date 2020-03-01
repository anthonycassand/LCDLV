package coffeemachine;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import coffeemachine.services.BeverageQuantityChecker;

public class ShortageTest {

	@Mock
	BeverageQuantityChecker bqc;
	
	@InjectMocks
	DrinkMakerService dms = new DrinkMakerService();
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void buyAShortageDrink() throws Exception {
		Mockito.when(bqc.isEmpty(Mockito.anyString())).thenReturn(true);
		Drink d = dms.buyYouADrink("C::", 1.f);
		Assertions.assertEquals(d.toString(), DrinkMakerService.SHORTAGE_MESSAGE);
	}
}

package coffeemachine;

import java.util.Date;

public class SoldDrinkEntity extends Drink {

	private Date sellingDateTime;
	
	public SoldDrinkEntity(Drink d, Date date) {
		super(d);
		sellingDateTime = date;
	}
}

package coffeemachine.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coffeemachine.entities.Drink;
import coffeemachine.entities.SoldDrinkEntity;
import coffeemachine.enums.EnumDrink;
import coffeemachine.services.impl.BeverageQuantityCheckerImpl;
import coffeemachine.services.impl.EmailNotifierImpl;

public class DrinkMakerService {
	
	BeverageQuantityChecker bqc = new BeverageQuantityCheckerImpl();
	EmailNotifier en = new EmailNotifierImpl();
	
	public static final String SHORTAGE_MESSAGE = "Your drink cannot be delivered because of a "
			+ "shortage. A notification has been sent;";
	
	public List<SoldDrinkEntity> soldDrinks = new ArrayList<>();
	
	public Drink buyYouADrink(final String message, Float moneyGiven) throws Exception {
		if (message == null || message.isEmpty()) {
			throw new Exception("message null or empty");
		}
		String[] splittedMessage = message.split(":", -1);
		EnumDrink drinkType;
		Boolean isDrinkHot = null;
		String type = splittedMessage[0];
		if (type.length() == 1) {
			drinkType = EnumDrink.getValueOf(type);
		} else if (type.length() == 2 && "h".equals(type.substring(1))) {
			drinkType = EnumDrink.getValueOf(type.substring(0, 1));
			isDrinkHot = true;
		} else {
			throw new Exception("wrong drink type value");
		}
		if (drinkType == null) {
			throw new Exception("unkown order type");
		}
		if (splittedMessage.length == 2 && drinkType == EnumDrink.M) {
			return new Drink(splittedMessage[1]);
		} else if (splittedMessage.length == 3 && drinkType != EnumDrink.M) {
			if (bqc.isEmpty(drinkType.getName())) {
				en.notifyMissingDrink(drinkType.getName()); 
				return new Drink(SHORTAGE_MESSAGE);
			}
			if (moneyGiven == null || moneyGiven < drinkType.getPrice()) {
				return new Drink("not enough money given");
			}
			Integer sugarNumber = null;
			Boolean hasStick = null;
			if (splittedMessage[1] != null && !splittedMessage[1].equals("")) {
				Integer i = Integer.parseInt(splittedMessage[1]);
				if (i != null && i < 0) {
					throw new Exception("incorrect number of sugar");
				} else if (i != null && i >= 0) {
					sugarNumber = i;
				}
			}
			if (splittedMessage[2] != null && !splittedMessage[2].isEmpty()) {
				hasStick = (Integer.parseInt(splittedMessage[2]) == 0);
			}
			Drink d = new Drink(drinkType, sugarNumber, hasStick, isDrinkHot);
			soldDrinks.add(new SoldDrinkEntity(d, new Date()));
			return d;
		} else {
			throw new Exception("wrong message length");
		}
	}
	
	public String generateReport() {
		final StringBuilder sb = new StringBuilder();
		final Map<EnumDrink, Integer> drinksSold = new HashMap<>();
		Double moneyEarned = 0d;
		
		for (final SoldDrinkEntity sde : soldDrinks) {
			final EnumDrink type = sde.getType();
			if (! drinksSold.containsKey(type)) {
				drinksSold.put(type, 1);
			} else {
				drinksSold.put(type, drinksSold.get(type)+1);
			}
			moneyEarned+= type.getPrice();
		}
		
		for (EnumDrink drinkType : drinksSold.keySet()) {
			sb.append(drinkType.getName());
			sb.append(" sold : ");
			sb.append(drinksSold.get(drinkType));
			sb.append("\n");
		}
		sb.append("total amount of money earned : ");
		DecimalFormat df = new java.text.DecimalFormat("0.##");
		df.setMinimumFractionDigits(2);
		sb.append(df.format(moneyEarned));
		sb.append(" euros");
		return sb.toString();
	}
}

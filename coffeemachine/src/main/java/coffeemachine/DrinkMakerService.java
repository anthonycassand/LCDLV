package coffeemachine;

import coffeemachine.enums.EnumDrink;

public class DrinkMakerService {
	
	public static Drink buyYouADrink(final String message, Float moneyGiven) throws Exception {
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
			return new Drink(drinkType, sugarNumber, hasStick, isDrinkHot);
		} else {
			throw new Exception("wrong message length");
		}
	}
}

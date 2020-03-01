package coffeemachine;

import coffeemachine.enums.EnumDrink;

public class DrinkMakerService {
	
	public static Drink buyYouADrink(final String message) throws Exception {
		if (message == null || message.isEmpty()) {
			throw new Exception("message null or empty");
		}
		String[] splittedMessage = message.split(":", -1);
		EnumDrink drinkType = EnumDrink.getValueOf(splittedMessage[0]);
		if (drinkType == null) {
			throw new Exception("unkown order type");
		}
		if (splittedMessage.length == 2 && drinkType == EnumDrink.M) {
			return new Drink(splittedMessage[1]);
		} else if (splittedMessage.length == 4 && drinkType != EnumDrink.M) {
			if (splittedMessage[3] == null || splittedMessage[3].equals("")) {
				throw new Exception("unexpected money given value");
			} else {
				final Float moneyGiven = Float.parseFloat(splittedMessage[3]);
				if (moneyGiven < drinkType.getPrice()) {
					return new Drink("not enough money given");
				}
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
			return new Drink(drinkType, sugarNumber, hasStick);
		} else {
			throw new Exception("wrong message length");
		}
	}
}

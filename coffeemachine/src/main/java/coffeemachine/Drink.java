package coffeemachine;
import coffeemachine.enums.EnumDrink;

public class Drink {
	EnumDrink drinkType;
	String messageToDisplay;
	Integer sugerNumber = 0;
	boolean hasStick = false;
	
	public Drink(final String message) throws Exception {
		if (message == null || message.isEmpty()) {
			throw new Exception("message null or empty");
		}
		String[] splittedMessage = message.split(":", -1);
		drinkType = EnumDrink.getValueOf(splittedMessage[0]);
		if (drinkType == null) {
			throw new Exception("unkown order type");
		}
		if (splittedMessage.length == 2) {
			if (drinkType == EnumDrink.M) {
				messageToDisplay = splittedMessage[1];
			} else {
				throw new Exception("incorrect message length");
			}
		} else if (splittedMessage.length == 3) {
			if (splittedMessage[1] != null && !splittedMessage[1].equals("")) {
				Integer i = Integer.parseInt(splittedMessage[1]);
				if (i != null && i < 0) {
					throw new Exception("incorrect number of sugar");
				} else if (i != null && i >= 0) {
					sugerNumber = i;
				}
				if (splittedMessage[2] != null && !splittedMessage[2].isEmpty()) {
					Integer hasStick = Integer.parseInt(splittedMessage[2]);
					this.hasStick = hasStick == 0;
				}
			}
		} else {
			throw new Exception("wrong message length");
		}
		
	}
	
	@Override
	public String toString() {
		if (drinkType == EnumDrink.M) {
			return messageToDisplay;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Drink maker makes 1 ");
			sb.append(drinkType.getName());
			sb.append(" with ");
			if (sugerNumber == 0) {
				sb.append("no");
			} else {
				sb.append(sugerNumber);
			}
			sb.append(" sugar");
			if (sugerNumber>1) {
				sb.append("s");
			}
			if (hasStick) {
				sb.append(" and a stick");
			} else {
				sb.append(" - and therefore no stick");
			}
			return sb.toString();
		}
	}
}

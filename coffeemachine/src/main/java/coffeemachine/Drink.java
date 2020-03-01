package coffeemachine;
import coffeemachine.enums.EnumDrink;

public class Drink {
	EnumDrink drinkType;
	String messageToDisplay;
	Integer sugerNumber = 0;
	boolean hasStick = false;
	
	public Drink(final String message) throws Exception {
		drinkType = EnumDrink.M;
		messageToDisplay = message;
	}
	
	
	
	public Drink(EnumDrink drinkType, Integer sugarNumber, Boolean hasStick) {
		this.drinkType = drinkType;
		if (sugarNumber != null) {
			this.sugerNumber = sugarNumber;
		}
		if (hasStick != null) {
			this.hasStick = hasStick;
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

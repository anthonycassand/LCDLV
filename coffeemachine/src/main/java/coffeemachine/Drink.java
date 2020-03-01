package coffeemachine;
import coffeemachine.enums.EnumDrink;

public class Drink {
	private EnumDrink drinkType;
	private String messageToDisplay;
	private Integer sugerNumber = 0;
	private boolean hasStick = false;
	private boolean isDrinkHot = false;
	
	public Drink(final String message) throws Exception {
		drinkType = EnumDrink.M;
		messageToDisplay = message;
	}
	
	public Drink(EnumDrink drinkType, Integer sugarNumber, Boolean hasStick, Boolean isDrinkHot) {
		this.drinkType = drinkType;
		if (sugarNumber != null) {
			this.sugerNumber = sugarNumber;
		}
		if (hasStick != null) {
			this.hasStick = hasStick;
		}
		if (isDrinkHot != null) {
			this.isDrinkHot = isDrinkHot;
		}
	}

	@Override
	public String toString() {
		if (drinkType == EnumDrink.M) {
			return messageToDisplay;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Drink maker makes 1 ");
			if (isDrinkHot) {
				sb.append("extra hot ");
			}
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

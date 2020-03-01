package coffeemachine.enums;

public enum EnumDrink {
	T("tea"),
	H("chocolate"),
	C("coffee"),
	M("message");
	private String drink;
	private EnumDrink(String s) {
		this.drink = s;
	}
	public static EnumDrink getValueOf(final String s) {
		for (EnumDrink element : EnumDrink.values()) {
			if (element.name().equals(s)) {
				return element;
			}
		}
		return null;
	}
	public String getName() {
		return drink;
	}
}

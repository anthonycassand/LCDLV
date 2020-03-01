package coffeemachine.enums;

public enum EnumDrink {
	
	T("tea", 0.4f),
	H("chocolate", 0.5f),
	C("coffee", 0.5f),
	O("orange juice", 0.6f),
	M("message", null);
	
	private String drink;
	private Float price;
	
	private EnumDrink(String s, Float d) {
		this.drink = s;
		this.price = d;
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
	
	public Float getPrice() {
		return price;
	}
}

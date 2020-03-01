package coffeemachine.services.impl;

import coffeemachine.services.EmailNotifier;

public class EmailNotifierImpl implements EmailNotifier {
	public void notifyMissingDrink(String drink) {
		System.out.println("missing drink " +drink+ " notified");
	}
}

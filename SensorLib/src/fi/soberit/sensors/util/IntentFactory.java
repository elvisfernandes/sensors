package fi.soberit.sensors.util;

import android.content.Intent;

public class IntentFactory {

	public static Intent create(String action) {
		final Intent i = new Intent();
		i.setAction(action);
		
		return i;
	}
}

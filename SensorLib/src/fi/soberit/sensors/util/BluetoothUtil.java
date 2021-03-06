package fi.soberit.sensors.util;

import fi.soberit.sensors.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.widget.Toast;

public class BluetoothUtil {

	public static final int REQUEST_ENABLE_BT = 132920;

	
	/**
	 * 
	 * Checks if device has Bluetooth and optionally asks system to display launch dialog.
	 * 
	 * @param context parent activity
	 * @return false if Bluetooth was enabled, true if it wasn't 
	 */	
	public static boolean enablingBluetooth(Activity context) {
		return enablingBluetooth(context, REQUEST_ENABLE_BT, R.string.no_bluetooth);
	}
	
	/**
	 * 
	 * Checks if device has Bluetooth and optionally asks system to display launch dialog.
	 * 
	 * @param context parent activity
	 * @param requestCode to be used in onActivityResult
	 * @param Resourse pointing to "You have no Bluetooth!" message
	 * @return was it disabled? 
	 */
	
	public static boolean enablingBluetooth(Activity context, int requestCode, int noBluetoothText) {
		final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		
		if (adapter == null) {
			Toast.makeText(context, R.string.no_bluetooth, Toast.LENGTH_LONG).show();
			context.setResult(Activity.RESULT_CANCELED);
			context.finish();
			return true;
		}
		
		if (!adapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			context.startActivityForResult(enableBtIntent, requestCode);
			
			return true;
		}
		
		return false;
	}
}

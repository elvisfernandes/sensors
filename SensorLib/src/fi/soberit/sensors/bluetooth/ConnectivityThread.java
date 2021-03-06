package fi.soberit.sensors.bluetooth;

public interface ConnectivityThread {
	
	public boolean isConnected();	
	public void setBluetoothAddress(String address);
	public String getBluetoothAddress();
	public Object getMonitor();
	
	public void closeSocket();
}
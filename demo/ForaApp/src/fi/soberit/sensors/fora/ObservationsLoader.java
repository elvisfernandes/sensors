package fi.soberit.sensors.fora;

import java.util.Collection;
import java.util.TreeSet;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import fi.soberit.sensors.Observation;
import fi.soberit.sensors.fora.db.BloodPressureDao;
import fi.soberit.sensors.fora.db.DatabaseHelper;
import fi.soberit.sensors.fora.db.GlucoseDao;
import fi.soberit.sensors.fora.db.PulseDao;
import fi.soberit.sensors.fora.db.Record;

class ObservationsLoader extends AsyncTaskLoader<Collection<Record>> {

	public static final String TAG = ObservationsLoader.class.getSimpleName();
	
	private BloodPressureDao pressureDao;
	private PulseDao pulseDao;
	private GlucoseDao glucoseDao;

	private long[] types;

	private DatabaseHelper dbHelper;

	public ObservationsLoader(Context context, long[] types) {
		super(context);
		
		this.types = types;

		dbHelper = new DatabaseHelper(context);

		pressureDao = new BloodPressureDao(dbHelper);
		pulseDao = new PulseDao(dbHelper);
		glucoseDao = new GlucoseDao(dbHelper);
	}

	@Override
	public Collection<Record> loadInBackground() {
		Log.d(TAG, "loadInBackground");	

		TreeSet<Record> result = new TreeSet<Record>();

		for (long type : types) {
			
			if (type == Observation.TYPE_INDEX_BLOOD_PRESSURE) {
				result.addAll(pressureDao.getMeasurements());
 
			} else if (type == Observation.TYPE_INDEX_GLUCOSE) {
				result.addAll(glucoseDao.getMeasurements());

			} else if (type == Observation.TYPE_INDEX_PULSE) {
				result.addAll(pulseDao.getMeasurements());

			}
		}

		return result;
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}
	
	@Override
	protected void onReset() {
		Log.d(TAG, "onReset()");
		
		dbHelper.closeDatabases();
	}
}
package net.sistran.mobile.main;

import android.app.IntentService;
import android.content.Intent;

import net.sistran.mobile.database.DatabaseCreator;
import net.sqlcipher.database.SQLiteDatabase;

public class BalancoSchedulingService extends IntentService {
	public BalancoSchedulingService() {
		super("SchedulingService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		try {
			SQLiteDatabase.loadLibs(this);
		} catch (Exception e) {
		}

		(DatabaseCreator.getBalancoDatabaseAdapter(this)).setAutos_realizados(0);
		(DatabaseCreator.getBalancoDatabaseAdapter(this)).setRrd_realizados(0);
		(DatabaseCreator.getBalancoDatabaseAdapter(this)).setTca_realizados(0);
		(DatabaseCreator.getBalancoDatabaseAdapter(this)).setTav_realizados(0);
		BalancoAlarmReceiver.completeWakefulIntent(intent);

	}

}

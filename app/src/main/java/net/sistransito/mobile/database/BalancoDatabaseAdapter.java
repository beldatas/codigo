package net.sistransito.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.timeandime.TimeAndIme;
import net.sistrnsitomobile.R;
import net.sqlcipher.database.SQLiteDatabase;

public class BalancoDatabaseAdapter {
	private BalancoDatabaseHelper databaseHelper;
	private Context context;
	private SQLiteDatabase db;
	private TimeAndIme ime;

	public BalancoDatabaseAdapter(Context context) {
		this.context = context;
		databaseHelper = new BalancoDatabaseHelper(context);
		ime = new TimeAndIme(context);
		db = databaseHelper.getWritableDatabase(ime.getIME());
		checkTable();
	}

	private int getValue(String columnName) {
		Cursor cursor = db.query(BalancoDatabaseHelper.TABLE_NAME, null, null,
				null, null, null, null);
		cursor.moveToFirst();
		int value = cursor.getInt(cursor.getColumnIndex(columnName));
		cursor.close();
		return value;

	}

	public int getAutos_realizados() {
		return getValue(BalancoDatabaseHelper.AUTO_REALIZADOS);
	}

	public int getTav_realizados() {
		return getValue(BalancoDatabaseHelper.TAV_REALIZADOS);
	}

	public int getTca_realizados() {
		return getValue(BalancoDatabaseHelper.TCA_REALIZADOS);
	}

	public int getRrd_realizados() {
		return getValue(BalancoDatabaseHelper.RRD_REALIZADOS);
	}

	public int getAutos_restante() {
		return getValue(BalancoDatabaseHelper.AUTO_RESTANTE);
	}

	public int getTav_restante() {
		return getValue(BalancoDatabaseHelper.TAV_RESTANTE);
	}

	public int getTca_restante() {
		return getValue(BalancoDatabaseHelper.TCA_RESTANTE);
	}

	public int getRrd_restante() {
		return getValue(BalancoDatabaseHelper.RRD_RESTANTE);
	}

	private void setValue(String columnName, int value) {
		ContentValues values = new ContentValues();
		values.put(columnName, value);
		db.update(BalancoDatabaseHelper.TABLE_NAME, values,
				SetttingDatabaseHelper.SETTING_ID_FILED + "= ? ",
				new String[] { "1" });

	}

	public void setAutos_realizados() {
		setValue(BalancoDatabaseHelper.AUTO_REALIZADOS,
				getAutos_realizados() + 1);
	}

	public void setTav_realizados() {
		setValue(BalancoDatabaseHelper.TAV_REALIZADOS, getTav_realizados() + 1);
	}

	public void setTca_realizados() {
		setValue(BalancoDatabaseHelper.TCA_REALIZADOS, getTca_realizados() + 1);
	}

	public void setRrd_realizados() {
		setValue(BalancoDatabaseHelper.RRD_REALIZADOS, getRrd_realizados() + 1);
	}

	public void setAutos_realizados(int autos_realizados) {
		setValue(BalancoDatabaseHelper.AUTO_REALIZADOS, autos_realizados);
	}

	public void setTav_realizados(int tav_realizados) {
		setValue(BalancoDatabaseHelper.TAV_REALIZADOS, tav_realizados);
	}

	public void setTca_realizados(int tca_realizados) {
		setValue(BalancoDatabaseHelper.TCA_REALIZADOS, tca_realizados);
	}

	public void setRrd_realizados(int rrd_realizados) {
		setValue(BalancoDatabaseHelper.RRD_REALIZADOS, rrd_realizados);
	}

	public void setAutos_restante(int autos_restante) {
		setValue(BalancoDatabaseHelper.AUTO_RESTANTE, autos_restante);
	}

	public void setTav_restante(int tav_restante) {
		setValue(BalancoDatabaseHelper.TAV_RESTANTE, tav_restante);
	}

	public void setTca_restante(int tca_restante) {
		setValue(BalancoDatabaseHelper.TCA_RESTANTE, tca_restante);
	}

	public void setRrd_restante(int rrd_restante) {
		setValue(BalancoDatabaseHelper.RRD_RESTANTE, rrd_restante);
	}

	public synchronized void close() {
		db.close();
	}

	private void checkTable() {

		Cursor mcursor = db.rawQuery("SELECT count(*) FROM "
				+ BalancoDatabaseHelper.TABLE_NAME, null);

		mcursor.moveToFirst();
		int icount = mcursor.getInt(0);
		if (icount > 0) {
			mcursor.close();
			return;
		} else {

			ContentValues values = new ContentValues();
			values.put(BalancoDatabaseHelper.ID_FIELD, 1);
			long a = db.insert(BalancoDatabaseHelper.TABLE_NAME, null, values);

			boolean c = a > 0;
			Log.d("ccccccccccccccc", "xxxxxxx" + c);
			mcursor.close();
		}
	}

	public String getShowView() {
		String data = AppConstants.NEW_LINE
				+ context.getResources().getString(R.string.autos_de_Infracao)
				+ AppConstants.NEW_LINE + AppConstants.NEW_LINE
				+ context.getResources().getString(R.string.autos_realizados)
				+ getAutos_realizados() + "     "
				+ context.getResources().getString(R.string.restante)
				+ getAutos_restante() + AppConstants.NEW_LINE;

		data += AppConstants.NEW_LINE
				+ context.getResources().getString(R.string.tav)
				+ AppConstants.NEW_LINE + AppConstants.NEW_LINE
				+ context.getResources().getString(R.string.tav_realizados)
				+ getTav_realizados() + "     "
				+ context.getResources().getString(R.string.restante)
				+ getTav_restante() + AppConstants.NEW_LINE;

		data += AppConstants.NEW_LINE
				+ context.getResources().getString(R.string.tca)
				+ AppConstants.NEW_LINE + AppConstants.NEW_LINE
				+ context.getResources().getString(R.string.tca_realizados)
				+ getTca_realizados() + "     "
				+ context.getResources().getString(R.string.restante)
				+ getTca_restante() + AppConstants.NEW_LINE;

		data += AppConstants.NEW_LINE
				+ context.getResources().getString(R.string.rrd)
				+ AppConstants.NEW_LINE + AppConstants.NEW_LINE
				+ context.getResources().getString(R.string.rrd_realizados)
				+ getRrd_realizados() + "     "
				+ context.getResources().getString(R.string.restante)
				+ getRrd_restante() + AppConstants.NEW_LINE;

		return data;
	}
}
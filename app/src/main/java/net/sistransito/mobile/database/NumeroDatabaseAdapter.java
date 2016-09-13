package net.sistransito.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import net.sistransito.mobile.timeandime.TimeAndIme;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

public class NumeroDatabaseAdapter {
	private TimeAndIme ime;
	private SQLiteDatabase database;
	private NumeroDatabaseHelper databaseHelper;
	private final int CHECK_NUM_SIZE = 10;

	public NumeroDatabaseAdapter(Context context) {
		ime = new TimeAndIme(context);
		databaseHelper = new NumeroDatabaseHelper(context);
		database = databaseHelper.getWritableDatabase(ime.getIME());
	}

	public void close() {
		database.close();
	}

	public boolean deleteData(String table_name, String colume_name, String data) {
		return this.database.delete(table_name, colume_name + "=?",
				new String[] { data }) > 0;
	}

	public boolean deleteAuotNumero(String data) {

		return deleteData(NumeroDatabaseHelper.TABLE_NAME_NUMERO_AUTO,
				NumeroDatabaseHelper.NUMERO_AUTO, data);
	}

	public boolean deleteTcaNumero(String data) {

		return deleteData(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TCA,
				NumeroDatabaseHelper.NUMERO_TCA, data);
	}

	public boolean deleteTavNumero(String data) {

		return deleteData(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TAV,
				NumeroDatabaseHelper.NUMERO_TAV, data);
	}

	public boolean deleteRrdNumero(String data) {

		return deleteData(NumeroDatabaseHelper.TABLE_NAME_NUMERO_RRD,
				NumeroDatabaseHelper.NUMERO_RRD, data);
	}

	private String getNumero(String table_name, String filed_name) {

		Cursor myCursor = this.database.query(table_name, null, null, null,
				null, null, null);
		if (myCursor.getCount() > 0) {
			myCursor.moveToLast();
			String num = myCursor
					.getString(myCursor.getColumnIndex(filed_name));
			myCursor.close();
			return num;
		} else {
			myCursor.close();
			return null;
		}
	}

	public String getAutoNumero() {
		return getNumero(NumeroDatabaseHelper.TABLE_NAME_NUMERO_AUTO,
				NumeroDatabaseHelper.NUMERO_AUTO);
	}

	public String geTavNumero() {

		return getNumero(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TAV,
				NumeroDatabaseHelper.NUMERO_TAV);
	}

	public String getTcaNumero() {

		return getNumero(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TCA,
				NumeroDatabaseHelper.NUMERO_TCA);
	}

	public String getRrdNumero() {

		return getNumero(NumeroDatabaseHelper.TABLE_NAME_NUMERO_RRD,
				NumeroDatabaseHelper.NUMERO_RRD);
	}

	private boolean updateTable(String tableName, String clume_name,
			ArrayList<String> list) {
		this.database.delete(tableName, null, null);
		long count = 0;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ContentValues values = new ContentValues();
				values.put(clume_name, list.get(i));
				count += this.database.insert(tableName, null, values);
			}
		}
		return count > 0;
	}

	public boolean setAutoNumero(ArrayList<String> list) {

		return updateTable(NumeroDatabaseHelper.TABLE_NAME_NUMERO_AUTO,
				NumeroDatabaseHelper.NUMERO_AUTO, list);
	}

	public boolean setTavNumero(ArrayList<String> list) {

		return updateTable(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TAV,
				NumeroDatabaseHelper.NUMERO_TAV, list);
	}

	public boolean setTcaNumero(ArrayList<String> list) {

		return updateTable(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TCA,
				NumeroDatabaseHelper.NUMERO_TCA, list);
	}

	public boolean setRrdNumero(ArrayList<String> list) {

		return updateTable(NumeroDatabaseHelper.TABLE_NAME_NUMERO_RRD,
				NumeroDatabaseHelper.NUMERO_RRD, list);
	}

	private boolean getcheck(String table_name) {
		Cursor myCursor = this.database.query(table_name, null, null, null,
				null, null, null);
		if (myCursor.getCount() <= CHECK_NUM_SIZE) {
			myCursor.close();
			return true;
		} else {
			myCursor.close();
			return false;
		}
	}

	public boolean isNeedAutodeUpdate() {
		return getcheck(NumeroDatabaseHelper.TABLE_NAME_NUMERO_AUTO);
	}

	public boolean isNeedTavUpdate() {
		return getcheck(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TAV);
	}

	public boolean isNeedTcaUpdate() {
		return getcheck(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TCA);
	}

	public boolean isNeedRrdUpdate() {
		return getcheck(NumeroDatabaseHelper.TABLE_NAME_NUMERO_RRD);
	}

	public int getRemainNumberRRD() {
		return getNumber(NumeroDatabaseHelper.TABLE_NAME_NUMERO_RRD);

	}

	public int getRemainNumberAUTO() {
		return getNumber(NumeroDatabaseHelper.TABLE_NAME_NUMERO_AUTO);

	}

	public int getRemainNumberTCA() {
		return getNumber(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TCA);

	}

	public int getRemainNumberTAV() {
		return getNumber(NumeroDatabaseHelper.TABLE_NAME_NUMERO_TAV);

	}

	private int getNumber(String tableName) {
		Cursor myCursor = this.database.query(tableName, null, null, null,
				null, null, null);
		if (myCursor != null) {
			int number = myCursor.getCount();
			myCursor.close();
			return number;
		} else {
			return 0;
		}

	}

}

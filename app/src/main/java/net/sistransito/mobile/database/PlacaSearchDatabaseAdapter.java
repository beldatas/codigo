package net.sistransito.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import net.sistransito.mobile.placa.dados.PlacaRawFormat;
import net.sistransito.mobile.timeandime.TimeAndIme;
import net.sqlcipher.database.SQLiteDatabase;

public class PlacaSearchDatabaseAdapter {

    private TimeAndIme ime;
	private PlacaSearchDatabaseHelper placaSearchDatabaseHelper;
	private SQLiteDatabase database;

	public PlacaSearchDatabaseAdapter(Context context) {
		ime = new TimeAndIme(context);
		placaSearchDatabaseHelper = new PlacaSearchDatabaseHelper(context);
		database = placaSearchDatabaseHelper.getWritableDatabase(ime.getIME());
	}

	public boolean insertPlacaSearchData(PlacaRawFormat placaRawFormat) {
		long check_insert = -1;
		if (placaRawFormat != null) {

			if (!isSamePlaca(placaRawFormat.getPLATE())) {
				ContentValues values = new ContentValues();
				values.put(PlacaSearchDatabaseHelper.COLUMN_PLACA,
						placaRawFormat.getPLATE());
				values.put(PlacaSearchDatabaseHelper.COLUMN_MODELO,
						placaRawFormat.getMODEL());
				values.put(PlacaSearchDatabaseHelper.COLUMN_COR,
						placaRawFormat.getCOLOR());
				values.put(PlacaSearchDatabaseHelper.COLUMN_TIPO,
						placaRawFormat.getTYPE());
				values.put(PlacaSearchDatabaseHelper.COLUMN_ANO_LICENCIAMENTO,
						placaRawFormat.getLICENSE_YEAR());

				values.put(PlacaSearchDatabaseHelper.COLUMN_DATA_LICENCIAMENTO,
						placaRawFormat.getLICENSE_DATE());
				values.put(
						PlacaSearchDatabaseHelper.COLUMN_STATUS_LICENCIAMENTO,
						placaRawFormat.getLICENSE_STATUS());
				values.put(PlacaSearchDatabaseHelper.COLUMN_IPVA,
						placaRawFormat.getIPVA());
				values.put(PlacaSearchDatabaseHelper.COLUMN_SEGURO,
						placaRawFormat.getINSURANCE());
				values.put(PlacaSearchDatabaseHelper.COLUMN_INFRACOES,
						placaRawFormat.getINFRACTIONS());
				values.put(PlacaSearchDatabaseHelper.COLUMN_RESTRICOES,
						placaRawFormat.getRESTRICTIONS());
				values.put(PlacaSearchDatabaseHelper.COLUMN_DATE,
						placaRawFormat.getDATE());
				values.put(PlacaSearchDatabaseHelper.COLUMN_LATITUDE,
						placaRawFormat.getLATITUDE());
				values.put(PlacaSearchDatabaseHelper.COLUMN_LONGITUDE,
						placaRawFormat.getLONGITUDE());

				check_insert = database.insert(
						PlacaSearchDatabaseHelper.TABLE_NAME, null, values);
			}
		}

		return check_insert > 0;

	}

	public boolean deletePlacaData(String placaName) {
		return false;
	}

	public synchronized void close() {
		database.close();
	}

	public Cursor getPlacaCursor() {
		Cursor myCursor = database.query(PlacaSearchDatabaseHelper.TABLE_NAME,
				null, null, null, null, null,
				PlacaSearchDatabaseHelper.COLUMN_ID + " DESC");
		return myCursor;
	}

	public Cursor getPlacaCursor_2() {
		Cursor myCursor = database.query(PlacaSearchDatabaseHelper.TABLE_NAME,
				null, null, null, null, null,
				PlacaSearchDatabaseHelper.COLUMN_ID + " DESC");
		return myCursor;
	}

	public Cursor getPlacaCursorFromID(int id) {
		Cursor myCursor = this.database.query(
				PlacaSearchDatabaseHelper.TABLE_NAME, null,
				PlacaSearchDatabaseHelper.COLUMN_ID + "=?", new String[]{id
						+ ""}, null, null, null);
		return myCursor;
	}

	public boolean deleteDataFromIdField(String id) {
		return this.database
				.delete(PlacaSearchDatabaseHelper.TABLE_NAME,
						PlacaSearchDatabaseHelper.COLUMN_ID + "=?",
						new String[] { id }) > 0;
	}

	private boolean isSamePlaca(String placa) {
		Cursor myCursor = database.query(PlacaSearchDatabaseHelper.TABLE_NAME,
				null, PlacaSearchDatabaseHelper.COLUMN_PLACA + "=?",
				new String[] { placa }, null, null, null);
		if (myCursor.getCount() > 0) {
			myCursor.close();

			return true;
		} else {
			myCursor.close();
			return false;
		}
	}
}



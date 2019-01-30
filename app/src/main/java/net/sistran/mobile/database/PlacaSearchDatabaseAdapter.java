package net.sistran.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import net.sistran.mobile.placa.dados.DataFromPlate;
import net.sistran.mobile.timeandime.TimeAndIme;
//import net.sqlcipher.database.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;

public class PlacaSearchDatabaseAdapter {

    private TimeAndIme ime;
	private PlacaSearchDatabaseHelper placaSearchDatabaseHelper;
	private SQLiteDatabase database;

	public PlacaSearchDatabaseAdapter(Context context) {
		ime = new TimeAndIme(context);
		placaSearchDatabaseHelper = new PlacaSearchDatabaseHelper(context);
		//database = placaSearchDatabaseHelper.getWritableDatabase(ime.getIME());
		database = placaSearchDatabaseHelper.getWritableDatabase();
	}

	public boolean insertPlacaSearchData(DataFromPlate dataPlate) {
		long check_insert = -1;

		if (dataPlate != null) {

			if (!isSamePlaca(dataPlate.getPlate())) {

				ContentValues values = new ContentValues();
				values.put(PlacaSearchDatabaseHelper.COLUMN_PLACA,
						dataPlate.getPlate());
				values.put(PlacaSearchDatabaseHelper.COLUMN_UF,
						dataPlate.getUf());
				values.put(PlacaSearchDatabaseHelper.COLUMN_PAIS,
						dataPlate.getPais());
				values.put(PlacaSearchDatabaseHelper.COLUMN_CHASSI,
						dataPlate.getChassi());
				values.put(PlacaSearchDatabaseHelper.COLUMN_MARCA,
						dataPlate.getMarca());
				values.put(PlacaSearchDatabaseHelper.COLUMN_MODELO,
						dataPlate.getModel());
				values.put(PlacaSearchDatabaseHelper.COLUMN_COR,
						dataPlate.getColor());
				values.put(PlacaSearchDatabaseHelper.COLUMN_TIPO,
						dataPlate.getType());
				values.put(PlacaSearchDatabaseHelper.COLUMN_CATEGORIA,
						dataPlate.getCategory());
				values.put(PlacaSearchDatabaseHelper.COLUMN_ANO_LICENCIAMENTO,
						dataPlate.getLicenceYear());
				values.put(PlacaSearchDatabaseHelper.COLUMN_DATA_LICENCIAMENTO,
						dataPlate.getLicenceData());
				values.put(PlacaSearchDatabaseHelper.COLUMN_STATUS_LICENCIAMENTO,
						dataPlate.getLicenceStatus());
				values.put(PlacaSearchDatabaseHelper.COLUMN_IPVA,
						dataPlate.getIpva());
				values.put(PlacaSearchDatabaseHelper.COLUMN_SEGURO,
						dataPlate.getInsurance());
				values.put(PlacaSearchDatabaseHelper.COLUMN_STATUS,
						dataPlate.getStatus());
				values.put(PlacaSearchDatabaseHelper.COLUMN_INFRACOES,
						dataPlate.getInfractions());
				values.put(PlacaSearchDatabaseHelper.COLUMN_RESTRICOES,
						dataPlate.getRestrictions());
				values.put(PlacaSearchDatabaseHelper.COLUMN_DATE,
						dataPlate.getDate());
				values.put(PlacaSearchDatabaseHelper.COLUMN_LATITUDE,
						dataPlate.getLatitude());
				values.put(PlacaSearchDatabaseHelper.COLUMN_LONGITUDE,
						dataPlate.getLongitude());

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



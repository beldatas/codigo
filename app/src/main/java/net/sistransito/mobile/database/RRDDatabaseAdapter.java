package net.sistransito.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sistransito.mobile.rrd.RRDData;
import net.sistransito.mobile.timeandime.TimeAndIme;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class RRDDatabaseAdapter {
	private RRDDatabaseHelper rrdDBHelper;
	private Context context;
	private SQLiteDatabase db;
	private TimeAndIme ime;

	public RRDDatabaseAdapter(Context context) {
		this.context = context;
		rrdDBHelper = new RRDDatabaseHelper(this.context);
		ime = new TimeAndIme(context);
		openWriteableDatabase();
	}

	public void openWriteableDatabase() {
		/// ime.getIME() return the device ime number and this is the password ok
		db = rrdDBHelper.getWritableDatabase(ime.getIME());
	}

	public void openReadableDatabase() {
		db = rrdDBHelper.getReadableDatabase(ime.getIME());
	}

	public synchronized void close() {
		db.close();
	}

	public boolean insertRrdInformation(RRDData data) {
		ContentValues values = new ContentValues();
		values.put(RRDDatabaseHelper.N_AUTO_DE_INFRACAO,
				data.getN_auto_de_infracao());
		values.put(RRDDatabaseHelper.NUMERO_RRD, data.getNumero_rrd());
		values.put(RRDDatabaseHelper.NOME_DO_CONDUTOR,
				data.getNome_do_condutor());
		values.put(RRDDatabaseHelper.DOCUMENTO, data.getDocumento());
		values.put(RRDDatabaseHelper.N_CRLV, data.getN_crlv());
		values.put(RRDDatabaseHelper.PLACA, data.getPlaca());

		values.put(RRDDatabaseHelper.N_REGISTRO, data.getN_registro());
		values.put(RRDDatabaseHelper.VALIDADE, data.getValidade());

		values.put(RRDDatabaseHelper.UF, data.getUf());
		values.put(RRDDatabaseHelper.MOTIVO_PARA_RECOLHIMENTO,
				data.getMotivo_para_recolhimento());
		values.put(RRDDatabaseHelper.QTD_DE_DISA_PARA_REGULARIZACAO,
				data.getQtd_de_dias_para_regularizacao());

		values.put(RRDDatabaseHelper.NUMERO_RRD, data.getNumero_rrd());

		long insert = db.insert(RRDDatabaseHelper.TABLE_NAME, null, values);

		if (insert > 0) {
			(DatabaseCreator.getDatabaseAdapterNumero(context))
					.deleteRrdNumero(data.getNumero_rrd());

			(DatabaseCreator.getBalancoDatabaseAdapter(context))
					.setRrd_realizados();
			(DatabaseCreator.getBalancoDatabaseAdapter(context))
					.setRrd_restante((DatabaseCreator
							.getDatabaseAdapterNumero(context))
							.getRemainNumberRRD());

			return true;
		} else {
			return false;
		}
	}

	public Cursor getRRDCursor() {
		Cursor myCursor = db.query(RRDDatabaseHelper.TABLE_NAME, null, null,
				null, null, null, RRDDatabaseHelper.COLUMN_ID + " DESC");
		return myCursor;

	}

	public long deleteData(String tableName, String whereClause,
			String[] whereArgs) {
		return db.delete(tableName, whereClause, whereArgs);
	}

	public Cursor getRRDCursorFromID(int id) {
		Cursor myCursor = this.db.query(RRDDatabaseHelper.TABLE_NAME, null,
				RRDDatabaseHelper.COLUMN_ID + "=?", new String[] { id + "" },
				null, null, null);
		return myCursor;

	}

	public String rrdComposeJSONfromSQLite() {
		ArrayList<HashMap<String, String>> rrdList = new ArrayList<HashMap<String, String>>();
		Cursor cursor = this.db.query(RRDDatabaseHelper.TABLE_NAME, null, null,
				null, null, null, null);
		RRDData data;
		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			do {
				data = new RRDData();
				data.setRRDDataFromCursor(cursor);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(RRDDatabaseHelper.N_AUTO_DE_INFRACAO,
						data.getN_auto_de_infracao());
				map.put(RRDDatabaseHelper.NOME_DO_CONDUTOR,
						data.getNome_do_condutor());
				map.put(RRDDatabaseHelper.DOCUMENTO, data.getDocumento());
				map.put(RRDDatabaseHelper.N_CRLV, data.getN_crlv());
				map.put(RRDDatabaseHelper.N_REGISTRO, data.getN_registro());
				map.put(RRDDatabaseHelper.VALIDADE, data.getValidade());
				map.put(RRDDatabaseHelper.PLACA, data.getPlaca());
				map.put(RRDDatabaseHelper.UF, data.getUf());
				map.put(RRDDatabaseHelper.MOTIVO_PARA_RECOLHIMENTO,
						data.getMotivo_para_recolhimento());
				map.put(RRDDatabaseHelper.QTD_DE_DISA_PARA_REGULARIZACAO,
						data.getQtd_de_dias_para_regularizacao());
				map.put(RRDDatabaseHelper.NUMERO_RRD, data.getNumero_rrd());

				rrdList.add(map);
			} while (cursor.moveToNext());

		} else {

			return null;
		}
		cursor.close();
		Gson gson = new GsonBuilder().create();
		// Use GSON to serialize Array List to JSON
		return gson.toJson(rrdList);
	}

	public void rrdUpdateSyncStatus(String numero_rrd) {
		this.db.delete(RRDDatabaseHelper.TABLE_NAME,
				RRDDatabaseHelper.NUMERO_RRD + "=?",
				new String[] { numero_rrd });
	}
}
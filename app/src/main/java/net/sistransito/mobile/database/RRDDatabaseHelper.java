package net.sistransito.mobile.database;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

public class RRDDatabaseHelper extends SQLiteOpenHelper {
	public static final String DB_NAME_DATABASE_NAME = "database_rrd.db";
	public static final int VERSION = 1;
	public static final String TABLE_NAME = "rrd";
	public static final String COLUMN_ID = "_id";
	public static final String N_AUTO_DE_INFRACAO = "n_auto_de_in";// public
																	// static
																	// final
																	// String
																	// NUMERO_AUTO
																	// =
																	// "numero_auto";
	public static final String NOME_DO_CONDUTOR = "nome_do_Condutor";
	public static final String DOCUMENTO = "documento";
	public static final String N_CRLV = "n_crlv";
	public static final String N_REGISTRO = "n_registro";
	public static final String VALIDADE = "validade";
	public static final String PLACA = "placa";
	public static final String UF = "uf";
	public static final String MOTIVO_PARA_RECOLHIMENTO = "motivo_para_re";
	public static final String QTD_DE_DISA_PARA_REGULARIZACAO = "qtd_de_dias";
	public static final String NUMERO_RRD = "numero_rrd";

	public static final String RRD_TABLE_SQL = "CREATE TABLE " + TABLE_NAME
			+ " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NOME_DO_CONDUTOR + " TEXT, " + DOCUMENTO + " TEXT, " + N_CRLV
			+ " TEXT, " + N_REGISTRO + " TEXT, " + VALIDADE + " TEXT, " + PLACA
			+ " TEXT, " + UF + " TEXT, " + MOTIVO_PARA_RECOLHIMENTO + " TEXT, "
			+ N_AUTO_DE_INFRACAO + " TEXT, " + QTD_DE_DISA_PARA_REGULARIZACAO
			+ " TEXT," + NUMERO_RRD + " TEXT UNIQUE )";

	public RRDDatabaseHelper(Context context) {
		super(context, TABLE_NAME, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("TABLE SQL", RRD_TABLE_SQL);
		db.execSQL(RRD_TABLE_SQL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// UPGRADE LOGIC

	}
}

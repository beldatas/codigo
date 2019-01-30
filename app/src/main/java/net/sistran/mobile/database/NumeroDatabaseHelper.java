package net.sistran.mobile.database;

//import net.sqlcipher.database.SQLiteDatabase;
//import net.sqlcipher.database.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;

public class NumeroDatabaseHelper extends SQLiteOpenHelper {

	public NumeroDatabaseHelper(Context context) {
		super(context, DATABASE_NAME_USUARIO, null, DatabaseCreator.VERSION);
	}

	// DATABASE NAME
	public static final String DATABASE_NAME_USUARIO = "database_numero.db";
	// ID FILED
	public static final String COLUNM_ID = "_id";
	// TABLE NAME
	public static final String TABLE_NAME_NUMERO_AUTO = "tb_numero_auto";
	public static final String TABLE_NAME_NUMERO_TAV = "tb_numero_tav";
	public static final String TABLE_NAME_NUMERO_TCA = "tb_numero_tca";
	public static final String TABLE_NAME_NUMERO_RRD = "tb_numero_rrd";

	// FIELD
	public static final String NUMERO_AUTO = "numero_auto";
	public static final String NUMERO_TAV = "numero_tav";
	public static final String NUMERO_TCA = "numero_tca";
	public static final String NUMERO_RRD = "numero_rrd";
	

	public static final String TABLE_SQl_AUTO = "CREATE TABLE "
			+ TABLE_NAME_NUMERO_AUTO + " (" + COLUNM_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUMERO_AUTO  + " TEXT )";

	public static final String TABLE_SQl_TAV = "CREATE TABLE "
			+ TABLE_NAME_NUMERO_TAV + " (" + COLUNM_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUMERO_TAV + " TEXT )";
	public static final String TABLE_SQl_TCA = "CREATE TABLE "
			+ TABLE_NAME_NUMERO_TCA + " (" + COLUNM_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUMERO_TCA +  " TEXT )";
	public static final String TABLE_SQl_RRD = "CREATE TABLE "
			+ TABLE_NAME_NUMERO_RRD + " (" + COLUNM_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUMERO_RRD + " TEXT )";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_SQl_AUTO);
		db.execSQL(TABLE_SQl_TAV);
		db.execSQL(TABLE_SQl_TCA);
		db.execSQL(TABLE_SQl_RRD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}

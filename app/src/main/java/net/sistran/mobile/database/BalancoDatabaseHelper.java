package net.sistran.mobile.database;

//import net.sqlcipher.database.SQLiteDatabase;
//import net.sqlcipher.database.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

public class BalancoDatabaseHelper extends SQLiteOpenHelper {
	public static final String DB_NAME_FROME = "database_balanco.db";
	public static final int VERSION = 1;
	public static final String TABLE_NAME = "balanco";
	public static final String ID_FIELD = "_id";
	public static final String AUTO_REALIZADOS = "autos_realizados";
	public static final String TAV_REALIZADOS = "tav_realizados";
	public static final String TCA_REALIZADOS = "tca_realizados";
	public static final String RRD_REALIZADOS = "rrd_realizados";
	public static final String AUTO_RESTANTE = "autos_restante";
	public static final String TAV_RESTANTE = "tav_restante";
	public static final String TCA_RESTANTE = "tca_restante";
	public static final String RRD_RESTANTE = "rrd_restante";
	public static final String NUMERO_AUTO = "numero_auto";
	public static final String TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
			+ ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ AUTO_REALIZADOS + " INTEGER, " + TAV_REALIZADOS + " INTEGER, "
			+ TCA_REALIZADOS + " INTEGER, " + RRD_REALIZADOS + " INTEGER, "
			+ AUTO_RESTANTE + " INTEGER, " + TAV_RESTANTE + " INTEGER, "
			+ TCA_RESTANTE + " INTEGER, " + RRD_RESTANTE + " INTEGER )";

	public BalancoDatabaseHelper(Context context) {
		super(context, TABLE_NAME, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("TABLE SQL", TABLE_SQL);
		db.execSQL(TABLE_SQL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// UPGRADE LOGIC

	}
}

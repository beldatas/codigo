package net.sistran.mobile.database;

		import android.content.ContentValues;
		import android.content.Context;
		import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
		import android.database.sqlite.SQLiteDatabase;
		import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

		import net.sistran.mobile.equipamentos.AutoEquipmentEntry;

        import java.io.File;
		import java.io.FileOutputStream;
		import java.io.IOException;
		import java.io.InputStream;
		import java.io.OutputStream;
		import java.util.ArrayList;

public class PrepopulatedDBOpenHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "sitransitoctb.db";
	public static String DB_PATH;
	private SQLiteDatabase database;
	private Context context;

	// infracoes

	public static final String TABLE_NAME_INFRACOES = "infracoes";
	public static final String INFRACOES_ID = "_id";
	public static final String INFRACOES_DESCRICAO = "infracao";
	public static final String INFRACOES_ENQUADRA = "enquadramento";
	public static final String INFRACOES_DESDOB = "desdobramento";
	public static final String INFRACOES_ART = "artigo";
	public static final String INFRACOES_RESPONSAVEL = "responsavel";
	public static final String INFRACOES_PONTOS = "pontos";
	public static final String INFRACOES_GRAVIDADE = "gravidade";
	public static final String INFRACOES_VALOR = "valor";
	public static final String INFRACOES_COMPETENCIA = "competencia";
	public static final String INFRACOES_MEDIDA_ADM = "medida_adm";
	public static final String INFRACOES_OBSERVACAO = "observacao";
	public static final String INFRACOES_NOTAS = "notas";

	// municipios
	public static final String TABLE_NAME_MUNICIPIOS = "municipios";
	public static final String NOME_DO_MUNICIPIO = "municipio";
	public static final String MUNICIPIOS_COD = "cod";
	public static final String MUNICIPIOS_UF = "uf";

	// AutoEquipamentoEntry
	public static final String AutoEquipamentoEntry_TABLE_NAME = "auto_equipamento";
	public static final String AutoEquipamentoEntry_COLUMN_DESCRICAO = "descricao";
	public static final String AutoEquipamentoEntry_COLUMN_MARCA = "marca";
	public static final String AutoEquipamentoEntry_COLUMN_MODELO = "modelo";
	public static final String AutoEquipamentoEntry_COLUMN_VALIDADE = "validade";
	public static final String AutoEquipamentoEntry_COLUMN_NUMERO_SERIE = "numero_serie";

	public PrepopulatedDBOpenHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.context = context;

		/*super(context, Environment.getExternalStorageDirectory()
				+ "/SistransitoMobile/data/"
				+ DB_NAME, null, 1);
		this.context = context;

		DB_PATH = Environment.getExternalStorageDirectory()
				+ "/SistransitoMobile/data/";*/

		if(android.os.Build.VERSION.SDK_INT >= 4.2){
			DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
		} else {
			DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		}

		this.database = openDatabase();

	}

	public SQLiteDatabase getDatabase() {
		return this.database;
	}

	public SQLiteDatabase openDatabase() {

		String path = DB_PATH + DB_NAME;

		if (database == null) {
			createDatabase();
			database = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READWRITE);
		}

		return database;
	}

	private void createDatabase() {
		boolean dbExists = checkDB();
		if (!dbExists) {
			this.getReadableDatabase();
			Log.e(getClass().getName(), "Database doesn't exist. Copying database from assets...");
			copyDatabase();
		} else {
			Log.e(getClass().getName(), "Database already exists");
		}
	}

	private void copyDatabase() {
		try {
			InputStream dbInputStream = context.getAssets().open(DB_NAME);
			String path = DB_PATH + DB_NAME;
			OutputStream dbOutputStream = new FileOutputStream(path);
			byte[] buffer = new byte[4096];
			int readCount = 0;
			while ((readCount = dbInputStream.read(buffer)) > 0) {
				dbOutputStream.write(buffer, 0, readCount);
			}

			dbInputStream.close();
			dbOutputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean checkDB() {
		String path = DB_PATH + DB_NAME;
		File file = new File(path);
		if (file.exists()) {
			Log.e(getClass().getName(), "Database already exists");
			return true;
		}
		Log.e(getClass().getName(), "Database does not exists");
		return false;
	}

	public synchronized void close() {
		if (this.database != null) {
			this.database.close();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	// autocomplete infracao
	public Cursor getInfracaoCursor() {
		Cursor myCursor = this.database.query(TABLE_NAME_INFRACOES, null, null, null, null, null, null);
		myCursor.moveToFirst();
		return myCursor;
	}


	public Cursor getInfracaoCursor(int id) {
		Cursor myCursor = this.database.query(
				TABLE_NAME_INFRACOES, null,
				PrepopulatedDBOpenHelper.INFRACOES_ID + "=?", new String[]{id
						+ ""}, null, null, null);
		myCursor.moveToFirst();
		return myCursor;
	}

	public Cursor getInfracaoCursor(String texto) {
		Cursor myCursor = this.database.rawQuery("SELECT * FROM "
						+ TABLE_NAME_INFRACOES
						+ " where " + PrepopulatedDBOpenHelper.INFRACOES_DESCRICAO + " || "
						+ PrepopulatedDBOpenHelper.INFRACOES_ART
						+ " like '%" + texto + "%'"
				, null);
		myCursor.moveToFirst();
		return myCursor;
	}

	public String getNameMunicipioCursor(String id) {

		String nomeMunicipio = null;

		Cursor myCursor = this.database.rawQuery("SELECT * FROM "
						+ TABLE_NAME_MUNICIPIOS
						+ " where " + PrepopulatedDBOpenHelper.MUNICIPIOS_COD
						+ " like '%" + id + "%'"
				, null);

		if (myCursor.getCount() > 0) {
			myCursor.moveToFirst();

			nomeMunicipio = (myCursor.getString(myCursor
					.getColumnIndex(NOME_DO_MUNICIPIO)));

			myCursor.close();
		} else {
			myCursor.close();
			return null;
		}

		return nomeMunicipio;

	}

	public Cursor getMunicipioCursorUf(String uf) {

		Cursor myCursor = this.database.rawQuery("SELECT * FROM "
						+ TABLE_NAME_MUNICIPIOS
						+ " where " + PrepopulatedDBOpenHelper.MUNICIPIOS_UF
						+ " like '%" + uf + "%'"
				, null);
		myCursor.moveToFirst();
		return myCursor;
	}

	// autocomplete municipio
	public Cursor getMunicipioCursor() {
		Cursor myCursor = this.database.query(TABLE_NAME_MUNICIPIOS, null,
				null, null, null, null, null);
		myCursor.moveToFirst();
		return myCursor;
	}

	// autocomplete municipio
	public Cursor getUfGeralCursor() {
		Cursor myCursor = this.database.query(TABLE_NAME_INFRACOES, null,
				null, null, null, null, null);
		myCursor.moveToFirst();
		return myCursor;
	}

	// AutoEquipamentoEntry

	public Cursor getAutoEquipamentoEntryCursor() {
		Cursor myCursor = this.database.query(AutoEquipamentoEntry_TABLE_NAME,
				null, null, null, null, null, null);
		myCursor.moveToFirst();

		//Log.d("equipamentos", myCursor.getCount() + "v");
		return myCursor;
	}

	public void setAutoEquipamentoEntryCursor(

			ArrayList<AutoEquipmentEntry> entries) {

		this.database.delete(AutoEquipamentoEntry_TABLE_NAME, null, null);

		AutoEquipmentEntry equipmentEntry;

		if (entries != null) {

			for (int i = 0; i < entries.size(); i++) {
				ContentValues values = new ContentValues();
				equipmentEntry = entries.get(i);
				values.put(AutoEquipamentoEntry_COLUMN_DESCRICAO,
						equipmentEntry.getDescricaoEquipamento());
				values.put(AutoEquipamentoEntry_COLUMN_MARCA,
						equipmentEntry.getMarcaEquipamento());
				values.put(AutoEquipamentoEntry_COLUMN_MODELO,
						equipmentEntry.getModeloEquipamento());
				values.put(AutoEquipamentoEntry_COLUMN_VALIDADE,
						equipmentEntry.getValidadeEquipamento());
				values.put(AutoEquipamentoEntry_COLUMN_NUMERO_SERIE,
						equipmentEntry.getNumeroSerie());
				this.database.insert(AutoEquipamentoEntry_TABLE_NAME, null,
						values);

			}
		}
	}

}

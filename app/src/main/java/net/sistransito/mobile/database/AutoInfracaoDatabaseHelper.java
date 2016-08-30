package net.sistransito.mobile.database;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

public class AutoInfracaoDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME_AUTODE = "database_autode.db";
	public static final String TABLE_NAME = "auto_infracao";

	// condutor
	public static final String COLUMN_ID = "_id";
	public static final String PLACA = "placa";
	public static final String CHASSI = "chassi";
	public static final String MODELO_DO_VEICULO = "modelo_veiculo";
	public static final String PAIS = "pais";
	public static final String COR_DO_VEICULO = "cordoveiculo";
	public static final String ESPECIE = "especie";
	public static final String CATEGORIA = "categoria";
	public static final String NOME_DO_CONDUCTOR = "condutor";
	public static final String CNH_PPD = "cnh_pdd";
	public static final String UF_CNH = "uf_cnh";

	public static final String TIPO_DE_DOCUMENTO = "tipo_de_documento";

	public static final String NUMERO_DOCUMENTO = "numero_documento";

	// infracao
	public static final String INFRACAO = "infracao";
	public static final String ENQUADRA = "enquadra";
	public static final String DESDOB = "desdob";
	public static final String ART = "art";
	public static final String CODIGO_DO_MUNICIPIO = "codigo_muncipio";
	public static final String MUNICIPIO = "municipio";
	public static final String UF = "uf";
	public static final String LOCAL = "local";
	public static final String DATA = "data";
	public static final String HORA = "hora";
	public static final String DESCRICAO = "descricao_equipamento";
	public static final String MARCA = "marca_equipamento";
	public static final String MODELO = "modelo";
	public static final String NUMERO_DE_SERIE = "numero_de_serio";
	public static final String MEDICO_REALIZADA = "medicao_realizada";
	public static final String VALOR_CONSIDERADA = "valor_considerada";
	public static final String N_DA_AMOSTRA = "numero_amostra_teste";

	// gerar ,
	public static final String RECOLHIMENTO = "recolhimento";
	public static final String PROCEDIMENTOS = "procedimentos";
	public static final String OBSERVACAO = "observacao";
	public static final String IDENTIFICACAO_EMBARCADOR = "iden_embr";
	public static final String CNPJ_CPF_EMBARCADOR = "cnp_embracador";
	public static final String IDENTIFICACAO_DO_TRANSPORTADOR = "iden_trans";
	public static final String CNPJ_CPF_TRANSPORTADOR = "cnp_transportadfor";

	public static final String NUMERO_AUTO = "numero_auto";

	// public static final String IDETIFICACAO_EMBAACADOR = "iden_embr";

	// AUTO DE TABLE SQL
	public static final String TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PLACA
			+ " TEXT, " + CHASSI + " TEXT, " + PAIS + " TEXT, "
			+ MODELO_DO_VEICULO + " TEXT, " + COR_DO_VEICULO + " TEXT, "
			+ ESPECIE + " TEXT, " + CATEGORIA + " TEXT, " + NOME_DO_CONDUCTOR
			+ " TEXT, " + CNH_PPD + " TEXT, " + UF_CNH + " TEXT, "
			+ TIPO_DE_DOCUMENTO + " TEXT, " + NUMERO_DOCUMENTO + " TEXT, "
			+ INFRACAO + " TEXT, " + ENQUADRA + " TEXT, " + DESDOB + " TEXT, "
			+ ART + " TEXT, " + CODIGO_DO_MUNICIPIO + " TEXT, " + MUNICIPIO
			+ " TEXT, " + UF + " TEXT, " + LOCAL + " TEXT, " + DATA + " TEXT, "
			+ HORA + " TEXT, " + DESCRICAO + " TEXT, " + MARCA + " TEXT, "
			+ MODELO + " TEXT, " + NUMERO_DE_SERIE + " TEXT, "
			+ MEDICO_REALIZADA + " TEXT, " + VALOR_CONSIDERADA + " TEXT, "
			+ N_DA_AMOSTRA + " TEXT, " + RECOLHIMENTO + " TEXT, "
			+ PROCEDIMENTOS + " TEXT, " + OBSERVACAO + " TEXT, "
			+ IDENTIFICACAO_EMBARCADOR + " TEXT, "
			+ IDENTIFICACAO_DO_TRANSPORTADOR + " TEXT, " + CNPJ_CPF_EMBARCADOR
			+ " TEXT, " + CNPJ_CPF_TRANSPORTADOR + " TEXT, " + NUMERO_AUTO
			+ " TEXT UNIQUE )";

	public AutoInfracaoDatabaseHelper(Context context) {
		super(context, DATABASE_NAME_AUTODE, null, DatabaseCreator.VERSION);
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
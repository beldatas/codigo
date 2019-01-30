package net.sistran.mobile.database;

//import net.sqlcipher.database.SQLiteDatabase;
//import net.sqlcipher.database.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import static net.sistran.mobile.appconstantes.AppConstants.DATABASE_NAME;

public class AutoInfracaoDatabaseHelper extends SQLiteOpenHelper {

	//public static final String DATABASE_NAME_AUTO = "auto_database.db";
	public static final String TABLE_NAME = "auto_infracao";

	// condutor
	public static final String COLUMN_ID = "_id";
	public static final String NUMERO_AUTO = "ait";
	public static final String PLACA = "placa";
	public static final String UF_VEICULO = "uf_veiculo";
	public static final String CHASSI = "chassi";
	public static final String MARCA_DO_VEICULO = "marca_veiculo";
	public static final String MODELO_DO_VEICULO = "modelo_veiculo";
	public static final String PAIS = "pais";
	public static final String COR_DO_VEICULO = "cor_veiculo";
	public static final String ESPECIE = "especie";
	public static final String CATEGORIA = "categoria";
	public static final String NOME_DO_CONDUTOR = "condutor";
	public static final String CNH_PPD = "cnh_pdd";
	public static final String UF_CNH = "uf_cnh";
	public static final String TIPO_DE_DOCUMENTO = "tipo_documento";
	public static final String NUMERO_DOCUMENTO = "numero_documento";

	// infracao
	public static final String INFRACAO = "infracao";
	public static final String ENQUADRA = "enquadramento";
	public static final String DESDOB = "desdobramento";
	public static final String ART = "artigo";
	public static final String CODIGO_DO_MUNICIPIO = "codigo_muncipio";
	public static final String MUNICIPIO = "municipio";
	public static final String UF = "uf";
	public static final String LOCAL = "local";
	public static final String DATA = "data";
	public static final String HORA = "hora";
	public static final String DESCRICAO = "descricao_equipamento";
	public static final String MARCA = "marca_equipamento";
	public static final String MODELO = "modelo_equipamento";
	public static final String NUMERO_DE_SERIE = "numero_de_serie";
	public static final String MEDICAO_REALIZADA = "medicao_realizada";
	public static final String VALOR_REGULAMENTADO = "valor_regulamentado";
	public static final String VALOR_CONSIDERADA = "valor_considerada";
	public static final String NUMERO_AMOSTRA = "numero_amostra";
	public static final String NUMERO_TCA = "numero_tca";

	// gerar ,
	public static final String RECOLHIMENTO = "recolhimento";
	public static final String PROCEDIMENTOS = "procedimentos";
	public static final String OBSERVACAO = "observacao";
	public static final String IDENTIFICACAO_EMBARCADOR = "embarcador";
	public static final String CPF_EMBARCADOR = "cpf_embarcador";
	public static final String CNPJ_EMBARCADOR = "cnpj_embarcador";
	public static final String IDENTIFICACAO_DO_TRANSPORTADOR = "transportador";
	public static final String CPF_TRANSPORTADOR = "cpf_transportador";
	public static final String CNPJ_TRANSPORTADOR = "cnpj_transportador";
	public static final String STATUS_CANCELAMENTO = "status_cancelamento";
	public static final String MOTIVO_CANCELAMENTO = "motivo_cancelamento";
	public static final String STATUS_SINCRONIZACAO = "status_sincronizacao";
	public static final String LATITUDE_AUTO = "latitude";
	public static final String LONGITUDE_AUTO = "longitude";
	public static final String DATA_HORA_AUTO = "data_hora_ait";
	public static final String AUTO_FOTO1 = "foto1";
	public static final String AUTO_FOTO2 = "foto2";
	public static final String AUTO_FOTO3 = "foto3";
	public static final String AUTO_FOTO4 = "foto4";
	public static final String STATUS_CONCLUIDO = "status_concluido";

	// AUTO DE TABLE SQL

	public static final String TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NUMERO_AUTO + " TEXT UNIQUE, " + PLACA + " TEXT, " + UF_VEICULO + " TEXT, "
			+ CHASSI + " TEXT, " + PAIS + " TEXT, " + MARCA_DO_VEICULO + " TEXT, "
			+ MODELO_DO_VEICULO + " TEXT, " + COR_DO_VEICULO + " TEXT, "
			+ ESPECIE + " TEXT, " + CATEGORIA + " TEXT, " + NOME_DO_CONDUTOR
			+ " TEXT, " + CNH_PPD + " TEXT, " + UF_CNH + " TEXT, "
			+ TIPO_DE_DOCUMENTO + " TEXT, " + NUMERO_DOCUMENTO + " TEXT, "
			+ INFRACAO + " TEXT, " + ENQUADRA + " TEXT, " + DESDOB + " TEXT, "
			+ ART + " TEXT, " + NUMERO_TCA + " TEXT, " + CODIGO_DO_MUNICIPIO + " TEXT, "
			+ MUNICIPIO + " TEXT, " + UF + " TEXT, "
			+ LOCAL + " TEXT, " + DATA + " TEXT, "
			+ HORA + " TEXT, " + DESCRICAO + " TEXT, " + MARCA + " TEXT, "
			+ MODELO + " TEXT, " + NUMERO_DE_SERIE + " TEXT, "
			+ MEDICAO_REALIZADA + " TEXT, " + VALOR_REGULAMENTADO + " TEXT, " + VALOR_CONSIDERADA + " TEXT, "
			+ NUMERO_AMOSTRA + " TEXT, " + RECOLHIMENTO + " TEXT, "
			+ PROCEDIMENTOS + " TEXT, " + OBSERVACAO + " TEXT, "
			+ IDENTIFICACAO_EMBARCADOR + " TEXT, "
			+ CPF_EMBARCADOR + " TEXT, "
			+ CNPJ_EMBARCADOR + " TEXT, "
			+ IDENTIFICACAO_DO_TRANSPORTADOR + " TEXT, "
			+ CPF_TRANSPORTADOR + " TEXT,"
			+ CNPJ_TRANSPORTADOR + " TEXT,"
			+ STATUS_CANCELAMENTO + " TEXT, "
			+ MOTIVO_CANCELAMENTO + " TEXT, "
			+ STATUS_SINCRONIZACAO + " TEXT, "
			+ LATITUDE_AUTO + " TEXT, "
			+ LONGITUDE_AUTO + " TEXT, "
			+ DATA_HORA_AUTO + " TEXT, "
			+ AUTO_FOTO1 + " TEXT, "
			+ AUTO_FOTO2 + " TEXT, "
			+ AUTO_FOTO3 + " TEXT, "
			+ AUTO_FOTO4 + " TEXT, "
			+ STATUS_CONCLUIDO + " TEXT )";

	public AutoInfracaoDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DatabaseCreator.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Log.d("TABLE SQL", TABLE_SQL);
		db.execSQL(TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// UPGRADE LOGIC
	}

}
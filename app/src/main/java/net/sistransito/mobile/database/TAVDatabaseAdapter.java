package net.sistransito.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sistransito.mobile.tav.TAVData;
import net.sistransito.mobile.timeandime.TimeAndIme;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class TAVDatabaseAdapter {
	private TimeAndIme ime;
	private SQLiteDatabase database;
	private TAVDatabaseHelper databaseHelper;
	private Context context;

	public TAVDatabaseAdapter(Context context) {
		ime = new TimeAndIme(context);
		databaseHelper = new TAVDatabaseHelper(context);
		database = databaseHelper.getWritableDatabase(ime.getIME());
		this.context = context;
	}

	// public DadosDoAuto getAutodeDataFromPlaca(String placa) {
	//
	// Cursor myCursor = this.database.query(TAVDatabaseHelper.TABLE_NAME,
	// null, TAVDatabaseHelper.PLACA + "=?",
	// new String[] { placa + "" }, null, null, null);
	//
	// if (myCursor.getCount() > 0) {
	// DadosDoAuto autode_data = new DadosDoAuto();
	// myCursor.moveToFirst();
	// autode_data.setAutoDeDataFromCursor(myCursor);
	// myCursor.close();
	// return autode_data;
	// } else {
	// myCursor.close();
	// return null;
	// }
	// }

	// public boolean isSamePlacaExit(String placa) {
	// Cursor myCursor = this.database.query(
	// TAVDatabaseHelper.TABLE_NAME, null,
	// TAVDatabaseHelper.PLACA + "=?", new String[] { placa
	// + "" }, null, null, null);
	// if (myCursor.getCount() > 0) {
	// myCursor.close();
	// return true;
	// } else {
	// myCursor.close();
	// return false;
	// }
	// }

	public void close() {
		database.close();
	}

	public boolean setData(TAVData data) {
		ContentValues values = new ContentValues();

		values.put(TAVDatabaseHelper.PLACA, data.getPlaca());

		values.put(TAVDatabaseHelper.NUMERO_DO_AUTO, data.getNumero_do_auto());
		values.put(TAVDatabaseHelper.NOME_DO_PROPRIETARIO,
				data.getNome_do_proprietario());
		values.put(TAVDatabaseHelper.CPF_CNPJ, data.getCpf_cnpj());
		values.put(TAVDatabaseHelper.NUMERO_DO_RENAVAM,
				data.getNumero_do_renavam());
		values.put(TAVDatabaseHelper.NUMERO_DO_CHASSI,
				data.getNumero_do_chassi());

		// Ve�culo
		// Estrutur

		values.put(TAVDatabaseHelper.CABECA_DE_ALAVANCA,
				data.getCabeca_de_alavanca());
		values.put(TAVDatabaseHelper.CARROCERIA, data.getCarroceria());
		values.put(TAVDatabaseHelper.FORRO, data.getForro());
		values.put(TAVDatabaseHelper.LATARIA_CAPO, data.getLataria_capo());
		values.put(TAVDatabaseHelper.LATARIA_LADO_DIREITO,
				data.getLataria_lado_direito());
		values.put(TAVDatabaseHelper.LATARIA_LADO_ESQUERDO,
				data.getLataria_lado_esquerdo());
		values.put(TAVDatabaseHelper.LATARIA_TAPA_PORTA_MALA,
				data.getLataria_tapa_porta_mala());
		values.put(TAVDatabaseHelper.LATARIA_TETO, data.getLataria_teto());

		// public static final String MOTOR = "motor";
		// public static final String PAINEL = "painel";
		// public static final String PINTURA_CAPO = "pintura_capo";
		// public static final String PINTURA_LADO_DIREITO =
		// "pintura_lado_direito";
		// public static final String PINTURA_LADO_ESQUERDO =
		// "pintura_lado_esquerdo";
		// public static final String PINTURA_PORTA_MALA = "pintura_porta_mala";
		// public static final String PINTURA_TETO = "pintura_teto";
		// public static final String RADIADOR = "radiador";
		// public static final String VIDROS_LATERAIS = "vidros_laterais";
		// public static final String VIDRO_PARA_BRISA = "vidro_para_brisa";
		// public static final String VIDRO_TRASEIRO = "vidro_traseiro";

		values.put(TAVDatabaseHelper.MOTOR, data.getMotor());
		values.put(TAVDatabaseHelper.PAINEL, data.getPainel());
		values.put(TAVDatabaseHelper.PINTURA_CAPO, data.getPintura_capo());
		values.put(TAVDatabaseHelper.PINTURA_LADO_DIREITO,
				data.getPintura_lado_direito());
		values.put(TAVDatabaseHelper.PINTURA_LADO_ESQUERDO,
				data.getPintura_lado_esquerdo());
		values.put(TAVDatabaseHelper.PINTURA_PORTA_MALA,
				data.getPintura_porta_mala());
		values.put(TAVDatabaseHelper.PINTURA_TETO, data.getPintura_teto());
		values.put(TAVDatabaseHelper.RADIADOR, data.getRadiador());
		values.put(TAVDatabaseHelper.VIDROS_LATERAIS, data.getVidros_laterais());
		values.put(TAVDatabaseHelper.VIDRO_PARA_BRISA,
				data.getVidro_para_brisa());
		values.put(TAVDatabaseHelper.VIDRO_TRASEIRO, data.getVidro_traseiro());

		// Ve�culo
		// Acess�rios
		// public static final String ANTENA_DE_RADIO = "antena_de_radio";
		// public static final String BAGAGEIRO = "bagageiro";
		// public static final String BANCOS = "bancos";
		// public static final String BATERIA = "bateria";
		// public static final String CALOTA = "calota";
		values.put(TAVDatabaseHelper.ANTENA_DE_RADIO, data.getAntena_de_radio());
		values.put(TAVDatabaseHelper.BAGAGEIRO, data.getBagageiro());
		values.put(TAVDatabaseHelper.BANCOS, data.getBancos());
		values.put(TAVDatabaseHelper.BATERIA, data.getBateria());
		values.put(TAVDatabaseHelper.CALOTA, data.getCalota());

		// public static final String CONDICIONADOR_DE_AR =
		// "condicionador_de_ar";
		// public static final String EXTINTOR_DE_INCENDIO =
		// "extintor_de_incendio";
		// public static final String FAROLETE_DIANTEIRO = "farolete_dianteiro";
		// public static final String FAROLETE_TRASEIRO = "farolete_traseiro";
		// public static final String MACACO = "macaco";

		values.put(TAVDatabaseHelper.CONDICIONADOR_DE_AR,
				data.getCondicionador_de_ar());
		values.put(TAVDatabaseHelper.EXTINTOR_DE_INCENDIO,
				data.getExtintor_de_incendio());
		values.put(TAVDatabaseHelper.FAROLETE_DIANTEIRO,
				data.getFarolete_dianteiro());
		values.put(TAVDatabaseHelper.FAROLETE_TRASEIRO,
				data.getFarolete_traseiro());
		values.put(TAVDatabaseHelper.MACACO, data.getMotor());

		// public static final String PARA_CHOQUE_DIANTEIRO =
		// "para_choque_dianteiro";
		// public static final String PARA_CHOQUE_TRASEIRO =
		// "para_choque_traseiro";
		// public static final String PARA_SOL_DO_CONDUTOR =
		// "para_sol_do_condutor";
		// public static final String PNEUS = "pneus";
		// public static final String PNEUS_ESTEPE = "pneus_estepe";

		values.put(TAVDatabaseHelper.PARA_CHOQUE_DIANTEIRO,
				data.getPara_choque_dianteiro());
		values.put(TAVDatabaseHelper.PARA_CHOQUE_TRASEIRO,
				data.getPara_choque_traseiro());
		values.put(TAVDatabaseHelper.PARA_SOL_DO_CONDUTOR,
				data.getPara_sol_do_condutor());
		values.put(TAVDatabaseHelper.PNEUS, data.getPneus());
		values.put(TAVDatabaseHelper.PNEUS_ESTEPE, data.getPneus_estepe());

		// public static final String RADIO = "radio";
		// public static final String RETROVISOR_INTERNO = "retrovisor_interno";
		// public static final String RETROVISOR_EXTERNO_DIREITO =
		// "retrovisor_externo_direito";
		// public static final String TAPETE = "tapete";
		// public static final String TRIANGULO = "triangulo";
		// public static final String VOLANTE = "volante";
		// public static final String GUIDAM = "guidam";

		values.put(TAVDatabaseHelper.RADIO, data.getRadio());
		values.put(TAVDatabaseHelper.RETROVISOR_INTERNO,
				data.getRetrovisor_interno());
		values.put(TAVDatabaseHelper.RETROVISOR_EXTERNO_DIREITO,
				data.getRetrovisor_externo_direito());
		values.put(TAVDatabaseHelper.TAPETE, data.getTapete());
		values.put(TAVDatabaseHelper.TRIANGULO, data.getTriangulo());
		values.put(TAVDatabaseHelper.VOLANTE, data.getVolante());
		values.put(TAVDatabaseHelper.GUIDAM, data.getGuidam());

		// // Geral
		// public static final String ODOMETRO = "odometro";
		// public static final String MARCADOR_DE_CONBUTIVEL =
		// "marcador_de_conbutivel";
		// public static final String REMOCAO_ATRAVES_DE = "remocao_atraves_de";
		// public static final String OBSERVACAO = "observacao";
		//
		// public static final String NOME_DA_EMPRESA = "nome_da_empresa";
		// public static final String NOME_DO_CONDUTOR_DO_GUINCHO =
		// "nome_condutor_guincho";

		values.put(TAVDatabaseHelper.ODOMETRO, data.getOdometro());
		values.put(TAVDatabaseHelper.MARCADOR_DE_CONBUTIVEL,
				data.getMarcador_de_combustivel());
		values.put(TAVDatabaseHelper.REMOCAO_ATRAVES_DE,
				data.getRemocao_atraves_de());
		values.put(TAVDatabaseHelper.OBSERVACAO, data.getObservacao());
		values.put(TAVDatabaseHelper.NOME_DA_EMPRESA, data.getNome_da_empresa());
		values.put(TAVDatabaseHelper.NOME_DO_CONDUTOR_DO_GUINCHO,
				data.getNome_do_condutor_do_guincho());

		values.put(TAVDatabaseHelper.NUMERO_TAV, data.getNumero_tav());

		long insert = this.database.insert(TAVDatabaseHelper.TABLE_NAME, null,
				values);

		if (insert > 0) {
			(DatabaseCreator.getDatabaseAdapterNumero(context))
					.deleteTavNumero(data.getNumero_tav());

			(DatabaseCreator.getBalancoDatabaseAdapter(context))
					.setTav_realizados();
			(DatabaseCreator.getBalancoDatabaseAdapter(context))
					.setTav_restante((DatabaseCreator
							.getDatabaseAdapterNumero(context))
							.getRemainNumberTAV());

			return true;
		} else {
			return false;
		}
	}

	public Cursor getTAVCursor() {
		Cursor myCursor = this.database.query(TAVDatabaseHelper.TABLE_NAME,
				null, null, null, null, null, TAVDatabaseHelper.COLUMN_ID
						+ " DESC");
		return myCursor;

	}

	public Cursor getTAVCursorFromID(int id) {
		Cursor myCursor = this.database.query(TAVDatabaseHelper.TABLE_NAME,
				null, TAVDatabaseHelper.COLUMN_ID + "=?", new String[] { id
						+ "" }, null, null, null);
		return myCursor;
	}

	public String tavComposeJSONfromSQLite() {
		ArrayList<HashMap<String, String>> tavList = new ArrayList<HashMap<String, String>>();
		Cursor cursor = this.database.query(TAVDatabaseHelper.TABLE_NAME, null,
				null, null, null, null, null);
		TAVData data;
		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			do {
				data = new TAVData();
				data.setTAVDataFromCursor(cursor);
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				Log.d("ppp", data.getPlaca());
				map.put(TAVDatabaseHelper.PLACA, data.getPlaca());
				// Condutor
				map.put(TAVDatabaseHelper.NUMERO_DO_AUTO,
						data.getNumero_do_auto());
				map.put(TAVDatabaseHelper.NOME_DO_PROPRIETARIO,
						data.getNome_do_proprietario());
				map.put(TAVDatabaseHelper.CPF_CNPJ, data.getCpf_cnpj());
				map.put(TAVDatabaseHelper.NUMERO_DO_RENAVAM,
						data.getNumero_do_renavam());
				map.put(TAVDatabaseHelper.NUMERO_DO_CHASSI,
						data.getNumero_do_chassi());
				// Ve�culo
				// Estrutur
				map.put(TAVDatabaseHelper.CABECA_DE_ALAVANCA,
						data.getCabeca_de_alavanca());
				map.put(TAVDatabaseHelper.CARROCERIA, data.getCarroceria());
				map.put(TAVDatabaseHelper.FORRO, data.getForro());
				map.put(TAVDatabaseHelper.LATARIA_CAPO, data.getLataria_capo());
				map.put(TAVDatabaseHelper.LATARIA_LADO_DIREITO,
						data.getLataria_lado_direito());
				map.put(TAVDatabaseHelper.LATARIA_LADO_ESQUERDO,
						data.getLataria_lado_esquerdo());
				map.put(TAVDatabaseHelper.LATARIA_TAPA_PORTA_MALA,
						data.getLataria_tapa_porta_mala());
				map.put(TAVDatabaseHelper.LATARIA_TETO, data.getLataria_teto());
				map.put(TAVDatabaseHelper.MOTOR, data.getMotor());
				map.put(TAVDatabaseHelper.PAINEL, data.getPainel());
				map.put(TAVDatabaseHelper.PINTURA_CAPO, data.getPintura_capo());
				map.put(TAVDatabaseHelper.PINTURA_LADO_DIREITO,
						data.getPintura_lado_direito());
				map.put(TAVDatabaseHelper.PINTURA_LADO_ESQUERDO,
						data.getPintura_lado_esquerdo());
				map.put(TAVDatabaseHelper.PINTURA_PORTA_MALA,
						data.getPintura_porta_mala());
				map.put(TAVDatabaseHelper.PINTURA_TETO, data.getPintura_teto());
				map.put(TAVDatabaseHelper.RADIADOR, data.getRadiador());
				map.put(TAVDatabaseHelper.VIDROS_LATERAIS,
						data.getVidros_laterais());
				map.put(TAVDatabaseHelper.VIDRO_PARA_BRISA,
						data.getVidro_para_brisa());
				map.put(TAVDatabaseHelper.VIDRO_TRASEIRO,
						data.getVidro_traseiro());

				// Ve�culo
				// Acess�rios

				map.put(TAVDatabaseHelper.ANTENA_DE_RADIO,
						data.getAntena_de_radio());
				map.put(TAVDatabaseHelper.BAGAGEIRO, data.getBancos());
				map.put(TAVDatabaseHelper.BANCOS, data.getBancos());
				map.put(TAVDatabaseHelper.BATERIA, data.getBateria());
				map.put(TAVDatabaseHelper.CALOTA, data.getCalota());
				map.put(TAVDatabaseHelper.CONDICIONADOR_DE_AR,
						data.getCondicionador_de_ar());
				map.put(TAVDatabaseHelper.EXTINTOR_DE_INCENDIO,
						data.getExtintor_de_incendio());
				map.put(TAVDatabaseHelper.FAROLETE_DIANTEIRO,
						data.getFarolete_dianteiro());
				map.put(TAVDatabaseHelper.FAROLETE_TRASEIRO,
						data.getFarolete_traseiro());
				map.put(TAVDatabaseHelper.MACACO, data.getMacaco());
				map.put(TAVDatabaseHelper.PARA_CHOQUE_DIANTEIRO,
						data.getPara_choque_dianteiro());
				map.put(TAVDatabaseHelper.PARA_CHOQUE_TRASEIRO,
						data.getPara_choque_traseiro());
				map.put(TAVDatabaseHelper.PARA_SOL_DO_CONDUTOR,
						data.getPara_sol_do_condutor());
				map.put(TAVDatabaseHelper.PNEUS, data.getPneus());
				map.put(TAVDatabaseHelper.PNEUS_ESTEPE, data.getPneus_estepe());
				map.put(TAVDatabaseHelper.RADIO, data.getRadio());
				map.put(TAVDatabaseHelper.RETROVISOR_INTERNO,
						data.getRetrovisor_interno());
				map.put(TAVDatabaseHelper.RETROVISOR_EXTERNO_DIREITO,
						data.getRetrovisor_externo_direito());
				map.put(TAVDatabaseHelper.TAPETE, data.getTapete());
				map.put(TAVDatabaseHelper.TRIANGULO, data.getTriangulo());
				map.put(TAVDatabaseHelper.VOLANTE, data.getVolante());
				map.put(TAVDatabaseHelper.GUIDAM, data.getGuidam());

				// Geral
				map.put(TAVDatabaseHelper.ODOMETRO, data.getOdometro());
				map.put(TAVDatabaseHelper.MARCADOR_DE_CONBUTIVEL,
						data.getMarcador_de_combustivel());
				map.put(TAVDatabaseHelper.REMOCAO_ATRAVES_DE,
						data.getRemocao_atraves_de());
				map.put(TAVDatabaseHelper.OBSERVACAO, data.getObservacao());
				map.put(TAVDatabaseHelper.NOME_DA_EMPRESA,
						data.getNome_da_empresa());
				map.put(TAVDatabaseHelper.NOME_DO_CONDUTOR_DO_GUINCHO,
						data.getNome_do_condutor_do_guincho());
				map.put(TAVDatabaseHelper.NUMERO_TAV, data.getNumero_tav());
	
				tavList.add(map);
			} while (cursor.moveToNext());
		} else {

			return null;
		}
		cursor.close();
		Gson gson = new GsonBuilder().create();
		// Use GSON to serialize Array List to JSON
		return gson.toJson(tavList);
	}

	public void TavUpdateSyncStatus(String numero_tav) {
		this.database.delete(TAVDatabaseHelper.TABLE_NAME,
				TAVDatabaseHelper.NUMERO_TAV + "=?",
				new String[] { numero_tav });
	}
}

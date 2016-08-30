package net.sistransito.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sistransito.mobile.tca.TcaData;
import net.sistransito.mobile.timeandime.TimeAndIme;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class TCADatabaseAdapter {
	private TimeAndIme ime;
	private SQLiteDatabase database;
	private TCADatabaseHelper databaseHelper;
	private Context context;

	public TCADatabaseAdapter(Context context) {
		ime = new TimeAndIme(context);
		databaseHelper = new TCADatabaseHelper(context);
		database = databaseHelper.getWritableDatabase(ime.getIME());
		this.context = context;
	}

	public void close() {
		database.close();
	}

	public boolean setData(TcaData data) {
		ContentValues values = new ContentValues();
		values.put(TCADatabaseHelper.NOME_DO_CONDUTOR,
				data.getNome_do_condutor());
		values.put(TCADatabaseHelper.CNH_PPD, data.getCnh_ppd());
		values.put(TCADatabaseHelper.CPF, data.getCpf());
		values.put(TCADatabaseHelper.ENDERECO, data.getEndereco());
		values.put(TCADatabaseHelper.BAIRRO, data.getBairro());
		values.put(TCADatabaseHelper.MUNICIPIO, data.getMunicipio());
		values.put(TCADatabaseHelper.MUNICIPIO_UF, data.getMunicipio_uf());
		values.put(TCADatabaseHelper.PLACA, data.getPlaca());
		values.put(TCADatabaseHelper.PLACA_UF, data.getPlaca_uf());
		values.put(TCADatabaseHelper.MARCA_MODELO, data.getMarca_modelo());
		values.put(
				TCADatabaseHelper.CONDUTOR_ENVOLVEU_SE_EM_ACIDENTE_DE_TRANSITO,
				data.getCondutor_envolveu_se_em_acidente_de_transito());
		values.put(

		TCADatabaseHelper.CONDUTOR_DECLARA_TER_INGERIDO_BEBIDA_ALCOOLICA,
				data.getCondutor_declara_ter_ingerido_bebida_alcoolica());

		values.put(TCADatabaseHelper.DATA_INGERIU_ALCOOL,
				data.getData_ingeriu_alcool());
		values.put(TCADatabaseHelper.HORA_INGERIU_ALCOOL,
				data.getHora_ingeriu_alcool());

		values.put(

		TCADatabaseHelper.CONDUTOR_DECLARA_TER_FEITO_USO_DE_SUBSTANCIA_TOXICA,
				data.getCondutor_declara_ter_feito_uso_de_substancia_toxica());

		values.put(TCADatabaseHelper.DATA_INGERIU_SUBSTANCIA,
				data.getData_ingeriu_substancia());
		values.put(TCADatabaseHelper.HORA_INGERIU_SUBSTANCIA,
				data.getHora_ingeriu_substancia());

		values.put(TCADatabaseHelper.O_CONDUTOR_APRESENTA_SINAIS_DE,
				data.getO_condutor_apresenta_sinais_de());
		values.put(TCADatabaseHelper.EM_SUA_ATITUDE_OCORRE,
				data.getEm_sua_atitude_ocorre());
		values.put(TCADatabaseHelper.SABE_ONDE_ESTA, data.getSabe_onde_esta());
		values.put(TCADatabaseHelper.SABE_A_DATA_E_A_HORA,
				data.getSabe_a_data_e_a_hora());
		values.put(TCADatabaseHelper.SABE_SEU_ENDERECO,
				data.getSabe_seu_endereco());
		values.put(TCADatabaseHelper.LEMBRA_DOS_ATOS_COMETIDOS,
				data.getLembra_dos_atos_cometidos());
		values.put(

		TCADatabaseHelper.EM_RELACO_A_SUA_CAPACIDADE_MOTORA_E_VERBAL_OCORRE,
				data.getEm_relaco_a_sua_capacidade_motora_e_verbal_ocorre());
		values.put(TCADatabaseHelper.CONCLUSAO, data.getConclusao());

		values.put(TCADatabaseHelper.NUMERO_TCA, data.getNumero_tca());
		values.put(TCADatabaseHelper.NUMERO_AUTO, data.getNumero_auto());

		long insert = this.database.insert(TCADatabaseHelper.TABLE_NAME, null,
				values);
		if (insert > 0) {
			(DatabaseCreator.getDatabaseAdapterNumero(context))
					.deleteTcaNumero(data.getNumero_tca());

			(DatabaseCreator.getBalancoDatabaseAdapter(context))
					.setTca_realizados();
			(DatabaseCreator.getBalancoDatabaseAdapter(context))
					.setTca_restante((DatabaseCreator
							.getDatabaseAdapterNumero(context))
							.getRemainNumberTCA());

			return true;
		} else {
			return false;
		}

	}

	public Cursor getTCACursor() {
		Cursor myCursor = this.database.query(TCADatabaseHelper.TABLE_NAME,
				null, null, null, null, null, TCADatabaseHelper.COLUMN_ID
						+ " DESC");
		return myCursor;

	}

	public Cursor getTCACursorFromID(int id) {
		Cursor myCursor = this.database.query(TCADatabaseHelper.TABLE_NAME,
				null, TCADatabaseHelper.COLUMN_ID + "=?", new String[] { id
						+ "" }, null, null, null);
		return myCursor;
	}

	public String tcaComposeJSONfromSQLite() {
		ArrayList<HashMap<String, String>> tcaList = new ArrayList<HashMap<String, String>>();
		Cursor cursor = this.database.query(TCADatabaseHelper.TABLE_NAME, null,
				null, null, null, null, null);
		TcaData data;
		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			do {
				data = new TcaData();
				data.setTCADataFromCursor(cursor);
				HashMap<String, String> map = new HashMap<String, String>();

				// Condutor/Ve�culo
				map.put(TCADatabaseHelper.NOME_DO_CONDUTOR,
						data.getNome_do_condutor());
				map.put(TCADatabaseHelper.CNH_PPD, data.getCnh_ppd());
				map.put(TCADatabaseHelper.CPF, data.getCpf());
				map.put(TCADatabaseHelper.ENDERECO, data.getEndereco());
				map.put(TCADatabaseHelper.BAIRRO, data.getBairro());
				map.put(TCADatabaseHelper.MUNICIPIO, data.getMunicipio());
				map.put(TCADatabaseHelper.MUNICIPIO_UF, data.getMunicipio_uf());
				map.put(TCADatabaseHelper.PLACA, data.getPlaca());
				map.put(TCADatabaseHelper.PLACA_UF, data.getPlaca_uf());
				map.put(TCADatabaseHelper.MARCA_MODELO, data.getMarca_modelo());

				// Question�rio

				map.put(TCADatabaseHelper.CONDUTOR_ENVOLVEU_SE_EM_ACIDENTE_DE_TRANSITO,
						data.getCondutor_envolveu_se_em_acidente_de_transito());
				map.put(TCADatabaseHelper.CONDUTOR_DECLARA_TER_INGERIDO_BEBIDA_ALCOOLICA,
						data.getCondutor_declara_ter_ingerido_bebida_alcoolica());

				map.put(TCADatabaseHelper.DATA_INGERIU_ALCOOL,
						data.getData_ingeriu_alcool());
				map.put(TCADatabaseHelper.HORA_INGERIU_ALCOOL,
						data.getHora_ingeriu_alcool());

				map.put(TCADatabaseHelper.CONDUTOR_DECLARA_TER_FEITO_USO_DE_SUBSTANCIA_TOXICA,
						data.getCondutor_declara_ter_feito_uso_de_substancia_toxica());

				map.put(TCADatabaseHelper.DATA_INGERIU_SUBSTANCIA,
						data.getData_ingeriu_substancia());
				map.put(TCADatabaseHelper.HORA_INGERIU_SUBSTANCIA,
						data.getHora_ingeriu_substancia());

				map.put(TCADatabaseHelper.O_CONDUTOR_APRESENTA_SINAIS_DE,
						data.getO_condutor_apresenta_sinais_de());
				map.put(TCADatabaseHelper.EM_SUA_ATITUDE_OCORRE,
						data.getEm_sua_atitude_ocorre());
				map.put(TCADatabaseHelper.SABE_ONDE_ESTA,
						data.getSabe_onde_esta());
				map.put(TCADatabaseHelper.SABE_A_DATA_E_A_HORA,
						data.getSabe_a_data_e_a_hora());
				map.put(TCADatabaseHelper.SABE_SEU_ENDERECO,
						data.getSabe_seu_endereco());
				map.put(TCADatabaseHelper.LEMBRA_DOS_ATOS_COMETIDOS,
						data.getLembra_dos_atos_cometidos());
				map.put(TCADatabaseHelper.EM_RELACO_A_SUA_CAPACIDADE_MOTORA_E_VERBAL_OCORRE,
						data.getEm_relaco_a_sua_capacidade_motora_e_verbal_ocorre());
				map.put(TCADatabaseHelper.CONCLUSAO, data.getConclusao());
				map.put(TCADatabaseHelper.NUMERO_TCA, data.getNumero_tca());
				map.put(TCADatabaseHelper.NUMERO_AUTO, data.getNumero_auto());

				tcaList.add(map);
			} while (cursor.moveToNext());

		} else {

			return null;
		}
		cursor.close();
		Gson gson = new GsonBuilder().create();
		// Use GSON to serialize Array List to JSON
		return gson.toJson(tcaList);
	}

	public void tcaUpdateSyncStatus(String numero_tca) {
		this.database.delete(TCADatabaseHelper.TABLE_NAME,
				TCADatabaseHelper.NUMERO_TCA + "=?",
				new String[] { numero_tca });
	}
}

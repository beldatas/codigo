package net.sistransito.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.timeandime.TimeAndIme;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AutoInfracaoDatabaseAdapter {
	private TimeAndIme ime;
	private SQLiteDatabase database;
	private AutoInfracaoDatabaseHelper databaseHelper;
	private Context context;

	public AutoInfracaoDatabaseAdapter(Context context) {
		ime = new TimeAndIme(context);
		databaseHelper = new AutoInfracaoDatabaseHelper(context);
		database = databaseHelper.getReadableDatabase(ime.getIME());
		this.context = context;
	}

	public DadosDoAuto getAutodeDataFromPlaca(String placa) {

		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null,
				AutoInfracaoDatabaseHelper.PLACA + "=?", new String[] { placa
						+ "" }, null, null, null);

		if (myCursor.getCount() > 0) {
			DadosDoAuto autode_data = new DadosDoAuto();
			myCursor.moveToFirst();
			autode_data.setAutoDeDataFromCursor(myCursor);
			myCursor.close();
			return autode_data;
		} else {
			myCursor.close();
			return null;
		}
	}

	public boolean isSamePlacaExit(String placa) {
		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null,
				AutoInfracaoDatabaseHelper.PLACA + "=?", new String[] { placa
						+ "" }, null, null, null);
		if (myCursor.getCount() > 0) {
			myCursor.close();
			return true;
		} else {
			myCursor.close();
			return false;
		}
	}

	public void close() {
		database.close();
	}

	public boolean setData(DadosDoAuto data) {
		ContentValues values = new ContentValues();
		values.put(AutoInfracaoDatabaseHelper.PLACA, data.getPlate());
		values.put(AutoInfracaoDatabaseHelper.CHASSI, data.getChassi());

		values.put(AutoInfracaoDatabaseHelper.PAIS, data.getPais());

		values.put(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO,
				data.getModel());
		values.put(AutoInfracaoDatabaseHelper.COR_DO_VEICULO,
				data.getCor_do_veiculo());
		values.put(AutoInfracaoDatabaseHelper.ESPECIE, data.getEspecie());
		values.put(AutoInfracaoDatabaseHelper.CATEGORIA, data.getCategoria());
		values.put(AutoInfracaoDatabaseHelper.NOME_DO_CONDUCTOR,
				data.getNome_do_Condutor());
		values.put(AutoInfracaoDatabaseHelper.CNH_PPD, data.getCnh_ppd());
		values.put(AutoInfracaoDatabaseHelper.UF_CNH, data.getUf_cnh());

		values.put(AutoInfracaoDatabaseHelper.TIPO_DE_DOCUMENTO,
				data.getTipo_de_documento());

		values.put(AutoInfracaoDatabaseHelper.NUMERO_DOCUMENTO,
				data.getNumero_documento());

		values.put(AutoInfracaoDatabaseHelper.INFRACAO, data.getInfracao());
		values.put(AutoInfracaoDatabaseHelper.ENQUADRA, data.getEnquadra());
		values.put(AutoInfracaoDatabaseHelper.DESDOB, data.getDesdob());
		values.put(AutoInfracaoDatabaseHelper.ART, data.getAmparo_legal());
		values.put(AutoInfracaoDatabaseHelper.CODIGO_DO_MUNICIPIO,
				data.getCodigo_do_municipio());
		values.put(AutoInfracaoDatabaseHelper.MUNICIPIO, data.getMunicipio());
		values.put(AutoInfracaoDatabaseHelper.UF, data.getUf());
		values.put(AutoInfracaoDatabaseHelper.LOCAL, data.getLocal());
		values.put(AutoInfracaoDatabaseHelper.DATA, data.getData());
		values.put(AutoInfracaoDatabaseHelper.HORA, data.getHora());
		values.put(AutoInfracaoDatabaseHelper.DESCRICAO, data.getDescricao());
		values.put(AutoInfracaoDatabaseHelper.MARCA,
				data.getMarca());
		values.put(AutoInfracaoDatabaseHelper.MODELO,
				data.getModelo());
		values.put(AutoInfracaoDatabaseHelper.NUMERO_DE_SERIE,
				data.getNumero_de_serie());
		values.put(AutoInfracaoDatabaseHelper.MEDICAO_REALIZADA,
				data.getMedicao_realizada());
		values.put(AutoInfracaoDatabaseHelper.VALOR_CONSIDERADA,
				data.getValor_considerada());
		values.put(AutoInfracaoDatabaseHelper.N_DA_AMOSTRA,
				data.getN_da_amostra());
		values.put(AutoInfracaoDatabaseHelper.RECOLHIMENTO,
				data.getRecolhimento());
		values.put(AutoInfracaoDatabaseHelper.PROCEDIMENTOS,
				data.getProcedimentos());
		values.put(AutoInfracaoDatabaseHelper.OBSERVACAO,
				data.getObservacao());
		values.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_EMBARCADOR,
				data.getIdetificacao_embarcador());
		values.put(AutoInfracaoDatabaseHelper.CNPJ_CPF_EMBARCADOR,
				data.getCnpj_cpf_embarcador());
		values.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_DO_TRANSPORTADOR,
				data.getIdentificacao_do_transportador());
		values.put(AutoInfracaoDatabaseHelper.CNPJ_CPF_TRANSPORTADOR,
				data.getCnpj_cpf_transportador());
		values.put(AutoInfracaoDatabaseHelper.NUMERO_AUTO,
				data.getNumero_auto());

		long insert = this.database.insert(AutoInfracaoDatabaseHelper.TABLE_NAME, null, values);

		if (insert > 0) {
			(DatabaseCreator.getDatabaseAdapterNumero(context))
					.deleteAuotNumero(data.getNumero_auto());
			(DatabaseCreator.getBalancoDatabaseAdapter(context))
					.setAutos_realizados();
			(DatabaseCreator.getBalancoDatabaseAdapter(context))
					.setAutos_restante((DatabaseCreator
							.getDatabaseAdapterNumero(context))
							.getRemainNumberAUTO());
			return true;
		} else {
			return false;
		}

	}

	public Cursor getAutoDeCursor() {
		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null, null, null, null,
				null, AutoInfracaoDatabaseHelper.COLUMN_ID + " DESC");
		return myCursor;

	}

	public Cursor getAutoDeCursorFromID(int id) {
		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null,
				AutoInfracaoDatabaseHelper.COLUMN_ID + "=?", new String[] { id
						+ "" }, null, null, null);
		return myCursor;

	}

	public DadosDoAuto getAutoDeDataFromAutoDeNumero(String auto_de_numero) {
		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null,
				AutoInfracaoDatabaseHelper.NUMERO_AUTO + "=?",
				new String[] { auto_de_numero + "" }, null, null, null);

		if (myCursor != null && myCursor.moveToFirst()) {
			DadosDoAuto dadosDoAuto = new DadosDoAuto();
			dadosDoAuto.setAutoDeDataFromCursor(myCursor);
			myCursor.close();
			return dadosDoAuto;
		}
		return null;

	}

	public String autoDeComposeJSONfromSQLite() {
		ArrayList<HashMap<String, String>> autoList = new ArrayList<HashMap<String, String>>();
		Cursor cursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null, null, null, null,
				null, null);
		DadosDoAuto data;

		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			do {
				data = new DadosDoAuto();
				data.setAutoDeDataFromCursor(cursor);
				HashMap<String, String> map = new HashMap<String, String>();

				// conductor

				map.put(AutoInfracaoDatabaseHelper.PLACA, data.getPlate());

				map.put(AutoInfracaoDatabaseHelper.CHASSI, data.getChassi());

				map.put(AutoInfracaoDatabaseHelper.PAIS, data.getPais());

				map.put(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO,
						data.getModel());
				map.put(AutoInfracaoDatabaseHelper.COR_DO_VEICULO,
						data.getCor_do_veiculo());
				map.put(AutoInfracaoDatabaseHelper.ESPECIE, data.getEspecie());
				map.put(AutoInfracaoDatabaseHelper.CATEGORIA,
						data.getCategoria());
				map.put(AutoInfracaoDatabaseHelper.NOME_DO_CONDUCTOR,
						data.getNome_do_Condutor());
				map.put(AutoInfracaoDatabaseHelper.CNH_PPD, data.getCnh_ppd());
				map.put(AutoInfracaoDatabaseHelper.UF_CNH, data.getUf_cnh());
				map.put(AutoInfracaoDatabaseHelper.TIPO_DE_DOCUMENTO,
						data.getTipo_de_documento());
				map.put(AutoInfracaoDatabaseHelper.NUMERO_DOCUMENTO,
						data.getNumero_documento());

				// infracao

				map.put(AutoInfracaoDatabaseHelper.INFRACAO, data.getInfracao());
				map.put(AutoInfracaoDatabaseHelper.ENQUADRA, data.getEnquadra());
				map.put(AutoInfracaoDatabaseHelper.DESDOB, data.getDesdob());
				map.put(AutoInfracaoDatabaseHelper.ART, data.getAmparo_legal());
				map.put(AutoInfracaoDatabaseHelper.CODIGO_DO_MUNICIPIO,
						data.getCodigo_do_municipio());
				map.put(AutoInfracaoDatabaseHelper.MUNICIPIO,
						data.getMunicipio());
				map.put(AutoInfracaoDatabaseHelper.UF, data.getUf());
				map.put(AutoInfracaoDatabaseHelper.LOCAL, data.getLocal());
				map.put(AutoInfracaoDatabaseHelper.DATA, data.getData());
				map.put(AutoInfracaoDatabaseHelper.HORA, data.getHora());

				map.put(AutoInfracaoDatabaseHelper.DESCRICAO,
						data.getDescricao());
				map.put(AutoInfracaoDatabaseHelper.MARCA, data.getMarca());
				map.put(AutoInfracaoDatabaseHelper.MODELO, data.getModelo());
				map.put(AutoInfracaoDatabaseHelper.NUMERO_DE_SERIE,
						data.getNumero_de_serie());
				map.put(AutoInfracaoDatabaseHelper.MEDICAO_REALIZADA,
						data.getMedicao_realizada());
				map.put(AutoInfracaoDatabaseHelper.VALOR_CONSIDERADA,
						data.getValor_considerada());
				map.put(AutoInfracaoDatabaseHelper.N_DA_AMOSTRA,
						data.getN_da_amostra());

				// gerar
				map.put(AutoInfracaoDatabaseHelper.RECOLHIMENTO,
						data.getRecolhimento());
				map.put(AutoInfracaoDatabaseHelper.PROCEDIMENTOS,
						data.getProcedimentos());
				map.put(AutoInfracaoDatabaseHelper.OBSERVACAO,
						data.getObservacao());
				map.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_EMBARCADOR,
						data.getIdetificacao_embarcador());
				map.put(AutoInfracaoDatabaseHelper.CNPJ_CPF_EMBARCADOR,
						data.getCnpj_cpf_embarcador());

				map.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_DO_TRANSPORTADOR,
						data.getIdentificacao_do_transportador());
				map.put(AutoInfracaoDatabaseHelper.CNPJ_CPF_TRANSPORTADOR,
						data.getCnpj_cpf_transportador());
				map.put(AutoInfracaoDatabaseHelper.NUMERO_AUTO,
						data.getNumero_auto());

				autoList.add(map);
			} while (cursor.moveToNext());
		} else {
			return null;
		}
		cursor.close();
		Gson gson = new GsonBuilder().create();
		// Use GSON to serialize Array List to JSON
		return gson.toJson(autoList);
	}

	public void autoDeUpdateSyncStatus(String numero_rrd) {
		this.database.delete(AutoInfracaoDatabaseHelper.TABLE_NAME,
				AutoInfracaoDatabaseHelper.NUMERO_AUTO + "=?",
				new String[] { numero_rrd });
	}
}

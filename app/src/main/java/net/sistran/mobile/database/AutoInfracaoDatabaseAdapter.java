package net.sistran.mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sistran.mobile.autoinfracao.DadosAuto;
import net.sistran.mobile.database.sync.SyncFiles;
import net.sistran.mobile.timeandime.TimeAndIme;
//import net.sqlcipher.database.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoInfracaoDatabaseAdapter {
	private TimeAndIme ime;
	private SQLiteDatabase database;
	private AutoInfracaoDatabaseHelper databaseHelper;
	private Context context;
	String foto;

	public AutoInfracaoDatabaseAdapter(Context context) {
		ime = new TimeAndIme(context);
		databaseHelper = new AutoInfracaoDatabaseHelper(context);
		//database = databaseHelper.getReadableDatabase(ime.getIME());
		database = databaseHelper.getReadableDatabase();
		this.context = context;
	}

	public DadosAuto getAutodeDataFromPlaca(String placa) {

		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null,
				AutoInfracaoDatabaseHelper.PLACA + "=?", new String[] { placa
						+ "" }, null, null, null);

		if (myCursor.getCount() > 0) {
			DadosAuto dadosAuto = new DadosAuto();
			myCursor.moveToFirst();
			dadosAuto.setAutoDataFromCursor(myCursor);
			myCursor.close();
			return dadosAuto;
		} else {
			myCursor.close();
			return null;
		}
	}

	public boolean isSamePlacaExist(String placa) {
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

	public boolean setDadosAuto(DadosAuto data) {
		ContentValues values = new ContentValues();
		values.put(AutoInfracaoDatabaseHelper.NUMERO_AUTO,
				data.getNumero_auto());
		values.put(AutoInfracaoDatabaseHelper.PLACA, data.getPlaca());
		values.put(AutoInfracaoDatabaseHelper.UF_VEICULO, data.getUf_veiculo());
		values.put(AutoInfracaoDatabaseHelper.CHASSI, data.getChassi());
		values.put(AutoInfracaoDatabaseHelper.PAIS, data.getPais());
		values.put(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO,
				data.getModelo_veiculo());
		values.put(AutoInfracaoDatabaseHelper.COR_DO_VEICULO,
				data.getCor_do_veiculo());
		values.put(AutoInfracaoDatabaseHelper.ESPECIE, data.getEspecie());
		values.put(AutoInfracaoDatabaseHelper.CATEGORIA, data.getCategoria());
		values.put(AutoInfracaoDatabaseHelper.NOME_DO_CONDUTOR,
				data.getNomeCondutor());
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
				data.getMarca_veiculo());
		values.put(AutoInfracaoDatabaseHelper.MODELO,
				data.getModelo_veiculo());
		values.put(AutoInfracaoDatabaseHelper.NUMERO_DE_SERIE,
				data.getNumeroDeSerie());
		values.put(AutoInfracaoDatabaseHelper.MEDICAO_REALIZADA,
				data.getMedicao_realizada());
		values.put(AutoInfracaoDatabaseHelper.VALOR_REGULAMENTADO,
				data.getValorRegulamentado());
		values.put(AutoInfracaoDatabaseHelper.VALOR_CONSIDERADA,
				data.getValor_considerada());
		values.put(AutoInfracaoDatabaseHelper.NUMERO_AMOSTRA,
				data.getNumeroAmostra());
		values.put(AutoInfracaoDatabaseHelper.RECOLHIMENTO,
				data.getRecolhimento());
		values.put(AutoInfracaoDatabaseHelper.PROCEDIMENTOS,
				data.getProcedimentos());
		values.put(AutoInfracaoDatabaseHelper.OBSERVACAO,
				data.getObservacao());
		values.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_EMBARCADOR,
				data.getIdetificacao_embarcador());
		values.put(AutoInfracaoDatabaseHelper.CPF_EMBARCADOR,
				data.getCpfEmbarcador());
		values.put(AutoInfracaoDatabaseHelper.CNPJ_EMBARCADOR,
				data.getCnpjEmbarcador());
		values.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_DO_TRANSPORTADOR,
				data.getIdentificacao_do_transportador());
		values.put(AutoInfracaoDatabaseHelper.CPF_TRANSPORTADOR,
				data.getCpfTransportador());
		values.put(AutoInfracaoDatabaseHelper.CNPJ_TRANSPORTADOR,
				data.getCnpjTransportador());
		values.put(AutoInfracaoDatabaseHelper.STATUS_CANCELAMENTO,
				data.getStatusCancelameto());
		values.put(AutoInfracaoDatabaseHelper.MOTIVO_CANCELAMENTO,
				data.getMotivoCancelamento());
		values.put(AutoInfracaoDatabaseHelper.STATUS_SINCRONIZACAO,
				data.getStatusSincronizacao());

		long insert = this.database.insert(AutoInfracaoDatabaseHelper.TABLE_NAME, null, values);

		if (insert > 0) {
			(DatabaseCreator.getDatabaseAdapterNumero(context))
					.deleteNumeroAuto(data.getNumero_auto());
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

	//Inseri o ID do auto sempre que se iniciar um novo AIT
	public boolean insertNumeroAuto(DadosAuto data){

		ContentValues values = new ContentValues();
		values.put(AutoInfracaoDatabaseHelper.NUMERO_AUTO,
				data.getNumero_auto());

		long insert = this.database.insert(AutoInfracaoDatabaseHelper.TABLE_NAME, null, values);

		if (insert > 0) {
			(DatabaseCreator.getDatabaseAdapterNumero(context))
					.deleteNumeroAuto(data.getNumero_auto());
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

	public boolean setCancelarAuto(DadosAuto data, String motivo) {

		ContentValues values = new ContentValues();

		values.put(AutoInfracaoDatabaseHelper.STATUS_CANCELAMENTO, "1");
		values.put(AutoInfracaoDatabaseHelper.MOTIVO_CANCELAMENTO, motivo);

		int update = this.database.update(AutoInfracaoDatabaseHelper.TABLE_NAME,
				values, AutoInfracaoDatabaseHelper.NUMERO_AUTO + "= ? ",
				new String[] { data.getNumero_auto() });

		Log.d("setCancelamento", update + "-" + motivo + " | numero Ait: " + data.getNumero_auto());

		if (update > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean setDadosAutoVeiculo(DadosAuto dadosAuto) {

		ContentValues values = new ContentValues();

		values.put(AutoInfracaoDatabaseHelper.PLACA, dadosAuto.getPlaca());
		values.put(AutoInfracaoDatabaseHelper.UF_VEICULO, dadosAuto.getUf_veiculo());
		values.put(AutoInfracaoDatabaseHelper.CHASSI, dadosAuto.getChassi());
		values.put(AutoInfracaoDatabaseHelper.PAIS, dadosAuto.getPais());
		values.put(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO,
				dadosAuto.getModelo_veiculo());
		values.put(AutoInfracaoDatabaseHelper.COR_DO_VEICULO,
				dadosAuto.getCor_do_veiculo());
		values.put(AutoInfracaoDatabaseHelper.ESPECIE, dadosAuto.getEspecie());
		values.put(AutoInfracaoDatabaseHelper.CATEGORIA, dadosAuto.getCategoria());
		values.put(AutoInfracaoDatabaseHelper.STATUS_CANCELAMENTO, "0");
		values.put(AutoInfracaoDatabaseHelper.MOTIVO_CANCELAMENTO, "");
		values.put(AutoInfracaoDatabaseHelper.STATUS_SINCRONIZACAO, "0");
		values.put(AutoInfracaoDatabaseHelper.LATITUDE_AUTO, dadosAuto.getLatitudeAuto());
		values.put(AutoInfracaoDatabaseHelper.LONGITUDE_AUTO, dadosAuto.getLongitudeAuto());
		values.put(AutoInfracaoDatabaseHelper.DATA_HORA_AUTO, dadosAuto.getDataHoraAuto());
		values.put(AutoInfracaoDatabaseHelper.STATUS_CONCLUIDO, "0");

		int update = this.database.update(AutoInfracaoDatabaseHelper.TABLE_NAME,
				values, AutoInfracaoDatabaseHelper.NUMERO_AUTO + "= ? ",
				new String[] { dadosAuto.getNumero_auto() });

		Log.d("setVeiculo", update + "-" + dadosAuto);

		if (update > 0) {
			(DatabaseCreator.getDatabaseAdapterNumero(context))
					.deleteNumeroAuto(dadosAuto.getNumero_auto());
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

	public boolean setDadosAutoCondutor(DadosAuto data) {

		ContentValues values = new ContentValues();

		values.put(AutoInfracaoDatabaseHelper.NOME_DO_CONDUTOR, data.getNomeCondutor());
		values.put(AutoInfracaoDatabaseHelper.CNH_PPD, data.getCnh_ppd());
		values.put(AutoInfracaoDatabaseHelper.UF_CNH, data.getUf_cnh());
		values.put(AutoInfracaoDatabaseHelper.TIPO_DE_DOCUMENTO, data.getTipo_de_documento());
		values.put(AutoInfracaoDatabaseHelper.NUMERO_DOCUMENTO,
				data.getNumero_documento());

		int update = this.database.update(AutoInfracaoDatabaseHelper.TABLE_NAME,
				values, AutoInfracaoDatabaseHelper.NUMERO_AUTO + "= ? ",
				new String[] { data.getNumero_auto() });

		Log.d("setCondutor", update + "-" + data);

		if (update > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean setDadosAutoEndereco(DadosAuto data) {

		ContentValues values = new ContentValues();

		values.put(AutoInfracaoDatabaseHelper.LOCAL, data.getLocal());
		values.put(AutoInfracaoDatabaseHelper.DATA, data.getData());
		values.put(AutoInfracaoDatabaseHelper.HORA, data.getHora());
		values.put(AutoInfracaoDatabaseHelper.CODIGO_DO_MUNICIPIO, data.getCodigo_do_municipio());
		values.put(AutoInfracaoDatabaseHelper.MUNICIPIO, data.getMunicipio());
		values.put(AutoInfracaoDatabaseHelper.UF, data.getUf());

		int update = this.database.update(AutoInfracaoDatabaseHelper.TABLE_NAME,
				values, AutoInfracaoDatabaseHelper.NUMERO_AUTO + "= ? ",
				new String[] { data.getNumero_auto() });

		Log.d("setEndereço", update + "-" + data);

		if (update > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean setDadosAutoInfracao(DadosAuto data) {

		ContentValues values = new ContentValues();

		values.put(AutoInfracaoDatabaseHelper.INFRACAO, data.getInfracao());
		values.put(AutoInfracaoDatabaseHelper.ENQUADRA, data.getEnquadra());
		values.put(AutoInfracaoDatabaseHelper.DESDOB, data.getDesdob());
		values.put(AutoInfracaoDatabaseHelper.ART, data.getAmparo_legal());
		values.put(AutoInfracaoDatabaseHelper.NUMERO_TCA, data.getNumeroTca());
		values.put(AutoInfracaoDatabaseHelper.DESCRICAO, data.getDescricao());
		values.put(AutoInfracaoDatabaseHelper.MARCA, data.getMarcaDoEquipamento());
		values.put(AutoInfracaoDatabaseHelper.MODELO, data.getModeloDoEquipamento());
		values.put(AutoInfracaoDatabaseHelper.NUMERO_DE_SERIE, data.getNumeroDeSerie());
		values.put(AutoInfracaoDatabaseHelper.MEDICAO_REALIZADA, data.getMedicao_realizada());
		values.put(AutoInfracaoDatabaseHelper.VALOR_REGULAMENTADO, data.getValorRegulamentado());
		values.put(AutoInfracaoDatabaseHelper.VALOR_CONSIDERADA, data.getValor_considerada());
		values.put(AutoInfracaoDatabaseHelper.NUMERO_AMOSTRA, data.getNumeroAmostra());
		values.put(AutoInfracaoDatabaseHelper.RECOLHIMENTO, data.getRecolhimento());
		values.put(AutoInfracaoDatabaseHelper.PROCEDIMENTOS, data.getProcedimentos());
		values.put(AutoInfracaoDatabaseHelper.OBSERVACAO, data.getObservacao());
		values.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_EMBARCADOR, data.getIdetificacao_embarcador());
		values.put(AutoInfracaoDatabaseHelper.CPF_EMBARCADOR, data.getCpfEmbarcador());
		values.put(AutoInfracaoDatabaseHelper.CNPJ_EMBARCADOR, data.getCnpjEmbarcador());
		values.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_DO_TRANSPORTADOR, data.getIdentificacao_do_transportador());
		values.put(AutoInfracaoDatabaseHelper.CPF_TRANSPORTADOR, data.getCpfTransportador());
		values.put(AutoInfracaoDatabaseHelper.CNPJ_TRANSPORTADOR, data.getCnpjTransportador());
		values.put(AutoInfracaoDatabaseHelper.STATUS_CONCLUIDO, "1");

		int update = this.database.update(AutoInfracaoDatabaseHelper.TABLE_NAME,
				values, AutoInfracaoDatabaseHelper.NUMERO_AUTO + "= ? ",
				new String[] { data.getNumero_auto() });

		Log.d("setInfracao", update + "-" + data);

		if (update > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean setDadosAutoFotos(DadosAuto dadosAuto) {

		ContentValues values = new ContentValues();

		values.put(AutoInfracaoDatabaseHelper.AUTO_FOTO1, dadosAuto.getFoto1());
		values.put(AutoInfracaoDatabaseHelper.AUTO_FOTO2, dadosAuto.getFoto2());
		values.put(AutoInfracaoDatabaseHelper.AUTO_FOTO3, dadosAuto.getFoto3());
		values.put(AutoInfracaoDatabaseHelper.AUTO_FOTO4, dadosAuto.getFoto4());

		int update = this.database.update(AutoInfracaoDatabaseHelper.TABLE_NAME,
				values, AutoInfracaoDatabaseHelper.NUMERO_AUTO + "= ? ",
				new String[] { dadosAuto.getNumero_auto() });

		Log.d("setFotos", update + "-" + dadosAuto);

		if (update > 0) {
			SyncFiles sync = new SyncFiles(context);
			sync.uploadImage(dadosAuto.getNumero_auto());
			return true;
		} else {
			return false;
		}

	}

	public Cursor getAutoCursor() {
		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null, null, null, null,
				null, AutoInfracaoDatabaseHelper.COLUMN_ID + " DESC");
		return myCursor;

	}

	public Cursor getAutoAtivoCursor() {
		Cursor myCursor = this.database.query(AutoInfracaoDatabaseHelper.TABLE_NAME, null,
				AutoInfracaoDatabaseHelper.STATUS_CONCLUIDO + "=?",
						new String[] { "1" }, null,
						null, AutoInfracaoDatabaseHelper.COLUMN_ID + " DESC");
		return myCursor;

	}

	public Cursor getAutoCursorFromID(int id) {
		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null,
				AutoInfracaoDatabaseHelper.COLUMN_ID + "=?", new String[] { id
						+ "" }, null, null, null);
		return myCursor;

	}

	public DadosAuto getDataFromNumeroAuto(String numeroAit) {
		Cursor myCursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null,
				AutoInfracaoDatabaseHelper.NUMERO_AUTO + "=?",
				new String[] { numeroAit + "" }, null, null, null);

		if (myCursor != null && myCursor.moveToFirst()) {
			DadosAuto dadosAuto = new DadosAuto();
			dadosAuto.setAutoDataFromCursor(myCursor);
			myCursor.close();
			return dadosAuto;
		}
		return null;

	}

	public String autoComposeJSONfromSQLite_() {
		ArrayList<HashMap<String, String>> arrayListaAuto = new ArrayList<HashMap<String, String>>();
		Cursor cursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null, null, null, null,
				null, null);
		DadosAuto dadosAuto;

		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			do {
				dadosAuto = new DadosAuto();
				dadosAuto.setAutoDataFromCursor(cursor);
				HashMap<String, String> map = new HashMap<String, String>();

				// condutor
				map.put(AutoInfracaoDatabaseHelper.NUMERO_AUTO,
						dadosAuto.getNumero_auto());
				map.put(AutoInfracaoDatabaseHelper.PLACA, dadosAuto.getPlaca());
				map.put(AutoInfracaoDatabaseHelper.UF_VEICULO, dadosAuto.getUf_veiculo());
				map.put(AutoInfracaoDatabaseHelper.CHASSI, dadosAuto.getChassi());
				map.put(AutoInfracaoDatabaseHelper.PAIS, dadosAuto.getPais());
				map.put(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO,
						dadosAuto.getModelo_veiculo());
				map.put(AutoInfracaoDatabaseHelper.COR_DO_VEICULO,
						dadosAuto.getCor_do_veiculo());
				map.put(AutoInfracaoDatabaseHelper.ESPECIE, dadosAuto.getEspecie());
				map.put(AutoInfracaoDatabaseHelper.CATEGORIA,
						dadosAuto.getCategoria());
				map.put(AutoInfracaoDatabaseHelper.NOME_DO_CONDUTOR,
						dadosAuto.getNomeCondutor());
				map.put(AutoInfracaoDatabaseHelper.CNH_PPD, dadosAuto.getCnh_ppd());
				map.put(AutoInfracaoDatabaseHelper.UF_CNH, dadosAuto.getUf_cnh());
				map.put(AutoInfracaoDatabaseHelper.TIPO_DE_DOCUMENTO,
						dadosAuto.getTipo_de_documento());
				map.put(AutoInfracaoDatabaseHelper.NUMERO_DOCUMENTO,
						dadosAuto.getNumero_documento());

				// infracao

				map.put(AutoInfracaoDatabaseHelper.INFRACAO, dadosAuto.getInfracao());
				map.put(AutoInfracaoDatabaseHelper.ENQUADRA, dadosAuto.getEnquadra());
				map.put(AutoInfracaoDatabaseHelper.DESDOB, dadosAuto.getDesdob());
				map.put(AutoInfracaoDatabaseHelper.ART, dadosAuto.getAmparo_legal());
				map.put(AutoInfracaoDatabaseHelper.CODIGO_DO_MUNICIPIO,
						dadosAuto.getCodigo_do_municipio());
				map.put(AutoInfracaoDatabaseHelper.MUNICIPIO,
						dadosAuto.getMunicipio());
				map.put(AutoInfracaoDatabaseHelper.UF, dadosAuto.getUf());
				map.put(AutoInfracaoDatabaseHelper.LOCAL, dadosAuto.getLocal());
				map.put(AutoInfracaoDatabaseHelper.DATA, dadosAuto.getData());
				map.put(AutoInfracaoDatabaseHelper.HORA, dadosAuto.getHora());

				map.put(AutoInfracaoDatabaseHelper.DESCRICAO,
						dadosAuto.getDescricao());
				map.put(AutoInfracaoDatabaseHelper.MARCA, dadosAuto.getMarca_veiculo());
				map.put(AutoInfracaoDatabaseHelper.MODELO, dadosAuto.getModelo_veiculo());
				map.put(AutoInfracaoDatabaseHelper.NUMERO_DE_SERIE,
						dadosAuto.getNumeroDeSerie());
				map.put(AutoInfracaoDatabaseHelper.MEDICAO_REALIZADA,
						dadosAuto.getMedicao_realizada());
				map.put(AutoInfracaoDatabaseHelper.VALOR_CONSIDERADA,
						dadosAuto.getValor_considerada());
				map.put(AutoInfracaoDatabaseHelper.NUMERO_AMOSTRA,
						dadosAuto.getNumeroAmostra());

				// gerar
				map.put(AutoInfracaoDatabaseHelper.RECOLHIMENTO,
						dadosAuto.getRecolhimento());
				map.put(AutoInfracaoDatabaseHelper.PROCEDIMENTOS,
						dadosAuto.getProcedimentos());
				map.put(AutoInfracaoDatabaseHelper.OBSERVACAO,
						dadosAuto.getObservacao());
				map.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_EMBARCADOR,
						dadosAuto.getIdetificacao_embarcador());
				map.put(AutoInfracaoDatabaseHelper.CPF_EMBARCADOR,
						dadosAuto.getCpfEmbarcador());
				map.put(AutoInfracaoDatabaseHelper.CNPJ_EMBARCADOR,
						dadosAuto.getCnpjEmbarcador());
				map.put(AutoInfracaoDatabaseHelper.IDENTIFICACAO_DO_TRANSPORTADOR,
						dadosAuto.getIdentificacao_do_transportador());
				map.put(AutoInfracaoDatabaseHelper.CPF_TRANSPORTADOR,
						dadosAuto.getCpfTransportador());
				map.put(AutoInfracaoDatabaseHelper.CNPJ_TRANSPORTADOR,
						dadosAuto.getCnpjTransportador());
				map.put(AutoInfracaoDatabaseHelper.STATUS_CANCELAMENTO,
						dadosAuto.getStatusCancelameto());
				map.put(AutoInfracaoDatabaseHelper.MOTIVO_CANCELAMENTO,
						dadosAuto.getMotivoCancelamento());
				map.put(AutoInfracaoDatabaseHelper.AUTO_FOTO1,
						dadosAuto.getFoto1());
				//compressBitmapToFile(destination);
				map.put(AutoInfracaoDatabaseHelper.AUTO_FOTO2,
						dadosAuto.getFoto2());
				map.put(AutoInfracaoDatabaseHelper.AUTO_FOTO3,
						dadosAuto.getFoto3());
				map.put(AutoInfracaoDatabaseHelper.AUTO_FOTO4,
						dadosAuto.getFoto4());
				map.put(AutoInfracaoDatabaseHelper.STATUS_SINCRONIZACAO,
						dadosAuto.getStatusSincronizacao());

				arrayListaAuto.add(map);
			} while (cursor.moveToNext());
		} else {
			return null;
		}
		cursor.close();
		Gson gson = new GsonBuilder().create();
		// Use GSON to serialize Array List to JSON
		return gson.toJson(arrayListaAuto);
	}

	private String getStringImage(Bitmap bmp) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] imageBytes = baos.toByteArray();
		return Base64.encodeToString(imageBytes, Base64.DEFAULT);
	}

	public String autoComposeJSONfromSQLite() {

		ArrayList<HashMap<String, String>> arrayListaAuto = new ArrayList<HashMap<String, String>>();

		Cursor cursor = this.database.query(
				AutoInfracaoDatabaseHelper.TABLE_NAME, null, null, null, null,
				null, null);
		DadosAuto dadosAuto;

		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			do {
				dadosAuto = new DadosAuto();
				dadosAuto.setAutoDataFromCursor(cursor);
				HashMap<String, String> map = new HashMap<String, String>();

				if(dadosAuto.getFoto1() != null){
					Bitmap bitmap = BitmapFactory.decodeFile(dadosAuto.getFoto1());
					foto = getStringImage(bitmap);
				}

				Log.d("ArrayImagem: ", foto);

				//map.put(AutoInfracaoDatabaseHelper.AUTO_FOTO1, foto);
                map.put(AutoInfracaoDatabaseHelper.NUMERO_AUTO,
                        dadosAuto.getNumero_auto());
                map.put(AutoInfracaoDatabaseHelper.PLACA, dadosAuto.getPlaca());

				//FileInputStream inputStream = new FileInputStream(file);
				//compressBitmapToFile(destination);

				arrayListaAuto.add(map);
			} while (cursor.moveToNext());
		} else {
			return null;
		}
		cursor.close();

		Gson gson = new GsonBuilder().create();
		return gson.toJson(arrayListaAuto);
	}

	public void autoUpdateSyncStatus(String ait) {
		this.database.delete(AutoInfracaoDatabaseHelper.TABLE_NAME,
				AutoInfracaoDatabaseHelper.NUMERO_AUTO + "=?",
				new String[] { ait });
	}

}

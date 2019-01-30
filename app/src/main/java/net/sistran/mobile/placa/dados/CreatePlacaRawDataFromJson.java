package net.sistran.mobile.placa.dados;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class CreatePlacaRawDataFromJson {
	private DataFromPlate dataFromPlate;

	public CreatePlacaRawDataFromJson(String data, Context context) {
		setDataFromPlate(data);
		dataFromPlate = new DataFromPlate(context);
		setDataFromPlate(data);
	}

	public DataFromPlate getDataFromPlate() {
		return dataFromPlate;
	}

	public void setDataFromPlate(String data) {
		JSONObject jsonObject;

		int success = 10;
		final int SUCCESS = 1;

		try {
			jsonObject = new JSONObject(data);
			success = jsonObject.getInt("success");

			if (success == SUCCESS) {

				try {

					dataFromPlate.setPlate(jsonObject.getString("placa"));

					dataFromPlate.setUf(jsonObject.getString("uf"));

					dataFromPlate.setChassi(jsonObject.getString("chassi"));

					dataFromPlate.setMarca(jsonObject.getString("marca"));

					dataFromPlate.setModel(jsonObject.getString("modelo"));

					dataFromPlate.setColor(jsonObject.getString("cor"));

					dataFromPlate.setType(jsonObject.getString("tipo"));

					dataFromPlate.setCategory(jsonObject.getString("categoria"));

					dataFromPlate.setLicenceYear(jsonObject
							.getString("ano_licenciamento"));

					dataFromPlate.setLicenceData(jsonObject
							.getString("data_licenciamento"));

					dataFromPlate.setLicenceStatus(jsonObject
							.getString("status_licenciamento"));

					dataFromPlate.setIpva(jsonObject.getString("ipva"));

					dataFromPlate.setInsurance(jsonObject.getString("seguro"));

					dataFromPlate.setInfractions(jsonObject
							.getString("infracoes"));
					dataFromPlate.setRestrictions(jsonObject
							.getString("restricoes"));

				} catch (Exception e) {

				}

			} else {
				dataFromPlate = null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

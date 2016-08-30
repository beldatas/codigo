package net.sistransito.mobile.numero;

import android.content.Context;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.DatabaseCreator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreateNumeroDataFromJson {
	private int type;
	private Context context;
	private String jsonString;
	private JSONArray jsonArray;
	private JSONObject object;
	private ArrayList<String> list;
	private boolean isSave;

	public CreateNumeroDataFromJson(int type, Context context, String jsonString) {
		this.type = type;
		this.context = context;
		this.jsonString = jsonString;

	}

	public boolean saveNumeroData() {
		if (jsonString != null) {
			try {
				jsonArray = new JSONArray(jsonString);
				list = new ArrayList<String>();
				for (int i = 0; i < jsonArray.length(); i++) {
					object = jsonArray.getJSONObject(i);
					list.add(object.getString("numero_tab"));
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			switch (type) {
			case AppConstants.NUMERO_AUTO:
				isSave = (DatabaseCreator.getDatabaseAdapterNumero(context))
						.setAutoNumero(list);
				break;
			case AppConstants.NUMERO_TAV:
				isSave = (DatabaseCreator.getDatabaseAdapterNumero(context))
						.setTavNumero(list);
				break;
			case AppConstants.NUMERO_TCA:
				isSave = (DatabaseCreator.getDatabaseAdapterNumero(context))
						.setTcaNumero(list);
				break;
			case AppConstants.NUMERO_RRD:
				isSave = (DatabaseCreator.getDatabaseAdapterNumero(context))
						.setRrdNumero(list);
				break;
			}
		}
		return isSave;
	}

}

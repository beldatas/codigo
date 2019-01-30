package net.sistran.mobile.database.sync;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import net.sistran.mobile.database.DatabaseCreator;
import net.sistran.mobile.fragment.AnyAlertDialog;
import net.sistran.mobile.http.CustomHttpClient;
import net.sistran.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class SyncInformation extends AsyncTask<String, String, String> {

	private Context context;
	private ProgressDialog pDialog;
	private String autode_sync_title = "AURODEJSON";
	private String tav_sync_title = "TAVJSON";
	private String tca_sync_title = "TCAJSON";
	private String rrd_sync_title = "RRDJSON";

	private String url_autode_sync = "http://sistransito.net/movel/replicar/insert_auto_de.php";
	private String url_tav_sync = "http://sistransito.net/movel/replicar/insert_tav.php";
	private String url_tca_sync = "http://sistransito.net/movel/replicar/insert_tca.php";
	private String url_rrd_sync = "http://sistransito.net/movel/replicar/insert_rrd.php";

	private String userResponse;
	private ArrayList<NameValuePair> listParamsUsers;
	private String autode_json, tav_json, tca_json, rrd_json;
	private boolean sync_status;

	public SyncInformation(Context con) {
		context = con;
	}

	@Override
	protected void onPreExecute() {
		Log.d("callllll", "pree");
		super.onPreExecute();
		pDialog = null;
		pDialog = new ProgressDialog(context);
		pDialog.setMax(100);
		pDialog.setCancelable(false);
		pDialog.setMessage(context.getResources().getString(R.string.sincronizacao_mgs));
		pDialog.show();
	}

	@Override
	protected String doInBackground(String... arg0) {

		autode_json = (DatabaseCreator.getDatabaseAdapterAutoInfracao(context))
				.autoComposeJSONfromSQLite();

		Log.d("callllll", "rrd" + rrd_json);
		Log.d("callllll", "tav" + tav_json);
		Log.d("callllll", "tca" + tca_json);
		Log.d("callllll", "autode" + autode_json);

		if (autode_json != null || tav_json != null || tca_json != null
				|| rrd_json != null) {
			sync_status = true;
			if (autode_json != null) {
				sync_autode();
			}
		} else {
			sync_status = false;
		}

		Log.d("callllllssssss", "" + sync_status);
		return null;

	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		// pDialog.setProgress(Integer.parseInt(values[0]));
	}

	@Override
	protected void onPostExecute(String result) {
		Log.d("callllllpost", "b");
		if ((pDialog != null) && (pDialog.isShowing())) {
			pDialog.dismiss();
		}
		if (sync_status) {
			AnyAlertDialog.dialogShow(
					context.getResources().getString(R.string.sincronizacao_concluida),
					context,
					context.getResources().getString(R.string.titulo_sincronizacao));
		} else {
			AnyAlertDialog.dialogShow(
					context.getResources().getString(R.string.nao_precisa_sincronizar),
					context,
					context.getResources().getString(R.string.titulo_sincronizacao));
		}
		Log.d("callllllpost", "a");
		super.onPostExecute(result);
	}

	private void sync_autode() {
		listParamsUsers = new ArrayList<NameValuePair>();
		listParamsUsers.add(new BasicNameValuePair(autode_sync_title,
				autode_json));
		try {
			CustomHttpClient client = new CustomHttpClient();

			userResponse = client.executeHttpPost(context, listParamsUsers,
					url_autode_sync);

			JSONArray resultJsonArray = new JSONArray(userResponse);
			Log.d("jason aaray ",
					resultJsonArray + " " + resultJsonArray.length());
			for (int loparg = 0; loparg < resultJsonArray.length(); loparg++) {
				JSONObject obj = (JSONObject) resultJsonArray.get(loparg);
				Log.d("ssssssssssss",
						obj.getString("id") + " " + obj.getString("status"));
				if ((obj.getString("status")).equals("yes")) {
					(DatabaseCreator.getDatabaseAdapterNumero(context))
							.deleteNumeroAuto(obj.getString("id"));
					(DatabaseCreator.getDatabaseAdapterAutoInfracao(context))
							.autoUpdateSyncStatus(obj.getString("id"));
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}

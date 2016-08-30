package net.sistransito.mobile.database.sync;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.fragment.AnyDiaglog;
import net.sistransito.mobile.http.CustomHttpClient;
import net.sistrnsitomobile.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class SyncInofrmation extends AsyncTask<String, String, String> {

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

	public SyncInofrmation(Context con) {
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

		rrd_json = (DatabaseCreator.getDatabaseAdapterRRD(context))
				.rrdComposeJSONfromSQLite();

		tav_json = (DatabaseCreator.getDatabaseAdapterTAV(context))
				.tavComposeJSONfromSQLite();

		tca_json = (DatabaseCreator.getDatabaseAdapterTCA(context))
				.tcaComposeJSONfromSQLite();

		autode_json = (DatabaseCreator.getDatabaseAdapterAutoInfracao(context))
				.autoDeComposeJSONfromSQLite();

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
			if (tav_json != null) {
				sync_tav();
			}
			if (tca_json != null) {
				sync_tca();
			}
			if (rrd_json != null) {
				sync_rrd();
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
			AnyDiaglog.DialogShow(
					context.getResources().getString(R.string.sincronizacao_concluida),
					context,
					context.getResources().getString(R.string.titulo_sincronizacao));
		} else {
			AnyDiaglog.DialogShow(
					context.getResources().getString(R.string.nao_precisa_sincronizar),
					context,
					context.getResources().getString(R.string.titulo_sincronizacao));
		}
		Log.d("callllllpost", "a");
		super.onPostExecute(result);
	}

	private void sync_tav() {
		listParamsUsers = new ArrayList<NameValuePair>();
		listParamsUsers.add(new BasicNameValuePair(tav_sync_title, tav_json));
		try {
			CustomHttpClient client = new CustomHttpClient();

			userResponse = client.executeHttpPost(context, listParamsUsers,
					url_tav_sync);

			JSONArray resultJsonArray = new JSONArray(userResponse);
			Log.d("jason aaray ",
					resultJsonArray + " " + resultJsonArray.length());
			for (int loparg = 0; loparg < resultJsonArray.length(); loparg++) {
				JSONObject obj = (JSONObject) resultJsonArray.get(loparg);
				Log.d("ssssssssssss",
						obj.getString("id") + " " + obj.getString("status"));
				if ((obj.getString("status")).equals("yes")) {
					(DatabaseCreator.getDatabaseAdapterNumero(context))
							.deleteTavNumero(obj.getString("id"));
					(DatabaseCreator.getDatabaseAdapterTAV(context))
							.TavUpdateSyncStatus(obj.getString("id"));

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void sync_tca() {
		listParamsUsers = new ArrayList<NameValuePair>();
		listParamsUsers.add(new BasicNameValuePair(tca_sync_title, tca_json));
		try {
			CustomHttpClient client = new CustomHttpClient();

			userResponse = client.executeHttpPost(context, listParamsUsers,
					url_tca_sync);

			JSONArray resultJsonArray = new JSONArray(userResponse);
			Log.d("jason aaray ",
					resultJsonArray + " " + resultJsonArray.length());
			for (int loparg = 0; loparg < resultJsonArray.length(); loparg++) {
				JSONObject obj = (JSONObject) resultJsonArray.get(loparg);
				Log.d("ssssssssssss",
						obj.getString("id") + " " + obj.getString("status"));
				if ((obj.getString("status")).equals("yes")) {
					(DatabaseCreator.getDatabaseAdapterNumero(context))
							.deleteTcaNumero(obj.getString("id"));
					(DatabaseCreator.getDatabaseAdapterTCA(context))
							.tcaUpdateSyncStatus(obj.getString("id"));

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void sync_rrd() {

		listParamsUsers = new ArrayList<NameValuePair>();
		listParamsUsers.add(new BasicNameValuePair(rrd_sync_title, rrd_json));
		try {
			CustomHttpClient client = new CustomHttpClient();

			userResponse = client.executeHttpPost(context, listParamsUsers,
					url_rrd_sync);

			JSONArray resultJsonArray = new JSONArray(userResponse);
			Log.d("jason aaray ",
					resultJsonArray + " " + resultJsonArray.length());
			for (int loparg = 0; loparg < resultJsonArray.length(); loparg++) {
				JSONObject obj = (JSONObject) resultJsonArray.get(loparg);
				Log.d("ssssssssssss",
						obj.getString("id") + " " + obj.getString("status"));
				if ((obj.getString("status")).equals("yes")) {
					(DatabaseCreator.getDatabaseAdapterNumero(context))
							.deleteRrdNumero(obj.getString("id"));

					(DatabaseCreator.getDatabaseAdapterRRD(context))
							.rrdUpdateSyncStatus(obj.getString("id"));
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

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
							.deleteAuotNumero(obj.getString("id"));
					(DatabaseCreator.getDatabaseAdapterAutoInfracao(context))
							.autoDeUpdateSyncStatus(obj.getString("id"));
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}

package net.sistran.mobile.numero;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import net.sistran.mobile.appconstantes.AppConstants;
import net.sistran.mobile.appobjeto.AppObject;
import net.sistran.mobile.http.WebClient;

public class NumeroHttpResultAnysTask extends
        AsyncTask<String, Integer, String> {

    private ProgressDialog pDialog;
    private String jsonText = null;
    private String url;
    private NumeroAnysListerner listener;
    private Context context;
    private int type;
    private boolean isSuccess;

    public NumeroHttpResultAnysTask(final NumeroAnysListerner listener,
                                    Context context, int type) {
        this.type = type;
        switch (this.type) {
            case AppConstants.NUMERO_AUTO:
                url = WebClient.URL_NUMERO_AUTO;
                break;
            case AppConstants.NUMERO_TAV:
                url = WebClient.URL_NUMERO_TAV;
                break;
            case AppConstants.NUMERO_TCA:
                url = WebClient.URL_NUMERO_TCA;
                break;
            case AppConstants.NUMERO_RRD:
                url = WebClient.URL_NUMERO_RRD;
                break;
        }

        this.context = context;
        this.listener = listener;
        pDialog = null;
        pDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        pDialog.setMax(100);
        pDialog.setCancelable(false);
        pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        listener.anysTaskComplete(false);
                        cancel(true);

                    }
                });
        pDialog.setMessage("Carregando\n .....");

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            jsonText = AppObject.getHttpClient().executeHttpGet(url);
            CreateNumeroDataFromJson numeroDataFromJson = new CreateNumeroDataFromJson(
                    type, context, jsonText);
            isSuccess = numeroDataFromJson.saveNumeroData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        if ((pDialog != null) && (pDialog.isShowing())) {
            pDialog.dismiss();
        }
        listener.anysTaskComplete(isSuccess);
        super.onPostExecute(result);
    }
}
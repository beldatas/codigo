package net.sistran.mobile.placa.dados;


import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import com.gc.materialdesign.widgets.ProgressDialog;

import net.sistran.mobile.appobjeto.AppObject;
import net.sistran.mobile.database.DatabaseCreator;
import net.sistran.mobile.fragment.FragmentCallBack;
import net.sistran.mobile.http.WebClient;

public class PlacaHttpResultAsyncTask extends AsyncTask<String, Integer, String> {
    public ProgressDialog pDialog;
    private String jsonText = null;
    private Context context;
    private boolean isOffline;
    private DataFromPlate dataPlate;
    private String placa;
    public FragmentCallBack listener;
    private CreatePlacaRawDataFromJson createPlacaRawData;
    private Location location;

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        cancel(true);
        listener.CallBack(null, isOffline);
    }

    public PlacaHttpResultAsyncTask(final FragmentCallBack listener,
                                    Context context, final boolean isOffline, String placa,
                                    Location location) {
        this.context = context;
        this.isOffline = isOffline;
        this.placa = placa;
        this.listener = listener;
        this.location = location;
        pDialog = null;
        pDialog = new ProgressDialog(context, "Carregando\n" + " .....");
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... arg0) {

        if (isOffline) {
            dataPlate = (DatabaseCreator
                    .getSearchDataSdcard(context)).getPlacaData(placa);

            (DatabaseCreator.getPlacaSearchDatabaseAdapter(context))
                    .insertPlacaSearchData(dataPlate);
        } else {

            try {
                jsonText = AppObject.getHttpClient()
                        .executeHttpGet(WebClient.PLACA_SEARCH + placa);

                createPlacaRawData = new CreatePlacaRawDataFromJson(jsonText,
                        context);
                dataPlate = createPlacaRawData.getDataFromPlate();
                /*dataPlate.setLATITUDE(String.valueOf(location
                        .getLatitude()));
                dataPlate.setLONGITUDE(String.valueOf(location
                        .getLongitude()));*/
                (DatabaseCreator.getPlacaSearchDatabaseAdapter(context))
                        .insertPlacaSearchData(dataPlate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if ((pDialog != null) && (pDialog.isShowing())) {
            pDialog.dismiss();
        }
        listener.CallBack(dataPlate, isOffline);
        super.onPostExecute(result);
    }

}

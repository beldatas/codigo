package net.sistransito.mobile.placa.dados;


import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import com.gc.materialdesign.widgets.ProgressDialog;

import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.fragment.FragmentCallBack;
import net.sistransito.mobile.http.WebClient;

public class PlacaHttpResultAnysTask extends AsyncTask<String, Integer, String> {
    public ProgressDialog pDialog;
    private String jsonText = null;
    private Context context;
    private boolean isOffline;
    private PlacaRawFormat placaRawFormat;
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

    public PlacaHttpResultAnysTask(final FragmentCallBack listener,
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
            placaRawFormat = (DatabaseCreator
                    .getPrepopulatedDBOpenHelper(context)).getPlacaData(placa);

            (DatabaseCreator.getPlacaSearchDatabaseAdapter(context))
                    .insertPlacaSearchData(placaRawFormat);
        } else {

            try {
                jsonText = AppObject.getHttpClient()
                        .executeHttpGet(WebClient.PLACA_SEARCH + placa);

                createPlacaRawData = new CreatePlacaRawDataFromJson(jsonText,
                        context);
                placaRawFormat = createPlacaRawData.getPlacaRawFormat();
//                placaRawFormat.setLATITUDE(String.valueOf(location
//                        .getLatitude()));
//                placaRawFormat.setLONGITUDE(String.valueOf(location
//                        .getLongitude()));
                (DatabaseCreator.getPlacaSearchDatabaseAdapter(context))
                        .insertPlacaSearchData(placaRawFormat);

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
        listener.CallBack(placaRawFormat, isOffline);
        super.onPostExecute(result);
    }
}

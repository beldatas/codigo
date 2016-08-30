package net.sistransito.mobile.numero;

import android.content.Context;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.http.WebClient;

;

public class CheckNumeros {

    private Context context;
    private String jsonText = null;
    private CreateNumeroDataFromJson numeroDataFromJson;

    public CheckNumeros(Context context) {
        super();
        this.context = context;
    }

    public void check() {

        if ((DatabaseCreator.getDatabaseAdapterNumero(context))
                .isNeedAutodeUpdate()) {

            try {
                jsonText = AppObject.getHttpClient().executeHttpGet(
                        WebClient.URL_NUMERO_AUTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            numeroDataFromJson = new CreateNumeroDataFromJson(
                    AppConstants.NUMERO_AUTO, context, jsonText);
            numeroDataFromJson.saveNumeroData();

        }
        if ((DatabaseCreator.getDatabaseAdapterNumero(context))
                .isNeedTavUpdate()) {


            try {
                jsonText = AppObject.getHttpClient().executeHttpGet(
                        WebClient.URL_NUMERO_TAV);
            } catch (Exception e) {
            }
            numeroDataFromJson = new CreateNumeroDataFromJson(
                    AppConstants.NUMERO_TAV, context, jsonText);
            numeroDataFromJson.saveNumeroData();

        }
        if ((DatabaseCreator.getDatabaseAdapterNumero(context))
                .isNeedTcaUpdate()) {


            try {
                jsonText = AppObject.getHttpClient().executeHttpGet(
                        WebClient.URL_NUMERO_TCA);
            } catch (Exception e) {
                e.printStackTrace();
            }
            numeroDataFromJson = new CreateNumeroDataFromJson(
                    AppConstants.NUMERO_TCA, context, jsonText);
            numeroDataFromJson.saveNumeroData();

        }
        if ((DatabaseCreator.getDatabaseAdapterNumero(context))
                .isNeedRrdUpdate()) {


            try {
                jsonText = AppObject.getHttpClient().executeHttpGet(
                        WebClient.URL_NUMERO_RRD);
            } catch (Exception e) {
                e.printStackTrace();
            }
            numeroDataFromJson = new CreateNumeroDataFromJson(
                    AppConstants.NUMERO_RRD, context, jsonText);
            numeroDataFromJson.saveNumeroData();
        }
    }
}


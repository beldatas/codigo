package net.sistran.mobile.http;

import android.content.Context;
import android.location.Location;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.sistran.mobile.database.TinyDB;

public class WebClient {

    private static final String BASE_URl = "http://sistransito.com.br/movel/dosis.pl?";

    public static final String PLACA_SEARCH = BASE_URl + "op=placa&p=";
    public static final String LOGIN_URl = BASE_URl + "op=fazlogin&login=";

    public static final String URL_NUMERO_AUTO = BASE_URl + "op=numero_auto";
    public static final String URL_NUMERO_TAV = BASE_URl + "op=numero_tav";
    public static final String URL_NUMERO_TCA = BASE_URl + "op=numero_tca";
    public static final String URL_NUMERO_RRD = BASE_URl + "op=numero_rrd";
    public static final String URL_EQUIPAMENTOS = BASE_URl + "op=equipamentos";
    public static final String URL_CLOGIN = BASE_URl + "op=clogin";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler, Context c) {
        TinyDB tdb = new TinyDB(c);
        params.put("X-API-KEY", tdb.getString("token"));
        client.cancelAllRequests(true);
        client.get(url, params, responseHandler);
    }

    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.setConnectTimeout(1000 * 1000);
        client.cancelAllRequests(true);
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params,
                            AsyncHttpResponseHandler responseHandler) {
        client.cancelAllRequests(true);
        client.setURLEncodingEnabled(true);
        client.post(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params,
                            AsyncHttpResponseHandler responseHandler, Context c) {
        TinyDB tdb = new TinyDB(c);
        params.put("X-API-KEY", tdb.getString("token"));
        client.cancelAllRequests(true);
        client.post(url, params, responseHandler);
    }

    public static String getLoginUrl(String email, String password, String imei,
                                     String mobile, Location latitude, Location longitude) {
        return LOGIN_URl + email + "&senha=" + password + "&imei=" + imei
                + "&mobile=" + mobile + "&horizontal=" + latitude + "&vestical=" + longitude;
    }
}

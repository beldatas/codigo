package net.sistransito.mobile.appobjeto;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import net.sistransito.SistransitoApplication;
import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.autoinfracao.lister.AutoDeLister;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.database.TinyDB;
import net.sistransito.mobile.http.CustomHttpClient;
import net.sistransito.mobile.placa.lister.PlacaList;
import net.sistransito.mobile.rrd.lister.RRDLister;
import net.sistransito.mobile.tav.lister.TAVLister;
import net.sistransito.mobile.tca.lister.TCALister;
import net.sistrnsitomobile.R;

public class AppObject {
    private static Vibrator vibrator;
    private static MediaPlayer mPlayer = null;
    private static CustomHttpClient customHttpClient;
    private static Animation slide_and_scale_animation = null;
    private static Typeface tf_normal, tf_current, tf_title;
    private static TinyDB tinyDB;

    public static Intent getPlacaListIntent(Context context) {
        if (placaListIntent == null)
            placaListIntent = new Intent(context, PlacaList.class);
        return placaListIntent;
    }

    public static Intent getAutoDeListerIntent(Context context) {
        if (autoDeListerIntent == null)
            autoDeListerIntent = new Intent(context, AutoDeLister.class);

        return autoDeListerIntent;
    }

    public static  Intent getRrdListerIntent(Context context) {
        if (rrdListerIntent == null)
            rrdListerIntent = new Intent(context, RRDLister.class);
        return rrdListerIntent;
    }

    public  static Intent getTcaListerIntent(Context context) {
        if (tcaListerIntent == null)
            tcaListerIntent = new Intent(context, TCALister.class);
        return tcaListerIntent;
    }

    public static Intent getTavListerIntent(Context context) {
        if (tavListerIntent == null)
            tavListerIntent = new Intent(context, TAVLister.class);
        return tavListerIntent;
    }

    private static Intent placaListIntent, autoDeListerIntent, rrdListerIntent, tcaListerIntent, tavListerIntent;


    public static TinyDB getTinyDB(Context c) {
        if (tinyDB == null)
            tinyDB = new TinyDB(c);
        return tinyDB;
    }

    public static void newInstance(Context context) {
        getfont(context);
        slide_and_scale_animation = AnimationUtils.loadAnimation(context,
                R.anim.slide_and_scale);

        customHttpClient = new CustomHttpClient();
        mPlayer = MediaPlayer.create(context, R.raw.right_answer);
        vibrator = (Vibrator) context
                .getSystemService(Service.VIBRATOR_SERVICE);
        tinyDB = new TinyDB(context);

        placaListIntent = new Intent(SistransitoApplication.getAppContext(), PlacaList.class);
        autoDeListerIntent = new Intent(SistransitoApplication.getAppContext(), AutoDeLister.class);
        rrdListerIntent = new Intent(SistransitoApplication.getAppContext(), RRDLister.class);
        tcaListerIntent = new Intent(SistransitoApplication.getAppContext(), TCALister.class);
        tavListerIntent = new Intent(SistransitoApplication.getAppContext(), TAVLister.class);
    }

    public static Typeface getTf_normal(Context context) {
        if (tf_normal == null)
            tf_normal = Typeface.createFromAsset(context.getAssets(),
                    "font/MuseoSans-300.ttf");
        return tf_normal;
    }

    public static Typeface getCurrentFont(Context context) {
        if (tf_current == null) {
            getfont(context);
        }
        return tf_current;
    }

    public static void getfont(Context context) {

        String font = (DatabaseCreator.getDatabaseAdapterSetting(context))
                .getFont();

        if ((font.equals(AppConstants.FONT_1))) {
            tf_current = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Regular.ttf");
        } else if ((font.equals(AppConstants.FONT_2))) {
            tf_current = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Regular.ttf");
        } else if ((font.equals(AppConstants.FONT_3))) {
            tf_current = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Regular.ttf");
        } else {
            tf_current = Typeface.DEFAULT;
        }
    }

    public static Typeface getTitlefont(Context context) {

        tf_title = Typeface.createFromAsset(context.getAssets(),
                "font/Roboto-Regular.ttf");
        return tf_title;

    }

    public static void setfont(Context context, String font) {
        (DatabaseCreator.getDatabaseAdapterSetting(context)).setFont(font);
        getfont(context);
    }

    public static Animation getSlideAndScaleAnimition(Context context) {
        if (slide_and_scale_animation == null) {
            slide_and_scale_animation = AnimationUtils.loadAnimation(context,
                    R.anim.slide_and_scale);
            return slide_and_scale_animation;
        }
        return slide_and_scale_animation;

    }

    public static CustomHttpClient getHttpClient() {
        if (customHttpClient == null) {
            customHttpClient = new CustomHttpClient();
        }
        return customHttpClient;
    }

    public static void startWarning(Context context) {
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(context, R.raw.right_answer);
        }
        mPlayer.start();
    }

    public static void startVibrate(Context context) {
        vibrator = (Vibrator) context
                .getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);
    }
}

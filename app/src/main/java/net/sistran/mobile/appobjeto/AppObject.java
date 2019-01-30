package net.sistran.mobile.appobjeto;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import net.sistran.SistranApplication;
import net.sistran.mobile.appconstantes.AppConstants;
import net.sistran.mobile.autoinfracao.lister.AutoLister;
import net.sistran.mobile.database.DatabaseCreator;
import net.sistran.mobile.database.TinyDB;
import net.sistran.mobile.http.CustomHttpClient;
import net.sistran.mobile.placa.lister.PlacaLister;

import net.sistran.R;

public class AppObject {
    private static Vibrator vibrator;
    private static MediaPlayer mPlayer = null;
    private static CustomHttpClient customHttpClient;
    private static Animation slideAndScaleAnimation = null;
    private static Typeface tfNormal, tfCurrent, tfTitle;
    private static TinyDB tinyDB;

    public static Intent getPlacaListIntent(Context context) {
        if (placaListIntent == null)
            placaListIntent = new Intent(context, PlacaLister.class);
        return placaListIntent;
    }

    public static Intent getAutoDeListerIntent(Context context) {
        if (autoListerIntent == null)
            autoListerIntent = new Intent(context, AutoLister.class);

        return autoListerIntent;
    }

    private static Intent placaListIntent, autoListerIntent;


    public static TinyDB getTinyDB(Context c) {
        if (tinyDB == null)
            tinyDB = new TinyDB(c);
        return tinyDB;
    }

    public static void newInstance(Context context) {
        getfont(context);
        slideAndScaleAnimation = AnimationUtils.loadAnimation(context,
                R.anim.slide_and_scale);

        customHttpClient = new CustomHttpClient();
        mPlayer = MediaPlayer.create(context, R.raw.right_answer);
        vibrator = (Vibrator) context
                .getSystemService(Service.VIBRATOR_SERVICE);
        tinyDB = new TinyDB(context);

        placaListIntent = new Intent(SistranApplication.getAppContext(), PlacaLister.class);
        autoListerIntent = new Intent(SistranApplication.getAppContext(), AutoLister.class);
    }

    public static Typeface getTf_normal(Context context) {
        if (tfNormal == null)
            tfNormal = Typeface.createFromAsset(context.getAssets(),
                    "font/MuseoSans-300.ttf");
        return tfNormal;
    }

    public static Typeface getCurrentFont(Context context) {
        if (tfCurrent == null) {
            getfont(context);
        }
        return tfCurrent;
    }

    public static void getfont(Context context) {

        String font = (DatabaseCreator.getDatabaseAdapterSetting(context))
                .getFont();

        if ((font.equals(AppConstants.FONT_1))) {
            tfCurrent = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Regular.ttf");
        } else if ((font.equals(AppConstants.FONT_2))) {
            tfCurrent = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Regular.ttf");
        } else if ((font.equals(AppConstants.FONT_3))) {
            tfCurrent = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Regular.ttf");
        } else {
            tfCurrent = Typeface.DEFAULT;
        }
    }

    public static Typeface getTitlefont(Context context) {

        tfTitle = Typeface.createFromAsset(context.getAssets(),
                "font/Roboto-Regular.ttf");
        return tfTitle;

    }

    public static void setfont(Context context, String font) {
        (DatabaseCreator.getDatabaseAdapterSetting(context)).setFont(font);
        getfont(context);
    }

    public static Animation getSlideAndScaleAnimition(Context context) {
        if (slideAndScaleAnimation == null) {
            slideAndScaleAnimation = AnimationUtils.loadAnimation(context,
                    R.anim.slide_and_scale);
            return slideAndScaleAnimation;
        }
        return slideAndScaleAnimation;

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

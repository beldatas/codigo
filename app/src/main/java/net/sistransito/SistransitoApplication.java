package net.sistransito;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by GaziRimon on 10/21/2015.
 */
public class SistransitoApplication  extends Application{
    private static Context context;
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    public static Context getAppContext() {
        return context;
    }
}

package net.sistransito.mobile.impressaobluetooth.PrintBitmap.base;

import android.content.Context;
import android.graphics.Bitmap;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.util.User;

/**
 * Created by rimon on 9/13/2016.
 */
public abstract class BasePrintBitmap {
    protected Context context;
    protected User user;

    public BasePrintBitmap(Context context) {
        this.context = context;
        user = AppObject.getTinyDB(context).getObject(AppConstants.user, User.class);
    }

    public abstract Bitmap getBitmap();
}

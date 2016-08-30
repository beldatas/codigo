package net.sistransito.mobile.impressaobluetooth.PrintBitmap;

import android.content.Context;
import android.graphics.Bitmap;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.PrintBitmapFormat;
import net.sistransito.mobile.tav.TAVData;
import net.sistransito.mobile.util.User;

/**
 * Created by GAZI_RIMON on 8/15/2016.
 */
public class TAVPrintBitmap {
    private DadosDoAuto aData;
    private User user;
    private Context context;
    private TAVData tavData;

    public TAVPrintBitmap(Context context, TAVData tavData, DadosDoAuto autoData) {
        this.context = context;
        user = AppObject.getTinyDB(context).getObject(AppConstants.user, User.class);
        this.aData=autoData;
        this.tavData=tavData;
    }

    public Bitmap getBitmap() {
        PrintBitmapFormat bitmapFormat = new PrintBitmapFormat(context);

        // here you can design like as the autode print dada oK? ok

        bitmapFormat.printDocumnetClose();
        bitmapFormat.saveBitmap();
        return bitmapFormat.getPrintBitmap();
    }
}


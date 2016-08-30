package net.sistransito.mobile.impressaobluetooth.PrintBitmap;

import android.content.Context;
import android.graphics.Bitmap;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.PrintBitmapFormat;
import net.sistransito.mobile.tca.TcaData;
import net.sistransito.mobile.util.User;

/**
 * Created by GAZI_RIMON on 8/15/2016.
 */
public class TCAPrintBitmap {
    private DadosDoAuto aData;
    private User user;
    private Context context;
    private TcaData tcaData;

    public TCAPrintBitmap(Context context,  TcaData tcaData, DadosDoAuto autoData) {
        this.context = context;
        user = AppObject.getTinyDB(context).getObject(AppConstants.user, User.class);
        this.aData=autoData;
        this.tcaData=tcaData;
    }

    public Bitmap getBitmap() {
        PrintBitmapFormat bitmapFormat = new PrintBitmapFormat(context);


        // here you can design like as the autode print dada oK? ok

        bitmapFormat.printDocumnetClose();
        bitmapFormat.saveBitmap();
        return bitmapFormat.getPrintBitmap();
    }
}


package net.sistransito.mobile.impressaobluetooth.PrintBitmap;

import android.content.Context;
import android.graphics.Bitmap;

import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.BasePrintBitmap;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.PrintBitmapFormat;
import net.sistransito.mobile.tca.TcaData;

/**
 * Created by GAZI_RIMON on 8/15/2016.
 */
public class TCAPrintBitmap extends BasePrintBitmap {
    private DadosDoAuto aData;
    private TcaData tcaData;

    public TCAPrintBitmap(Context context, TcaData tcaData, DadosDoAuto autoData) {
        super(context);
        this.aData = autoData;
        this.tcaData = tcaData;
    }

    @Override
    public Bitmap getBitmap() {
        PrintBitmapFormat bitmapFormat = new PrintBitmapFormat(context);


        // here you can design like as the autode print dada oK? ok

        bitmapFormat.printDocumnetClose();
        bitmapFormat.saveBitmap();
        return bitmapFormat.getPrintBitmap();
    }
}


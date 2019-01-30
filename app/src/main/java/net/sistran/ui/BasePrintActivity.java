package net.sistran.ui;

import android.bluetooth.BluetoothDevice;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.sistran.mobile.database.DatabaseCreator;
import net.sistran.mobile.database.SetttingDatabaseHelper;
import net.sistran.mobile.fragment.AnyAlertDialog;
import net.sistran.mobile.impressaobluetooth.PrintBitmap.base.BasePrintBitmap;
import net.sistran.mobile.impressaobluetooth.bluetooth.Bluetooth;
import net.sistran.mobile.impressaobluetooth.bluetooth.BluetoothPrinterListerner;
import net.sistran.mobile.impressaobluetooth.bluetooth.ESCP;
import net.sistran.mobile.util.Rotinas;
import net.sistran.R;

import java.util.Set;

public abstract class BasePrintActivity extends AppCompatActivity
        implements BluetoothPrinterListerner {
    private Bluetooth mBth = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void print(Object mainData, Object autoData) {
        if (mBth == null) {
            mBth = new Bluetooth();
        }
        onPrintData(mainData, autoData);
    }

    @Override
    public void print(Object mainData, Object autoData, String via) {
        if (mBth == null) {
            mBth = new Bluetooth();
        }
        onPrintData(mainData, autoData, via);
    }

    protected abstract void onPrintData(Object mainData, Object autoData);

    protected abstract void onPrintData(Object mainData, Object autoData, String via);

    protected void startPrint(final BasePrintBitmap printBitmap) {
        if (mBth == null) {
            mBth = new Bluetooth();
        }
        if (checkBth()) {
            Rotinas.showAlert(getResources().getString(R.string.mgs_print), this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ESCP.ImageToEsc(printBitmap.getBitmap(),
                            mBth.Ostream, 8, 8);
                    closeBth();
                }
            }).start();
        }
    }

    public void closeBth() {
        if (mBth.isConnected()) {
            mBth.Close();
        }
    }

    public boolean checkBth() {

        Cursor cursor = (DatabaseCreator
                .getDatabaseAdapterSetting(getApplicationContext()))
                .getSettingCursor();

        String nPrinter = cursor.getString(cursor
                .getColumnIndex(SetttingDatabaseHelper.SETTING_PRINTER));

        if (!mBth.isConnected()) {
            if (!mBth.Enable()) {
                AnyAlertDialog.msgAlertSimple("Nao foi possivel habilitar bluetooth, tente " +
                        "habilitar manualmente e tente novamente.", this);
                return false;
            }
            String mac = null;
            Set<BluetoothDevice> devices = mBth.GetBondedDevices();
            for (BluetoothDevice device : devices) {
                if (nPrinter.equals(device.getName())) {
                    mac = device.getAddress();
                }
            }
            if (mac == null) {
                AnyAlertDialog.msgAlertSimple("Nao foi encontrada a impressora "
                        + nPrinter + "\n\nFaça o pareamento com o disposivo e tente novamente.", this);
                return false;
            }
            if (!mBth.Open(mac)) {
                AnyAlertDialog.msgAlertSimple("Nao foi possivel conectar ao dispositivo ["
                        + mac
                        + "]\n\nLigue ou conecte o dispositivo e tente novamente.", this);
                return false;
            }
        }
        return true;
    }

}

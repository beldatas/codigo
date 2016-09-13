package net.sistransito.ui;

import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.BasePrintBitmap;
import net.sistransito.mobile.impressaobluetooth.bluetooth.Bluetooth;
import net.sistransito.mobile.impressaobluetooth.bluetooth.BluetoothPrinterListerner;
import net.sistransito.mobile.impressaobluetooth.bluetooth.ESCP;

import java.util.Set;

public abstract class BasePrintActivity extends AppCompatActivity implements
        BluetoothPrinterListerner {
    private Bluetooth mBth = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void print(Object mainData, Object autodeData) {
        if (mBth != null) {
            mBth = new Bluetooth();
        }
        onPrintData(mainData, autodeData);
    }

    protected abstract void onPrintData(Object mainData, Object autodeData);

    protected void startPrint(final BasePrintBitmap printBitmap) {
        if (checkBth()) {
            Toast.makeText(this, "Imprimindo...", Toast.LENGTH_LONG).show();
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
        if (!mBth.isConnected()) {
            if (!mBth.Enable()) {
                Alert("Nao foi possivel abilitar bluetooth, tente abilitar manualmente e tente novamente.");
                return false;
            }
            String mac = null;
            Set<BluetoothDevice> devices = mBth.GetBondedDevices();
            for (BluetoothDevice device : devices) {
                if ("MPT-III".equals(device.getName())) {
                    mac = device.getAddress();
                }
            }
            if (mac == null) {
                Alert("Nao foi encontrada MPT-III\n\nFaça o pareamento com o disposivo e tente novamente.");
                return false;
            }
            if (!mBth.Open(mac)) {
                Alert("Nao foi possivel conectar ao dispositivo ["
                        + mac
                        + "]\n\nLigue ou conecte o dispositivo e tente novamente.");
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    public void Alert(String message) {
        AlertDialog ad = new AlertDialog.Builder(this).create();
        ad.setCancelable(false);
        ad.setMessage(message);
        ad.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }
}

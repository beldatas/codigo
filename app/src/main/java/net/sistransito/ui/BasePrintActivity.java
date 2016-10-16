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
import net.sistrnsitomobile.R;

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
            Toast.makeText(this, getResources().getString(
                    R.string.printing), Toast.LENGTH_LONG).show();
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
                Alert(getResources().getString(R.string.erro_bluetooth));
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
                Alert(getResources().getString(R.string.print_not_found));
                return false;
            }
            if (!mBth.Open(mac)) {
                Alert(getResources().getString(R.string.printer_off)
                        + mac
                        + getResources().getString(R.string.start_printer));
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
        ad.setButton(getResources().getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }
}

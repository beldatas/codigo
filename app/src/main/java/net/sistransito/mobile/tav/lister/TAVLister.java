package net.sistransito.mobile.tav.lister;

import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.impressaobluetooth.bluetooth.Bluetooth;
import net.sistransito.mobile.impressaobluetooth.bluetooth.BluetoothPrinterListerner;
import net.sistransito.mobile.impressaobluetooth.bluetooth.ESCP;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.TAVPrintBitmap;
import net.sistransito.mobile.tav.TAVData;
import net.sistrnsitomobile.R;

import java.util.Set;

public class TAVLister extends AppCompatActivity implements OnClickListener,
        BluetoothPrinterListerner {
    private TAVListerExpandableAdapter expandableAdapter;
    private ExpandableListView expandableListView;
    private ImageButton im_btn_back;
    private TextView tvMessage;
    private RelativeLayout tav_lister_layout;
    private Cursor cursor;
    private Bluetooth mBth;
    public final int ENABLE_BT__REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tav_lister_main);
        initializedView();
    }

    private void initializedView() {
        im_btn_back = (ImageButton) findViewById(R.id.im_btn_back);
        im_btn_back.setOnClickListener(this);

        if (checCursor()) {
            addResultView();
        } else {
            addNoResultView();
        }
    }

    private boolean checCursor() {
        cursor = (DatabaseCreator.getDatabaseAdapterTAV(this)).getTAVCursor();
        return cursor.getCount() > 0;
    }

    private void addNoResultView() {
        tav_lister_layout = (RelativeLayout) findViewById(R.id.tav_lister_layout);
        cursor.close();
        tvMessage = new TextView(this);
        tvMessage.setText(getResources().getString(
                R.string.nehum_resultado_retornado));
        tvMessage.setGravity(Gravity.CENTER);
        tvMessage.setTextAppearance(this, android.R.style.TextAppearance_Large);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        tvMessage.setLayoutParams(params);
        tvMessage.setTextColor(getResources().getColor(R.color.line_color));
        if (tvMessage.getParent() == null) {
            tav_lister_layout.addView(tvMessage);
        }
    }

    private void addResultView() {
        expandableAdapter = new TAVListerExpandableAdapter(cursor,
                TAVLister.this);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewTca);
        expandableListView.setAdapter(expandableAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_btn_back:
                finish();
                break;
        }

    }

    @Override
    public void print(Object mainData, Object autodeData) {
        if (mBth != null) {
            setPrintData(mainData, autodeData);
        } else {
            mBth = new Bluetooth();
            setPrintData(mainData, autodeData);
        }

    }

    private void setPrintData(final Object mainData, final Object autodeData) {
        if (checkBth()) {
            Toast.makeText(this, "Imprimindo...", Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final TAVData tavData = (TAVData) mainData;
                    final DadosDoAuto autoData = (DadosDoAuto) autodeData;
                    TAVPrintBitmap tavPrintBitmap = new TAVPrintBitmap(TAVLister.this, tavData, autoData);
                    ESCP.ImageToEsc(tavPrintBitmap.getBitmap(), mBth.Ostream, 8, 8);
                    closeBth();
                }
            }).start();
        }
    }

    public boolean closeBth() {
        if (mBth.isConnected()) {
            return mBth.Close();
        }
        return false;
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

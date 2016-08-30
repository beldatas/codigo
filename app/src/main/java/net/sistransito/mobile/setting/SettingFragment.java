package net.sistransito.mobile.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.CheckBox;

import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.database.SettingDatabaseAdapter;
import net.sistransito.mobile.database.sync.SyncInofrmation;
import net.sistransito.mobile.network.NetworkConnection;
import net.sistrnsitomobile.R;

public class SettingFragment extends Fragment implements
        OnCheckedChangeListener, OnClickListener {

    private ImageButton im_btn_back;
    private CheckBox checkBoxVibrar, checkBoxRington, checkBoxAutoBackup;
    private SettingDatabaseAdapter database;
    private TextView tv_title;
    private Button btn_sync;

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting_main, null, false);
        database = DatabaseCreator.getDatabaseAdapterSetting(getActivity());
        initializedView();
        return view;
    }
    public static SettingFragment newInstance() {
        return new SettingFragment();
    }


    private void initializedView() {
        checkBoxAutoBackup = (CheckBox) view.findViewById(R.id.checkBoxAutoBackup);
        checkBoxRington = (CheckBox) view.findViewById(R.id.checkBoxRington);
        checkBoxVibrar = (CheckBox) view.findViewById(R.id.checkBoxVibarte);
        btn_sync = (Button) view.findViewById(R.id.btn_sync);

        btn_sync.setOnClickListener(this);

        if (database.getAutobackup()) {
            checkBoxAutoBackup.setChecked(true);

        } else {
            checkBoxAutoBackup.setChecked(false);
        }
        if (database.getRingtone()) {
            checkBoxRington.setChecked(true);
        } else {
            checkBoxRington.setChecked(false);
        }
        if (database.getVibrator()) {
            checkBoxVibrar.setChecked(true);
            Log.d("VVVVVVV", database.getVibrator() + " ");

        } else {
            checkBoxVibrar.setChecked(false);
            Log.d("VVVVV", database.getVibrator() + " ");
        }
        checkBoxVibrar.setOnCheckedChangeListener(this);
        checkBoxRington.setOnCheckedChangeListener(this);
        checkBoxAutoBackup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton view, boolean isChecked) {
        switch (view.getId()) {
            case R.id.checkBoxAutoBackup:
                if (isChecked) {
                    database.setAutobackup(true);
                } else {
                    database.setAutobackup(false);
                }

                break;
            case R.id.checkBoxRington:
                if (isChecked) {
                    database.setRingtone(true);
                } else {
                    database.setRingtone(false);
                }

                break;
            case R.id.checkBoxVibarte:
                Log.d("hhhhhhhhhhhhhhhhhhhhhhh", database.getVibrator() + "");
                if (isChecked) {
                    database.setVibrator(true);
                } else {
                    database.setVibrator(false);
                }
                Log.d("hhhhhhhhhhhhhhhhhhhhhhh", database.getVibrator() + "");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sync:
                if (NetworkConnection.isNetworkAvailable(getActivity())) {
                    SyncInofrmation inofrmation = new SyncInofrmation(getActivity());
                    inofrmation.execute("");
                } else {
                    Toast.makeText(getActivity(),
                            getResources().getString(R.string.sem_conexao),
                            Toast.LENGTH_LONG).show();
                }

                break;

        }

    }

}

package net.sistran.mobile.placa.lister;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import net.sistran.mobile.appobjeto.AppObject;
import net.sistran.R;

public class LogListFragment extends Fragment implements OnClickListener {
    private View view;
    private Button btnListarPlacas, btnListarAutos, btnTracker;

    public static LogListFragment newInstance() {
        return new LogListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.historico_list_fragment, null, false);
        initializedView();
        return view;
    }

    private void initializedView() {
        btnListarPlacas = (Button) view.findViewById(R.id.btn_listar_placas);
        btnListarPlacas.setOnClickListener(this);
        btnListarAutos = (Button) view.findViewById(R.id.btn_listar_autos);
        btnListarAutos.setOnClickListener(this);
        btnTracker = (Button) view.findViewById(R.id.btn_tracker);
        btnTracker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_listar_placas:
                startActivity(AppObject.getPlacaListIntent(getActivity()));
                break;
            case R.id.btn_listar_autos:
                startActivity(AppObject.getAutoDeListerIntent(getActivity()));
                break;
            case R.id.btn_tracker:
                startActivity(AppObject.getTrackerIntent(getActivity()));
                break;
        }
    }
}

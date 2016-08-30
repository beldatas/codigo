package net.sistransito.mobile.placa.lister;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import net.sistransito.mobile.appobjeto.AppObject;
import net.sistrnsitomobile.R;

public class LogFragment extends Fragment implements OnClickListener {
    private View view;
    private Button btn_listar_placas, btn_listar_autos, btn_listar_rrd,
            btn_listar_tca, btn_listar_tav;

    public static LogFragment newInstance() {
        return new LogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.log_fragment, null, false);
        initializedView();
        return view;
    }

    private void initializedView() {
        btn_listar_placas = (Button) view.findViewById(R.id.btn_listar_placas);
        btn_listar_placas.setOnClickListener(this);
        btn_listar_autos = (Button) view.findViewById(R.id.btn_listar_autos);
        btn_listar_autos.setOnClickListener(this);
        btn_listar_rrd = (Button) view.findViewById(R.id.btn_listar_rrd);
        btn_listar_rrd.setOnClickListener(this);
        btn_listar_tca = (Button) view.findViewById(R.id.btn_listar_tca);
        btn_listar_tca.setOnClickListener(this);
        btn_listar_tav = (Button) view.findViewById(R.id.btn_listar_tav);
        btn_listar_tav.setOnClickListener(this);
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

            case R.id.btn_listar_rrd:
                startActivity(AppObject.getRrdListerIntent(getActivity()));
                break;

            case R.id.btn_listar_tca:
                startActivity(AppObject.getTcaListerIntent(getActivity()));
                break;
            case R.id.btn_listar_tav:
                startActivity(AppObject.getTavListerIntent(getActivity()));
                break;

        }

    }

}

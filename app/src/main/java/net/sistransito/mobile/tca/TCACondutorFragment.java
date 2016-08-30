package net.sistransito.mobile.tca;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

import net.sistransito.mobile.adapter.AnyArrayAdapter;
import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.fragment.AnyDiaglogFragmentForFragment;
import net.sistransito.mobile.fragment.AnyDialogListener;
import net.sistransito.mobile.numero.NumeroAnysListerner;
import net.sistransito.mobile.numero.NumeroHttpResultAnysTask;
import net.sistrnsitomobile.R;

import java.util.Arrays;
import java.util.List;

public class TCACondutorFragment extends Fragment implements
        AnyDialogListener {
    private View view;
    private EditText et_tca_nome_do_condutor, et_tca_cnh_ppd, et_tca_cpf,
            et_tca_endereco, et_tca_bairro, et_tca_municipio, et_tca_placa,
            et_tca_placa_uf, et_tca_model;

    private Spinner sp_tca_municipio_uf;

    private List<String> list_uf;
    private AnyArrayAdapter<String> adapter_uf;
    private TcaData data;
    private Bundle bundle;
    private AnyDiaglogFragmentForFragment diaglogFragmentForFragment;

    public static TCACondutorFragment newInstance() {
        return new TCACondutorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tca_condutor_fragment, null, false);
        initializedView();
        getTCAObject();
        addListener();
        checkNumeroAutoDe();
        return view;
    }

    private void checkNumeroAutoDe() {
        String value = (DatabaseCreator.getDatabaseAdapterNumero(getActivity()))
                .getTcaNumero();
        if (value == null) {
            diaglogFragmentForFragment = new AnyDiaglogFragmentForFragment();
            diaglogFragmentForFragment.setTargetFragment(this, 0);
            bundle = new Bundle();
            bundle.putInt(AppConstants.DIAGLOG_TITLE_ID, R.string.titulo_tela_sincronizacao);
            bundle.putInt(AppConstants.DIAGLOG_MGS_ID, R.string.mgs_sincronizacao);
            diaglogFragmentForFragment.setArguments(bundle);
            diaglogFragmentForFragment.setCancelable(false);
            diaglogFragmentForFragment
                    .show(getChildFragmentManager(), "dialog");
        } else {
            data.setNumero_tca(value);
        }
    }

    private void getRecomandedUpdate() {
        et_tca_nome_do_condutor.setText(data.getNome_do_condutor());
        et_tca_cnh_ppd.setText(data.getCnh_ppd());

        if (data.getCpf() == null) {
            data.setCpf("");
        } else {
            et_tca_cpf.setText(data.getCpf());
        }

        et_tca_placa.setText(data.getPlaca());
        et_tca_placa_uf.setText(data.getPlaca_uf());
        et_tca_model.setText(data.getMarca_modelo());
    }

    private void addListener() {
        et_tca_endereco.addTextChangedListener(new ChangeText(
                R.id.et_tca_endereco));
        et_tca_bairro
                .addTextChangedListener(new ChangeText(R.id.et_tca_bairro));
        et_tca_municipio.addTextChangedListener(new ChangeText(
                R.id.et_tca_municipio));

        sp_tca_municipio_uf
                .setOnItemSelectedListener(new OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int pos, long id) {
                        data.setMunicipio_uf((String) parent
                                .getItemAtPosition(pos));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });
    }

    private void getTCAObject() {
        data = TCAObgect.getTCAOject();
        getRecomandedUpdate();

    }

    // private void initializedSelectetItems() {
    //
    // }

    private void initializedView() {
        et_tca_nome_do_condutor = (EditText) view
                .findViewById(R.id.et_tca_nome_do_condutor);
        et_tca_nome_do_condutor.setEnabled(false);
        et_tca_cnh_ppd = (EditText) view.findViewById(R.id.et_tca_cnh_ppd);
        et_tca_cnh_ppd.setEnabled(false);
        et_tca_cpf = (EditText) view.findViewById(R.id.et_tca_cpf);
        et_tca_cpf.setEnabled(false);
        et_tca_endereco = (EditText) view.findViewById(R.id.et_tca_endereco);
        et_tca_bairro = (EditText) view.findViewById(R.id.et_tca_bairro);
        et_tca_municipio = (EditText) view.findViewById(R.id.et_tca_municipio);
        et_tca_placa = (EditText) view.findViewById(R.id.et_tca_placa);
        et_tca_placa.setEnabled(false);
        et_tca_placa_uf = (EditText) view.findViewById(R.id.et_tca_placa_uf);
        et_tca_placa_uf.setEnabled(false);
        et_tca_model = (EditText) view.findViewById(R.id.et_tca_model);
        et_tca_model.setEnabled(false);
        sp_tca_municipio_uf = (Spinner) view
                .findViewById(R.id.sp_tca_municipio_uf);
        list_uf = Arrays.asList(getResources().getStringArray(
                R.array.tca_uf_array));
        adapter_uf = new AnyArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, android.R.id.text1,
                list_uf);
        sp_tca_municipio_uf.setAdapter(adapter_uf);
    }

    private class ChangeText implements TextWatcher {
        private int id;

        public ChangeText(int id) {
            this.id = id;
        }

        @Override
        public void afterTextChanged(Editable value) {
            String s = (value.toString()).trim();
            switch (id) {
                case R.id.et_tca_endereco:
                    data.setEndereco(s);
                    break;
                case R.id.et_tca_bairro:
                    data.setBairro(s);
                    break;
                case R.id.et_tca_municipio:
                    data.setMunicipio(s);
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        }

    }

    @Override
    public void onDialogTaskWork(boolean isWork) {
        if (isWork) {

            (new NumeroHttpResultAnysTask(new NumeroAnysListerner() {

                @Override
                public void anysTaskComplete(boolean isComplete) {
                    if (isComplete) {
                        checkNumeroAutoDe();
                    }
                }
            }, getActivity(), AppConstants.NUMERO_TCA)).execute("");

        } else {
            getActivity().finish();
        }

    }
}
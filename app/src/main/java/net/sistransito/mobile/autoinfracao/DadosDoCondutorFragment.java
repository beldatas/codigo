package net.sistransito.mobile.autoinfracao;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

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

public class DadosDoCondutorFragment extends Fragment implements
		AnyDialogListener {
	private View view;
	private EditText et_auto_placa, et_auto_modelo_veiculo,
			et_nome_do_condutor, et_auto_cnh_ppd, et_uf_cnh,
			et_auto_cor_do_veiculo, et_autode_chassi, et_numero_documento;
	private Spinner auto_spinner_especie, auto_spinner_categoria,
			autode_spinner_pais;
	private List<String> list_categoria, list_especie, list_pais;
	private AnyArrayAdapter<String> adapter_categoria, adapter_especie,
			adapter_pais;
	private DadosDoAuto data;
	private Bundle bundle;
	private AnyDiaglogFragmentForFragment diaglogFragmentForFragment;

	private RadioGroup rg_tipo_de_documento;

	public static DadosDoCondutorFragment newInstance() {
		return new DadosDoCondutorFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.auto_condutor_fragment, null, false);
		initializedView();
		getAutodeObject();
		checkNumeroAutoDe();
		return view;
	}

	private void checkNumeroAutoDe() {
		String value = (DatabaseCreator.getDatabaseAdapterNumero(getActivity()))
				.getAutoNumero();
		if (value == null) {
			diaglogFragmentForFragment = new AnyDiaglogFragmentForFragment();
			diaglogFragmentForFragment.setTargetFragment(this, 0);
			bundle = new Bundle();
			bundle.putInt(AppConstants.DIAGLOG_TITLE_ID,
					R.string.titulo_tela_sincronizacao);
			bundle.putInt(AppConstants.DIAGLOG_MGS_ID,
					R.string.sincronizacao_mgs);
			diaglogFragmentForFragment.setArguments(bundle);
			diaglogFragmentForFragment.setCancelable(false);
			diaglogFragmentForFragment
					.show(getChildFragmentManager(), "dialog");
		} else {
			data.setNumero_auto(value);
		}
	}

	private void addListener() {
		et_nome_do_condutor.addTextChangedListener(new ChangeText(
				R.id.et_nome_do_condutor));

		et_auto_cnh_ppd.addTextChangedListener(new ChangeText(
				R.id.et_autode_cnh_ppd));

		et_uf_cnh.addTextChangedListener(new ChangeText(R.id.et_uf_cnh));
		et_autode_chassi.addTextChangedListener(new ChangeText(
				R.id.et_autode_chassi));
		et_numero_documento.addTextChangedListener(new ChangeText(
				R.id.et_numero_documento));

		autode_spinner_pais
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long Id) {

						data.setPais((String) parent.getItemAtPosition(pos));

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

		auto_spinner_categoria
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long Id) {

						data.setCategoria((String) parent
								.getItemAtPosition(pos));

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
		auto_spinner_especie
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long Id) {
						data.setEspecie((String) parent.getItemAtPosition(pos));
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

		rg_tipo_de_documento
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup arg0, int check_id) {
						if (check_id != -1) {
							String value = ((RadioButton) view
									.findViewById(rg_tipo_de_documento
											.getCheckedRadioButtonId()))
									.getText().toString();
							value = value.trim();
							//Toast.makeText(getActivity(), "aaaaaaaa : " + value, Toast.LENGTH_LONG).show();
							data.setTipo_de_documento(value);
						}

					}
				});

	}

	private void getAutodeObject() {
		data = AutoObjeto.getAutoDeOject();
		if (data.isDataisNull()) {
			setViewEnable(true);
			addListener();
		} else if (data.isStoreFullData()) {
			setViewEnable(true);
			getRecomandedUpdate();
			getOtherUpdate();
			addListener();
		} else {
			setViewEnable(true);
			getRecomandedUpdate();
			addListener();

		}
	}

	private void initializedView() {
		et_auto_placa = (EditText) view.findViewById(R.id.et_autode_placa);

		et_autode_chassi = (EditText) view.findViewById(R.id.et_autode_chassi);
		et_auto_modelo_veiculo = (EditText) view
				.findViewById(R.id.et_autode_model);
		et_auto_cor_do_veiculo = (EditText) view
				.findViewById(R.id.et_autode_cor_do_veiculo);

		et_nome_do_condutor = (EditText) view
				.findViewById(R.id.et_nome_do_condutor);

		et_auto_cnh_ppd = (EditText) view.findViewById(R.id.et_autode_cnh_ppd);
		et_uf_cnh = (EditText) view.findViewById(R.id.et_uf_cnh);

		list_categoria = Arrays.asList(getResources().getStringArray(
				R.array.autode_categoria));

		list_pais = Arrays.asList(getResources().getStringArray(
				R.array.filter_nome_pais));

		adapter_pais = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				list_pais);

		adapter_categoria = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				list_categoria);
		list_especie = Arrays.asList(getResources().getStringArray(
				R.array.autode_especie));
		adapter_especie = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				list_especie);

		auto_spinner_especie = (Spinner) view.findViewById(R.id.autode_spinner_especie);
		auto_spinner_especie.setAdapter(adapter_especie);

		autode_spinner_pais = (Spinner) view.findViewById(R.id.autode_spinner_pais);
		autode_spinner_pais.setAdapter(adapter_pais);

		auto_spinner_categoria = (Spinner) view.findViewById(R.id.autode_spinner_categoria);
		auto_spinner_categoria.setAdapter(adapter_categoria);

		rg_tipo_de_documento = (RadioGroup) view
				.findViewById(R.id.rg_tipo_de_documento);

		et_numero_documento = (EditText) view
				.findViewById(R.id.et_numero_documento);

	}

	private void getRecomandedUpdate() {
		et_auto_placa.setText(data.getPlate());
		et_auto_modelo_veiculo.setText(data.getModel());
		et_auto_cor_do_veiculo.setText(data.getCor_do_veiculo());
	}

	private void getOtherUpdate() {

		et_autode_chassi.setText(data.getChassi());
		int selection_1 = 0, selection_2 = 0, selection_3 = 0;
		selection_1 = list_categoria.indexOf(data.getCategoria());
		selection_2 = list_especie.indexOf(data.getEspecie());

		selection_3 = list_pais.indexOf(data.getPais());

		autode_spinner_pais.setSelection(selection_3);
		auto_spinner_categoria.setSelection(selection_1);
		auto_spinner_especie.setSelection(selection_2);
		et_auto_cnh_ppd.setText(data.getCnh_ppd());
		et_uf_cnh.setText(data.getUf_cnh());
		et_nome_do_condutor.setText(data.getNome_do_Condutor());
		et_numero_documento.setText(data.getNumero_documento());
	}

	private class ChangeText implements TextWatcher {
		private int id;

		public ChangeText(int id) {
			this.id = id;
		}

		@Override
		public void afterTextChanged(Editable s) {

			if (s.toString() != null) {
				switch (id) {
				case R.id.et_nome_do_condutor:
					data.setNome_do_Condutor(s.toString());
					break;
				case R.id.et_autode_cnh_ppd:
					data.setCnh_ppd(s.toString());
					break;
				case R.id.et_uf_cnh:
					data.setUf_cnh(s.toString());
					break;

				case R.id.et_autode_chassi:
					data.setChassi(s.toString());
					break;

				case R.id.et_numero_documento:
					data.setNumero_documento(s.toString());
					break;

				case R.id.et_autode_placa:
					data.setPlate(s.toString());
					break;
				case R.id.et_autode_cor_do_veiculo:
					data.setCor_do_veiculo(s.toString());
					break;
				case R.id.et_autode_model:
					data.setModel(s.toString());
					break;
				}
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
			}, getActivity(), AppConstants.NUMERO_AUTO)).execute("");

		} else {
			getActivity().finish();
		}
	}

	private void setViewEnable(boolean isEnable) {
		et_auto_placa.setEnabled(isEnable);
		et_auto_cor_do_veiculo.setEnabled(isEnable);
		et_auto_modelo_veiculo.setEnabled(isEnable);

		if(isEnable){

			et_auto_placa.addTextChangedListener(new ChangeText(R.id.et_autode_placa));
			et_auto_cor_do_veiculo.addTextChangedListener(new ChangeText(R.id.et_autode_cor_do_veiculo));
			et_auto_modelo_veiculo.addTextChangedListener(new ChangeText(R.id.et_autode_model));
		}

	}
}
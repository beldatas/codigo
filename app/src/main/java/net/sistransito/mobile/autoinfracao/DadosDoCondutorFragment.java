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
	private EditText editPlacaVeiculo, editModeloVeiculo,
			editNomeCondutor, editCnhPpdAcc, editUfRegistro,
			editCorVeiculo, editChassiVeiculo, editNumeroDocumento;
	private Spinner spinnerEspecie, spinnerCategoria,
			spinnerPais;
	private List<String> listCategoria, listEspecie, listPais;
	private AnyArrayAdapter<String> adapterCategoria, adapterEspecie,
			adapterPais;
	private DadosDoAuto data;
	private Bundle bundle;
	private AnyDiaglogFragmentForFragment diaglogFragmentForFragment;

	private RadioGroup radioTipoDocumento;

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
		editNomeCondutor.addTextChangedListener(new ChangeText(
				R.id.editNomeCondutor));

		editCnhPpdAcc.addTextChangedListener(new ChangeText(
				R.id.editCnhPpdAcc));

		editUfRegistro.addTextChangedListener(new ChangeText(R.id.editUfRegistro));
		editChassiVeiculo.addTextChangedListener(new ChangeText(
				R.id.editChassiVeiculo));
		editNumeroDocumento.addTextChangedListener(new ChangeText(
				R.id.editNumeroDocumento));

		spinnerPais
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

		spinnerCategoria
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
		spinnerEspecie
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

		radioTipoDocumento
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup arg0, int check_id) {
						if (check_id != -1) {
							String value = ((RadioButton) view
									.findViewById(radioTipoDocumento
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
		editPlacaVeiculo = (EditText) view.findViewById(R.id.editPlacaVeiculo);

		editChassiVeiculo = (EditText) view.findViewById(R.id.editChassiVeiculo);
		editModeloVeiculo = (EditText) view
				.findViewById(R.id.editModeloVeiculo);
		editCorVeiculo = (EditText) view
				.findViewById(R.id.editCorVeiculo);

		editNomeCondutor = (EditText) view
				.findViewById(R.id.editNomeCondutor);

		editCnhPpdAcc = (EditText) view.findViewById(R.id.editCnhPpdAcc);
		editUfRegistro = (EditText) view.findViewById(R.id.editUfRegistro);

		listCategoria = Arrays.asList(getResources().getStringArray(
				R.array.listCategoria));

		listPais = Arrays.asList(getResources().getStringArray(
				R.array.filter_nome_pais));

		adapterPais = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				listPais);

		adapterCategoria = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				listCategoria);
		listEspecie = Arrays.asList(getResources().getStringArray(
				R.array.autode_especie));
		adapterEspecie = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				listEspecie);

		spinnerEspecie = (Spinner) view.findViewById(R.id.autode_spinner_especie);
		spinnerEspecie.setAdapter(adapterEspecie);

		spinnerPais = (Spinner) view.findViewById(R.id.autode_spinner_pais);
		spinnerPais.setAdapter(adapterPais);

		spinnerCategoria = (Spinner) view.findViewById(R.id.autode_spinner_categoria);
		spinnerCategoria.setAdapter(adapterCategoria);

		radioTipoDocumento = (RadioGroup) view
				.findViewById(R.id.rg_tipo_de_documento);

		editNumeroDocumento = (EditText) view
				.findViewById(R.id.editNumeroDocumento);

	}

	private void getRecomandedUpdate() {
		editPlacaVeiculo.setText(data.getPlate());
		editModeloVeiculo.setText(data.getModel());
		editCorVeiculo.setText(data.getCor_do_veiculo());
	}

	private void getOtherUpdate() {

		editChassiVeiculo.setText(data.getChassi());
		int selection_1 = 0, selection_2 = 0, selection_3 = 0;
		selection_1 = listCategoria.indexOf(data.getCategoria());
		selection_2 = listEspecie.indexOf(data.getEspecie());

		selection_3 = listPais.indexOf(data.getPais());

		spinnerPais.setSelection(selection_3);
		spinnerCategoria.setSelection(selection_1);
		spinnerEspecie.setSelection(selection_2);
		editCnhPpdAcc.setText(data.getCnh_ppd());
		editUfRegistro.setText(data.getUf_cnh());
		editNomeCondutor.setText(data.getNome_do_Condutor());
		editNumeroDocumento.setText(data.getNumero_documento());
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
				case R.id.editNomeCondutor:
					data.setNome_do_Condutor(s.toString());
					break;
				case R.id.editCnhPpdAcc:
					data.setCnh_ppd(s.toString());
					break;
				case R.id.editUfRegistro:
					data.setUf_cnh(s.toString());
					break;

				case R.id.editChassiVeiculo:
					data.setChassi(s.toString());
					break;

				case R.id.editNumeroDocumento:
					data.setNumero_documento(s.toString());
					break;

				case R.id.editPlacaVeiculo:
					data.setPlate(s.toString());
					break;
				case R.id.editCorVeiculo:
					data.setCor_do_veiculo(s.toString());
					break;
				case R.id.editModeloVeiculo:
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
		editPlacaVeiculo.setEnabled(isEnable);
		editCorVeiculo.setEnabled(isEnable);
		editModeloVeiculo.setEnabled(isEnable);

		if(isEnable){

			editPlacaVeiculo.addTextChangedListener(new ChangeText(R.id.editPlacaVeiculo));
			editCorVeiculo.addTextChangedListener(new ChangeText(R.id.editCorVeiculo));
			editModeloVeiculo.addTextChangedListener(new ChangeText(R.id.editModeloVeiculo));
		}

	}
}
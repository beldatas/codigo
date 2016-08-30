package net.sistransito.mobile.tav;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.fragment.AnyDiaglogFragmentForFragment;
import net.sistransito.mobile.fragment.AnyDialogListener;
import net.sistransito.mobile.numero.NumeroAnysListerner;
import net.sistransito.mobile.numero.NumeroHttpResultAnysTask;
import net.sistrnsitomobile.R;

public class TAVConductorFragment extends Fragment implements
		AnyDialogListener {
	private View view;

	private TAVData data;
	private EditText et_numero_do_auto, et_nome_do_proprietario, et_CPF_CNPJ,
			et_numero_do_Renavam, et_numero_do_chassi;
	private Bundle bundle;
	private AnyDiaglogFragmentForFragment diaglogFragmentForFragment;

	public static TAVConductorFragment newInstance() {
		return new TAVConductorFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tav_condutor_fragment, null, false);
		initializedView();
		getAutodeObject();
		checkNumeroAutoDe();
		return view;
	}

	private void checkNumeroAutoDe() {
		String value = (DatabaseCreator.getDatabaseAdapterNumero(getActivity()))
				.geTavNumero();
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
			data.setNumero_tav(value);

		}
	}

	private void addListener() {
		et_numero_do_auto.addTextChangedListener(new ChangeText(
				R.id.et_numero_do_auto));
		et_nome_do_proprietario.addTextChangedListener(new ChangeText(
				R.id.et_nome_do_proprietario));

		et_CPF_CNPJ.addTextChangedListener(new ChangeText(R.id.et_CPF_CNPJ));

		et_numero_do_Renavam.addTextChangedListener(new ChangeText(
				R.id.et_numero_do_Renavam));

		et_numero_do_chassi.addTextChangedListener(new ChangeText(
				R.id.et_numero_do_chassi));

	}

	private void getAutodeObject() {
		data = TAVObgect.getTAVOject();
		getRecomandedUpdate();

	}

	private void getRecomandedUpdate() {
      et_numero_do_auto.setText(data.getNumero_do_auto());
	}

	private void initializedView() {
    
		et_numero_do_auto = (EditText) view
				.findViewById(R.id.et_numero_do_auto);
		et_nome_do_proprietario = (EditText) view
				.findViewById(R.id.et_nome_do_proprietario);

		et_CPF_CNPJ = (EditText) view.findViewById(R.id.et_CPF_CNPJ);

		et_numero_do_Renavam = (EditText) view
				.findViewById(R.id.et_numero_do_Renavam);

		et_numero_do_chassi = (EditText) view
				.findViewById(R.id.et_numero_do_chassi);

		addListener();

	}

	private class ChangeText implements TextWatcher {
		private int id;

		public ChangeText(int id) {
			this.id = id;
		}

		@Override
		public void afterTextChanged(Editable editable) {
			String s = editable.toString();
			switch (id) {
			case R.id.et_numero_do_auto:
				data.setNumero_do_auto(s);
				break;
			case R.id.et_CPF_CNPJ:
				data.setCpf_cnpj(s);
				break;

			case R.id.et_numero_do_Renavam:
				data.setNumero_do_renavam(s);
				break;

			case R.id.et_numero_do_chassi:
				data.setNumero_do_chassi(s);
				break;

			case R.id.et_nome_do_proprietario:
				data.setNome_do_proprietario(s);
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
			}, getActivity(), AppConstants.NUMERO_TAV)).execute("");

		} else {
			getActivity().finish();
		}
	}
}
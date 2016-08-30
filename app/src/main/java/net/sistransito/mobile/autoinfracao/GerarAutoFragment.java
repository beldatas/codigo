package net.sistransito.mobile.autoinfracao;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import net.sistransito.mobile.autoinfracao.lister.AutoDeLister;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.fragment.AnyDiaglog;
import net.sistransito.mobile.fragment.UpdateFragment;
import net.sistrnsitomobile.R;

public class GerarAutoFragment extends Fragment implements
		OnClickListener, UpdateFragment, OnCheckedChangeListener {
	private View view;

	private RadioGroup rg_procedimentos, rg_recolhimento;
	private EditText et_auto_observacao, et_auto_identificacao_embarcador,
			et_auto_cnpj_cpf_embarcador,
			et_auto_identificacao_do_transportador,
			et_autode_cnpj_cpf_transportador;
	private Button btn_auto_conferir, btn_auto_gerar;
	private DadosDoAuto data;

	public static GerarAutoFragment newInstance() {
		return new GerarAutoFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.auto_gerar_fragment, null, false);
		initializedView();
		getAutodeObject();
		return view;
	}

	private void addListener() {

		et_auto_observacao.addTextChangedListener(new ChangeText(
				R.id.et_autode_observacao));

		et_auto_identificacao_embarcador.addTextChangedListener(new ChangeText(
				R.id.et_autode_identificacao_embracador));

		et_auto_cnpj_cpf_embarcador.addTextChangedListener(new ChangeText(
				R.id.et_autode_CNPJ_CPF_embracador));

		et_auto_identificacao_do_transportador
				.addTextChangedListener(new ChangeText(
						R.id.et_autode_identificacao_do_transportadfor));

		et_autode_cnpj_cpf_transportador.addTextChangedListener(new ChangeText(
				R.id.et_autode_CNPJ_CPF_transportadfor));

		btn_auto_conferir.setOnClickListener(this);
		btn_auto_gerar.setOnClickListener(this);
		rg_procedimentos.setOnCheckedChangeListener(this);
		rg_recolhimento.setOnCheckedChangeListener(this);
	}

	private void getAutodeObject() {
		data = AutoObjeto.getAutoDeOject();
		if (data.isDataisNull()) {
			addListener();
		} else if (data.isStoreFullData()) {
			getRecomandedUpdate();
			addListener();
		} else {
			addListener();
			initializedSelectetItems();
		}
	}

	private void initializedSelectetItems() {
		// checkBox_autode_retencao.setChecked(false);
		// checkBox_autode_remocao.setChecked(false);
		// checkBox_autode_CLRV.setChecked(false);
		// checkBox_autode_CNH.setChecked(false);
		// checkBox_autode_PPD.setChecked(false);
		// data.setRemocao(AppConstants.cross);
		// data.setRetencao(AppConstants.cross);
		// data.setClrv(AppConstants.cross);
		// data.setCnh(AppConstants.cross);
		// data.setPpd(AppConstants.cross);
		// et_autode_observacao.setText("");
		// et_autode_identificacao_embracador.setText("");
		// et_autode_CNPJ_CPF_embracador.setText("");
		// et_autode_identificacao_do_transportadfor.setText("");
		// et_autode_CNPJ_CPF_transportadfor.setText("");
	}

	private void getRecomandedUpdate() {
		et_auto_observacao.setText(data.getObservacao());
		et_auto_identificacao_embarcador.setText(data
				.getIdetificacao_embarcador());
		et_auto_cnpj_cpf_embarcador.setText(data.getCnpj_cpf_embarcador());
		et_auto_identificacao_do_transportador.setText(data
				.getIdentificacao_do_transportador());
		et_autode_cnpj_cpf_transportador.setText(data
				.getCnpj_cpf_transportador());

	}

	private void initializedView() {

		et_auto_observacao = (EditText) view
				.findViewById(R.id.et_autode_observacao);
		et_auto_identificacao_embarcador = (EditText) view
				.findViewById(R.id.et_autode_identificacao_embracador);
		et_auto_cnpj_cpf_embarcador = (EditText) view
				.findViewById(R.id.et_autode_CNPJ_CPF_embracador);
		et_auto_identificacao_do_transportador = (EditText) view
				.findViewById(R.id.et_autode_identificacao_do_transportadfor);
		et_autode_cnpj_cpf_transportador = (EditText) view
				.findViewById(R.id.et_autode_CNPJ_CPF_transportadfor);

		btn_auto_conferir = (Button) view
				.findViewById(R.id.btn_autode_conferir);
		btn_auto_gerar = (Button) view.findViewById(R.id.btn_autode_gerar);

		rg_recolhimento = (RadioGroup) view.findViewById(R.id.rg_recolhimento);
		rg_procedimentos = (RadioGroup) view
				.findViewById(R.id.rg_procedimentos);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_autode_conferir:
			AnyDiaglog.DialogShow(data.getAutoDeViewData(getActivity()),
					getActivity(),
					getActivity().getResources().getString(R.string.autuar));
			break;
		case R.id.btn_autode_gerar:
			DatabaseCreator.getDatabaseAdapterAutoInfracao(getActivity())
					.setData(data);
			startActivity(new Intent(getActivity(), AutoDeLister.class));
			getActivity().finish();
			break;
		}

	}

	private class ChangeText implements TextWatcher {
		private int id;

		public ChangeText(int id) {
			this.id = id;
		}

		@Override
		public void afterTextChanged(Editable s) {
			switch (id) {
			case R.id.et_autode_observacao:
				data.setObservacao(s.toString());
				break;
			case R.id.et_autode_identificacao_embracador:
				data.setIdetificacao_embarcador(s.toString());
				break;
			case R.id.et_autode_CNPJ_CPF_embracador:
				data.setCnpj_cpf_embarcador(s.toString());
				break;
			case R.id.et_autode_identificacao_do_transportadfor:
				data.setIdentificacao_do_transportador(s.toString());
				break;
			case R.id.et_autode_CNPJ_CPF_transportadfor:
				data.setCnpj_cpf_transportador(s.toString());
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
	public void Update() {
		String s = data.getObservacao();
		et_auto_observacao.setText(s);
	}

	@Override
	public void onCheckedChanged(RadioGroup v, int check_id) {
		switch (v.getId()) {
		case R.id.rg_recolhimento:
			if (check_id != -1) {
				data.setRecolhimento(((RadioButton) view
						.findViewById(rg_recolhimento.getCheckedRadioButtonId()))
						.getText().toString());
			}
			break;
		case R.id.rg_procedimentos:
			if (check_id != -1) {
				data.setProcedimentos(((RadioButton) view
						.findViewById(rg_procedimentos
								.getCheckedRadioButtonId())).getText()
						.toString());
			}

			break;
		}

	}
}

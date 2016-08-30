package net.sistransito.mobile.tav;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import net.sistransito.mobile.adapter.AnyArrayAdapter;
import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.fragment.AnyDiaglog;
import net.sistransito.mobile.tav.lister.TAVLister;
import net.sistrnsitomobile.R;

import java.util.Arrays;
import java.util.List;

public class TAVGegrarFragment extends Fragment implements
		OnClickListener, OnCheckedChangeListener {
	private View view;
	private TAVData data;

	private RadioGroup rg_marcador_de_conbutivel_1,
			rg_marcador_de_conbutivel_2;
	private EditText et_odometo, et_nome_da_empresa,
			et_nome_do_condutor_do_guincho, et_tav_observacao;

	private Button btn_tav_conferir, btn_tav_gerar;

	private Spinner sp_remocao_atraves_de;
	private CheckBox cb_tav_12, cb_tav_14, cb_tav_vazio, cb_tav_danificado,
			cb_tav_cheio, cb_tav_34;

	private List<String> list_remocao_atraves_de;
	private AnyArrayAdapter<String> adapter_remocao_atraves_de;

	private LinearLayout linearLayout_parent, linearLayout_child;
	public static TAVGegrarFragment newInstance() {
		return new TAVGegrarFragment();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tav_geral_fragment, null, false);
		initializedView();
		getAutodeObject();
		return view;
	}

	private void addListener() {
		et_odometo.addTextChangedListener(new ChangeText(R.id.et_odometo));

		et_nome_da_empresa.addTextChangedListener(new ChangeText(
				R.id.et_nome_da_empresa));

		et_nome_do_condutor_do_guincho.addTextChangedListener(new ChangeText(
				R.id.et_nome_do_condutor_do_guincho));

		et_tav_observacao.addTextChangedListener(new ChangeText(
				R.id.et_tav_observacao));
		btn_tav_gerar.setOnClickListener(this);
		btn_tav_conferir.setOnClickListener(this);

		sp_remocao_atraves_de
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						String s = (String) parent.getItemAtPosition(pos);
						data.setRemocao_atraves_de(s);

						if (s.compareTo(list_remocao_atraves_de
								.get(list_remocao_atraves_de.size() - 1)) == 0) {

							addView();

						} else {
							removeView();
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		rg_marcador_de_conbutivel_1.setOnCheckedChangeListener(this);
		rg_marcador_de_conbutivel_2.setOnCheckedChangeListener(this);
	}

	private void getAutodeObject() {
		data = TAVObgect.getTAVOject();

	}

	private void initializedView() {
		et_odometo = (EditText) view.findViewById(R.id.et_odometo);
		et_nome_da_empresa = (EditText) view
				.findViewById(R.id.et_nome_da_empresa);
		et_nome_do_condutor_do_guincho = (EditText) view
				.findViewById(R.id.et_nome_do_condutor_do_guincho);
		et_tav_observacao = (EditText) view
				.findViewById(R.id.et_tav_observacao);

		btn_tav_gerar = (Button) view.findViewById(R.id.btn_tav_gerar);
		btn_tav_conferir = (Button) view.findViewById(R.id.btn_tav_conferir);

		sp_remocao_atraves_de = (Spinner) view
				.findViewById(R.id.sp_remocao_atraves_de);

		list_remocao_atraves_de = Arrays.asList(getResources().getStringArray(
				R.array.remocao_atraves_de));

		adapter_remocao_atraves_de = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				list_remocao_atraves_de);

		sp_remocao_atraves_de.setAdapter(adapter_remocao_atraves_de);

		linearLayout_parent = (LinearLayout) view
				.findViewById(R.id.linearLayout_parent);
		linearLayout_child = (LinearLayout) view
				.findViewById(R.id.linearLayout_child);

		rg_marcador_de_conbutivel_1 = (RadioGroup) view
				.findViewById(R.id.rg_marcador_de_conbutivel_1);
		rg_marcador_de_conbutivel_2 = (RadioGroup) view
				.findViewById(R.id.rg_marcador_de_conbutivel_2);

		addListener();

	}

	public void getMarcador_de_conbutivel() {
		String s = null;
		if (cb_tav_danificado.isChecked())
			s += AppConstants.COMMA + cb_tav_danificado.getText();
		if (cb_tav_vazio.isChecked())
			s += AppConstants.COMMA + cb_tav_vazio.getText();
		if (cb_tav_14.isChecked())
			s += AppConstants.COMMA + cb_tav_14.getText();
		if (cb_tav_12.isChecked())
			s += AppConstants.COMMA + cb_tav_12.getText();
		if (cb_tav_34.isChecked())
			s += AppConstants.COMMA + cb_tav_34.getText();
		if (cb_tav_cheio.isChecked())
			s += AppConstants.COMMA + cb_tav_cheio.getText();
		if (s != null) {
			s = s.substring(AppConstants.COMMA.length());
			data.setMarcador_de_conbutivel(s);
		}

	}

	private class ChangeText implements TextWatcher {
		private int id;

		public ChangeText(int id) {
			this.id = id;
		}

		@Override
		public void afterTextChanged(Editable editable) {
			String s = editable.toString().trim();
			switch (id) {

			case R.id.et_odometo:
				data.setOdometro(s);
				break;

			case R.id.et_nome_da_empresa:
				data.setNome_da_empresa(s);
				break;

			case R.id.et_nome_do_condutor_do_guincho:
				data.setNome_do_condutor_do_guincho(s);
				break;

			case R.id.et_tav_observacao:
				data.setObservacao(s);
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tav_gerar:
			(DatabaseCreator.getDatabaseAdapterTAV(getActivity()))
					.setData(data);
			getActivity().startActivity(
					new Intent(getActivity(), TAVLister.class));

			getActivity().finish();

			break;

		case R.id.btn_tav_conferir:

			AnyDiaglog.DialogShow(data.getTAVListerView(getActivity()),
					getActivity(), getResources()
							.getString(R.string.listar_tav));

			break;
		}

	}

	private void removeView() {
		if (linearLayout_child.getParent() != null) {
			linearLayout_parent.removeView(linearLayout_child);
		}

	}

	private void addView() {
		if (linearLayout_child.getParent() == null) {
			linearLayout_parent.addView(linearLayout_child);
		}

	}

	@Override
	public void onCheckedChanged(RadioGroup v, int check_id) {

		switch (v.getId()) {
		case R.id.rg_marcador_de_conbutivel_1:
			rg_marcador_de_conbutivel_2.setOnCheckedChangeListener(null);
			rg_marcador_de_conbutivel_2.clearCheck();
			data.setMarcador_de_conbutivel(((RadioButton) (view
					.findViewById(rg_marcador_de_conbutivel_1
							.getCheckedRadioButtonId()))).getText().toString());
			rg_marcador_de_conbutivel_2.setOnCheckedChangeListener(this);

			break;

		case R.id.rg_marcador_de_conbutivel_2:
			rg_marcador_de_conbutivel_1.setOnCheckedChangeListener(null);
			rg_marcador_de_conbutivel_1.clearCheck();
			data.setMarcador_de_conbutivel(((RadioButton) (view
					.findViewById(rg_marcador_de_conbutivel_2
							.getCheckedRadioButtonId()))).getText().toString());
			rg_marcador_de_conbutivel_1.setOnCheckedChangeListener(this);

			break;

		}

	}

}

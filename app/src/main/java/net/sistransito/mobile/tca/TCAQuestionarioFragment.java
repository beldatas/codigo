package net.sistransito.mobile.tca;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import net.sistransito.bibliotecas.datepicker.DateListener;
import net.sistransito.bibliotecas.datepicker.MyDatePicker;
import net.sistransito.bibliotecas.datepicker.MyTimePicker;
import net.sistransito.bibliotecas.datepicker.TimeListener;
import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.fragment.AnyDiaglog;
import net.sistransito.mobile.tca.lister.TCALister;
import net.sistrnsitomobile.R;

public class TCAQuestionarioFragment extends Fragment implements
		OnClickListener, TimeListener, DateListener, OnCheckedChangeListener,
		CompoundButton.OnCheckedChangeListener {
	private Bundle bundle;
	private MyTimePicker myTimePicker;
	private MyDatePicker myDatePicker;
	private View view;
	private Button btn_tca_conferir, btn_tca_gerar,
			btn_tca_date_picker_condutor_alcoolica,
			btn_tca_time_picker_condutor_alcoolica,
			btn_tca_date_picker_condutor_toxica,
			btn_tca_time_picker_condutor_toxica;
	private TcaData data;
	private RadioGroup rg_condutor_transito, rg_condutor_alcoolica,
			rg_condutor_toxica, rg_sabe_onde_esta, rg_sabe_a_data_e_a_hora,
			rg_sabe_seu_enderecao, rg_lembra_dos_atos_cometidos, rg_conclusao;

	private LinearLayout linear_layout_alcoolica_parent,
			linear_layout_alcoolica_child, linear_layout_toxica_parent,
			linear_layout_toxica_child;

	private String sim, nao;
	private String button_data_text, buttton_hora_text;

	private CheckBox checkBox_condutor_apresenta_1,
			checkBox_condutor_apresenta_2, checkBox_condutor_apresenta_3,
			checkBox_condutor_apresenta_4, checkBox_condutor_apresenta_5,
			checkBox_condutor_apresenta_6;

	private CheckBox cb_atitude_ocorre_1, cb_atitude_ocorre_2,
			cb_atitude_ocorre_3, cb_atitude_ocorre_4, cb_atitude_ocorre_5,
			cb_atitude_ocorre_6;

	private CheckBox cb_tca_em_relacao_a_sua_verbal_ocorre_1,
			cb_tca_em_relacao_a_sua_verbal_ocorre_2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater
				.inflate(R.layout.tca_questionario_fragment, null, false);
		initializedView();
		getTCAObject();
		addListener();
		return view;
	}

	public static  TCAQuestionarioFragment newInstance() {
		return new TCAQuestionarioFragment();
	}

	private void addListener() {
		btn_tca_gerar.setOnClickListener(this);
		btn_tca_conferir.setOnClickListener(this);
		rg_condutor_transito.setOnCheckedChangeListener(this);
		rg_condutor_alcoolica.setOnCheckedChangeListener(this);
		rg_condutor_toxica.setOnCheckedChangeListener(this);

		rg_sabe_onde_esta.setOnCheckedChangeListener(this);
		rg_sabe_a_data_e_a_hora.setOnCheckedChangeListener(this);
		rg_sabe_seu_enderecao.setOnCheckedChangeListener(this);
		rg_lembra_dos_atos_cometidos.setOnCheckedChangeListener(this);
		rg_conclusao.setOnCheckedChangeListener(this);

		btn_tca_date_picker_condutor_toxica.setOnClickListener(this);
		btn_tca_time_picker_condutor_toxica.setOnClickListener(this);

		btn_tca_date_picker_condutor_alcoolica.setOnClickListener(this);
		btn_tca_time_picker_condutor_alcoolica.setOnClickListener(this);

		checkBox_condutor_apresenta_1.setOnCheckedChangeListener(this);
		checkBox_condutor_apresenta_2.setOnCheckedChangeListener(this);
		checkBox_condutor_apresenta_3.setOnCheckedChangeListener(this);
		checkBox_condutor_apresenta_4.setOnCheckedChangeListener(this);
		checkBox_condutor_apresenta_5.setOnCheckedChangeListener(this);
		checkBox_condutor_apresenta_6.setOnCheckedChangeListener(this);

		cb_atitude_ocorre_1.setOnCheckedChangeListener(this);
		cb_atitude_ocorre_2.setOnCheckedChangeListener(this);
		cb_atitude_ocorre_3.setOnCheckedChangeListener(this);
		cb_atitude_ocorre_4.setOnCheckedChangeListener(this);
		cb_atitude_ocorre_5.setOnCheckedChangeListener(this);
		cb_atitude_ocorre_6.setOnCheckedChangeListener(this);

		cb_tca_em_relacao_a_sua_verbal_ocorre_1
				.setOnCheckedChangeListener(this);
		cb_tca_em_relacao_a_sua_verbal_ocorre_2
				.setOnCheckedChangeListener(this);

	}

	private void getTCAObject() {
		data = TCAObgect.getTCAOject();
		getRecomandedUpdate();
	}

	private void initializedView() {
		sim = (getResources().getString(R.string.tca_sim)).trim();
		nao = (getResources().getString(R.string.tca_title_nao)).trim();

		button_data_text = getResources().getString(R.string.tca_data);
		buttton_hora_text = getResources().getString(R.string.tca_hora);

		

		btn_tca_gerar = (Button) view.findViewById(R.id.btn_tca_gerar);
		btn_tca_conferir = (Button) view.findViewById(R.id.btn_tca_conferir);
		rg_condutor_transito = (RadioGroup) view
				.findViewById(R.id.rg_condutor_transito);
		rg_condutor_alcoolica = (RadioGroup) view
				.findViewById(R.id.rg_condutor_alcoolica);
		rg_condutor_toxica = (RadioGroup) view
				.findViewById(R.id.rg_condutor_toxica);
		rg_sabe_onde_esta = (RadioGroup) view
				.findViewById(R.id.rg_sabe_onde_esta);
		rg_sabe_a_data_e_a_hora = (RadioGroup) view
				.findViewById(R.id.rg_sabe_a_data_e_a_hora);

		rg_sabe_seu_enderecao = (RadioGroup) view
				.findViewById(R.id.rg_sabe_seu_enderecao);

		rg_lembra_dos_atos_cometidos = (RadioGroup) view
				.findViewById(R.id.rg_lembra_dos_atos_cometidos);
		rg_conclusao = (RadioGroup) view.findViewById(R.id.rg_conclusao);

		linear_layout_alcoolica_parent = (LinearLayout) view
				.findViewById(R.id.linear_layout_alcoolica_parent);
		linear_layout_alcoolica_child = (LinearLayout) view
				.findViewById(R.id.linear_layout_alcoolica_child);

		linear_layout_toxica_parent = (LinearLayout) view
				.findViewById(R.id.linear_layout_toxica_parent);
		linear_layout_toxica_child = (LinearLayout) view
				.findViewById(R.id.linear_layout_toxica_child);

		btn_tca_date_picker_condutor_toxica = (Button) view
				.findViewById(R.id.btn_tca_date_picker_condutor_toxica);
		btn_tca_time_picker_condutor_toxica = (Button) view
				.findViewById(R.id.btn_tca_time_picker_condutor_toxica);
		btn_tca_date_picker_condutor_alcoolica = (Button) view
				.findViewById(R.id.btn_tca_date_picker_condutor_alcoolica);
		btn_tca_time_picker_condutor_alcoolica = (Button) view
				.findViewById(R.id.btn_tca_time_picker_condutor_alcoolica);

		removeAlcooliaView();
		removeToxicaView();

		checkBox_condutor_apresenta_1 = (CheckBox) view
				.findViewById(R.id.checkBox_condutor_apresenta_1);
		checkBox_condutor_apresenta_2 = (CheckBox) view
				.findViewById(R.id.checkBox_condutor_apresenta_2);
		checkBox_condutor_apresenta_3 = (CheckBox) view
				.findViewById(R.id.checkBox_condutor_apresenta_3);
		checkBox_condutor_apresenta_4 = (CheckBox) view
				.findViewById(R.id.checkBox_condutor_apresenta_4);
		checkBox_condutor_apresenta_5 = (CheckBox) view
				.findViewById(R.id.checkBox_condutor_apresenta_5);
		checkBox_condutor_apresenta_6 = (CheckBox) view
				.findViewById(R.id.checkBox_condutor_apresenta_6);

		cb_atitude_ocorre_1 = (CheckBox) view
				.findViewById(R.id.cb_atitude_ocorre_1);
		cb_atitude_ocorre_2 = (CheckBox) view
				.findViewById(R.id.cb_atitude_ocorre_2);
		cb_atitude_ocorre_3 = (CheckBox) view
				.findViewById(R.id.cb_atitude_ocorre_3);
		cb_atitude_ocorre_4 = (CheckBox) view
				.findViewById(R.id.cb_atitude_ocorre_4);
		cb_atitude_ocorre_5 = (CheckBox) view
				.findViewById(R.id.cb_atitude_ocorre_5);
		cb_atitude_ocorre_6 = (CheckBox) view
				.findViewById(R.id.cb_atitude_ocorre_6);

		cb_tca_em_relacao_a_sua_verbal_ocorre_1 = (CheckBox) view
				.findViewById(R.id.cb_tca_em_relacao_a_sua_verbal_ocorre_1);

		cb_tca_em_relacao_a_sua_verbal_ocorre_2 = (CheckBox) view
				.findViewById(R.id.cb_tca_em_relacao_a_sua_verbal_ocorre_2);
	}

	private void getRecomandedUpdate() {
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_tca_conferir:
			AnyDiaglog
					.DialogShow(data.getTCAViewData(getActivity()),
							getActivity(), getActivity().getResources()
									.getString(R.string.listar_tca));
			break;
		case R.id.btn_tca_gerar:
			(DatabaseCreator.getDatabaseAdapterTCA(getActivity()))
					.setData(data);
			getActivity().startActivity(
					new Intent(getActivity(), TCALister.class));
			getActivity().finish();

			break;

		case R.id.btn_tca_date_picker_condutor_toxica:
			callDatePicker(R.id.btn_tca_date_picker_condutor_toxica);
			break;
		case R.id.btn_tca_date_picker_condutor_alcoolica:
			callDatePicker(R.id.btn_tca_date_picker_condutor_alcoolica);
			break;

		case R.id.btn_tca_time_picker_condutor_alcoolica:
			callTimePicker(R.id.btn_tca_time_picker_condutor_alcoolica);
			break;

		case R.id.btn_tca_time_picker_condutor_toxica:
			callTimePicker(R.id.btn_tca_time_picker_condutor_toxica);
			break;
		}

	}

	@Override
	public void onCheckedChanged(RadioGroup view, int check_id) {
		switch (view.getId()) {
		case R.id.rg_condutor_transito:
			if (check_id != -1) {
				String value = ((RadioButton) view
						.findViewById(rg_condutor_transito
								.getCheckedRadioButtonId())).getText()
						.toString();
				value = value.trim();
				data.setCondutor_envolveu_se_em_acidente_de_transito(value);
			}
			break;
		case R.id.rg_condutor_alcoolica:
			if (check_id != -1) {
				if (rg_condutor_alcoolica.getCheckedRadioButtonId() == R.id.rd_condutor_alcoolica_sim) {
					addAlcooliaView();
					data.setCondutor_declara_ter_ingerido_bebida_alcoolica(sim);
				} else if (rg_condutor_alcoolica.getCheckedRadioButtonId() == R.id.rd_condutor_alcoolica_nao) {
					removeAlcooliaView();
					data.setCondutor_declara_ter_ingerido_bebida_alcoolica(nao);
					btn_tca_date_picker_condutor_alcoolica
							.setText(button_data_text);
					btn_tca_time_picker_condutor_alcoolica
							.setText(buttton_hora_text);
				}
			}
			break;

		case R.id.rg_condutor_toxica:

			if (check_id != -1) {

				if (rg_condutor_toxica.getCheckedRadioButtonId() == R.id.rd_condutor_toxica_sim) {
					addToxicaView();
					data.setCondutor_declara_ter_feito_uso_de_substancia_toxica(sim);

				} else if (rg_condutor_toxica.getCheckedRadioButtonId() == R.id.rd_condutor_toxica_nao) {
					removeToxicaView();
					data.setCondutor_declara_ter_feito_uso_de_substancia_toxica(nao);

					btn_tca_date_picker_condutor_toxica
							.setText(button_data_text);
					btn_tca_time_picker_condutor_toxica
							.setText(buttton_hora_text);
				}

			}
			break;

		case R.id.rg_sabe_onde_esta:
			if (check_id != -1) {

				data.setSabe_onde_esta(((RadioButton) view
						.findViewById(rg_sabe_onde_esta
								.getCheckedRadioButtonId())).getText()
						.toString().trim());

			}
			break;

		case R.id.rg_sabe_a_data_e_a_hora:
			if (check_id != -1) {

				data.setSabe_a_data_e_a_hora(((RadioButton) view
						.findViewById(rg_sabe_a_data_e_a_hora
								.getCheckedRadioButtonId())).getText()
						.toString().trim());

			}
			break;
		case R.id.rg_sabe_seu_enderecao:
			if (check_id != -1) {

				data.setSabe_seu_endereco(((RadioButton) view
						.findViewById(rg_sabe_seu_enderecao
								.getCheckedRadioButtonId())).getText()
						.toString().trim());

			}
			break;
		case R.id.rg_lembra_dos_atos_cometidos:
			if (check_id != -1) {

				data.setLembra_dos_atos_cometidos(((RadioButton) view
						.findViewById(rg_lembra_dos_atos_cometidos
								.getCheckedRadioButtonId())).getText()
						.toString().trim());

			}
			break;

		case R.id.rg_conclusao:
			if (check_id != -1) {
				data.setConclusao(((RadioButton) view.findViewById(rg_conclusao
						.getCheckedRadioButtonId())).getText().toString()
						.trim());
			}
			break;
		}

	}

	private void removeAlcooliaView() {
		if (linear_layout_alcoolica_child.getParent() != null)
			linear_layout_alcoolica_parent
					.removeView(linear_layout_alcoolica_child);

	}

	private void addAlcooliaView() {
		if (linear_layout_alcoolica_child.getParent() == null)
			linear_layout_alcoolica_parent
					.addView(linear_layout_alcoolica_child);

	}

	private void removeToxicaView() {
		if (linear_layout_toxica_child.getParent() != null)
			linear_layout_toxica_parent.removeView(linear_layout_toxica_child);

	}

	private void addToxicaView() {
		if (linear_layout_toxica_child.getParent() == null)
			linear_layout_toxica_parent.addView(linear_layout_toxica_child);

	}

	@Override
	public void date(String date, int view_id) {
		Log.d("date_picker", "date_picker");
		if (date != null) {
			switch (view_id) {
			case R.id.btn_tca_date_picker_condutor_toxica:

				btn_tca_date_picker_condutor_toxica.setText(date);
				data.setData_ingeriu_substancia(date);
				break;
			case R.id.btn_tca_date_picker_condutor_alcoolica:
				btn_tca_date_picker_condutor_alcoolica.setText(date);
				data.setData_ingeriu_alcool(date);

				break;
			}

		}

	}

	@Override
	public void time(String time, int view_id) {
		if (time != null) {
			switch (view_id) {

			case R.id.btn_tca_time_picker_condutor_toxica:

				btn_tca_time_picker_condutor_toxica.setText(time);
				data.setHora_ingeriu_substancia(time);
				break;
			case R.id.btn_tca_time_picker_condutor_alcoolica:
				btn_tca_time_picker_condutor_alcoolica.setText(time);
				data.setHora_ingeriu_alcool(time);

				break;
			}

		}

	}

	private void callTimePicker(int id) {
		bundle = new Bundle();
		myTimePicker = new MyTimePicker();
		myTimePicker.setTargetFragment(this, 0);
		bundle.putInt(MyTimePicker.MY_TIME_PICKER_ID, id);
		myTimePicker.setArguments(bundle);
		myTimePicker.show(getActivity().getSupportFragmentManager(), "time");

	}

	private void callDatePicker(int id) {
		bundle = new Bundle();
		myDatePicker = new MyDatePicker();
		myDatePicker.setTargetFragment(this, 0);
		bundle.putInt(MyDatePicker.MY_DATE_PICKER_ID, id);
		myDatePicker.setArguments(bundle);
		myDatePicker.show(getActivity().getSupportFragmentManager(), "date");

	}

	@Override
	public void onCheckedChanged(CompoundButton view, boolean isChecked) {

		switch (view.getId()) {
		case R.id.checkBox_condutor_apresenta_1:
		case R.id.checkBox_condutor_apresenta_2:
		case R.id.checkBox_condutor_apresenta_3:
		case R.id.checkBox_condutor_apresenta_4:
		case R.id.checkBox_condutor_apresenta_5:
		case R.id.checkBox_condutor_apresenta_6:
			get_O_condutor_apresenta_sinais_de();
			break;
		case R.id.cb_atitude_ocorre_1:
		case R.id.cb_atitude_ocorre_2:
		case R.id.cb_atitude_ocorre_3:
		case R.id.cb_atitude_ocorre_4:
		case R.id.cb_atitude_ocorre_5:
		case R.id.cb_atitude_ocorre_6:
			getEm_sua_atitude_ocorre();
			break;

		case R.id.cb_tca_em_relacao_a_sua_verbal_ocorre_1:
		case R.id.cb_tca_em_relacao_a_sua_verbal_ocorre_2:
			getem_relacao_a_sua_verbal_ocorre();

			break;

		}
	}

	private void get_O_condutor_apresenta_sinais_de() {
		String format = "";

		if (checkBox_condutor_apresenta_1.isChecked()) {
			format += (AppConstants.COMMA + checkBox_condutor_apresenta_1
					.getText().toString());
		}
		if (checkBox_condutor_apresenta_2.isChecked()) {
			format += (AppConstants.COMMA + checkBox_condutor_apresenta_2
					.getText().toString());
		}
		if (checkBox_condutor_apresenta_3.isChecked()) {
			format += (AppConstants.COMMA + checkBox_condutor_apresenta_3
					.getText().toString());
		}
		if (checkBox_condutor_apresenta_4.isChecked()) {
			format += (AppConstants.COMMA + checkBox_condutor_apresenta_4
					.getText().toString());
		}
		if (checkBox_condutor_apresenta_5.isChecked()) {
			format += (AppConstants.COMMA + checkBox_condutor_apresenta_5
					.getText().toString());
		}
		if (checkBox_condutor_apresenta_6.isChecked()) {
			format += (AppConstants.COMMA + checkBox_condutor_apresenta_6
					.getText().toString());
		}
		if (!format.equals("")) {
			Log.d("dddddddddddddddddddddddd", format);
			format = (format.substring(AppConstants.COMMA.length()));
			format = format.trim();
			data.setO_condutor_apresenta_sinais_de(format);

			Log.d("dddddddddddddddddddddddd", format);
		}
	}

	private void getEm_sua_atitude_ocorre() {
		String format = "";
		if (cb_atitude_ocorre_1.isChecked()) {
			format += (AppConstants.COMMA + cb_atitude_ocorre_1.getText()
					.toString());
		}
		if (cb_atitude_ocorre_2.isChecked()) {
			format += (AppConstants.COMMA + cb_atitude_ocorre_2.getText()
					.toString());
		}
		if (cb_atitude_ocorre_3.isChecked()) {
			format += (AppConstants.COMMA + cb_atitude_ocorre_3.getText()
					.toString());
		}
		if (cb_atitude_ocorre_4.isChecked()) {
			format += (AppConstants.COMMA + cb_atitude_ocorre_4.getText()
					.toString());
		}
		if (cb_atitude_ocorre_5.isChecked()) {
			format += (AppConstants.COMMA + cb_atitude_ocorre_5.getText()
					.toString());
		}
		if (cb_atitude_ocorre_6.isChecked()) {
			format += (AppConstants.COMMA + cb_atitude_ocorre_6.getText()
					.toString());
		}
		if (!format.equals("")) {
			format = (format.substring(AppConstants.COMMA.length()));
			format = format.trim();
			data.setEm_sua_atitude_ocorre(format);
		}
	}

	private void getem_relacao_a_sua_verbal_ocorre() {

		String format = "";
		if (cb_tca_em_relacao_a_sua_verbal_ocorre_1.isChecked()) {
			format += (AppConstants.NEW_LINE + cb_tca_em_relacao_a_sua_verbal_ocorre_1
					.getText().toString());
		}
		if (cb_tca_em_relacao_a_sua_verbal_ocorre_2.isChecked()) {
			format += (AppConstants.NEW_LINE + cb_tca_em_relacao_a_sua_verbal_ocorre_2
					.getText().toString());

		}
		if (!format.equals("")) {
			format = (format.substring(AppConstants.NEW_LINE.length()));
			format = format.trim();
			data.setEm_relaco_a_sua_capacidade_motora_e_verbal_ocorre(format);
		}
	}

}
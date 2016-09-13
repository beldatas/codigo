package net.sistransito.mobile.autoinfracao;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import net.sistransito.bibliotecas.datepicker.DateListener;
import net.sistransito.bibliotecas.datepicker.MyDatePicker;
import net.sistransito.bibliotecas.datepicker.MyTimePicker;
import net.sistransito.bibliotecas.datepicker.TimeListener;
import net.sistransito.mobile.adapter.AnyArrayAdapter;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.database.PrepopulatedDBOpenHelper;
import net.sistrnsitomobile.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AutoDeInfracaoFragment extends
		Fragment implements
		OnClickListener, TimeListener, DateListener {

	// TextView and AutoCompleteTextView auto_autode_Municipio
	// TextView and EditView codigo_do_municipio
	// TextView and EditView auto_de_UF
	// TextView and EditView et_autode_local
	// Button btn_autode_date_picker and btn_autode_time_picker
	
	private View view;
	private AutoCompleteTextView autoCompleteInfracao,
			autoCompleteMunicipios;
	private DadosDoAuto data;
	private EditText et_auto_enquadra, et_auto_desdob, et_auto_art,
			et_auto_de_codigo_do_municipio, auto_de_uf, et_auto_local,
			et_auto_marca, et_auto_modelo_veiculo, et_auto_numero_de_serie,
			et_auto_medicao_realizada, et_auto_valor,
			et_auto_numero_da_amostra;
	private Button btn_auto_date_picker, btn_auto_time_picker;
	private Spinner spinner_auto_descricao;
	private List<String> list_descricao, list_marca, list_modelo,
			list_numero_de_serie;
	private AnyArrayAdapter<String> adapter_descricao;
	private List<String> municipio_array, cod_array, uf_array, auto_infracao,
			auto_observacao, auto_desdob, auto_art, auto_enquadra;
	private ArrayAdapter<String> adapter_municipio, adapter_infracao;

	private TextView tv_auto_autode_Muni, tv_autode_de_codigo_do_municipio,
			tv_auto_de_UF;

	private Bundle bundle;

	public static AutoDeInfracaoFragment newInstance() {
		return new AutoDeInfracaoFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.auto_infracao_fragment, null, false);
		initializedView();
		getAutodeObject();
		return view;
	}

	private void initializedView() {
		autoCompleteInfracao = (AutoCompleteTextView) view.findViewById(R.id.tv_autocomplete_infracao);
		et_auto_enquadra = (EditText) view.findViewById(R.id.et_autode_enquadra);
		et_auto_desdob = (EditText) view.findViewById(R.id.et_autode_desdob);
		et_auto_art = (EditText) view.findViewById(R.id.et_autode_amparo_legal);

		tv_auto_autode_Muni = (TextView) view
				.findViewById(R.id.tv_auto_autode_Muni);
		autoCompleteMunicipios = (AutoCompleteTextView) view
				.findViewById(R.id.auto_autode_Muni);

		tv_autode_de_codigo_do_municipio = (TextView) view
				.findViewById(R.id.tv_autode_de_codigo_do_municipio);
		et_auto_de_codigo_do_municipio = (EditText) view
				.findViewById(R.id.et_autode_de_codigo_do_municipio);
		auto_de_uf = (EditText) view.findViewById(R.id.auto_de_UF);

		tv_auto_de_UF = (TextView) view.findViewById(R.id.tv_auto_de_UF);
		et_auto_local = (EditText) view.findViewById(R.id.et_autode_local);
		btn_auto_date_picker = (Button) view
				.findViewById(R.id.btn_autode_date_picker);
		btn_auto_time_picker = (Button) view
				.findViewById(R.id.btn_autode_time_picker);

		et_auto_marca = (EditText) view.findViewById(R.id.et_autode_marca);
		et_auto_modelo_veiculo = (EditText) view
				.findViewById(R.id.et_autode_modelo);
		et_auto_marca.setEnabled(false);
		et_auto_modelo_veiculo.setEnabled(false);

		et_auto_numero_de_serie = (EditText) view
				.findViewById(R.id.et_autode_numero_de_serie);
		et_auto_numero_de_serie.setEnabled(false);
		et_auto_medicao_realizada = (EditText) view
				.findViewById(R.id.et_autode_medicao_realizada);
		et_auto_valor = (EditText) view.findViewById(R.id.et_autode_valor);
		et_auto_valor.setEnabled(false);
		et_auto_numero_da_amostra = (EditText) view
				.findViewById(R.id.et_autode_n_da_amostra);

		et_auto_de_codigo_do_municipio.setEnabled(false);
		auto_de_uf.setEnabled(false);
		et_auto_enquadra.setEnabled(false);
		et_auto_desdob.setEnabled(false);
		et_auto_art.setEnabled(false);

		setMunicipioAutoComplete();
		setInfracaoAutoComplete();
		setDescricaoSpinner();

	}

	private void setDescricaoSpinner() {

		list_descricao = new ArrayList<String>();
		list_marca = new ArrayList<String>();
		list_modelo = new ArrayList<String>();
		list_numero_de_serie = new ArrayList<String>();

		Cursor cursor = ((DatabaseCreator
				.getPrepopulatedDBOpenHelper(getActivity()))
				.getAutoEquipamentoEntryCoursor());
		cursor.moveToFirst();

		for (int i = 0; i < cursor.getCount(); i++) {

			list_descricao
					.add(cursor.getString(cursor
							.getColumnIndex(PrepopulatedDBOpenHelper.AutoEquipamentoEntry_COLUMN_DESCRICAO)));

			list_marca
					.add(cursor.getString(cursor
							.getColumnIndex(PrepopulatedDBOpenHelper.AutoEquipamentoEntry_COLUMN_MARCA)));

			list_modelo
					.add(cursor.getString(cursor
							.getColumnIndex(PrepopulatedDBOpenHelper.AutoEquipamentoEntry_COLUMN_MODELO)));
			list_numero_de_serie
					.add(cursor.getString(cursor
							.getColumnIndex(PrepopulatedDBOpenHelper.AutoEquipamentoEntry_COLUMN_NUMERO_SERIE)));

			cursor.moveToNext();
		}
		cursor.close();

		adapter_descricao = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				list_descricao);

		spinner_auto_descricao = (Spinner) view
				.findViewById(R.id.spinner_autode_descricao);
		spinner_auto_descricao.setAdapter(adapter_descricao);

		spinner_auto_descricao
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
						Log.d("descricao", "ok");
						data.setDescricao((String) parent
								.getItemAtPosition(pos));
						int realpositon = list_descricao.indexOf(data.getDescricao());

						data.setMarca(list_marca.get(realpositon));
						data.setModelo(list_modelo.get(realpositon));
						data.setNumero_de_serie(list_numero_de_serie
								.get(realpositon));

						et_auto_marca.setText(data.getMarca());
						et_auto_modelo_veiculo.setText(data.getModelo());
						et_auto_numero_de_serie.setText(data
								.getNumero_de_serie());

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

	private void setInfracaoAutoComplete() {

		Cursor cursor = (DatabaseCreator
				.getPrepopulatedDBOpenHelper(getActivity()))
				.getInfracaoCoursor();
		auto_infracao = new ArrayList<String>();
		auto_desdob = new ArrayList<String>();
		auto_art = new ArrayList<String>();
		auto_enquadra = new ArrayList<String>();
		auto_observacao = new ArrayList<String>();
		for (int i = 0; i < cursor.getCount(); i++) {
			auto_infracao
					.add(cursor.getString(cursor
							.getColumnIndex(PrepopulatedDBOpenHelper.INFRACOES_INFRACOES)));
			auto_enquadra
					.add(cursor.getString(cursor
							.getColumnIndex(PrepopulatedDBOpenHelper.INFRACOES_ENQUADRA)));
			auto_desdob
					.add(cursor.getString(cursor
							.getColumnIndex(PrepopulatedDBOpenHelper.INFRACOES_DESDOB)));
			auto_art.add(cursor.getString(cursor
					.getColumnIndex(PrepopulatedDBOpenHelper.INFRACOES_ART)));

			auto_observacao
					.add(cursor.getString(cursor
							.getColumnIndex(PrepopulatedDBOpenHelper.INFRACOES_OBSERVACAO)));
			cursor.moveToNext();
		}
		cursor.close();

		adapter_infracao = new ArrayAdapter<String>(getActivity(),
				R.layout.custom_autocompletar, R.id.autoCompleteItem,
				auto_infracao);

		autoCompleteInfracao.setAdapter(adapter_infracao);
		autoCompleteInfracao
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
											int pos, long id) {

						data.setInfracao((String) parent.getItemAtPosition(pos));
						int real_position = auto_infracao.indexOf(data
								.getInfracao());
						data.setEnquadra(auto_enquadra.get(real_position));
						et_auto_enquadra.setText(data.getEnquadra());
						data.setDesdob(auto_desdob.get(real_position));
						et_auto_desdob.setText(data.getDesdob());
						data.setAmparo_legal(auto_art.get(real_position));
						et_auto_art.setText(data.getAmparo_legal());
						data.setObservacao(auto_observacao.get(real_position));
					}
				});

	}

	private void addListener() {

		et_auto_local.addTextChangedListener(new ChangeText(
				R.id.et_autode_local));
		et_auto_marca.addTextChangedListener(new ChangeText(
				R.id.et_autode_marca));
		et_auto_numero_de_serie.addTextChangedListener(new ChangeText(
				R.id.et_autode_numero_de_serie));
		et_auto_modelo_veiculo.addTextChangedListener(new ChangeText(
				R.id.et_autode_modelo));
		et_auto_medicao_realizada.addTextChangedListener(new ChangeText(
				R.id.et_autode_medicao_realizada));
		et_auto_valor.addTextChangedListener(new ChangeText(
				R.id.et_autode_valor));
		et_auto_numero_da_amostra.addTextChangedListener(new ChangeText(
				R.id.et_autode_n_da_amostra));
		btn_auto_date_picker.setOnClickListener(this);
		btn_auto_time_picker.setOnClickListener(this);

	}

	private void getAutodeObject() {
		data = AutoObjeto.getAutoDeOject();

		if (data.isDataisNull()) {
			addListener();
		} else if (data.isStoreFullData()) {
			getRecomandedUpdate();
			addListener();
			setViewNovoAuto();
		} else {
			addListener();
			initializedSelectetItems();
		}

	}

	private void initializedSelectetItems() {
		// et_auto_enquadra.setText("");
		// et_auto_desdob.setText("");
		// et_auto_art.setText("");
		// et_auto_de_codigo_do_municipio.setText("");
		// auto_de_uf.setText("");
		// et_auto_local.setText("");
		// et_auto_marca.setText("");
		// et_auto_modelo_veiculo.setText("");
		// et_auto_numero_de_serie.setText("");
		// et_auto_medicao_realizada.setText("");
		// et_auto_valor.setText("");
		// et_auto_numero_da_amostra.setText("");
		// spinner_auto_descricao.setSelection(0);

	}

	private void getRecomandedUpdate() {
		et_auto_enquadra.setText(data.getEnquadra());
		et_auto_desdob.setText(data.getDesdob());
		et_auto_art.setText(data.getAmparo_legal());
		et_auto_de_codigo_do_municipio.setText(data.getCodigo_do_municipio());
		auto_de_uf.setText(data.getUf());
		et_auto_local.setText(data.getLocal());
		et_auto_marca.setText(data.getMarca());
		et_auto_modelo_veiculo.setText(data.getModelo());
		et_auto_numero_de_serie.setText(data.getNumero_de_serie());
		et_auto_medicao_realizada.setText(data.getMedicao_realizada());
		et_auto_valor.setText(data.getValor_considerada());
		et_auto_numero_da_amostra.setText(data.getN_da_amostra());
		spinner_auto_descricao.setSelection(0);

	}

	private class ChangeText implements TextWatcher {
		private int id;

		public ChangeText(int id) {
			this.id = id;
		}

		@Override
		public void afterTextChanged(Editable edit) {
			String s = (edit.toString()).trim();
			switch (id) {
			case R.id.et_autode_local:
				data.setLocal(s.toString());
				break;
			case R.id.et_autode_marca:
				data.setMarca(s.toString());
				break;
			case R.id.et_autode_numero_de_serie:
				data.setNumero_de_serie(s.toString());
				break;
			case R.id.et_autode_modelo:
				data.setModelo(s.toString());
				break;
			case R.id.et_autode_medicao_realizada:
				if (!s.equals(""))
					if (checkNumberMedicaoNumber(s.toString())) {
						data.setMedicao_realizada(s.toString());
					} else {
						et_auto_medicao_realizada.setText("");
					}
				break;
			case R.id.et_autode_valor:
				data.setValor_considerada(s.toString());
				break;
			case R.id.et_autode_n_da_amostra:
				data.setN_da_amostra(s.toString());
				break;
			}
		}

		private boolean checkNumberMedicaoNumber(String s) {

			try {
				double medicaoConsiderada = 0.00;
				double medicaoRealizada = Double.valueOf(s);

				if (medicaoRealizada < 0.05) {
					medicaoRealizada = 0.00;
				} else if (medicaoRealizada >= 0.05
						&& medicaoRealizada <= 0.40) {
					medicaoConsiderada = medicaoRealizada - 0.032;
				} else if (medicaoRealizada > 0.40 && medicaoRealizada <= 2) {
					medicaoConsiderada = medicaoRealizada - (medicaoRealizada * 8) / 100;
				} else {
					medicaoConsiderada = medicaoRealizada - (medicaoRealizada * 30) / 100;
				}

				DecimalFormat df = new DecimalFormat("#.##");
				medicaoConsiderada = Double.valueOf(df.format(medicaoConsiderada));
				et_auto_valor.setText(String.valueOf(medicaoConsiderada));

				return true;

			} catch (NumberFormatException e) {
				return false;
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
		case R.id.btn_autode_date_picker:
			MyDatePicker pickerdate = new MyDatePicker();
			bundle = new Bundle();
			bundle.putInt(MyDatePicker.MY_DATE_PICKER_ID,
					R.id.btn_autode_date_picker);
			pickerdate.setArguments(bundle);
			pickerdate.setTargetFragment(this, 0);
			pickerdate.show(getActivity().getSupportFragmentManager(), "date");
			break;

		case R.id.btn_autode_time_picker:
			MyTimePicker picker = new MyTimePicker();
			bundle = new Bundle();
			bundle.putInt(MyTimePicker.MY_TIME_PICKER_ID,
					R.id.btn_autode_time_picker);
			picker.setArguments(bundle);
			picker.setTargetFragment(this, 0);
			picker.show(getActivity().getSupportFragmentManager(), "time");
			break;
		}

	}

	public void setMunicipioAutoComplete() {
		municipio_array = new ArrayList<String>();
		cod_array = new ArrayList<String>();
		uf_array = new ArrayList<String>();

		Cursor myCursor = ((DatabaseCreator
				.getPrepopulatedDBOpenHelper(getActivity()))
				.getMunicipioCursor());
		do {
			municipio_array.add(myCursor.getString(myCursor.getColumnIndex(PrepopulatedDBOpenHelper.NOME_DO_MUNICIPIO)));
			uf_array.add(myCursor.getString(myCursor.getColumnIndex(PrepopulatedDBOpenHelper.MUNICIPIOS_UF)));
			cod_array.add(myCursor.getString(myCursor.getColumnIndex(PrepopulatedDBOpenHelper.MUNICIPIOS_COD)));
		} while (myCursor.moveToNext());
		    myCursor.close();
		    adapter_municipio = new ArrayAdapter<String>(getActivity(), R.layout.custom_autocompletar, R.id.autoCompleteItem, municipio_array);
		    autoCompleteMunicipios.setAdapter(adapter_municipio);

		    autoCompleteMunicipios.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
										int pos, long arg3) {
					data.setMunicipio((String) parent
							.getItemAtPosition(pos));
					int real_position = municipio_array
							.indexOf((String) parent.getItemAtPosition(pos));
					data.setCodigo_do_municipio(cod_array
							.get(real_position));
					et_auto_de_codigo_do_municipio.setText(data
							.getCodigo_do_municipio());
					data.setUf(uf_array.get(real_position));
					auto_de_uf.setText(data.getUf());

				}
			});
	}

	@Override
	public void time(String time, int view_id) {
		if (time != null) {
			data.setHora(time);
			btn_auto_time_picker.setText(data.getHora());
		}

	}

	@Override
	public void date(String date, int view_id) {
		if (date != null) {
			data.setData(date);
			btn_auto_date_picker.setText(data.getData());
		}

	}

	private void setViewNovoAuto() {
		autoCompleteMunicipios.setVisibility(AutoCompleteTextView.GONE);
		et_auto_de_codigo_do_municipio.setVisibility(TextView.GONE);
		tv_auto_autode_Muni.setVisibility(TextView.GONE);
		tv_autode_de_codigo_do_municipio.setVisibility(TextView.GONE);
		et_auto_de_codigo_do_municipio.setVisibility(EditText.GONE);
		auto_de_uf.setVisibility(EditText.GONE);
		tv_auto_de_UF.setVisibility(TextView.GONE);
		btn_auto_date_picker.setVisibility(Button.GONE);
		btn_auto_time_picker.setVisibility(Button.GONE);

	}

}

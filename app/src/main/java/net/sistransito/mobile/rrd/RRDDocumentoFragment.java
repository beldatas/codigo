package net.sistransito.mobile.rrd;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import net.sistransito.bibliotecas.datepicker.DateListener;
import net.sistransito.bibliotecas.datepicker.MyDatePicker;
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

public class RRDDocumentoFragment extends Fragment implements
		AnyDialogListener, OnClickListener, DateListener {
	private View view;
	private EditText et_rrd_n_auto_de_infracao, et_rrd_nome_do_condutor,
			et_rrd_n_crlv, et_rrd_placa, et_rrd_uf, et_rrd_n_registro;
	private Spinner spinner_rrd_documento;
	private RRDData data;
	private List<String> list_documento;
	private AnyArrayAdapter<String> adapter_documento;
	private AnyDiaglogFragmentForFragment diaglogFragmentForFragment;
	private Bundle bundle;
	private Button btn_rrd_validade;

	private LinearLayout layout_remove_n_registro_child,
			layout_remove_placa_child, layout_remove_parent;


	public static RRDDocumentoFragment  newInstance() {
		return new RRDDocumentoFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.rrd_documento_fragment, null, false);
		initializedView();
		getAutodeObject();
		checkNumeroAutoDe();
		return view;
	}

	private void addListener() {

	}

	private void checkNumeroAutoDe() {
		String value = (DatabaseCreator.getDatabaseAdapterNumero(getActivity()))
				.getRrdNumero();
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
			data.setNumero_rrd(value);

		}
	}

	private void getAutodeObject() {
		data = RRDObgect.getRRDDeOject();
		getRecomandedUpdate();
		addListener();
		initializedSelectetItems();
	}

	private void initializedSelectetItems() {
		// spinner_rrd_documento.setSelection(0);
		// et_rrd_nome_do_condutor.setText("");
		// et_rrd_n_crlv.setText("");
		// et_rrd_uf.setText("");
	}

	private void initializedView() {

		layout_remove_parent = (LinearLayout) view
				.findViewById(R.id.layout_remove_parent);
		layout_remove_placa_child = (LinearLayout) view
				.findViewById(R.id.layout_remove_placa_child);
		layout_remove_n_registro_child = (LinearLayout) view
				.findViewById(R.id.layout_remove_n_registro_child);

		et_rrd_n_auto_de_infracao = (EditText) view
				.findViewById(R.id.et_rrd_n_auto_de_infracao);
		et_rrd_nome_do_condutor = (EditText) view
				.findViewById(R.id.et_rrd_nome_do_condutor);
		et_rrd_nome_do_condutor.setEnabled(false);

		et_rrd_n_crlv = (EditText) view.findViewById(R.id.et_rrd_n_crlv);
		et_rrd_placa = (EditText) view.findViewById(R.id.et_rrd_placa);
		et_rrd_placa.setEnabled(false);
		et_rrd_uf = (EditText) view.findViewById(R.id.et_rrd_uf);
		et_rrd_uf.setEnabled(false);
		et_rrd_n_crlv
				.addTextChangedListener(new ChangeText(R.id.et_rrd_n_crlv));

		et_rrd_n_registro = (EditText) view
				.findViewById(R.id.et_rrd_n_registro);

		et_rrd_n_registro.addTextChangedListener(new ChangeText(
				R.id.et_rrd_n_registro));
		btn_rrd_validade = (Button) view.findViewById(R.id.btn_rrd_validade);
		btn_rrd_validade.setOnClickListener(this);

		et_rrd_uf.addTextChangedListener(new ChangeText(R.id.et_rrd_uf));

		list_documento = Arrays.asList(getResources().getStringArray(
				R.array.rrd_documento));

		adapter_documento = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				list_documento);

		spinner_rrd_documento = (Spinner) view
				.findViewById(R.id.spinner_rrd_documento);
		spinner_rrd_documento.setAdapter(adapter_documento);
		spinner_rrd_documento
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						data.setDocumento((String) parent
								.getItemAtPosition(pos));

						if (pos != 0) {
							addNregistroView();
						} else {
							addPlacaView();
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

	}

	private void getRecomandedUpdate() {
		et_rrd_n_auto_de_infracao.setText(data.getN_auto_de_infracao());
		et_rrd_placa.setText(data.getPlaca());
		et_rrd_nome_do_condutor.setText(data.getNome_do_condutor());
		et_rrd_uf.setText(data.getUf());
		et_rrd_n_registro.setText(data.getN_registro());
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
			case R.id.et_rrd_n_crlv:
				data.setN_crlv(s);
				break;
			case R.id.et_rrd_n_registro:
				data.setN_registro(s);
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
			}, getActivity(), AppConstants.NUMERO_RRD)).execute("");

		} else {
			getActivity().finish();
		}

	}

	private void addPlacaView() {

		if (layout_remove_placa_child.getParent() == null) {
			layout_remove_parent.addView(layout_remove_placa_child);
		}
		removeregistroView();

	}

	private void addNregistroView() {
		if (layout_remove_n_registro_child.getParent() == null) {
			layout_remove_parent.addView(layout_remove_n_registro_child);
		}
		removePlacaView();

	}

	private void removePlacaView() {
		if (layout_remove_placa_child.getParent() != null) {
			et_rrd_n_crlv.setText("");
			layout_remove_parent.removeView(layout_remove_placa_child);
		}
	}

	private void removeregistroView() {
		if (layout_remove_n_registro_child.getParent() != null) {
			//et_rrd_n_registro.setText("");
			btn_rrd_validade.setText(R.string.rrd_validade);
			data.setValidade("");
			layout_remove_parent.removeView(layout_remove_n_registro_child);
		}

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_rrd_validade) {
			MyDatePicker pickerdate = new MyDatePicker();
			bundle = new Bundle();
			bundle.putInt(MyDatePicker.MY_DATE_PICKER_ID, R.id.btn_rrd_validade);
			pickerdate.setArguments(bundle);
			pickerdate.setTargetFragment(this, 0);
			pickerdate.show(getActivity().getSupportFragmentManager(), "date");
		}

	}

	@Override
	public void date(String date, int view_id) {
		if (view_id == R.id.btn_rrd_validade) {
			btn_rrd_validade.setText(date);
			data.setValidade(date);
		}
	}
}
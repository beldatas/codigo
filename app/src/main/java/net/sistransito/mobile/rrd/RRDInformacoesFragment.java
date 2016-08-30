package net.sistransito.mobile.rrd;

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
import android.widget.EditText;
import android.widget.Spinner;

import net.sistransito.mobile.adapter.AnyArrayAdapter;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.rrd.lister.RRDLister;
import net.sistrnsitomobile.R;

import java.util.Arrays;
import java.util.List;

public class RRDInformacoesFragment extends Fragment {
	private View view;
	private EditText et_rrd_motivo_para_recolhimento;
	private Spinner spinner_rrd_qtd_de_dias_para_regularizacao;
	private RRDData data;
	private List<String> list_qtd_de;
	private AnyArrayAdapter<String> adapter_qtd_de;

	private Button btn_rrd_imprimir;

	public static RRDInformacoesFragment newInstance() {
		return new RRDInformacoesFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.rrd_informacoes_fragment, null, false);
		initializedView();
		getAutodeObject();
		return view;
	}

	private void addListener() {

	}

	private void getAutodeObject() {
		data = RRDObgect.getRRDDeOject();
		getRecomandedUpdate();
		addListener();
		initializedSelectetItems();
	}

	private void initializedSelectetItems() {
		et_rrd_motivo_para_recolhimento.setText("");
		spinner_rrd_qtd_de_dias_para_regularizacao.setSelection(0);

	}

	private void initializedView() {
		et_rrd_motivo_para_recolhimento = (EditText) view
				.findViewById(R.id.et_rrd_motivo_para_recolhimento);
		et_rrd_motivo_para_recolhimento.addTextChangedListener(new ChangeText(
				R.id.et_rrd_motivo_para_recolhimento));

		list_qtd_de = Arrays.asList(getResources().getStringArray(
				R.array.qtd_de));
		adapter_qtd_de = new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				list_qtd_de);

		spinner_rrd_qtd_de_dias_para_regularizacao = (Spinner) view
				.findViewById(R.id.spinner_rrd_qtd_de_dias_para_regularizacao);
		spinner_rrd_qtd_de_dias_para_regularizacao.setAdapter(adapter_qtd_de);
		spinner_rrd_qtd_de_dias_para_regularizacao
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						data.setQtd_de_dias_para_regularizacao((String) parent
								.getItemAtPosition(pos));
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});

		btn_rrd_imprimir = (Button) view.findViewById(R.id.btn_rrd_imprimir);
		btn_rrd_imprimir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				(DatabaseCreator.getDatabaseAdapterRRD(getActivity()))
						.insertRrdInformation(data);
				
				startActivity(new Intent(getActivity(),RRDLister.class));
				getActivity().finish();
			}
		});

	}

	private void getRecomandedUpdate() {

	}

	private class ChangeText implements TextWatcher {
		private int id;

		public ChangeText(int id) {
			this.id = id;
		}

		@Override
		public void afterTextChanged(Editable s) {
			if (id == R.id.et_rrd_motivo_para_recolhimento) {
				data.setMotivo_para_recolhimento((s.toString()).trim());
			}

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

	}
}
package net.sistransito.mobile.tav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import net.sistransito.mobile.adapter.AnyArrayAdapter;
import net.sistrnsitomobile.R;

import java.util.Arrays;

public class TAVAcessoriosFragment extends Fragment implements
		OnItemSelectedListener {
	private View view;
	private FilterName filterName;

	private TAVData data;
	private Spinner antena_de_radio, bagageiro, bancos, bateria, calota,
			condicionador_de_ar, extintor_de_incendio, farolete_dianteiro,
			farolete_traseiro, macaco, para_choque_dianteiro,
			para_choque_traseiro, para_sol_do_condutor, pneus, pneus_estepe,
			radio, retrovisor_interno, retrovisor_externo_direito, tapete,
			triangulo, volante, guidam;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tav_veiculo_acessorios_fragment, null,
				false);
		filterName = new FilterName(getActivity());
		initializedView();
		getAutodeObject();
		return view;
	}

	private void addListener() {
		antena_de_radio.setOnItemSelectedListener(this);
		bagageiro.setOnItemSelectedListener(this);
		bancos.setOnItemSelectedListener(this);
		bateria.setOnItemSelectedListener(this);
		calota.setOnItemSelectedListener(this);
		condicionador_de_ar.setOnItemSelectedListener(this);
		extintor_de_incendio.setOnItemSelectedListener(this);
		farolete_dianteiro.setOnItemSelectedListener(this);
		farolete_traseiro.setOnItemSelectedListener(this);
		macaco.setOnItemSelectedListener(this);
		para_choque_dianteiro.setOnItemSelectedListener(this);
		para_choque_traseiro.setOnItemSelectedListener(this);
		para_sol_do_condutor.setOnItemSelectedListener(this);
		pneus.setOnItemSelectedListener(this);
		pneus_estepe.setOnItemSelectedListener(this);
		radio.setOnItemSelectedListener(this);
		retrovisor_interno.setOnItemSelectedListener(this);
		retrovisor_externo_direito.setOnItemSelectedListener(this);
		tapete.setOnItemSelectedListener(this);
		triangulo.setOnItemSelectedListener(this);
		volante.setOnItemSelectedListener(this);
		guidam.setOnItemSelectedListener(this);

	}

	private void getAutodeObject() {
		data = TAVObgect.getTAVOject();

	}

	private void initializedView() {
		antena_de_radio = (Spinner) view.findViewById(R.id.antena_de_radio);
		bagageiro = (Spinner) view.findViewById(R.id.bagageiro);
		bancos = (Spinner) view.findViewById(R.id.bancos);
		bateria = (Spinner) view.findViewById(R.id.bateria);
		calota = (Spinner) view.findViewById(R.id.calota);
		condicionador_de_ar = (Spinner) view
				.findViewById(R.id.condicionador_de_ar);
		extintor_de_incendio = (Spinner) view
				.findViewById(R.id.extintor_de_incendio);
		farolete_dianteiro = (Spinner) view
				.findViewById(R.id.farolete_dianteiro);
		farolete_traseiro = (Spinner) view.findViewById(R.id.farolete_traseiro);
		macaco = (Spinner) view.findViewById(R.id.macaco);
		para_choque_dianteiro = (Spinner) view
				.findViewById(R.id.para_choque_dianteiro);
		para_choque_traseiro = (Spinner) view
				.findViewById(R.id.para_choque_traseiro);
		para_sol_do_condutor = (Spinner) view
				.findViewById(R.id.para_sol_do_condutor);
		pneus = (Spinner) view.findViewById(R.id.pneus);
		pneus_estepe = (Spinner) view.findViewById(R.id.pneus_estepe);
		radio = (Spinner) view.findViewById(R.id.radio);
		retrovisor_interno = (Spinner) view
				.findViewById(R.id.retrovisor_interno);
		retrovisor_externo_direito = (Spinner) view
				.findViewById(R.id.retrovisor_externo_direito);
		tapete = (Spinner) view.findViewById(R.id.tapete);
		triangulo = (Spinner) view.findViewById(R.id.triangulo);
		volante = (Spinner) view.findViewById(R.id.volante);
		guidam = (Spinner) view.findViewById(R.id.guidam);
		addAdapter();
		addListener();
	}

	private void addAdapter() {

		antena_de_radio.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_0))));
		bagageiro.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_1))));
		bancos.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_2))));
		bateria.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_3))));
		calota.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_4))));
		condicionador_de_ar.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.ac_5))));
		extintor_de_incendio.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.ac_6))));
		farolete_dianteiro.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.ac_7))));
		farolete_traseiro.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_8))));
		macaco.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_9))));
		para_choque_dianteiro.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.ac_10))));
		para_choque_traseiro.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.ac_11))));
		para_sol_do_condutor.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.ac_12))));
		pneus.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_13))));
		pneus_estepe.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_14))));
		radio.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_15))));
		retrovisor_interno.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.ac_16))));
		retrovisor_externo_direito.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.ac_17))));
		tapete.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_18))));
		triangulo.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_19))));
		volante.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_20))));
		guidam.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.ac_21))));

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		String value = (String) parent.getItemAtPosition(pos);
		value = filterName.filter(value);
		switch (parent.getId()) {
		case R.id.antena_de_radio:
			data.setAntena_de_radio(value);
			break;
		case R.id.bagageiro:
			data.setBagageiro(value);
			break;
		case R.id.bancos:
			data.setBancos(value);
			break;
		case R.id.bateria:
			data.setBateria(value);
			break;
		case R.id.calota:
			data.setCalota(value);
			break;
		case R.id.condicionador_de_ar:
			data.setCondicionador_de_ar(value);
			break;
		case R.id.extintor_de_incendio:
			data.setExtintor_de_incendio(value);
			break;
		case R.id.farolete_dianteiro:
			data.setFarolete_dianteiro(value);
			break;
		case R.id.farolete_traseiro:
			data.setFarolete_traseiro(value);
			break;
		case R.id.macaco:
			data.setMacaco(value);
			break;
		case R.id.para_choque_dianteiro:
			data.setPara_choque_dianteiro(value);
			break;
		case R.id.para_choque_traseiro:
			data.setPara_choque_traseiro(value);
			break;
		case R.id.para_sol_do_condutor:
			data.setPara_sol_do_condutor(value);
			break;
		case R.id.pneus:
			data.setPneus(value);
			break;
		case R.id.pneus_estepe:
			data.setPneus_estepe(value);
			break;
		case R.id.radio:
			data.setRadio(value);
			break;
		case R.id.retrovisor_interno:
			data.setRetrovisor_interno(value);
			break;
		case R.id.retrovisor_externo_direito:
			data.setRetrovisor_externo_direito(value);
			break;
		case R.id.tapete:
			data.setTapete(value);
			break;
		case R.id.triangulo:
			data.setTriangulo(value);
			break;
		case R.id.volante:
			data.setVolante(value);
			break;
		case R.id.guidam:
			data.setGuidam(value);
			break;

		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}

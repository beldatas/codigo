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


public class TAVEstruturaFragment extends Fragment implements
		OnItemSelectedListener {
	private View view;

	private TAVData data;
	private FilterName filterName;

	private Spinner cabeca_de_alavanca, carroceria, forro, lataria_capo,
			lataria_lado_direito, lataria_lado_esquerdo,
			lataria_tapa_porta_mala, lataria_teto, motor, painel, pintura_capo,
			pintura_lado_direito, pintura_lado_esquerdo, pintura_porta_mala,
			pintura_teto, radiador, vidros_laterais, vidro_para_brisa,
			vidro_traseiro;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tav_veiculo_estrutura_fragment, null,
				false);
		filterName = new FilterName(getActivity());
		initializedView();
		getAutodeObject();
		return view;
	}

	private void addListener() {
		cabeca_de_alavanca.setOnItemSelectedListener(this);

		carroceria.setOnItemSelectedListener(this);

		forro.setOnItemSelectedListener(this);
		lataria_capo.setOnItemSelectedListener(this);
		lataria_lado_direito.setOnItemSelectedListener(this);
		lataria_lado_esquerdo.setOnItemSelectedListener(this);
		lataria_tapa_porta_mala.setOnItemSelectedListener(this);
		lataria_teto.setOnItemSelectedListener(this);
		motor.setOnItemSelectedListener(this);
		painel.setOnItemSelectedListener(this);
		pintura_capo.setOnItemSelectedListener(this);
		pintura_lado_direito.setOnItemSelectedListener(this);
		pintura_lado_esquerdo.setOnItemSelectedListener(this);
		pintura_porta_mala.setOnItemSelectedListener(this);
		pintura_teto.setOnItemSelectedListener(this);
		radiador.setOnItemSelectedListener(this);
		vidros_laterais.setOnItemSelectedListener(this);
		vidro_para_brisa.setOnItemSelectedListener(this);
		vidro_traseiro.setOnItemSelectedListener(this);

	}

	private void getAutodeObject() {
		data = TAVObgect.getTAVOject();

	}

	private void initializedView() {

		cabeca_de_alavanca = (Spinner) view
				.findViewById(R.id.cabeca_de_alavanca);
		carroceria = (Spinner) view.findViewById(R.id.carroceria);
		forro = (Spinner) view.findViewById(R.id.forro);
		lataria_capo = (Spinner) view.findViewById(R.id.lataria_capo);
		lataria_lado_direito = (Spinner) view
				.findViewById(R.id.lataria_lado_direito);
		lataria_lado_esquerdo = (Spinner) view
				.findViewById(R.id.lataria_lado_esquerdo);
		lataria_tapa_porta_mala = (Spinner) view
				.findViewById(R.id.lataria_tapa_porta_mala);
		lataria_teto = (Spinner) view.findViewById(R.id.lataria_teto);
		motor = (Spinner) view.findViewById(R.id.motor);
		painel = (Spinner) view.findViewById(R.id.painel);
		pintura_capo = (Spinner) view.findViewById(R.id.pintura_capo);
		pintura_lado_direito = (Spinner) view
				.findViewById(R.id.pintura_lado_direito);
		pintura_lado_esquerdo = (Spinner) view
				.findViewById(R.id.pintura_lado_esquerdo);
		pintura_porta_mala = (Spinner) view
				.findViewById(R.id.pintura_porta_mala);
		pintura_teto = (Spinner) view.findViewById(R.id.pintura_teto);
		radiador = (Spinner) view.findViewById(R.id.radiador);
		vidros_laterais = (Spinner) view.findViewById(R.id.vidros_laterais);
		vidro_para_brisa = (Spinner) view.findViewById(R.id.vidro_para_brisa);
		vidro_traseiro = (Spinner) view.findViewById(R.id.vidro_traseiro);

		setAdapter();

		addListener();
	}

	private void setAdapter() {

		cabeca_de_alavanca.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.es_0))));

		carroceria.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_1))));

		forro.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_2))));

		lataria_capo.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_3))));

		lataria_lado_direito.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.es_4))));

		lataria_lado_esquerdo.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.es_5))));

		lataria_tapa_porta_mala.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.es_6))));

		lataria_teto.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_7))));

		motor.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_8))));

		painel.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_9))));

		pintura_capo.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_10))));

		pintura_lado_direito.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.es_11))));

		pintura_lado_esquerdo.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.es_12))));

		pintura_porta_mala.setAdapter(new AnyArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				android.R.id.text1, Arrays.asList(getResources()
						.getStringArray(R.array.es_13))));

		pintura_teto.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_14))));

		radiador.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_15))));

		vidros_laterais.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_16))));

		vidro_para_brisa.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_17))));

		vidro_traseiro.setAdapter(new AnyArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, android.R.id.text1,
				Arrays.asList(getResources().getStringArray(R.array.es_18))));

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long is) {

		String value = (String) parent.getItemAtPosition(pos);
		value = filterName.filter(value);
		switch (parent.getId()) {
		case R.id.cabeca_de_alavanca:
			data.setCabeca_de_alavanca(value);
			break;
		case R.id.carroceria:
			data.setCarroceria(value);
			break;
		case R.id.forro:
			data.setForro(value);
			break;

		case R.id.lataria_capo:
			data.setLataria_capo(value);
			break;

		case R.id.lataria_lado_direito:
			data.setLataria_lado_direito(value);
			break;
		case R.id.lataria_lado_esquerdo:
			data.setLataria_lado_esquerdo(value);
			break;
		case R.id.lataria_tapa_porta_mala:
			data.setLataria_tapa_porta_mala(value);
			break;
		case R.id.lataria_teto:
			data.setLataria_teto(value);
			break;
		case R.id.motor:
			data.setMotor(value);
			break;
		case R.id.painel:
			data.setPainel(value);
			break;
		case R.id.pintura_capo:
			data.setPintura_capo(value);
			break;
		case R.id.pintura_lado_direito:
			data.setPintura_lado_direito(value);
			break;
		case R.id.pintura_lado_esquerdo:
			data.setPintura_lado_esquerdo(value);
			break;
		case R.id.pintura_porta_mala:
			data.setPintura_porta_mala(value);
			break;
		case R.id.pintura_teto:
			data.setPintura_teto(value);
			break;
		case R.id.radiador:
			data.setRadiador(value);
			break;
		case R.id.vidros_laterais:
			data.setVidros_laterais(value);
			break;
		case R.id.vidro_para_brisa:
			data.setVidro_para_brisa(value);
			break;
		case R.id.vidro_traseiro:
			data.setVidro_traseiro(value);
			break;

		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

}

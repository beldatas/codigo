package net.sistransito.mobile.autoinfracao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sistrnsitomobile.R;


public class SubTituloTabGerarAutoFragment extends Fragment {
	private View view;
	public static SubTituloTabGerarAutoFragment newInstance() {
		return new SubTituloTabGerarAutoFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.auto_gerar_fragment_subtitulo, null, false);
		return view;
	}

}

package net.sistransito.mobile.tav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sistrnsitomobile.R;


public class SubTitlTAVGegrarFragment extends Fragment {
	private View view;

	public static SubTitlTAVGegrarFragment newInstance() {
		return new SubTitlTAVGegrarFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tav_gerar_fragment_subtitulo, null, false);
		return view;
	}

}

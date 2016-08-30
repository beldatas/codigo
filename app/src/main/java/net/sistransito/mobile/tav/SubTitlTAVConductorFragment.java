package net.sistransito.mobile.tav;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sistrnsitomobile.R;


public class SubTitlTAVConductorFragment extends Fragment {
	private View view;

	public static SubTitlTAVConductorFragment  newInstance() {
		return new SubTitlTAVConductorFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tav_conductor_fragment_subtitulo, null, false);
		return view;
	}

}

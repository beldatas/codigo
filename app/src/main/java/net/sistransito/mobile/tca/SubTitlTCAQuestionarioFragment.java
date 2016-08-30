package net.sistransito.mobile.tca;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sistrnsitomobile.R;


public class SubTitlTCAQuestionarioFragment extends
		Fragment {
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tca_questionario_fragment_subtitulo,
				null, false);
		return view;
	}
	public static SubTitlTCAQuestionarioFragment newInstance() {
		return new SubTitlTCAQuestionarioFragment();
	}

}

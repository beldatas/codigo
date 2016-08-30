package net.sistransito.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.sistransito.mobile.autoinfracao.AutoDeInfracaoFragment;
import net.sistransito.mobile.autoinfracao.DadosDoCondutorFragment;
import net.sistransito.mobile.autoinfracao.GerarAutoFragment;

public class AutoDeStartSectionsPagerAdapter extends FragmentPagerAdapter {

	public AutoDeStartSectionsPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return  DadosDoCondutorFragment.newInstance();
		case 1:
			return  AutoDeInfracaoFragment.newInstance();
		case 2:
			return  GerarAutoFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
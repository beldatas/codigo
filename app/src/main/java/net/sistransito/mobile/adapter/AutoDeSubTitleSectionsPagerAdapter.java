package net.sistransito.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import net.sistransito.mobile.autoinfracao.SubTituloTabCondutorFragment;
import net.sistransito.mobile.autoinfracao.SubTituloTabGerarAutoFragment;
import net.sistransito.mobile.autoinfracao.SubTituloTabInfracaoFragment;

public class AutoDeSubTitleSectionsPagerAdapter extends FragmentPagerAdapter {

	@Override
	public void finishUpdate(ViewGroup container) {
		super.finishUpdate(container);
	}

	@Override
	public void startUpdate(ViewGroup container) {
		super.startUpdate(container);
	}

	public AutoDeSubTitleSectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {

		case 0:
			return  SubTituloTabCondutorFragment.newInstance();
		case 1:
			return  SubTituloTabInfracaoFragment.newInstance();
		case 2:
			return  SubTituloTabGerarAutoFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}
}
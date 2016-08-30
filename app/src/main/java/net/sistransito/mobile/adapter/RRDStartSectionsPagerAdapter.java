package net.sistransito.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.sistransito.mobile.rrd.RRDDocumentoFragment;
import net.sistransito.mobile.rrd.RRDInformacoesFragment;


public class RRDStartSectionsPagerAdapter extends FragmentPagerAdapter {

	public RRDStartSectionsPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return RRDDocumentoFragment.newInstance();
		case 1:
			return  RRDInformacoesFragment.newInstance();

		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
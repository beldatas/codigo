package net.sistransito.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.sistransito.mobile.tav.TAVAcessoriosFragment;
import net.sistransito.mobile.tav.TAVEstruturaFragment;

public class TAVEstruturaAcessoriosSectionsPagerAdapter extends
		FragmentPagerAdapter {

	public TAVEstruturaAcessoriosSectionsPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new TAVEstruturaFragment();
		case 1:
			return new TAVAcessoriosFragment();

		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
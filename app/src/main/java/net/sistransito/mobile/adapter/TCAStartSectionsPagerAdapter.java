package net.sistransito.mobile.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.sistransito.mobile.tca.TCACondutorFragment;
import net.sistransito.mobile.tca.TCAQuestionarioFragment;

public class TCAStartSectionsPagerAdapter extends FragmentPagerAdapter {

	public TCAStartSectionsPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return  TCACondutorFragment.newInstance();
		case 1:
			return  TCAQuestionarioFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
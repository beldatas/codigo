package net.sistransito.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import net.sistransito.mobile.tav.SubTitlTAVConductorFragment;
import net.sistransito.mobile.tav.SubTitlTAVGegrarFragment;
import net.sistransito.mobile.tav.SubTitlTAVVeiculoFragment;

public class TAVSubTitleSectionsPagerAdapter extends FragmentPagerAdapter {

	@Override
	public void finishUpdate(ViewGroup container) {
		super.finishUpdate(container);
	}

	@Override
	public void startUpdate(ViewGroup container) {
		super.startUpdate(container);
	}

	public TAVSubTitleSectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {

		case 0:
			return  SubTitlTAVConductorFragment.newInstance();
		case 1:
			return  SubTitlTAVVeiculoFragment.newInstance();
		case 2:
			return  SubTitlTAVGegrarFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}
}
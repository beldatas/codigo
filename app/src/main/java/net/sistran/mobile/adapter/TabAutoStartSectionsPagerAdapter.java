package net.sistran.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.sistran.mobile.autoinfracao.TabAutoEnderecoFragment;
import net.sistran.mobile.autoinfracao.TabAutoInfracaoFragment;
import net.sistran.mobile.autoinfracao.TabAutoCondutorFragment;
import net.sistran.mobile.autoinfracao.TabAutoGerarFragment;
import net.sistran.mobile.autoinfracao.TabAutoVeiculoFragment;

public class TabAutoStartSectionsPagerAdapter extends FragmentPagerAdapter {

	public TabAutoStartSectionsPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return TabAutoVeiculoFragment.newInstance();
		case 1:
			return TabAutoCondutorFragment.newInstance();
		case 2:
			return TabAutoEnderecoFragment.newInstance();
		case 3:
			return TabAutoInfracaoFragment.newInstance();
		case 4:
			return TabAutoGerarFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 5;
	}

}
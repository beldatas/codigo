package net.sistran.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import net.sistran.mobile.autoinfracao.TabCondutorSubTituloFragment;
import net.sistran.mobile.autoinfracao.TabEnderecoSubTituloFragment;
import net.sistran.mobile.autoinfracao.TabGerarSubTituloFragment;
import net.sistran.mobile.autoinfracao.TabInfracaoSubTituloFragment;
import net.sistran.mobile.autoinfracao.TabVeiculoSubTituloFragment;

public class TabAutoSubTitleSectionsPagerAdapter extends FragmentPagerAdapter {

	@Override
	public void finishUpdate(ViewGroup container) {
		super.finishUpdate(container);
	}

	@Override
	public void startUpdate(ViewGroup container) {
		super.startUpdate(container);
	}

	public TabAutoSubTitleSectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
  		case 0:
  			return TabVeiculoSubTituloFragment.newInstance();
  		case 1:
  			return TabCondutorSubTituloFragment.newInstance();
  		case 2:
  			return TabEnderecoSubTituloFragment.newInstance();
  		case 3:
  			return TabInfracaoSubTituloFragment.newInstance();
  		case 4:
  			return TabGerarSubTituloFragment.newInstance();
  		}
  		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 5;
	}
}
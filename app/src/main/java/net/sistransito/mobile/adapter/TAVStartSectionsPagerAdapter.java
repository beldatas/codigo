package net.sistransito.mobile.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.sistransito.mobile.tav.TAVConductorFragment;
import net.sistransito.mobile.tav.TAVGegrarFragment;
import net.sistransito.mobile.tav.TAVVeiculoFragment;

public class TAVStartSectionsPagerAdapter extends FragmentPagerAdapter {

    public TAVStartSectionsPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return TAVConductorFragment.newInstance();
            case 1:
                return TAVVeiculoFragment.newInstance();
            case 2:
                return TAVGegrarFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}
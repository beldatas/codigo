package net.sistransito.mobile.tav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import net.sistransito.mobile.adapter.TAVEstruturaAcessoriosSectionsPagerAdapter;
import net.sistransito.mobile.viewpager.AnySwipeableViewPager;
import net.sistransito.mobile.viewpager.DepthPageTransformer;
import net.sistransito.mobile.viewpager.FixedSpeedScroller;
import net.sistrnsitomobile.R;

import java.lang.reflect.Field;

public class TAVVeiculoFragment extends Fragment implements
		OnClickListener {
	private View view;
	private AnySwipeableViewPager pager;
	private TAVEstruturaAcessoriosSectionsPagerAdapter pagerAdapter;

	private Button btn_t_v_estrutura, btn_t_v_acessorios;
	private Field mScroller;
	private FixedSpeedScroller scroller;

	public static TAVVeiculoFragment newInstance() {
		return new TAVVeiculoFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater
				.inflate(R.layout.tav_veiculo_main_fragment, null, false);

		initializedView();
		return view;
	}

	private void initializedView() {
		setviewPager();
		btn_t_v_acessorios = (Button) view
				.findViewById(R.id.btn_t_v_acessorios);
		btn_t_v_estrutura = (Button) view.findViewById(R.id.btn_t_v_estrutura);
		addListener();

	}

	private void setviewPager() {
		pagerAdapter = new TAVEstruturaAcessoriosSectionsPagerAdapter(
				getChildFragmentManager());
		pager = (AnySwipeableViewPager) view.findViewById(R.id.e_a_pager);
		pager.setSwipeable(false);
		pager.setPageMargin(20);
		pager.setPageTransformer(true, new DepthPageTransformer());
		pager.setOffscreenPageLimit(pagerAdapter.getCount());
		pager.setAdapter(pagerAdapter);

		try {
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			scroller = new FixedSpeedScroller(getActivity());
			scroller.setFixedDuration(2000);
			mScroller.set(pager, scroller);

		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

	}

	private void addListener() {
		btn_t_v_acessorios.setOnClickListener(this);
		btn_t_v_estrutura.setOnClickListener(this);
		btn_t_v_estrutura.performClick();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_t_v_acessorios:
			btn_t_v_acessorios.setEnabled(false);
			btn_t_v_estrutura.setEnabled(true);
			pager.setCurrentItem(1);
			break;

		case R.id.btn_t_v_estrutura:
			btn_t_v_acessorios.setEnabled(true);
			btn_t_v_estrutura.setEnabled(false);
			pager.setCurrentItem(0);
			break;
		}
	}

}

package net.sistransito.mobile.rrd;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.viewpagerindicator.UnderlinePageIndicator;

import net.sistransito.mobile.adapter.RRDStartSectionsPagerAdapter;
import net.sistransito.mobile.adapter.RRDSubTitleSectionsPagerAdapter;
import net.sistransito.mobile.viewpager.AnySwipeableViewPager;
import net.sistransito.mobile.viewpager.DepthPageTransformer;
import net.sistransito.mobile.viewpager.FixedSpeedScroller;
import net.sistransito.mobile.viewpager.ZoomOutPageTransformer;
import net.sistrnsitomobile.R;

import java.lang.reflect.Field;

public class RRDActivity extends AppCompatActivity implements
		OnClickListener {
	private AnySwipeableViewPager pager;
	private AnySwipeableViewPager sub_title_pager;
	private Field mScroller;
	private FixedSpeedScroller scroller;
	private UnderlinePageIndicator indicator;
	private ImageView im_btn_tab_documento, im_btn_tab_informacoes,
			im_btn_back;
	private RRDStartSectionsPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.rrd_lista_main);
		getRRDObject();
		initializedView();
		setMainPager();
		setSubTitlePager();
		setTitleInidcator();

	}

	private void getRRDObject() {
		Bundle bundleFrom = getIntent().getExtras();
		RRDObgect.setRRDObject((RRDData) bundleFrom.getSerializable(RRDData
				.getRRDId()));
	}

	private void setTitleInidcator() {
		indicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		indicator.setFades(false);

		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int positon) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		setCurrentTabSelectedIteams(pager.getCurrentItem());

	}

	private void setSubTitlePager() {
		sub_title_pager = (AnySwipeableViewPager) findViewById(R.id.rrd_title_pager);
		sub_title_pager.setSwipeable(false);
		sub_title_pager.setAdapter(new RRDSubTitleSectionsPagerAdapter(
				getSupportFragmentManager()));
		sub_title_pager.setPageMargin(20);
		sub_title_pager.setPageTransformer(true, new ZoomOutPageTransformer());

		try {

			mScroller.set(sub_title_pager, scroller);

		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

	}

	private void setMainPager() {
		adapter = new RRDStartSectionsPagerAdapter(
				getSupportFragmentManager());
		pager = (AnySwipeableViewPager) findViewById(R.id.rrd_start_pager);
		pager.setSwipeable(false);
		pager.setPageMargin(20);
		pager.setPageTransformer(true, new DepthPageTransformer());
		pager.setOffscreenPageLimit(adapter.getCount());
		pager.setAdapter(adapter);

		try {
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			scroller = new FixedSpeedScroller(this);
			scroller.setFixedDuration(2000);
			mScroller.set(pager, scroller);

		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.im_btn_tab_documento:
			setPagerPosition(0);
			break;
		case R.id.im_btn_tab_informacoes:
			setPagerPosition(1);

			break;
		case R.id.im_btn_back:
			finish();
		}
	}

	private void setPagerPosition(int position) {
		setCurrentTabSelectedIteams(position);
		sub_title_pager.setCurrentItem(position);
		pager.setCurrentItem(position);
	}

	private void setCurrentTabSelectedIteams(int positon) {
		switch (positon) {
		case 0:
			im_btn_tab_documento.setSelected(true);
			im_btn_tab_informacoes.setSelected(false);

			break;

		case 1:
			im_btn_tab_documento.setSelected(false);
			im_btn_tab_informacoes.setSelected(true);

			break;

		}
	}

	private void initializedView() {
		im_btn_tab_informacoes = (ImageView) findViewById(R.id.im_btn_tab_informacoes);
		im_btn_tab_documento = (ImageView) findViewById(R.id.im_btn_tab_documento);

		im_btn_tab_informacoes.setOnClickListener(this);
		im_btn_tab_documento.setOnClickListener(this);

		im_btn_back = (ImageView) findViewById(R.id.im_btn_back);
		im_btn_back.setOnClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() >= 0)
			return false;
		return true;
	}
}

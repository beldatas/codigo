package net.sistran.mobile.login;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import net.sistran.mobile.appconstantes.AppConstants;
import net.sistran.mobile.fragment.AnyPageChangeListener;
import net.sistran.mobile.viewpager.AnySwipeableViewPager;
import net.sistran.mobile.viewpager.FixedSpeedScroller;
import net.sistran.R;

import java.lang.reflect.Field;


public class LoginActivity extends AppCompatActivity implements
        AnyPageChangeListener {

    private AnySwipeableViewPager pager;
    private LoginSectionsPagerAdapter adapter;
    private Field mScroller;
    private FixedSpeedScroller scroller;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.login_hint);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        setPager();
    }

    private void setPager() {
        pager = (AnySwipeableViewPager) findViewById(R.id.login_pager);
        pager.setSwipeable(false);
        adapter = new LoginSectionsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(getIntent().getIntExtra(
                AppConstants.LOGIN_FRAGMENT_ID, 0));
        try {
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            scroller = new FixedSpeedScroller(this);
            mScroller.set(pager, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

    @Override
    public void onPageChange(int id) {

        if (id == 0) {
            toolbar.setTitle(R.string.login_hint);
        } else {
            toolbar.setTitle(R.string.registro_hint);
        }
        pager.setCurrentItem(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

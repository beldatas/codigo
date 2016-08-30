package net.sistransito;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.rey.material.widget.ProgressView;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.login.LoginActivity;
import net.sistransito.mobile.main.InstallTask;
import net.sistransito.mobile.main.MainUserActivity;
import net.sistrnsitomobile.R;

public class MainActivity extends AppCompatActivity {
    // second test
    private LinearLayout linear_layout;
    private InstallTask mTask;
    private ProgressView mProgressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializedView();
        startViewAnimation();
        getInstallTask();

    }

    private void initializedView() {
        mProgressBar = (ProgressView) findViewById(R.id.progressBar);
    }

    private void startViewAnimation() {
        linear_layout = (LinearLayout) findViewById(R.id.linear_layour);
        linear_layout.startAnimation(AppObject
                .getSlideAndScaleAnimition(this));
    }

    public void updateProgress(Integer integer) {
        mProgressBar.setProgress(integer);

    }

    @SuppressWarnings("deprecation")
    private void getInstallTask() {
        mTask = (InstallTask) this.getLastNonConfigurationInstance();
        if (mTask == null) {
            mTask = new InstallTask(MainActivity.this);
            mTask.execute();
        } else {
            mProgressBar.setProgress(100);
        }
    }

    public void installWorkComplete() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppObject.getTinyDB(MainActivity.this).getBoolean(AppConstants.isLogin, false)) {
                    mProgressBar.stop();
                    finish();
                    startActivity(new Intent(MainActivity.this, MainUserActivity.class));

                } else {
                    mProgressBar.stop();
                    finish();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        }, 3000);
    }
}

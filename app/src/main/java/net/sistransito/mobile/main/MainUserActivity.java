package net.sistransito.mobile.main;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.KeyboardUtil;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import net.sistransito.mobile.ajuda.AjudaFragment;
import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.autoinfracao.AutoActivity;
import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.cnh.CnfFragment;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.equipamentos.AutoEquipmentEntryJsonTask;
import net.sistransito.mobile.http.WebClient;
import net.sistransito.mobile.numero.CheckNumeros;
import net.sistransito.mobile.placa.consulta.ConsultFragment;
import net.sistransito.mobile.placa.lister.LogFragment;
import net.sistransito.mobile.profile.ProfileFragment;
import net.sistransito.mobile.setting.SettingFragment;
import net.sistransito.mobile.util.DialogMaterial;
import net.sistransito.mobile.util.User;
import net.sistrnsitomobile.R;


public class MainUserActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {
    //save our header or resul
    private AccountHeader headerResult = null;
    private Drawer result = null;
    //prifile
   private IProfile profile;
    private User user;

    private DialogMaterial dialogMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //setup imageloder
        setImageLoder();
        inti(savedInstanceState);
        checkFirstTimeAppSetup();
        getpermisson();
    }

    private void setImageLoder() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(500)).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                        // default
                .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(defaultOptions)
                        // default
                .build();
        ImageLoader.getInstance().init(config);


    }

    private void inti(Bundle savedInstanceState) {
        user = AppObject.getTinyDB(this).getObject(AppConstants.user, User.class);
        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        this.setSupportActionBar(toolbar);

       profile = new ProfileDrawerItem().withName(user.getNome()).withEmail(user.getNome_orgao());
        //create account
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
               .withSavedInstance(savedInstanceState).addProfiles(profile)
                .build();

        ImageLoader.getInstance().loadImage(user.getProfile_image(),
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view,
                                                  Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        profile = new ProfileDrawerItem().withName(user.getNome()).withEmail(user.getNome_orgao()).withIcon(loadedImage);
                        headerResult.clear();
                        headerResult.addProfile(profile, 0);
                    }
                });

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this).withActionBarDrawerToggleAnimated(true)
                .withToolbar(toolbar).withAccountHeader(headerResult)
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        KeyboardUtil.hideKeyboard(MainUserActivity.this);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();

        //    result.addItem(new DividerDrawerItem());
        result.addItem(new PrimaryDrawerItem().withName(R.string.acesso_ao_sistransito).withIcon(R.mipmap.ic_i_car_p));
        result.addItem(new PrimaryDrawerItem().withName(R.string.texto_titulo_cpf).withIcon(R.mipmap.ic_i_id_p));
        result.addItem(new PrimaryDrawerItem().withName(R.string.opcoes_log).withIcon(R.mipmap.ic_log_n));
        result.addItem(new DividerDrawerItem());
        result.addItem(new PrimaryDrawerItem().withName(R.string.logout).withIcon(R.mipmap.ic_logout));
        result.addItem(new PrimaryDrawerItem().withName(R.string.setting).withIcon(R.mipmap.ic_settting_nav));
        result.addItem(new PrimaryDrawerItem().withName(R.string.profile).withIcon(R.mipmap.ic_profile));
        result.addItem(new DividerDrawerItem());
        result.addItem(new PrimaryDrawerItem().withName(R.string.ajuda_sobre_app).withIcon(R.mipmap.ic_help_nav));
        result.setOnDrawerItemClickListener(MainUserActivity.this);
        //react on the keyboard
        result.setSelection(0);
        // result.keyboardSupportEnabled(this, true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.btn_overflow_autuar:
                DadosDoAuto data = new DadosDoAuto();
                data.setDataisNull(true);
                Intent intent = new Intent(this,
                        AutoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DadosDoAuto.getAutoDeId(), data);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.action_autos_restantes:
                DialogMaterial.getBottomSheet(DatabaseCreator
                        .getBalancoDatabaseAdapter(this)
                        .getShowView(), Color.BLACK, this).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

        if (drawerItem != null && drawerItem instanceof Nameable) {
            int title = ((Nameable) drawerItem).getNameRes();
            getSupportActionBar().setTitle(title);
            switch (title) {
                case R.string.acesso_ao_sistransito:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ConsultFragment.newInstance()).commit();
                    break;
                case R.string.texto_titulo_cpf:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, CnfFragment.newInstance()).commit();
                    break;
                case R.string.opcoes_log:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, LogFragment.newInstance()).commit();
                    break;
                case R.string.logout:
                    AppObject.getTinyDB(this).putBoolean(AppConstants.isLogin, false);
                    finish();
                    break;
                case R.string.setting:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, SettingFragment.newInstance()).commit();
                    break;
                case R.string.ajuda_sobre_app:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, AjudaFragment.newInstance()).commit();
                    break;
                case R.string.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ProfileFragment.newInstance()).commit();
                    break;
            }
        }

        return false;
    }

    private void checkFirstTimeAppSetup() {
        if (!AppObject.getTinyDB(this).getBoolean("is_first_time", false)) {
            (new FirstInstall()).execute();
            AppObject.getTinyDB(this).putBoolean("is_first_time", true);
        }
    }

    private class FirstInstall extends AsyncTask<String, String, String> {
        ProgressDialog pd = new ProgressDialog(MainUserActivity.this, getResources().getString(R.string.processing));

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
            pd.setCancelable(false);
        }

        protected String doInBackground(String... params) {
            try {
                AutoEquipmentEntryJsonTask
                        entryJsonTask = new AutoEquipmentEntryJsonTask(AppObject.getHttpClient().executeHttpGet(WebClient.URL_EQUIPAMENTOS), MainUserActivity.this);
                entryJsonTask.prepareEntry();

            } catch (Exception e) {
                e.printStackTrace();
            }
            CheckNumeros checkNumeros = new CheckNumeros(MainUserActivity.this);
            checkNumeros.check();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
        }
    }

    private void getpermisson() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}

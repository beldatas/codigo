package net.sistransito.mobile.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.rey.material.widget.TextView;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.appobjeto.AppObject;
import net.sistransito.mobile.fragment.AnyPageChangeListener;
import net.sistransito.mobile.http.WebClient;
import net.sistransito.mobile.main.MainUserActivity;
import net.sistransito.mobile.network.NetworkConnection;
import net.sistransito.mobile.util.DialogMaterial;
import net.sistransito.mobile.util.User;
import net.sistrnsitomobile.R;

import cz.msebera.android.httpclient.Header;


public class FragmentLogin extends Fragment implements OnClickListener {

    private View view;
    private AnyPageChangeListener anyPageChangeListener;
    private TextView btn_register;
    private Button btn_login;
    private EditText et_email, et_password;
    private InputMethodManager imm;
    private ProgressDialog pd;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        anyPageChangeListener = (LoginActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, null, false);
        imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        initializedView();
        return view;
    }

    private void initializedView() {
        btn_register = (TextView) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_password = (EditText) view.findViewById(R.id.et_password);
        pd = new ProgressDialog(getActivity(), getResources().getString(R.string.processing));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                anyPageChangeListener.onPageChange(AppConstants.LOGIN_FRAGMENT_1);
                break;
            case R.id.btn_login:
                if (checkInput()) {
                    if (NetworkConnection.isNetworkAvailable(getActivity())) {
                        imm.hideSoftInputFromWindow(btn_login.getWindowToken(), 0);
                        userLogin();
                    } else {
                        DialogMaterial.getBottomSheet(getResources().getString(R.string.sem_conexao), Color.RED, getActivity()).show();
                    }
                }
                break;
        }
    }

    private boolean checkInput() {
        if (et_email.getText().toString().equals("")) {
            et_email.setError(getResources().getString(
                    R.string.texto_inserir_email));
            et_email.requestFocus();
            return false;
        } else if (et_password.getText().toString().equals("")) {
            et_password.setError(getResources().getString(
                    R.string.texto_inserir_senha));
            et_password.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void userLogin() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String url = WebClient.getLoginUrl(email, password);

        WebClient.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                pd.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                pd.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Gson gson = new Gson();
                User user = gson.fromJson(response, User.class);
                Log.d("uuuuuu", user.toString());
                if (user.getSuccess().compareTo("1") == 0) {
                    AppObject.getTinyDB(getActivity()).putBoolean(AppConstants.isLogin, true);
                    AppObject.getTinyDB(getActivity()).putObject(AppConstants.user, user);
                    getActivity().startActivity(new Intent(getActivity(), MainUserActivity.class));
                    getActivity().finish();
                } else {
                    showLoginFailMgs();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showLoginFailMgs();
            }
        });
    }
    private void showLoginFailMgs() {
        DialogMaterial.getBottomSheet(getResources().getString(R.string.login_fail), Color.RED, getActivity()).show();
    }
}

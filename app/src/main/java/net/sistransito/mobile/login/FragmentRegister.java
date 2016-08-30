package net.sistransito.mobile.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rey.material.app.BottomSheetDialog;
import com.rey.material.widget.TextView;

import net.sistransito.mobile.appconstantes.AppConstants;
import net.sistransito.mobile.fragment.AnyPageChangeListener;
import net.sistransito.mobile.http.WebClient;
import net.sistransito.mobile.network.NetworkConnection;
import net.sistransito.mobile.timeandime.TimeAndIme;
import net.sistransito.mobile.util.DialogMaterial;
import net.sistrnsitomobile.R;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class FragmentRegister extends Fragment implements
        OnClickListener {
    private View view;
    private Button  btn_sign_up;
    private TextView btn_login;
    private AnyPageChangeListener anyPageChangeListener;
    private EditText et_name, et_orgao, et_login, et_matricula, et_cpf,
            et_password;
    private String name, orgao, login, matricula, cpf, password;
    private TimeAndIme ime;
    private ProgressDialog pd;
    private ImageView im_prifile_image;
    private String imgPath, fileName;
    private  RequestParams params;
    private final int RESULT_LOAD_IMG = 111;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        anyPageChangeListener = (LoginActivity) activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tela_cadastrar_fragment, null, false);
        initializedView();
        return view;
    }

    private void initializedView() {
        ime = new TimeAndIme(getActivity());
        im_prifile_image = (ImageView) view.findViewById(R.id.im_prifile_image);
        im_prifile_image.setOnClickListener(this);
        btn_login = (TextView) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_sign_up = (Button) view.findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(this);
        et_password = (EditText) view.findViewById(R.id.et_password);
        et_login = (EditText) view.findViewById(R.id.et_login);
        et_cpf = (EditText) view.findViewById(R.id.et_cpf);
        et_matricula = (EditText) view.findViewById(R.id.et_matricula);
        et_orgao = (EditText) view.findViewById(R.id.et_autority);
        et_name = (EditText) view.findViewById(R.id.et_name);
        pd = new ProgressDialog(getActivity(), getResources().getString(R.string.processing));
        params = new RequestParams();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                anyPageChangeListener.onPageChange(AppConstants.LOING_FRAGMENT_0);
                break;
            case R.id.btn_sign_up:
                if (NetworkConnection.isNetworkAvailable(getActivity())) {
                    if (checkInput())
                        userSingUp();

                } else {
                    DialogMaterial.getBottomSheet(getResources().getString(R.string.sem_conexao), Color.RED, getActivity()).show();
                }
                break;
            case R.id.im_prifile_image:
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                break;
        }
    }


    private void userSingUp() {
        name = et_name.getText().toString();
        orgao = et_orgao.getText().toString();
        login = et_login.getText().toString();
        matricula = et_matricula.getText().toString();
        cpf = et_cpf.getText().toString();
        password = et_password.getText().toString();

        String url = "http://sistransito.net/movel/dosis.pl?op=clogin"
                + "&imei=" + "&orgao=" + ime.getIME() + orgao + "&nome=" + name
                + "&mat=" + matricula + "&cpf=" + cpf + "&login=" + login
                + "&senha=" + password;

        WebClient.post(url, null, new JsonHttpResponseHandler() {
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
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject response) {
                if (response.toString().contains("1")) {

                    final BottomSheetDialog dialog = DialogMaterial.getBottomSheet(getResources().getString(R.string.registro_concluido), Color.RED, getActivity());
                    dialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            anyPageChangeListener.onPageChange(0);
                            anyPageChangeListener.onPageChange(0);
                            dialog.dismiss();
                        }
                    }, 3000);
                } else {
                    DialogMaterial.getBottomSheet(getResources().getString(R.string.registro_nao_concluido), Color.RED, getActivity()).show();
                }

            }
        });

    }

    private void setMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle(getResources().getString(R.string.registro_hint));
        builder.setMessage(getResources().getString(R.string.registro_concluido));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    private boolean checkInput() {
        if (et_name.getText().toString().equals("")) {
            et_name.setError(getResources().getString(R.string.invalid_inout));
            return false;
        } else if (et_orgao.getText().toString().equals("")) {
            et_orgao.setError(getResources().getString(R.string.invalid_inout));
            return false;
        } else if (et_matricula.getText().toString().equals("")) {
            et_matricula.setError(getResources().getString(R.string.invalid_inout));
            return false;
        } else if (et_cpf.getText().toString().equals("")) {
            et_cpf.setError(getResources().getString(R.string.invalid_inout));
            return false;
        } else if (et_login.getText().toString().equals("")) {
            et_login.setError(getResources().getString(R.string.invalid_inout));
            return false;
        } else if (et_password.getText().toString().equals("")) {
            et_password.setError(getResources().getString(R.string.invalid_inout));
            return false;
        } else if (fileName == null) {
            Toast.makeText(getActivity(), "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == Activity.RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgPath = cursor.getString(columnIndex);
                cursor.close();
                // Set the Image in ImageView
                im_prifile_image.setImageBitmap(BitmapFactory
                        .decodeFile(imgPath));
                // Get the Image's file name
                String fileNameSegments[] = imgPath.split("/");
                fileName = fileNameSegments[fileNameSegments.length - 1];
                // Put file name in Async Http Post Param which will used in Php web app
                params.put("profile_image", fileName);

            } else {
                Toast.makeText(getActivity(), "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
                fileName = null;
            }
        } catch (Exception e) {

            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
            fileName = null;
        }
    }
}

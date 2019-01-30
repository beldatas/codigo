package net.sistran.mobile.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import net.sistran.mobile.autoinfracao.AutoActivity;
import net.sistran.mobile.util.Rotinas;
import net.sistran.R;

public class AnyAlertDialog{


	public static void dialogShow(String mgs, Context context, String title) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				AlertDialog.THEME_HOLO_LIGHT);
		builder.setTitle(title);
		builder.setMessage(mgs);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	public static void msgAlertSimple(String message, Context context) {

		AlertDialog.Builder dialog = new AlertDialog.Builder(context,
				AlertDialog.THEME_HOLO_LIGHT);

		//AlertDialog ad = new AlertDialog.Builder(this).create();
		dialog.setCancelable(false);
		dialog.setMessage(message);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	public static void dialogView(final Context mycontext, String title){
		LayoutInflater linear = LayoutInflater.from(mycontext);
		View viewCancelar = linear.inflate(R.layout.auto_cancelar, null);
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mycontext,
                //R.style.AlertDialogTheme);
                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		alertDialog.setView(viewCancelar);

		final EditText motivo = (EditText) viewCancelar.findViewById(R.id.edit_justificativa);
		alertDialog.setTitle(title);

		alertDialog.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (motivo.getText().toString().equals("")) {
					Rotinas.showAlert(mycontext.getString(R.string.auto_justificativa), mycontext);
				} else {
					((AutoActivity) mycontext).setCancelar(mycontext, motivo.getText().toString());
					((Activity) mycontext).finish();
				}

			}
		});
		alertDialog.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
		AlertDialog dialog = alertDialog.create();
		dialog.show();
		dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(100,100,100));
		dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(100,100,100));
	}

}

package net.sistransito.mobile.placa.lister;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.database.PlacaSearchDatabaseAdapter;
import net.sistrnsitomobile.R;

import java.util.ArrayList;

public class PlacaListerDelete extends AppCompatActivity implements OnClickListener,
		OnCheckedChangeListener {
	private PlacaListerDeleteExpendableAdapter logExpendableAdapter;
	private ExpandableListView expandableListView;
	private CheckBox cb_check_all;
	private Button btn_delete;
	private TextView tvMessage;
	private RelativeLayout placa_lister_layout;
	private ProgressDialog pDialog = null;
	private PlacaSearchDatabaseAdapter placaSearchDatabaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.placa_lista_delete_main);
		expandableListView = ((ExpandableListView) findViewById(R.id.remove_expandableListView));
		cb_check_all = (CheckBox) findViewById(R.id.cb_check_all);
		cb_check_all.setOnCheckedChangeListener(this);
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_delete.setOnClickListener(this);
		addAdapter();
	}

	private void addAdapter() {
		placaSearchDatabaseAdapter = DatabaseCreator
				.getPlacaSearchDatabaseAdapter(this);
		if (checkView()) {
			setAdapter();
		} else {
			setMessage();
		}

	}

	private void setMessage() {

		placa_lister_layout = (RelativeLayout) findViewById(R.id.placa_lister_layout_remove);
		if (expandableListView.getParent() != null) {
			placa_lister_layout.removeView(expandableListView);
		}
		tvMessage = new TextView(this);
		tvMessage.setText(getResources().getString(R.string.nehum_resultado_retornado));
		tvMessage.setGravity(Gravity.CENTER);
		tvMessage.setTextAppearance(this, android.R.style.TextAppearance_Large);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		tvMessage.setLayoutParams(params);
		tvMessage.setTextColor(getResources().getColor(R.color.line_color));
		if (tvMessage.getParent() == null) {
			placa_lister_layout.addView(tvMessage);
		}
		btn_delete.setEnabled(false);
		cb_check_all.setEnabled(false);

	}

	private void setAdapter() {
		logExpendableAdapter = new PlacaListerDeleteExpendableAdapter(
				placaSearchDatabaseAdapter.getPlacaCursor(), this);
		expandableListView.setAdapter(logExpendableAdapter);
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false; // This way the expander cannot be
								// collapsed
			}
		});
		btn_delete.setEnabled(true);
		cb_check_all.setEnabled(true);

	}

	private boolean checkView() {
		return placaSearchDatabaseAdapter.getPlacaCursor().getCount() > 0;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.im_btn_back:
			finish();
			startActivity(new Intent(PlacaListerDelete.this, PlacaList.class));
			break;
		case R.id.btn_delete:
			cb_check_all.setOnCheckedChangeListener(null);
			(new DeleteData()).execute("");
			cb_check_all.setOnCheckedChangeListener(this);
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (buttonView.getId() == R.id.cb_check_all) {
			if (isChecked) {

				if (checkView()) {
					PlacaListerDeleteExpendableAdapter.chnageState = true;
					for (int i = 0; i < PlacaListerDeleteExpendableAdapter.selectedStatus.length; i++) {
						PlacaListerDeleteExpendableAdapter.selectedStatus[i] = true;
					}
					logExpendableAdapter.notifyDataSetChanged();
				}

			} else {
				for (int i = 0; i < PlacaListerDeleteExpendableAdapter.selectedStatus.length; i++) {
					PlacaListerDeleteExpendableAdapter.selectedStatus[i] = false;
				}
				logExpendableAdapter.notifyDataSetChanged();
			}
		}
	}

	class DeleteData extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = null;
			pDialog = new ProgressDialog(PlacaListerDelete.this,
					AlertDialog.THEME_HOLO_LIGHT);
			pDialog.setMax(100);
			pDialog.setCancelable(false);
			pDialog.setMessage(getResources().getString(R.string.deletando));
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			ArrayList<String> list = new ArrayList<String>();

			for (int i = 0; i < PlacaListerDeleteExpendableAdapter.idNumber.length; i++) {
				if (!((PlacaListerDeleteExpendableAdapter.idNumber[i]).equals(""))) {
					list.add(PlacaListerDeleteExpendableAdapter.idNumber[i]);
				}
			}
			if (list.size() > 0) {

				String deleteIdFields[] = new String[list.size()];
				deleteIdFields = list.toArray(deleteIdFields);

				for (int i = 0; i < deleteIdFields.length; i++) {
					if ((DatabaseCreator
							.getPlacaSearchDatabaseAdapter(PlacaListerDelete.this))
							.deleteDataFromIdField(deleteIdFields[i])) {

					}
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			// pDialog.setProgress(Integer.parseInt(values[0]));
		}

		@Override
		protected void onPostExecute(String result) {
			if ((pDialog.isShowing()) || (pDialog != null)) {
				pDialog.dismiss();
				pDialog = null;
			}
			cb_check_all.setChecked(false);
			logExpendableAdapter.notifyDataSetChanged();
			super.onPostExecute(result);
			addAdapter();
		}
	}
}

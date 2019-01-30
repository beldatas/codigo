package net.sistran.mobile.placa.lister;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorTreeAdapter;
import android.widget.TextView;

import net.sistran.mobile.autoinfracao.AutoActivity;
import net.sistran.mobile.autoinfracao.DadosAuto;
import net.sistran.mobile.database.DatabaseCreator;
import net.sistran.mobile.database.PlacaSearchDatabaseHelper;
import net.sistran.R;

public class PlacaListerExpandableAdapter extends CursorTreeAdapter {
	private LayoutInflater mInflator;
	private Context mycontext;

	public PlacaListerExpandableAdapter(Cursor cursor, Context context) {
		super(cursor, context);
		mycontext = context;
		mInflator = LayoutInflater.from(context);
	}

	@Override
	protected void bindChildView(View arg0, Context arg1, Cursor arg2,
			boolean arg3) {
	}

	@Override
	protected void bindGroupView(View view, Context arg1, Cursor cursor,
			boolean arg3) {
		TextView logPlate, logTime, logLicenseStatus;
		logPlate = (TextView) view.findViewById(R.id.log_plate);
		logLicenseStatus = (TextView) view
				.findViewById(R.id.log_license_status);
		logTime = (TextView) view.findViewById(R.id.log_time);
		logPlate.setText(cursor.getString(cursor
				.getColumnIndex(PlacaSearchDatabaseHelper.COLUMN_PLACA)));
		logLicenseStatus
				.setText(cursor.getString(cursor
						.getColumnIndex(PlacaSearchDatabaseHelper.COLUMN_STATUS_LICENCIAMENTO)));
		logTime.setText(cursor.getString(cursor
				.getColumnIndex(PlacaSearchDatabaseHelper.COLUMN_DATE)));

	}

	@Override
	protected View newChildView(Context arg0, Cursor arg1, boolean arg2,
			ViewGroup arg3) {
		return null;
	}

	@Override
	protected View newGroupView(Context context, Cursor cursor,
			boolean isExpendable, ViewGroup parent) {
		View view = mInflator.inflate(R.layout.placa_lista_listview_parent,
				null);

		TextView logPlate, logTime, logLicenseStatus;

		logPlate = (TextView) view.findViewById(R.id.log_plate);
		logLicenseStatus = (TextView) view.findViewById(R.id.log_license_status);
		logTime = (TextView) view.findViewById(R.id.log_time);

		logPlate.setText(cursor.getString(cursor
				.getColumnIndex(PlacaSearchDatabaseHelper.COLUMN_PLACA)));
		logLicenseStatus
				.setText(cursor.getString(cursor
						.getColumnIndex(PlacaSearchDatabaseHelper.COLUMN_STATUS_LICENCIAMENTO)));
		logTime.setText(cursor.getString(cursor
				.getColumnIndex(PlacaSearchDatabaseHelper.COLUMN_DATE)));
		return view;
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		int groupId = groupCursor.getInt(groupCursor
				.getColumnIndex(PlacaSearchDatabaseHelper.COLUMN_ID));
		return (DatabaseCreator.getPlacaSearchDatabaseAdapter(mycontext))
				.getPlacaCursorFromID(groupId);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		final int gPosition = groupPosition;
		final String placa = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_PLACA));

		final String uf = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_UF));

		final String pais = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_PAIS));

		final String chassi = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_CHASSI));

		final String cor = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_COR));

		final String marca = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_MARCA));

		final String modelo = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_MODELO));

		final String especie = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_TIPO));

		final String categoria = getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_CATEGORIA));

		View mView = null;
		if (view != null) {
			mView = view;
		} else {
			mView = mInflator.inflate(R.layout.placa_lista_listview_child,
					parent, false);
		}

		TextView tvMarca, tvModel, tvColor, tvType, tvCategory, tvLicenseYear, tvLicenseDate, tvIpva, tvInsurance, tvIntraction, tvRestrictions;

		Button btnPlateCriarNovoAuto, btnPlateCriarAuto;

		btnPlateCriarNovoAuto = (Button) mView
				.findViewById(R.id.btn_plate_criar_novo_auto);
		btnPlateCriarAuto = (Button) mView.findViewById(R.id.btn_plate_criar_auto);

		if ((DatabaseCreator.getDatabaseAdapterAutoInfracao(mycontext))
				.isSamePlacaExist(placa)) {
			btnPlateCriarNovoAuto.setEnabled(true);
			btnPlateCriarAuto.setEnabled(false);

		} else {
			btnPlateCriarNovoAuto.setEnabled(false);
			btnPlateCriarAuto.setEnabled(true);
		}

		btnPlateCriarNovoAuto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openDadosAuto((DatabaseCreator
						.getDatabaseAdapterAutoInfracao(mycontext))
						.getAutodeDataFromPlaca(placa));
			}

		});

		btnPlateCriarAuto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DadosAuto dadosAuto = new DadosAuto();
				dadosAuto.setPlaca(placa);
				dadosAuto.setUf_veiculo(uf.toUpperCase());
				dadosAuto.setPais(pais);
				dadosAuto.setChassi(chassi);
				dadosAuto.setMarca_veiculo(marca);
				dadosAuto.setModelo_veiculo(modelo);
				dadosAuto.setCor_do_veiculo(cor);
				dadosAuto.setEspecie(especie.toUpperCase());
				dadosAuto.setCategoria(categoria.toUpperCase());
				dadosAuto.setStoreFullData(false);
				openDadosAuto(dadosAuto);
			}
		});

		tvMarca = (TextView) mView.findViewById(R.id.log_marca);
		tvModel = (TextView) mView.findViewById(R.id.log_model);
		tvColor = (TextView) mView.findViewById(R.id.log_color);
		tvType = (TextView) mView.findViewById(R.id.log_type);
		tvCategory = (TextView) mView.findViewById(R.id.log_category);
		tvLicenseYear = (TextView) mView.findViewById(R.id.log_license_year);
		tvLicenseDate = (TextView) mView.findViewById(R.id.log_license_date);
		tvIpva = (TextView) mView.findViewById(R.id.log_ipva);
		tvInsurance = (TextView) mView.findViewById(R.id.log_insurance);
		tvIntraction = (TextView) mView.findViewById(R.id.log_infraction);
		tvRestrictions = (TextView) mView.findViewById(R.id.log_restriction);

		tvMarca.setText(mycontext.getResources().getString(
				R.string.marca_format)
		        + getGroup(gPosition)
				.getString(
						getGroup(gPosition)
								.getColumnIndex(
										PlacaSearchDatabaseHelper.COLUMN_MARCA)));
		tvModel.setText(mycontext.getResources().getString(
				R.string.modelo_format)
				+ modelo);

		tvColor.setText(mycontext.getResources().getString(R.string.cor_format)
				+ cor);

		tvType.setText(mycontext.getResources().getString(R.string.tipo_format)
				+ getGroup(gPosition).getString(
						getGroup(gPosition).getColumnIndex(
								PlacaSearchDatabaseHelper.COLUMN_TIPO)));

		tvCategory.setText(mycontext.getResources().getString(R.string.categoria_format)
				+ getGroup(gPosition).getString(
				getGroup(gPosition).getColumnIndex(
						PlacaSearchDatabaseHelper.COLUMN_CATEGORIA)));

		tvLicenseYear
				.setText(mycontext.getResources().getString(
						R.string.ano_licenciamento_format)
						+ getGroup(gPosition)
								.getString(
										getGroup(gPosition)
												.getColumnIndex(
														PlacaSearchDatabaseHelper.COLUMN_ANO_LICENCIAMENTO)));

		tvLicenseDate
				.setText(mycontext.getResources().getString(
						R.string.data_licenciamento_format)
						+ getGroup(gPosition)
								.getString(
										getGroup(gPosition)
												.getColumnIndex(
														PlacaSearchDatabaseHelper.COLUMN_DATA_LICENCIAMENTO)));
		tvIpva.setText(mycontext.getResources().getString(R.string.ipva_format)
				+ getGroup(gPosition).getString(
						getGroup(gPosition).getColumnIndex(
								PlacaSearchDatabaseHelper.COLUMN_IPVA)));

		tvInsurance.setText(mycontext.getResources().getString(
				R.string.seguro_format)
				+ getGroup(gPosition).getString(
						getGroup(gPosition).getColumnIndex(
								PlacaSearchDatabaseHelper.COLUMN_SEGURO)));

		tvIntraction.setText(mycontext.getResources().getString(
				R.string.multas_format)
				+ getGroup(gPosition).getString(
						getGroup(gPosition).getColumnIndex(
								PlacaSearchDatabaseHelper.COLUMN_INFRACOES)));

		tvRestrictions.setText(mycontext.getResources().getString(
				R.string.restricoes_format)
				+ getGroup(gPosition).getString(
						getGroup(gPosition).getColumnIndex(
								PlacaSearchDatabaseHelper.COLUMN_RESTRICOES)));

		return mView;
	}

	private void openDadosAuto(DadosAuto data) {
		Intent intent = new Intent(mycontext, AutoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(DadosAuto.getIDAuto(), data);
		intent.putExtras(bundle);
		mycontext.startActivity(intent);
		((Activity) mycontext).finish();
	}
}

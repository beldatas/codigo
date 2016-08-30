package net.sistransito.mobile.autoinfracao.lister;


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

import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.database.AutoInfracaoDatabaseHelper;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.impressaobluetooth.bluetooth.BluetoothPrinterListerner;
import net.sistransito.mobile.rrd.RRDActivity;
import net.sistransito.mobile.rrd.RRDData;
import net.sistransito.mobile.tav.TAVActivity;
import net.sistransito.mobile.tav.TAVData;
import net.sistransito.mobile.tca.TCAActivity;
import net.sistransito.mobile.tca.TcaData;
import net.sistrnsitomobile.R;

public class AutodeListerExpandableAdapter extends CursorTreeAdapter {
	private LayoutInflater mInflator;
	private Context mycontext;
	private BluetoothPrinterListerner printListener;

	public AutodeListerExpandableAdapter(Cursor cursor, Context context) {
		super(cursor, context);
		mycontext = context;
		mInflator = LayoutInflater.from(context);
		printListener = (BluetoothPrinterListerner) context;
	}

	@Override
	protected void bindChildView(View arg0, Context arg1, Cursor arg2,
			boolean arg3) {
	}

	@Override
	protected void bindGroupView(View view, Context arg1, Cursor cursor,
			boolean arg3) {
		TextView log_plate, log_model, log_art;
		log_plate = (TextView) view.findViewById(R.id.log_plate);
		log_model = (TextView) view.findViewById(R.id.log_model);
		log_art = (TextView) view.findViewById(R.id.log_art);

		log_plate.setText(cursor.getString(cursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.PLACA)));
		log_model.setText(cursor.getString(cursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO)));
		log_art.setText(cursor.getString(cursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.ART)));

	}

	@Override
	protected View newChildView(Context arg0, Cursor arg1, boolean arg2,
			ViewGroup arg3) {
		return null;
	}

	@Override
	protected View newGroupView(Context context, Cursor cursor,
			boolean isExpendable, ViewGroup parent) {
		View view = mInflator.inflate(R.layout.auto_lista_listview_parent,
				null);
		TextView log_plate, log_model, log_art;
		log_plate = (TextView) view.findViewById(R.id.log_plate);
		log_model = (TextView) view.findViewById(R.id.log_model);
		log_art = (TextView) view.findViewById(R.id.log_art);

		log_plate.setText(cursor.getString(cursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.PLACA)));
		log_model.setText(cursor.getString(cursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.MODELO_DO_VEICULO)));
		log_art.setText(cursor.getString(cursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.ART)));
		return view;
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		int groupId = groupCursor.getInt(groupCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.COLUMN_ID));
		return (DatabaseCreator.getDatabaseAdapterAutoInfracao(mycontext))
				.getAutoDeCursorFromID(groupId);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		// final int gPosition = groupPosition;
		TextView ver_autos_childview;
		Button ver_autos_tav, ver_autos_tca, ver_autos_rrd, ver_autos_print;
		View mView = null;
		if (view != null) {
			mView = view;
		} else {
			mView = mInflator.inflate(R.layout.auto_lista_listview_child,
					parent, false);
		}

		ver_autos_childview = (TextView) mView
				.findViewById(R.id.ver_autos_childview);
		final DadosDoAuto dadosDoAuto = new DadosDoAuto();
		dadosDoAuto.setAutoDeDataFromCursor(getGroup(groupPosition));
		ver_autos_childview
				.setText(dadosDoAuto.getAutoDeListViewData(mycontext));

		ver_autos_tav = (Button) mView.findViewById(R.id.ver_autos_tav);
		ver_autos_tca = (Button) mView.findViewById(R.id.ver_autos_tca);
		ver_autos_rrd = (Button) mView.findViewById(R.id.ver_autos_rrd);
		ver_autos_print = (Button) mView.findViewById(R.id.ver_autos_print);

		ver_autos_tav.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				TAVData data = new TAVData();
				data.setPlaca(dadosDoAuto.getPlate());
				data.setNumero_do_auto(dadosDoAuto.getNumero_auto());
				openTAV(data);

			}
		});

		ver_autos_tca.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				TcaData data = new TcaData();
				data.setNome_do_condutor(dadosDoAuto.getNome_do_Condutor());
				data.setCnh_ppd(dadosDoAuto.getCnh_ppd());
				data.setCpf(dadosDoAuto.getUf_cnh());
				data.setPlaca(dadosDoAuto.getPlate());
				data.setPlaca_uf(dadosDoAuto.getUf());
				data.setMarca_modelo(dadosDoAuto.getModel());
				data.setNumero_auto(dadosDoAuto.getNumero_auto());
				openTCA(data);

			}
		});

		ver_autos_rrd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				RRDData data = new RRDData();
				data.setN_auto_de_infracao(dadosDoAuto.getNumero_auto());
				data.setPlaca(dadosDoAuto.getPlate());
				data.setNome_do_condutor(dadosDoAuto.getNome_do_Condutor());
				data.setUf(dadosDoAuto.getUf());
				data.setN_registro(dadosDoAuto.getCnh_ppd());
				openRRD(data);
			}
		});

		ver_autos_print.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				printListener.print(dadosDoAuto, null);

			}
		});
		return mView;
	}

	private void openRRD(RRDData data) {
		Intent intent = new Intent(mycontext, RRDActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(RRDData.getRRDId(), data);
		intent.putExtras(bundle);
		mycontext.startActivity(intent);
		((Activity) mycontext).finish();
	}

	private void openTCA(TcaData data) {
		Intent intent = new Intent(mycontext, TCAActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(TcaData.getTcaId(), data);
		intent.putExtras(bundle);
		mycontext.startActivity(intent);
		((Activity) mycontext).finish();
	}

	private void openTAV(TAVData data) {
		Intent intent = new Intent(mycontext, TAVActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(TAVData.getTavId(), data);
		intent.putExtras(bundle);
		mycontext.startActivity(intent);
		((Activity) mycontext).finish();
	}
}

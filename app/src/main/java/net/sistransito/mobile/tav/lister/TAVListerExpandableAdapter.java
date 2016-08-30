package net.sistransito.mobile.tav.lister;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorTreeAdapter;
import android.widget.TextView;

import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.database.TAVDatabaseHelper;
import net.sistransito.mobile.impressaobluetooth.bluetooth.BluetoothPrinterListerner;
import net.sistransito.mobile.tav.TAVData;
import net.sistrnsitomobile.R;

public class TAVListerExpandableAdapter extends CursorTreeAdapter {
	private LayoutInflater mInflator;
	private Context mycontext;
	private BluetoothPrinterListerner printListener;

	public TAVListerExpandableAdapter(Cursor cursor, Context context) {
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
		TextView log_placa, log_nome_do_proprietario, log_number_do_auto;

		log_placa = (TextView) view.findViewById(R.id.log_plate);

		log_nome_do_proprietario = (TextView) view
				.findViewById(R.id.log_nome_do_proprietario);
		log_number_do_auto = (TextView) view
				.findViewById(R.id.log_number_do_auto);

		log_placa.setText(cursor.getString(cursor
				.getColumnIndex(TAVDatabaseHelper.PLACA)));

		log_nome_do_proprietario.setText(cursor.getString(cursor
				.getColumnIndex(TAVDatabaseHelper.NOME_DO_PROPRIETARIO)));

		log_number_do_auto.setText(cursor.getString(cursor
				.getColumnIndex(TAVDatabaseHelper.NUMERO_DO_AUTO)));

	}

	@Override
	protected View newChildView(Context arg0, Cursor arg1, boolean arg2,
			ViewGroup arg3) {
		return null;
	}

	@Override
	protected View newGroupView(Context context, Cursor cursor,
			boolean isExpendable, ViewGroup parent) {
		View view = mInflator
				.inflate(R.layout.tav_lister_listview_parent, null);
		TextView log_placa, log_nome_do_proprietario, log_number_do_auto;

		log_placa = (TextView) view.findViewById(R.id.log_plate);

		log_nome_do_proprietario = (TextView) view
				.findViewById(R.id.log_nome_do_proprietario);
		log_number_do_auto = (TextView) view
				.findViewById(R.id.log_number_do_auto);

		log_placa.setText(cursor.getString(cursor
				.getColumnIndex(TAVDatabaseHelper.PLACA)));

		log_nome_do_proprietario.setText(cursor.getString(cursor
				.getColumnIndex(TAVDatabaseHelper.NOME_DO_PROPRIETARIO)));

		log_number_do_auto.setText(cursor.getString(cursor
				.getColumnIndex(TAVDatabaseHelper.NUMERO_DO_AUTO)));
		return view;
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		int groupId = groupCursor.getInt(groupCursor
				.getColumnIndex(TAVDatabaseHelper.COLUMN_ID));
		return (DatabaseCreator.getDatabaseAdapterTAV(mycontext))
				.getTAVCursorFromID(groupId);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		// final int gPosition = groupPosition;
		TextView ver_tav_childview;
		Button ver_tav_print;
		View mView = null;
		if (view != null) {
			mView = view;
		} else {
			mView = mInflator.inflate(R.layout.tav_lister_listview_child,
					parent, false);
		}
		ver_tav_childview = (TextView) mView
				.findViewById(R.id.ver_tav_childview);
		final TAVData tavData = new TAVData();

		tavData.setTAVDataFromCursor(getGroup(groupPosition));

		ver_tav_childview.setText(tavData.getTCAListViewData(mycontext));

		ver_tav_print = (Button) mView.findViewById(R.id.ver_tav_print);
		ver_tav_print.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				DadosDoAuto auoAutoDeData = DatabaseCreator
						.getDatabaseAdapterAutoInfracao(mycontext)
						.getAutoDeDataFromAutoDeNumero(
								tavData.getNumero_do_auto());

				printListener.print(tavData, auoAutoDeData);

			}
		});
		return mView;
	}
}

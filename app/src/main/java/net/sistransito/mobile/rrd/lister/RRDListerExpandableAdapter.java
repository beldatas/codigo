package net.sistransito.mobile.rrd.lister;


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
import net.sistransito.mobile.database.AutoInfracaoDatabaseHelper;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.database.RRDDatabaseHelper;
import net.sistransito.mobile.impressaobluetooth.bluetooth.BluetoothPrinterListerner;
import net.sistransito.mobile.rrd.RRDData;
import net.sistrnsitomobile.R;

public class RRDListerExpandableAdapter extends CursorTreeAdapter {
	private LayoutInflater mInflator;
	private Context mycontext;
	private BluetoothPrinterListerner printListener;

	public RRDListerExpandableAdapter(Cursor cursor, Context context) {
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
		TextView log_plate, log_documento;
		log_plate = (TextView) view.findViewById(R.id.log_plate);
		log_documento = (TextView) view.findViewById(R.id.log_documento);

		log_plate.setText(cursor.getString(cursor
				.getColumnIndex(RRDDatabaseHelper.PLACA)));
		log_documento.setText(cursor.getString(cursor
				.getColumnIndex(RRDDatabaseHelper.DOCUMENTO)));

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
				.inflate(R.layout.rrd_lista_listview_parent, null);
		TextView log_plate, log_documento;
		log_plate = (TextView) view.findViewById(R.id.log_plate);
		log_documento = (TextView) view.findViewById(R.id.log_documento);

		log_plate.setText(cursor.getString(cursor
				.getColumnIndex(RRDDatabaseHelper.PLACA)));
		log_documento.setText(cursor.getString(cursor
				.getColumnIndex(RRDDatabaseHelper.DOCUMENTO)));

		return view;
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		int groupId = groupCursor.getInt(groupCursor
				.getColumnIndex(AutoInfracaoDatabaseHelper.COLUMN_ID));
		return (DatabaseCreator.getDatabaseAdapterRRD(mycontext))
				.getRRDCursorFromID(groupId);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		// final int gPosition = groupPosition;
		TextView ver_rrd_childview;
		Button ver_rrd_print;
		View mView = null;
		if (view != null) {
			mView = view;
		} else {
			mView = mInflator.inflate(R.layout.rrd_lista_listview_child,
					parent, false);
		}
		ver_rrd_childview = (TextView) mView
				.findViewById(R.id.ver_rrd_childview);
		final RRDData rrdData = new RRDData();
		rrdData.setRRDDataFromCursor(getGroup(groupPosition));
		ver_rrd_childview.setText(rrdData.getRRDListViewData(mycontext));
		ver_rrd_print = (Button) mView.findViewById(R.id.ver_rrd_print);
		ver_rrd_print.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DadosDoAuto auoAutoDeData = DatabaseCreator
						.getDatabaseAdapterAutoInfracao(mycontext)
						.getAutoDeDataFromAutoDeNumero(
								rrdData.getN_auto_de_infracao());

				printListener.print(rrdData, auoAutoDeData);
			}
		});
		return mView;
	}
}

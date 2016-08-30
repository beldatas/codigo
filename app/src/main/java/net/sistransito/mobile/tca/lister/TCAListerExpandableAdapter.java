package net.sistransito.mobile.tca.lister;


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
import net.sistransito.mobile.database.TCADatabaseHelper;
import net.sistransito.mobile.impressaobluetooth.bluetooth.BluetoothPrinterListerner;
import net.sistransito.mobile.tca.TcaData;
import net.sistrnsitomobile.R;

public class TCAListerExpandableAdapter extends CursorTreeAdapter {
	private LayoutInflater mInflator;
	private Context mycontext;
	private BluetoothPrinterListerner printListener;

	public TCAListerExpandableAdapter(Cursor cursor, Context context) {
		super(cursor, context);
		mycontext = context;
		mInflator = LayoutInflater.from(context);
		printListener = (BluetoothPrinterListerner)context;
	}

	@Override
	protected void bindChildView(View arg0, Context arg1, Cursor arg2,
			boolean arg3) {
	}

	@Override
	protected void bindGroupView(View view, Context arg1, Cursor cursor,
			boolean arg3) {
		TextView log_placa, log_nome, log_cnh_ppd;

		log_placa = (TextView) view.findViewById(R.id.log_placa);

		log_nome = (TextView) view.findViewById(R.id.log_nome);
		log_cnh_ppd = (TextView) view.findViewById(R.id.log_chn_ppd);

		log_placa.setText(cursor.getString(cursor
				.getColumnIndex(TCADatabaseHelper.PLACA)));

		log_nome.setText(cursor.getString(cursor
				.getColumnIndex(TCADatabaseHelper.NOME_DO_CONDUTOR)));

		log_cnh_ppd.setText(cursor.getString(cursor
				.getColumnIndex(TCADatabaseHelper.CNH_PPD)));

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
				.inflate(R.layout.tca_lister_listview_parent, null);
		TextView log_placa, log_nome, log_cnh_ppd;

		log_placa = (TextView) view.findViewById(R.id.log_placa);

		log_nome = (TextView) view.findViewById(R.id.log_nome);
		log_cnh_ppd = (TextView) view.findViewById(R.id.log_chn_ppd);

		log_placa.setText(cursor.getString(cursor
				.getColumnIndex(TCADatabaseHelper.PLACA)));

		log_nome.setText(cursor.getString(cursor
				.getColumnIndex(TCADatabaseHelper.NOME_DO_CONDUTOR)));

		log_cnh_ppd.setText(cursor.getString(cursor
				.getColumnIndex(TCADatabaseHelper.CNH_PPD)));

		return view;
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		int groupId = groupCursor.getInt(groupCursor
				.getColumnIndex(TCADatabaseHelper.COLUMN_ID));
		return (DatabaseCreator.getDatabaseAdapterTCA(mycontext))
				.getTCACursorFromID(groupId);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		// final int gPosition = groupPosition;
		TextView ver_tca_childview;
		Button ver_tca_print;
		View mView = null;
		if (view != null) {
			mView = view;
		} else {
			mView = mInflator.inflate(R.layout.tca_lister_listview_child,
					parent, false);
		}
		ver_tca_childview = (TextView) mView
				.findViewById(R.id.ver_tca_childview);
		final TcaData tcaData = new TcaData();
		tcaData.setTCADataFromCursor(getGroup(groupPosition));
		ver_tca_childview.setText(tcaData.getTCAListViewData(mycontext));
		ver_tca_print = (Button) mView.findViewById(R.id.ver_tca_print);
		ver_tca_print.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				DadosDoAuto auoAutoDeData = DatabaseCreator
						.getDatabaseAdapterAutoInfracao(mycontext)
						.getAutoDeDataFromAutoDeNumero(tcaData.getNumero_auto());

				printListener.print(tcaData, auoAutoDeData);

			}
		});
		return mView;
	}
}

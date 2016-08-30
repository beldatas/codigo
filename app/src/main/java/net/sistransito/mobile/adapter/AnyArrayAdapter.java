package net.sistransito.mobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AnyArrayAdapter<T> extends ArrayAdapter<T> {

	// private Context context;
	private int text_id;

	public AnyArrayAdapter(Context context, int resource,
			int textViewResourceId, List<T> objects) {
		super(context, resource, textViewResourceId, objects);
		// this.context = context;
		text_id = textViewResourceId;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = super.getView(position, convertView, parent);
		TextView tv = (TextView) v.findViewById(text_id);
		tv.setSingleLine(false);
		tv.setTextColor(Color.WHITE);
		// tv.setTypeface(AppObject.getCurrentFont(context));
		tv.setPadding(0, 0, 15, 0);
		tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		return v;

	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		View v = super.getView(position, convertView, parent);
		TextView tv = (TextView) v.findViewById(text_id);
		tv.setSingleLine(false);
		tv.setTextColor(Color.BLACK);
		tv.setBackgroundColor(Color.WHITE);
		tv.setPadding(4, 10, 5, 10);
		//v.setBackgroundColor(Color.WHITE);
		return v;
	}

}
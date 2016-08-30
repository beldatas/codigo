package net.sistransito.mobile.tav;

import android.content.Context;

import net.sistrnsitomobile.R;

import java.util.Arrays;
import java.util.List;

public class FilterName {
	private List<String> list_full_name;
	private List<String> list_short_name;

	public FilterName(Context context) {

		list_full_name = Arrays.asList(context.getResources().getStringArray(
				R.array.filter_full_name));

		list_short_name = Arrays.asList(context.getResources().getStringArray(
				R.array.filter_short_name));
	}

	public String filter(String filterData) {
		int value = list_full_name.indexOf(filterData);
		if (value!=-1) {
			return list_short_name.get(value);
		} else
			return filterData;
	}
}

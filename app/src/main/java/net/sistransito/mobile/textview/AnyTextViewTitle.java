package net.sistransito.mobile.textview;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import net.sistransito.mobile.appobjeto.AppObject;

public class AnyTextViewTitle extends TextView {

	public AnyTextViewTitle(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);

		init();
	}

	public AnyTextViewTitle(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	public AnyTextViewTitle(Context context) {
		super(context);

		init();
	}

	private void init() {
		setTypeface(AppObject.getTitlefont(getContext()));
	}

}

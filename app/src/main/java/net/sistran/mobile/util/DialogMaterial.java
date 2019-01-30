package net.sistran.mobile.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.rey.material.app.BottomSheetDialog;

import net.sistran.R;

/**
 * Created by GaziRimon on 8/22/2015.
 */
public class DialogMaterial {

    private static BottomSheetDialog bottomSheetDialog;
    private static TextView tv_mgs;

    public static BottomSheetDialog getBottomSheet(String mgs, int color, Context cnt) {
        bottomSheetDialog = new BottomSheetDialog(cnt);
        View v = LayoutInflater.from(cnt).inflate(R.layout.view_bottomsheet, null);
        tv_mgs = ((TextView) v.findViewById(R.id.tv_message));
        tv_mgs.setText(mgs);
        tv_mgs.setTextColor(color);
        bottomSheetDialog.contentView(v);
        return bottomSheetDialog;
    }
    public static void setMgs(String mgs) {
        tv_mgs.setText(mgs);
    }

}
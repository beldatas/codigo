package net.sistransito.mobile.tav.lister;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.sistransito.mobile.autoinfracao.DadosDoAuto;
import net.sistransito.mobile.database.DatabaseCreator;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.TAVPrintBitmap;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.BasePrintBitmap;
import net.sistransito.mobile.tav.TAVData;
import net.sistransito.ui.BasePrintActivity;
import net.sistrnsitomobile.R;

public class TAVLister extends BasePrintActivity implements OnClickListener {
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tav_lister_main);
        initializedView();
    }

    @Override
    protected void onPrintData(Object mainData, Object autodeData) {
        BasePrintBitmap tavPrintBitmap = new TAVPrintBitmap(this,
                (TAVData) mainData, (DadosDoAuto) autodeData);
        startPrint(tavPrintBitmap);
    }

    private void initializedView() {
        ImageButton im_btn_back = (ImageButton) findViewById(R.id.im_btn_back);
        im_btn_back.setOnClickListener(this);

        if (checCursor()) {
            addResultView();
        } else {
            addNoResultView();
        }
    }

    private boolean checCursor() {
        cursor = (DatabaseCreator.getDatabaseAdapterTAV(this)).getTAVCursor();
        return cursor.getCount() > 0;
    }

    private void addNoResultView() {
        RelativeLayout tav_lister_layout = (RelativeLayout) findViewById(R.id.tav_lister_layout);
        cursor.close();
        TextView tvMessage = new TextView(this);
        tvMessage.setText(getResources().getString(
                R.string.nehum_resultado_retornado));
        tvMessage.setGravity(Gravity.CENTER);
        tvMessage.setTextAppearance(this, android.R.style.TextAppearance_Large);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        tvMessage.setLayoutParams(params);
        tvMessage.setTextColor(getResources().getColor(R.color.line_color));
        if (tvMessage.getParent() == null) {
            tav_lister_layout.addView(tvMessage);
        }
    }

    private void addResultView() {
        TAVListerExpandableAdapter expandableAdapter = new TAVListerExpandableAdapter(cursor,
                TAVLister.this);
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewTca);
        expandableListView.setAdapter(expandableAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_btn_back:
                finish();
                break;
        }
    }
}

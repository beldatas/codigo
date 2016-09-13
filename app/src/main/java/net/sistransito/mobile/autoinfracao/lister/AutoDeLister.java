package net.sistransito.mobile.autoinfracao.lister;

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
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.AutoDePrintBitmap;
import net.sistransito.mobile.impressaobluetooth.PrintBitmap.base.BasePrintBitmap;
import net.sistransito.ui.BasePrintActivity;
import net.sistrnsitomobile.R;

public class AutoDeLister extends BasePrintActivity implements OnClickListener {
    private AutodeListerExpandableAdapter expandableAdapter;
    private ExpandableListView expandableListView;
    private ImageButton im_btn_back;
    private TextView tvMessage;
    private RelativeLayout autode_lister_layout;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_lista_main);
        initializedView();
    }

    @Override
    protected void onPrintData(Object mainData, Object autodeData) {
        BasePrintBitmap printBitmap = new AutoDePrintBitmap(this, (DadosDoAuto) mainData);
        startPrint(printBitmap);
    }

    private void initializedView() {
        im_btn_back = (ImageButton) findViewById(R.id.im_btn_back);
        im_btn_back.setOnClickListener(this);

        if (checCursor()) {
            addResultView();
        } else {
            addNoResultView();
        }
    }

    private boolean checCursor() {
        cursor = (DatabaseCreator.getDatabaseAdapterAutoInfracao(this))
                .getAutoDeCursor();
        return cursor.getCount() > 0;
    }

    private void addNoResultView() {
        autode_lister_layout = (RelativeLayout) findViewById(R.id.autode_lister_layout);
        cursor.close();
        tvMessage = new TextView(this);
        tvMessage.setText(getResources().getString(
                R.string.nehum_resultado_retornado));
        tvMessage.setGravity(Gravity.CENTER);
        tvMessage.setTextAppearance(this, android.R.style.TextAppearance_Large);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        tvMessage.setLayoutParams(params);
        tvMessage.setTextColor(getResources().getColor(R.color.line_color));
        if (tvMessage.getParent() == null) {
            autode_lister_layout.addView(tvMessage);
        }
    }

    private void addResultView() {
        expandableAdapter = new AutodeListerExpandableAdapter(cursor,
                AutoDeLister.this);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewPlaca);
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
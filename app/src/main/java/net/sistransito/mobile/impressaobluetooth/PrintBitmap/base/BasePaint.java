package net.sistransito.mobile.impressaobluetooth.PrintBitmap.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;

public class BasePaint {

    public final int PAGE_WIDTH = 576, PAGE_HEIGHT = 3200;
    public final int MARGIN_SMALL = 5;
    public final int MARGIN_LARGE = 10;
    public final int TABLE_BORDER = 2;
    private Context context;
    public Paint paint;
    protected Bitmap printBitmap;
    public int y_position, x_position_start, x_text_start, x_position_end;
    public Canvas canvas;

    public BasePaint(Context context) {
        this.context = context;

        y_position = MARGIN_LARGE + MARGIN_SMALL;
        x_position_start = MARGIN_LARGE;
        x_position_end = PAGE_WIDTH - MARGIN_LARGE;
        x_text_start = x_position_start + MARGIN_SMALL;

        printBitmap = getRawBitmap();
        canvas = new Canvas(printBitmap);
        paint = new Paint(Color.BLACK);
        setPaintNormal();
        //        paintTitle.setTextSize(17);
//
//        paintTitleBold = new Paint(Color.BLACK);
//        paintTitleBold.setTextSize(19);
//        paintTitleBold.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
//
//        paintSubTitleBold = new Paint(Color.BLACK);
//        paintSubTitleBold.setTextSize((float) 17.5);
//        paintSubTitleBold.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
//
//        paintNormal = new Paint(Color.BLACK);
//        paintNormal.setTextSize(15);
//        paintNormal.setTypeface(Typeface.create(Typeface.SANS_SERIF,
//                Typeface.NORMAL));
//
//        paintMedio = new Paint(Color.BLACK);
//        paintMedio.setTextSize(19);
//        paintMedio.setTypeface(Typeface.create(Typeface.SANS_SERIF,
//                Typeface.NORMAL));
//
//        paintMaior = new Paint(Color.BLACK);
//        paintMaior.setTextSize(25);
//        paintMaior.setTypeface(Typeface.create(Typeface.SANS_SERIF,
//                Typeface.BOLD));

    }

    public void setPaintBold() {
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,
                Typeface.BOLD));

    }

    public void setPaintNormal() {
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,
                Typeface.NORMAL));

    }

    public Context getContext() {
        return context;
    }

    public Bitmap getBitmapFromAsset(String filePath, int widthHeight) {
        AssetManager assetManager = getContext().getAssets();
        InputStream stream;
        Bitmap bitmap = null;
        try {
            stream = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(stream);
        } catch (IOException ignored) {
        }
        return scaleDown(bitmap, true, widthHeight);
    }

    public Bitmap scaleDown(Bitmap realImage,
                            boolean filter, int widthHeight) {
        float ratio = Math.min(
                (float) widthHeight / realImage.getWidth(),
                (float) widthHeight / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());
        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width, height, filter);
        return newBitmap;
    }

    private Bitmap getRawBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(PAGE_WIDTH, PAGE_HEIGHT, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        return bitmap;
    }

    public Bitmap getPrintBitmap() {
        return printBitmap;
    }

    public void saveBitmap() {
        SaveBitmap saveBitmap = new SaveBitmap(context);
        saveBitmap.saveImage(printBitmap);
    }

    public void drawBitmap(String fileName, int widthHeight, int left, int top) {
        widthHeight = widthHeight - 4 * MARGIN_SMALL;
        left += MARGIN_SMALL;
        top += MARGIN_SMALL;
        Bitmap bitmap = getBitmapFromAsset(fileName, widthHeight);
        canvas.drawBitmap(bitmap, left, top, new Paint());
    }

    private Bitmap getFinalBitmap() {
        Bitmap bitmapReturn = Bitmap.createBitmap(printBitmap.getWidth(), y_position,
                Bitmap.Config.RGB_565);
        Canvas g = new Canvas(bitmapReturn);
        g.drawColor(Color.WHITE);
        g.drawBitmap(printBitmap, 0, 0, new Paint());
        return bitmapReturn;
    }

    public void printDocumnetClose() {
        printBitmap = getFinalBitmap();
    }
}

package com.inube.demonav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    Button click;
    ImageView view_img;
    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        click=(Button)findViewById(R.id.button);
        view_img=(ImageView)findViewById(R.id.img);
         barChart = findViewById(R.id.barchart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarEntry> barEntries1 = new ArrayList<>();
        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        ArrayList<BarEntry> barEntries3 = new ArrayList<>();

        barEntries.add(new BarEntry(1,989.21f));
        barEntries.add(new BarEntry(2,420.22f));
        barEntries.add(new BarEntry(3,758));
        barEntries.add(new BarEntry(4,3078.97f));
        barEntries.add(new BarEntry(5,14586.96f));
        barEntries.add(new BarEntry(6,400.4f));
        barEntries.add(new BarEntry(7,5888.58f));

        barEntries1.add(new BarEntry(1,950));
        barEntries1.add(new BarEntry(2,791));
        barEntries1.add(new BarEntry(3,630));
        barEntries1.add(new BarEntry(4,782));
        barEntries1.add(new BarEntry(5,2714.54f));
        barEntries1.add(new BarEntry(6,500));
        barEntries1.add(new BarEntry(7,2173.36f));

        barEntries2.add(new BarEntry(1,900));
        barEntries2.add(new BarEntry(2,691));
        barEntries2.add(new BarEntry(3,1030));
        barEntries2.add(new BarEntry(4,382));
        barEntries2.add(new BarEntry(5,2714f));
        barEntries2.add(new BarEntry(6,5000));
        barEntries2.add(new BarEntry(7,1173f));

        barEntries3.add(new BarEntry(1,200));
        barEntries3.add(new BarEntry(2,991));
        barEntries3.add(new BarEntry(3,1830));
        barEntries3.add(new BarEntry(4,3082));
        barEntries3.add(new BarEntry(5,214));
        barEntries3.add(new BarEntry(6,5600));
        barEntries3.add(new BarEntry(7,9173));

        BarDataSet barDataSet = new BarDataSet(barEntries,"DATA SET 1");
        barDataSet.setColor(Color.parseColor("#F44336"));
        BarDataSet barDataSet1 = new BarDataSet(barEntries1,"DATA SET 2");
        barDataSet1.setColors(Color.parseColor("#9C27B0"));
        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"DATA SET 3");
        barDataSet1.setColors(Color.parseColor("#e241f4"));
        BarDataSet barDataSet3 = new BarDataSet(barEntries3,"DATA SET 4");
        barDataSet1.setColors(Color.parseColor("#42f46e"));

        String[] months = new String[] {"TYPE 1", "TYPE 2", "TYPE 3", "TYPE 4"};
        BarData data = new BarData(barDataSet,barDataSet1,barDataSet2,barDataSet3);
        barChart.setData(data);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        barChart.getAxisLeft().setAxisMinimum(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularityEnabled(true);

        float barSpace = 0.02f;
        float groupSpace = 0.3f;
        int groupCount = 4;

        //IMPORTANT *****
        data.setBarWidth(0.15f);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        barChart.groupBars(0, groupSpace, barSpace); // perform the "explicit" grouping


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.aia_pdf_image);//assign your bitmap;
//                    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.aia_pdf_image_1);//assign your bitmap;
////
////                    Bitmap[] listBmp = {bitmap1, bitmap2};
////
////                    Bitmap mergedImg = mergeMultiple(listBmp);

                    Bitmap bitmap2=getViewBitmap(barChart);

                    Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.aia_pdf_image);//assign your bitmap;

                    Bitmap[] listBmp = {bitmap1, bitmap2};
//                    Bitmap mergedImg = overlay(bitmap1,bitmap2);
                    Bitmap mergedImg = mergeBitmap(bitmap1,bitmap2);

                    view_img.setImageBitmap(mergedImg);
                }catch (Exception e){
                    Log.e("error" ,e.getMessage());
                }
            }
        });
    }
    public static Bitmap overlay(Bitmap firstImage, Bitmap secondImage) {
//        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
//        Canvas canvas = new Canvas(bmOverlay);
//        canvas.drawBitmap(bmp1, new Matrix(), null);
//        canvas.drawBitmap(bmp2, 0, 0, null);
//        return bmOverlay;
        Bitmap result = Bitmap.createBitmap(firstImage.getWidth() + secondImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, firstImage.getWidth(), 0f, null);
        return result;
    }
    private Bitmap mergeMultiple(Bitmap[] parts){

        Bitmap result = Bitmap.createBitmap(parts[0].getWidth(), parts[0].getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        for (int i = 0; i < parts.length; i++) {
            canvas.drawBitmap(parts[i], parts[i].getWidth()*(i%2), parts[i].getHeight()*(i/2), paint);
        }
        return result;
    }

    public  Bitmap getViewBitmap(View view){
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    public Bitmap mergeBitmap(Bitmap bitmap1, Bitmap bitmap2) {
        Bitmap mergedBitmap = null;

        int w=0, h = 0;

        h = bitmap1.getHeight() + bitmap2.getHeight();
        w=bitmap1.getWidth()+bitmap2.getWidth();
//        if (bitmap1.getWidth() > bitmap2.getWidth()) {
//            w = bitmap1.getWidth();
//        } else {
//            w = bitmap2.getWidth();
//        }

        mergedBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(mergedBitmap);

        canvas.drawBitmap(bitmap1, 0f, 0f, null);
        canvas.drawBitmap(bitmap2, 0f, bitmap1.getHeight(), null);


        return mergedBitmap;
    }
}

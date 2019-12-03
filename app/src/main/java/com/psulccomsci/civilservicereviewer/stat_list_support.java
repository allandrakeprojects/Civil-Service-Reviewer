package com.psulccomsci.civilservicereviewer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class stat_list_support extends AppCompatActivity {

    int a=1, b=3, c=6,d=2, e=9, f=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_list_support);
        BarChart chart = findViewById(R.id.bargraph2);

        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new BarEntry(1f, a));
        NoOfEmp.add(new BarEntry(2f, b));
        NoOfEmp.add(new BarEntry(3f, c));
        NoOfEmp.add(new BarEntry(4f, d));
        NoOfEmp.add(new BarEntry(5f, e));
        NoOfEmp.add(new BarEntry(6f, f));


        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        chart.animateY(5000);
        BarData data = new BarData(bardataset);
        data.setBarWidth(0.9f);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
        chart.setFitBars(true);




    }
}

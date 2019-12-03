package com.psulccomsci.civilservicereviewer.ui.statistics;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.psulccomsci.civilservicereviewer.R;
import com.psulccomsci.civilservicereviewer.ScoreDBHelper;
import com.psulccomsci.civilservicereviewer.dbhelper;
import com.psulccomsci.civilservicereviewer.reader;
import com.psulccomsci.civilservicereviewer.scoreMain;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {
    int a=1, b=3, c=6,d=2, e=9, f=1;
    String x, y, z;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    ScoreDBHelper scoreDBHelper;
    Cursor scoretemp, counter, examtemp;
    int i=1, count=0;
    String scorer, examname;
    private StatisticsViewModel statisticsViewModelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                statisticsViewModelViewModel =
                ViewModelProviders.of(this).get(StatisticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        ArrayList<String> list= new ArrayList<>();

        list.add("Scores");
        openHelper = new ScoreDBHelper(getContext());
        db = openHelper.getWritableDatabase();
        scoreDBHelper = new ScoreDBHelper(getContext());
        scoretemp = db.rawQuery(" SELECT SCORES FROM "+ scoreDBHelper.table_scores + " where ID = "+i,null);
        counter = db.rawQuery(" SELECT SCORES FROM "+ scoreDBHelper.table_scores,null);
        counter.getCount();
        count = counter.getCount();

        if (counter.getCount()==0){}else{
            while (i <= count) {

                scoretemp = db.rawQuery(" SELECT SCORES FROM " + scoreDBHelper.table_scores + " where ID = " + i, null);
                scoretemp.moveToFirst();
                examtemp = db.rawQuery(" SELECT EXAM FROM " + scoreDBHelper.table_scores + " where ID = " + i, null);
                examtemp.moveToFirst();
                scorer = scoretemp.getString(0);
                examname = examtemp.getString(0);
                list.add(i+".)  "+examname + " - "+scorer);
                i++;
            }


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        ListView listView = (ListView)root.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                Intent intent = new Intent(getContext(), reader.class);

            }
        });
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BarChart chart = view.findViewById(R.id.bargraph2);
        ArrayList<Integer> barlist= new ArrayList<>();

        ArrayList NoOfEmp = new ArrayList();

        openHelper = new ScoreDBHelper(getContext());
        db = openHelper.getWritableDatabase();
        scoreDBHelper = new ScoreDBHelper(getContext());
        counter = db.rawQuery(" SELECT SCORES FROM "+ scoreDBHelper.table_scores,null);
        count = counter.getCount();
        if(counter.getCount()==0){
            NoOfEmp.add(new BarEntry(0, 0));
            BarDataSet bardataset = new BarDataSet(NoOfEmp, "Subjects of Exam");
            chart.animateY(3000);
            BarData data = new BarData(bardataset);
            data.setBarWidth(0.9f);
            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
            chart.setData(data);
            chart.setFitBars(true);
            Toast.makeText(getContext(), "No content available now!!! Please take the exam to record your progress!!!", Toast.LENGTH_SHORT).show();
        }else{
            int k = 1;
            int j = 1;
            while (k <= count) {
                scoretemp = db.rawQuery(" SELECT SCORES FROM " + scoreDBHelper.table_scores + " where ID = " + k, null);
                scoretemp.moveToFirst();
                scorer = scoretemp.getString(0);
                j = Integer.parseInt(scorer);
                NoOfEmp.add(new BarEntry(k, j));
                k++;
            }

            BarDataSet bardataset = new BarDataSet(NoOfEmp, "Subjects of Exam");
            chart.animateY(3000);
            BarData data = new BarData(bardataset);
            data.setBarWidth(0.9f);
            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
            chart.setData(data);
            chart.setFitBars(true);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
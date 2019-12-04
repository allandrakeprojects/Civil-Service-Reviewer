package com.psulccomsci.civilservicereviewer.ui.statistics;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Spinner;
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
    Spinner spinnerStatistics;
    int a=1, b=3, c=6,d=2, e=9, f=1;
    String x, y, z;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    ScoreDBHelper scoreDBHelper;
    Cursor scoretemp, counter, examtemp;
    int i=1, count=0;
    String scorer, examname;
    private StatisticsViewModel statisticsViewModelViewModel;
    ArrayList<String> list= new ArrayList<>();
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                statisticsViewModelViewModel =
                ViewModelProviders.of(this).get(StatisticsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_statistics, container, false);


        spinnerStatistics = root.findViewById(R.id.spinnerStatistics);

        list.add("Scores");
        openHelper = new ScoreDBHelper(getContext());
        db = openHelper.getWritableDatabase();
        scoreDBHelper = new ScoreDBHelper(getContext());

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list);

        spinnerStatistics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Shared Preference ------------
                SharedPreferences sharedPref = getContext().getSharedPreferences("mypref", 0);
                String currentUser = sharedPref.getString("currentUser", "");
                // End ------------

                String selected = parentView.getItemAtPosition(position).toString();
                if(selected.equals("Professional")){
                    list.clear();

                    scoretemp = db.rawQuery("SELECT SCORES FROM "+ scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '0' GROUP BY EXAM",null);
                    counter = db.rawQuery("SELECT SCORES FROM "+ scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '0' GROUP BY EXAM",null);
                    counter.getCount();
                    count = counter.getCount();

                    if (counter.getCount()==0){
                        list.add("No Data");
                    }else{
                        scoretemp = db.rawQuery("SELECT MAX(EXAM), SCORES FROM " + scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '0' GROUP BY EXAM", null);
                        while(scoretemp.moveToNext()){
                            scorer = scoretemp.getString(1);
                            examname = scoretemp.getString(0);
                            list.add(examname + " --- Score: " + scorer);
                        }
                    }

                    listView = root.findViewById(R.id.listView);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            // TODO Auto-generated method stub
                            String value = adapter.getItem(position);
                            Intent intent = new Intent(getContext(), reader.class);
                        }
                    });

                    adapter.notifyDataSetChanged();

                    // Bar Graph ---------------
                    BarChart chart = root.findViewById(R.id.bargraph2);
                    ArrayList<Integer> barlist= new ArrayList<>();

                    ArrayList NoOfEmp = new ArrayList();

                    openHelper = new ScoreDBHelper(getContext());
                    db = openHelper.getWritableDatabase();
                    scoreDBHelper = new ScoreDBHelper(getContext());

                    counter = db.rawQuery("SELECT MAX(SCORES) FROM "+ scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '0' GROUP BY EXAM",null);
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


                        scoretemp = db.rawQuery("SELECT MAX(SCORES) FROM "+ scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '0' GROUP BY EXAM", null);
                        while(scoretemp.moveToNext()){
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
                else if(selected.equals("Non Professional")){
                    list.clear();

                    scoretemp = db.rawQuery("SELECT SCORES FROM "+ scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '1' GROUP BY EXAM",null);
                    counter = db.rawQuery("SELECT SCORES FROM "+ scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '1' GROUP BY EXAM",null);
                    counter.getCount();
                    count = counter.getCount();

                    if (counter.getCount()==0){
                        list.add("No Data");
                    }else{
                        scoretemp = db.rawQuery("SELECT MAX(EXAM), SCORES FROM " + scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '1' GROUP BY EXAM", null);
                        while(scoretemp.moveToNext()){
                            scorer = scoretemp.getString(1);
                            examname = scoretemp.getString(0);
                            list.add(examname + " --- Score: " + scorer);
                        }
                    }

                    listView = root.findViewById(R.id.listView);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            // TODO Auto-generated method stub
                            String value = adapter.getItem(position);
                            Intent intent = new Intent(getContext(), reader.class);
                        }
                    });

                    adapter.notifyDataSetChanged();

                    // Bar Graph ---------------
                    BarChart chart = root.findViewById(R.id.bargraph2);
                    ArrayList<Integer> barlist= new ArrayList<>();

                    ArrayList NoOfEmp = new ArrayList();

                    openHelper = new ScoreDBHelper(getContext());
                    db = openHelper.getWritableDatabase();
                    scoreDBHelper = new ScoreDBHelper(getContext());

                    counter = db.rawQuery("SELECT MAX(SCORES) FROM "+ scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '1' GROUP BY EXAM",null);
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


                        scoretemp = db.rawQuery("SELECT MAX(SCORES) FROM "+ scoreDBHelper.table_scores + " WHERE USER= + '" + currentUser + "'" + " AND TYPE = '1' GROUP BY EXAM", null);
                        while(scoretemp.moveToNext()){
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
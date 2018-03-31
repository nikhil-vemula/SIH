package com.jasmine.vsnick.sih;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalysisFragment extends Fragment {

    float non,nc,np;
    public AnalysisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        non=nc=np=0;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("structures");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Structure structure = dataSnapshot.getValue(Structure.class);
                if(structure.getStatus().equals("on-going")){
                    non++;
                }else if(structure.getStatus().equals("completed")){
                    nc++;
                }else if(structure.getStatus().equals("proposed")){
                    Log.d("yo1", "onChildAdded: ");
                    np++;
                }else{
                    Log.d("yo", "onChildAdded: ");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("Analysis Frag", "onViewCreated: "+np);
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(2f, 0));
        entries.add(new PieEntry(2f, 1));
        entries.add(new PieEntry(2f, 2));
        PieDataSet dataset = new PieDataSet(entries, "");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");

//        PieChart chart = new PieChart(getContext());
//        getActivity().setContentView(chart);
        PieChart chart = getActivity().findViewById(R.id.piechart);

        PieData data = new PieData(dataset);
        chart.setData(data);

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(4f, 0));
        entries1.add(new BarEntry(8f, 1));
        entries1.add(new BarEntry(6f, 2));
        BarDataSet dataset1 = new BarDataSet(entries1, "");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels1 = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");

//        PieChart chart = new PieChart(getContext());
//        getActivity().setContentView(chart);
        BarChart chart1 = getActivity().findViewById(R.id.barchart);

        BarData data1 = new BarData(dataset1);
        chart1.setData(data1);




    }
}

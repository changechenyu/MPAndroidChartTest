package com.example.chenyu.mpandroidcharttest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Think on 2015/11/29.
 */
public class ScatterChartFragment extends Fragment{
    public BarChart barChart;
    public ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
    public BarDataSet dataset;
    public ArrayList<String> labels=new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bar_chart, container, false);
        barChart= (BarChart) view.findViewById(R.id.bar_chart);
        initEntriesData();
        initLableData();
        show();
        return view;
    }
    public void initEntriesData(){
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));
    }
    public void initLableData(){
        labels.add("一月");
        labels.add("二月");
        labels.add("三月");
        labels.add("四月");
        labels.add("五月");
        labels.add("六月");
    }
    public void show(){
        dataset= new BarDataSet(entries,"# of Calls");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data=new BarData(labels,dataset);
        LimitLine line=new LimitLine(10f);
        barChart.setData(data);
//        chart.animateXY(5000,5000);
//        chart.animateX(5000);
        barChart.animateY(2000);
        barChart.setDescription("hello MPandroidChart");
    }
}

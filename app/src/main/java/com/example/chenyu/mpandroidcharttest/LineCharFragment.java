package com.example.chenyu.mpandroidcharttest;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by Think on 2015/11/29.
 */
public class LineCharFragment extends Fragment {
    public LineChart lineChart;
    public ArrayList<String> x=new ArrayList<String>();
    public ArrayList<Entry> y=new ArrayList<Entry>();
    public ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
    public LineData lineData=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.liner_chart, container, false);
        lineChart= (LineChart) view.findViewById(R.id.spread_line_chart);
        LineData resultLineData=getLineData(40, 100);
        showChart(lineChart,resultLineData, Color.rgb(110, 190, 224));
        return view;
    }
    /**
     * 初始化数据
     * count 表示坐标点个数，range表示等下y值生成的范围
     */
    public LineData getLineData(int count,float range){
        for(int i=0;i<count;i++){  //X轴显示的数据
            x.add(i+"");
        }
        for(int i=0;i<count;i++){//y轴的数据
            float result=(float)(Math.random()*range)+3;
            y.add(new Entry(result,i));
        }
        LineDataSet lineDataSet=new LineDataSet(y,"折线图");//y轴数据集合
        lineDataSet.setLineWidth(1f);//线宽
        lineDataSet.setCircleSize(2f);//现实圆形大小
        lineDataSet.setColor(Color.RED);//现实颜色
        lineDataSet.setCircleSize(Color.BLUE);//圆形颜色
        lineDataSet.setHighLightColor(Color.WHITE);//高度线的颜色
        lineDataSets.add(lineDataSet);
        lineData=new LineData(x,lineDataSets);
        return lineData;
    }
    /**
     * 设置样式
     */
    public void showChart(com.github.mikephil.charting.charts.LineChart lineChart,LineData lineData,int color){
        lineChart.setDrawBorders(false);//是否添加边框
        lineChart.setDescription("有风险的数据");//数据描述
        lineChart.setNoDataTextDescription("我需要数据");//没数据显示
        lineChart.setDrawGridBackground(true);//是否显示表格颜色
        lineChart.setBackgroundColor(Color.YELLOW);//背景颜色
        lineChart.setData(lineData);//设置数据
        Legend legend=lineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.CIRCLE);//样式
        legend.setFormSize(6f);//字体
        legend.setTextColor(Color.WHITE);//设置颜色
        lineChart.animateX(3000);//X轴的动画
    }
}

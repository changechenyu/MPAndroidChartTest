package com.example.chenyu.mpandroidcharttest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public BarChart barChart;
    public ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
    public BarDataSet dataset;
    public ArrayList<String> labels=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        BarCharFragment barCharFragment=new BarCharFragment();
        transaction.replace(R.id.content, barCharFragment);
        transaction.commit();
//        barChart= (BarChart) findViewById(R.id.bar_chart);
//        initEntriesData();
//        initLableData();
//        show();
        //显示右上角的3个点
        makeActionOverflowMenuShown();
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
        barChart.animateY(3000);
        barChart.setDescription("hello MPandroidChart");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        setMenuItemTextColorToWhite(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        int id = item.getItemId();
        switch (id){
            case R.id.BarChart:
                BarCharFragment barCharFragment=new BarCharFragment();
                transaction.replace(R.id.content, barCharFragment);
                transaction.commit();
                return true;
            case R.id.LineChart:
                LineCharFragment lineCharFragment=new LineCharFragment();
                transaction.replace(R.id.content, lineCharFragment);
                transaction.commit();
                return true;
            case R.id.RadarChart:
                Toast.makeText(MainActivity.this, "RadarChart", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.PieChart:
                Toast.makeText(MainActivity.this, "PieChart", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.ScatterChart:
                Toast.makeText(MainActivity.this, "ScatterChart", Toast.LENGTH_SHORT).show();
                return true;
        }
        // transaction.addToBackStack();
        // 事务提交

        return super.onOptionsItemSelected(item);
    }
    private void makeActionOverflowMenuShown() {
        //devices with hardware menu button (e.g. Samsung Note) don't show action overflow menu
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {

        }
    }
    /**
     * 修改 ActionBar 上的菜单字体颜色
     */
    public static void setMenuItemTextColorToWhite(final Activity activity){
        activity.getLayoutInflater().setFactory(new LayoutInflater.Factory() {
            @Override
            public View onCreateView(String name, Context context,
                                     AttributeSet attrs) {
                if (name.equalsIgnoreCase("com.android.internal.view.menu.IconMenuItemView")
                        || name.equalsIgnoreCase("com.android.internal.view.menu.ActionMenuItemView")) {
                    try {
                        LayoutInflater f = activity.getLayoutInflater();
                        final View view = f.createView(name, null, attrs);
                        System.out.println((view instanceof TextView));
                        if (view instanceof TextView) {
                            ((TextView) view).setTextColor(Color.RED/*这里修改颜色*/);
                        }
                        return view;
                    } catch (InflateException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

        });
    }
}

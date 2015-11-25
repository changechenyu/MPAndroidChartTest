package com.example.chenyu.mpandroidcharttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
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
    public ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
    public BarDataSet dataset;
    public ArrayList<String> labels=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEntriesData();
        initLableData();
        dataset= new BarDataSet(entries,"# of Calls");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        BarChart chart=new BarChart(this);
        BarData data=new BarData(labels,dataset);
        LimitLine line=new LimitLine(10f);
        chart.setData(data);
//        chart.animateXY(5000,5000);
//        chart.animateX(5000);
        chart.animateY(3000);

        setContentView(chart);
        chart.setDescription("hello MPandroidChart");
        //显示右上角的3个点
        makeActionOverflowMenuShown();
    }
    public void initEntriesData(){
        entries.add(new BarEntry(4f,0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f,3));
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_material:
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_circles:
                Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_water_drop:
                Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_ring:
                Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                return true;
        }

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
}

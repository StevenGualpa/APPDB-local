package com.uteq.app_hidro_01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.github.farshidroohi.ChartEntity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class MainActivity_HistoricoTemperatura extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseReference mDatabaseReference;
    private HashMap<String, ArrayList<Object>> dataLists;
    private LineChart lineChart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_historico_temperatura);
        /*
        io.github.farshidroohi.LineChart lineChart2 = findViewById(R.id.lineChart);

        ChartEntity firstChartEntity = new ChartEntity(Color.RED, new float[]{113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f});
        ChartEntity secondChartEntity = new ChartEntity(Color.YELLOW,new float[]{113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f});

        ArrayList<ChartEntity> list = new ArrayList<>();
        list.add(firstChartEntity);
        list.add(secondChartEntity);

        lineChart2.setList(list);
        lineChart2.setLegend(Arrays.asList(new String[]{"05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31"}));
        lineChart2.refreshDrawableState();


         */
        lineChart = findViewById(R.id.line_chart);

        // Configura el gráfico de líneas
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setPinchZoom(true);

        // Añade datos de ejemplo
        List<Entry> exampleData = new ArrayList<>();
        exampleData.add(new Entry(10.05f, 10));
       exampleData.add(new Entry(10.02f, 25));
       exampleData.add(new Entry(10.10f, 14));
      exampleData.add(new Entry(10.12f, 25));
      //  exampleData.add(new Entry(4, 15));

        // Crea un conjunto de datos y configura su apariencia
        LineDataSet dataSet = new LineDataSet(exampleData, "Datos de ejemplo");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawCircles(true);
        dataSet.setDrawValues(false);

        // Añade el conjunto de datos al gráfico
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresca el gráfic

    }


}
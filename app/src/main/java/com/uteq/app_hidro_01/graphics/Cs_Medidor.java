package com.uteq.app_hidro_01.graphics;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cs_Medidor
{


    //Genera el Grafico de la Temperatura
    public ArcGauge GeneratorGraphicsTemperature(ArcGauge retorno)
    {
        com.ekn.gruzer.gaugelibrary.Range Rango_1;
        Rango_1=new com.ekn.gruzer.gaugelibrary.Range();

        Rango_1.setFrom(0);Rango_1.setTo(200);
        Rango_1.setColor(Color.RED);

        retorno.setMinValue(0);
        retorno.setMaxValue(200);
      //  retorno.setValue(30.5);

        retorno.addRange(Rango_1);
        return retorno;
    }
    public ArcGauge GeneratorGraphicsLight(ArcGauge retorno)
    {
        com.ekn.gruzer.gaugelibrary.Range Rango_1;
        Rango_1=new com.ekn.gruzer.gaugelibrary.Range();

        Rango_1.setFrom(0);Rango_1.setTo(2000);
        Rango_1.setColor(Color.GREEN);

        retorno.setMinValue(0);
        retorno.setMaxValue(2000);
        //  retorno.setValue(30.5);

        retorno.addRange(Rango_1);
        return retorno;
    }

    public ArcGauge GeneratorGraphicsWater(ArcGauge retorno)
    {
        com.ekn.gruzer.gaugelibrary.Range Rango_1;
        Rango_1=new com.ekn.gruzer.gaugelibrary.Range();

        Rango_1.setFrom(0);Rango_1.setTo(2000);
        Rango_1.setColor(Color.BLUE);

        retorno.setMinValue(0);
        retorno.setMaxValue(2000);
        //  retorno.setValue(30.5);

        retorno.addRange(Rango_1);
        return retorno;
    }

    public ArcGauge GeneratorGraphicsPH(ArcGauge retorno)
    {
        com.ekn.gruzer.gaugelibrary.Range Rango_1;
        Rango_1=new com.ekn.gruzer.gaugelibrary.Range();

        Rango_1.setFrom(0);Rango_1.setTo(14);
        Rango_1.setColor(Color.YELLOW);

        retorno.setMinValue(0);
        retorno.setMaxValue(14);
        //  retorno.setValue(30.5);

        retorno.addRange(Rango_1);
        return retorno;
    }


    public LineChart GeneratorGraphicsHistory(LineChart retorno, Map<String, Float> data, String labr){
        List<Entry> entries = new ArrayList<>();
        int index = 0;
        for (Float value : data.values()) {
            entries.add(new Entry(index++, value));
        }

        LineDataSet dataSet = new LineDataSet(entries, labr);
        dataSet.setColor(Color.GREEN);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//version 02
        // Añadir relleno degradado
        dataSet.setDrawFilled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Drawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                    new int[]{Color.RED, Color.TRANSPARENT});
            dataSet.setFillDrawable(gradientDrawable);
        } else {
            dataSet.setFillColor(Color.RED);
        }

        LineData lineData = new LineData(dataSet);
        retorno.setData(lineData);
        retorno.invalidate(); // Refrescar el gráfico

        return retorno;
    }


    public HalfGauge GeneratorGraphicsLlenado(HalfGauge retorno)
    {
        com.ekn.gruzer.gaugelibrary.Range Rango_1;
        Rango_1=new com.ekn.gruzer.gaugelibrary.Range();

        Rango_1.setFrom(0);Rango_1.setTo(1);

        Rango_1.setColor(Color.BLUE);
        retorno.setMinValue(0);
        retorno.setMaxValue(1);
        retorno.setValue(0);

        retorno.addRange(Rango_1);
        return retorno;
    }
}

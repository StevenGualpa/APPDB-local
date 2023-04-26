package com.uteq.app_hidro_01.graphics;

import android.graphics.Color;

import com.ekn.gruzer.gaugelibrary.ArcGauge;

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

        Rango_1.setFrom(0);Rango_1.setTo(10000);
        Rango_1.setColor(Color.GREEN);

        retorno.setMinValue(0);
        retorno.setMaxValue(10000);
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
}

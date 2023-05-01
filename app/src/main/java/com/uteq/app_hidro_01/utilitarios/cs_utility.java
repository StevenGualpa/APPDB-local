package com.uteq.app_hidro_01.utilitarios;

import android.content.Context;
import android.content.Intent;

import com.uteq.app_hidro_01.MainActivity_InsertPerson;
import com.uteq.app_hidro_01.MainActivity_Loggin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class cs_utility {

    public long getFechaMilisegundos(){
        Calendar calendar =Calendar.getInstance();
        long tiempounix = calendar.getTimeInMillis();

        return tiempounix;
    }
    public String getFechaNormal(long fechamilisegundos){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        String fecha = sdf.format(fechamilisegundos);
        return fecha;
    }

}

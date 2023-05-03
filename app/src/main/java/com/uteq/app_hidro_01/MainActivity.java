package com.uteq.app_hidro_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uteq.app_hidro_01.graphics.Cs_Medidor;
import com.uteq.app_hidro_01.models.HydroGrow;
import com.uteq.app_hidro_01.utilitarios.MyFirebaseMessagingService;



import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    ArcGauge Medidor_temperatura;
    ArcGauge Medidor_luz;
    ArcGauge Medidor_Aire;
    ArcGauge Medidor_PH;
    HalfGauge Medido_Lllenado;
    SwitchCompat BtnFlujo,BtnLLenado,BtnLuces,BtnNut01;
    SwitchCompat BtnNut02;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private boolean estadoluz;
    private boolean estadoflujo;
    private boolean estadollenado;

    private boolean estadon01;

    private boolean estadon02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Medidor_temperatura =findViewById(R.id.Medidor_temperatura);
        Medidor_luz=findViewById(R.id.Medidor_luz);
        Medidor_Aire=findViewById(R.id.Medidor_Aire);
        Medidor_PH=findViewById(R.id.Medidor_PH);
        Medido_Lllenado=findViewById(R.id.Medidor_LLenado);

        Cs_Medidor v=new Cs_Medidor();
        v.GeneratorGraphicsTemperature(Medidor_temperatura);
        Medidor_temperatura.setActivated(true);
        v.GeneratorGraphicsLight(Medidor_luz);
        v.GeneratorGraphicsWater(Medidor_Aire);
        v.GeneratorGraphicsPH(Medidor_PH);
        v.GeneratorGraphicsLlenado(Medido_Lllenado);

        //Inicializamos Botones
        BtnFlujo=findViewById(R.id.Btn_Flujo);
        BtnLLenado=findViewById(R.id.Btn_Llenado);
        BtnLuces=findViewById(R.id.Btn_Luces);
        BtnNut01=findViewById(R.id.Btn_Nutriente01);
        BtnNut02=findViewById(R.id.Btn_Nutriente02);

        //Eventos Click
        BtnFlujo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadoflujo)
                   {
                       showNotification("Título de la notificación", "Contenido de la notificación");
                       databaseReference.child("RealTime").child("FLUJO DE AGUA ").setValue(false);}
                else
                    databaseReference.child("RealTime").child("FLUJO DE AGUA ").setValue(true);
            }
        });


        BtnLLenado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadollenado)
                    databaseReference.child("RealTime").child("LLENADO DE AGUA ").setValue(false);
                else
                    databaseReference.child("RealTime").child("LLENADO DE AGUA ").setValue(true);
            }
        });

        BtnLuces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(estadoluz)
                    databaseReference.child("RealTime").child("LUCES LED ").setValue(false);
                else
                    databaseReference.child("RealTime").child("LUCES LED ").setValue(true);

            }
        });

        BtnNut01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadon01)
                    databaseReference.child("RealTime").child("NUTRIENTE NITROGENO FOSFORO ").setValue(false);
                else
                    databaseReference.child("RealTime").child("NUTRIENTE NITROGENO FOSFORO ").setValue(true);
            }
        });


        BtnNut02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadon02)
                    databaseReference.child("RealTime").child("NUTRIENTE POTASIO ").setValue(false);
                else
                    databaseReference.child("RealTime").child("NUTRIENTE POTASIO ").setValue(true);
            }
        });
        Medidor_temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, MainActivity_HistoricoTemperatura.class);
                startActivity(intent);
            }
        });

        Medidor_luz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, MainActivity_HistoricoLuz.class);
                startActivity(intent);
            }
        });

        Medidor_Aire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, MainActivity_HistoricoCalidadAire.class);
                startActivity(intent);
            }
        });

        Medidor_PH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, MainActivity_HistoricoPh.class);
                startActivity(intent);
            }
        });
        inicializarFirebase();
        Extraer();



    }

    private void showNotification(String title, String content) {
        String channelId = "my_channel_id";
        String channelName = "My Channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Descripción del canal");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.ic_delete) // Reemplaza con el ícono de tu aplicación
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = notificationBuilder.build();
        notificationManager.notify(1, notification); // El primer parámetro es un ID único para la notificación
    }

    private void  inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void Extraer(){
        databaseReference.child("RealTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    //Asignamos Datos a Graficos
                    HydroGrow H=new HydroGrow();
                    H.setTemperatura(Double.parseDouble(dataSnapshot.child("TEMPERATURA ").getValue().toString()));
                    H.setLUMINOSIDAD(Double.parseDouble(dataSnapshot.child("LUMINOSIDAD ").getValue().toString()));
                    H.setCalidad_del_Aire(dataSnapshot.child("CALIDAD DEL AIRE ").getValue().toString());
                    H.setNIVEL_DE_PH(Integer.parseInt(dataSnapshot.child("NIVEL DE PH ").getValue().toString()));
                    H.setAguaLlena(Integer.parseInt(dataSnapshot.child("NIVEL DE AGUA ABAJO ").getValue().toString()));


                    Medidor_temperatura.setValue(H.getTemperatura());
                    Medidor_luz.setValue(H.getLUMINOSIDAD());
                    Medidor_Aire.setValue(Double.parseDouble(H.getCalidad_del_Aire()));
                    Medidor_PH.setValue(H.getNIVEL_DE_PH());
                    Medido_Lllenado.setValue(H.CambioValor());

                    if(Medidor_luz.getValue()>500)
                        showNotification("Alerta", "Luz alta");

                    //Asignamos Estados a los Botones
                    H.setFLUJO_DE_AGUA((Boolean) dataSnapshot.child("FLUJO DE AGUA ").getValue());
                    H.setLlenado_de_Agua((Boolean) dataSnapshot.child("LLENADO DE AGUA ").getValue());
                    H.setLUCES_LED((Boolean) dataSnapshot.child("LUCES LED ").getValue());
                    H.setNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO((Boolean) dataSnapshot.child("NUTRIENTE NITROGENO FOSFORO ").getValue());
                    H.setNUTRIENTE_DE_POTASIO((Boolean) dataSnapshot.child("NUTRIENTE POTASIO ").getValue());

                    //Valores Perrones Booleanos
                    estadoflujo=H.isFLUJO_DE_AGUA();
                    estadoluz=H.isLUCES_LED();
                    estadollenado=H.isLlenado_de_Agua();
                    estadon01=H.isNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO();
                    estadon02=H.isNUTRIENTE_DE_POTASIO();


                    //Cambiamos de Estados
                    BtnFlujo.setChecked(estadoflujo);
                    BtnLuces.setChecked(estadoluz);
                    BtnLLenado.setChecked(estadollenado);
                    BtnNut01.setChecked(estadon01);
                    BtnNut02.setChecked(estadon02);

                    if(estadoflujo) showNotification("Alerta","El Flujo del Agua esta encedidas");
                    if(estadoluz) showNotification("Alerta","Las Luces estan encedidas");
                    if(estadollenado) showNotification("Alerta","El Llenado esta encedido");
                    if(estadon01) showNotification("Alerta","El suministro de nutrientes esta encedido");
                    if(estadon02) showNotification("Alerta","El suministro de nutrientes esta encedido");



                    BtnFlujo.setText(H.getEstadoFLUJO_DE_AGUA());
                    BtnLLenado.setText(H.getLlenado_de_Agua());
                    BtnLuces.setText(H.getEstadoLUCES_LED());
                    BtnNut01.setText(H.getEstadoNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO());
                    BtnNut02.setText(H.getEstadoNUTRIENTE_DE_POTASIO());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
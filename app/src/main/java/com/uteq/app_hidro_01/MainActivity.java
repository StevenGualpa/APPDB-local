package com.uteq.app_hidro_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uteq.app_hidro_01.graphics.Cs_Medidor;
import com.uteq.app_hidro_01.models.HydroGrow;
import com.uteq.app_hidro_01.models.acostumbrado;

public class MainActivity extends AppCompatActivity {

    ArcGauge Medidor_temperatura;
    ArcGauge Medidor_luz;
    ArcGauge Medidor_Aire;
    ArcGauge Medidor_PH;
    Button BtnFlujo,BtnLLenado,BtnLuces,BtnNut01,BtnNut02;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private boolean estadoluz;
    private boolean estadoflujo;
    private boolean estadollenado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Medidor_temperatura =findViewById(R.id.Medidor_temperatura);
        Medidor_luz=findViewById(R.id.Medidor_luz);
        Medidor_Aire=findViewById(R.id.Medidor_Aire);
        Medidor_PH=findViewById(R.id.Medidor_PH);


        Cs_Medidor v=new Cs_Medidor();
        v.GeneratorGraphicsTemperature(Medidor_temperatura);
        Medidor_temperatura.setActivated(true);
        v.GeneratorGraphicsLight(Medidor_luz);
        v.GeneratorGraphicsWater(Medidor_Aire);
        v.GeneratorGraphicsPH(Medidor_PH);

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
                    databaseReference.child("RealTime").child("FLUJO DE AGUA ").setValue(false);
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
                Intent intent = new Intent( MainActivity.this, MainActivity_HistoricoTemperatura.class);
                startActivity(intent);
            }
        });


        BtnNut02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,MainActivityLoggin.class);
                startActivity(intent);
            }
        });
        inicializarFirebase();
        Extraer();

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

                    Medidor_temperatura.setValue(H.getTemperatura());
                    Medidor_luz.setValue(H.getLUMINOSIDAD());
                    Medidor_Aire.setValue(Double.parseDouble(H.getCalidad_del_Aire()));
                    Medidor_PH.setValue(H.getNIVEL_DE_PH());

                    //Asignamos Estados a los Botones
                    H.setFLUJO_DE_AGUA((Boolean) dataSnapshot.child("FLUJO DE AGUA ").getValue());
                    H.setLlenado_de_Agua((Boolean) dataSnapshot.child("LLENADO DE AGUA ").getValue());
                    H.setLUCES_LED((Boolean) dataSnapshot.child("LUCES LED ").getValue());

                    estadoflujo=H.isFLUJO_DE_AGUA();
                    estadoluz=H.isLUCES_LED();
                    estadollenado=H.isLlenado_de_Agua();
                   // H.setNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO((Boolean) dataSnapshot.child("NUTRIENTE DE NITRÒGENO Y FOSFORO").getValue());
                  //  H.setNUTRIENTE_DE_POTASIO((Boolean) dataSnapshot.child("NUTRIENTE DE POTASIO").getValue());

               BtnFlujo.setText(H.getEstadoFLUJO_DE_AGUA());
               BtnLLenado.setText(H.getLlenado_de_Agua());
               BtnLuces.setText(H.getEstadoLUCES_LED());
                  //  BtnNut01.setText(H.getEstadoNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO());
                   // BtnNut02.setText(H.getEstadoNUTRIENTE_DE_POTASIO());
                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void Update(String Phat,String Hijo, String Value){
        databaseReference.child(Phat).child(Hijo).setValue(Value);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
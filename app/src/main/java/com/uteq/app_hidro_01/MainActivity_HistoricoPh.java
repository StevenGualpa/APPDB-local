package com.uteq.app_hidro_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uteq.app_hidro_01.graphics.Cs_Medidor;

import java.util.HashMap;
import java.util.Map;

public class MainActivity_HistoricoPh extends AppCompatActivity {

    private LineChart lineChart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Cs_Medidor cs_medidor;
    ImageView retorno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_historico_ph);

        lineChart = findViewById(R.id.HistoricoPH_Linechart);
        retorno=findViewById(R.id.HistoricoPH_ImageView_Regresar);
        retorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity_HistoricoPh.this, MainActivity.class);
                startActivity(intent);
            }
        });
        inicializarFirebase();
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("HydroGrow").child("NIVEL DE PH ");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Float> data = new HashMap<>();
                int n=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(n<20){
                        data.put(snapshot.getKey(), snapshot.getValue(Float.class));
                        n++;
                    }
                    else break;
                }
                cs_medidor=new Cs_Medidor();
                lineChart=cs_medidor.GeneratorGraphicsHistory(lineChart,data,"Nivel de Ph");
                //setupLineChart(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", "Error al cargar datos de Firebase", databaseError.toException());
            }
        });
    }

    private void  inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
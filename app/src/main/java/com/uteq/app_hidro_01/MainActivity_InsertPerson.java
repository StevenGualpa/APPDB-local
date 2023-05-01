package com.uteq.app_hidro_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uteq.app_hidro_01.models.Persona;
import com.uteq.app_hidro_01.utilitarios.cs_Validation;
import com.uteq.app_hidro_01.utilitarios.cs_utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class MainActivity_InsertPerson extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    cs_Validation csValidator;
    cs_utility csUtility;
    EditText inputNombre, inputTelefono, inputuser, inputclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_insert_person);


        //Inicializamos
        ImageView btnInsertar = findViewById(R.id.Insertar_ImageView_Insertar);
        inputNombre = findViewById(R.id.Insert_EditText_inputNombre);
        inputTelefono = findViewById(R.id.Insert_EditText_inputTelefono);
        inputuser = findViewById(R.id.Insert_EditText_inputUser);
        inputclave = findViewById(R.id.Insert_EditText_inputPassword);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              insertar();
            }
        });

        inicializarFirebase();
    }

    private void insertar(){
        String nombres = inputNombre.getText().toString();
        String telefono = inputTelefono.getText().toString();
        String user=inputuser.getText().toString();
        String password=inputclave.getText().toString();
        csValidator=new cs_Validation();
        csUtility=new cs_utility();
        if(nombres.isEmpty() || nombres.length()<3){
            csValidator.showError(inputNombre, "Nombre invalido (Min. 3 letras)");
        }else if(telefono.isEmpty() || telefono.length() <9 ){
            csValidator.showError(inputTelefono, "Telefono invalido (Min. 9 números)");
        }else if(user.isEmpty() || user.length() <2 ){
            csValidator.showError(inputuser, "Usuario invalido (Min. 2 letras)");
        }else if(password.isEmpty() || password.length() <4 ){
            csValidator.showError(inputclave, "Clave invalido (Min. 4 letras)");
        }else{
            Persona p = new Persona();
            p.setIdpersona(UUID.randomUUID().toString());
            p.setNombres(nombres);
            p.setTelefono(telefono);
            p.setUsuario(user);
            p.setPassword(password);
            p.setFecharegistro(csUtility.getFechaNormal(csUtility.getFechaMilisegundos()));
            p.setTimestap(csUtility.getFechaMilisegundos()*-1);
            databaseReference.child("Personas").child(p.getIdpersona()).setValue(p);
            Toast.makeText(MainActivity_InsertPerson.this, "Registrado Correctamente", Toast.LENGTH_LONG).show();
        }
    }

    private void  inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
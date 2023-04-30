package com.uteq.app_hidro_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uteq.app_hidro_01.models.Persona;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class MainActivity_InsertPerson extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    EditText inputNombre, inputTelefono, inputuser, inputclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_insert_person);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                MainActivity_InsertPerson.this
        );
        Button btnInsertar = findViewById(R.id.btnInsertar);
        final EditText mInputNombres = findViewById(R.id.inputNombre);
        final EditText mInputTelefono = findViewById(R.id.inputTelefono);
        final EditText mInputuser = findViewById(R.id.inputUser);
        final EditText mInputpaswword = findViewById(R.id.inputPassword);
        final  AlertDialog dialog = mBuilder.create();
        dialog.show();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombres = mInputNombres.getText().toString();
                String telefono = mInputTelefono.getText().toString();
                String user=mInputuser.getText().toString();
                String password=mInputpaswword.getText().toString();
                if(nombres.isEmpty() || nombres.length()<3){
                    showError(mInputNombres, "Nombre invalido (Min. 3 letras)");
                }else if(telefono.isEmpty() || telefono.length() <9 ){
                    showError(mInputTelefono, "Telefono invalido (Min. 9 números)");
                }else{
                    Persona p = new Persona();
                    p.setIdpersona(UUID.randomUUID().toString());
                    p.setNombres(nombres);
                    p.setTelefono(telefono);
                    p.setUsuario(user);
                    p.setPassword(password);
                    p.setFecharegistro(getFechaNormal(getFechaMilisegundos()));
                    p.setTimestap(getFechaMilisegundos() * -1);
                    databaseReference.child("Personas").child(p.getIdpersona()).setValue(p);
                    Toast.makeText(MainActivity_InsertPerson.this, "Registrado Correctamente", Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }
            }
        });

        inicializarFirebase();
    }

    private void  inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }



    public void insertar(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                MainActivity_InsertPerson.this
        );



    }

    public void showError(EditText input, String s){
        input.requestFocus();
        input.setError(s);
    }

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
    public boolean validarInputs(){
        String nombre = inputNombre.getText().toString();
        String telefono = inputTelefono.getText().toString();
        if(nombre.isEmpty() || nombre.length() < 3){
            showError(inputNombre, "Nombre invalido. (Min 3 letras)");
            return true;
        }else if(telefono.isEmpty() || telefono.length() < 9){
            showError(inputTelefono, "Telefono invalido (Min 9 números)");
            return true;
        }else{
            return false;
        }
    }
}
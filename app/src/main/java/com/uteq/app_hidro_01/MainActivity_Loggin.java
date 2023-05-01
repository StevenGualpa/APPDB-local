package com.uteq.app_hidro_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uteq.app_hidro_01.models.Persona;

public class MainActivity_Loggin extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView img_iniciar;
    TextView txt_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loggin2);
        img_iniciar =findViewById(R.id.Loggin_imageView_IniciarSesion);
        img_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user=findViewById(R.id.Loggin_EditText_inputuser);
                EditText password=findViewById(R.id.Loggin_EditText_inputpassword);
                String user_= user.getText().toString();
                String pass=password.getText().toString();
                signIn(user_,pass);
            }
        });
        txt_registrar =findViewById(R.id.textView_registrar);
        txt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity_Loggin.this, MainActivity_InsertPerson.class);
                startActivity(intent);
            }
        });
        inicializarFirebase();
    }
    private void  inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    public void signIn(final String username, final String password) {
        databaseReference.child("Personas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean userFound = false;
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Persona person = userSnapshot.getValue(Persona.class);
                    if (person != null && person.getUsuario().equals(username) && person.getPassword().equals(password)) {
                        userFound = true;

                        Intent intent = new Intent( MainActivity_Loggin.this, MainActivity.class);
                        startActivity(intent);
                        // Inicio de sesión exitoso, realiza las acciones necesarias aquí
                        break;
                    }
                }

                if (!userFound) {
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Error al leer los datos, maneja el error aquí
            }
        });
    }
}
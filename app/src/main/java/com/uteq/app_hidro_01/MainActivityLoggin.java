package com.uteq.app_hidro_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uteq.app_hidro_01.models.Persona;

public class MainActivityLoggin extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loggin);
    btn_iniciar =findViewById(R.id.btnIngresar);
    btn_iniciar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText user=findViewById(R.id.username);
            EditText password=findViewById(R.id.password);
            String user_= user.getText().toString();
            String pass=password.getText().toString();
            signIn(user_,pass);
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

                        Intent intent = new Intent( MainActivityLoggin.this, MainActivity.class);
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
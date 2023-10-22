package com.example.pizzatec.administrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzatec.R;
import com.example.pizzatec.administrador.mantenimiento.pizza.ListaPizzaActivity;
import com.example.pizzatec.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class AdminPrincipalActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    Button btnCerrarSesion;
    Button btnUsuario, btnPizza, btnPastas, btnComplementos;
    TextView txtNom, txtApe, txtCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_principal);

        btnCerrarSesion = findViewById(R.id.btnCerrarSesionAd);
        btnUsuario = findViewById(R.id.btnUsuarioMan);
        btnPizza = findViewById(R.id.btnPizzaMan);
        btnPastas = findViewById(R.id.btnPastasMan);
        btnComplementos = findViewById(R.id.btnComplementosMan);
        txtNom = findViewById(R.id.nomUserAd);
        txtApe = findViewById(R.id.apeUser);
        txtCorreo = findViewById(R.id.correoUserAd);

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminPrincipalActivity.this, "MENU USUARIO", Toast.LENGTH_SHORT).show();
            }
        });

        btnPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminPrincipalActivity.this, "MENU PIZZA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminPrincipalActivity.this, ListaPizzaActivity.class);
                startActivity(intent);
            }
        });

        btnPastas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminPrincipalActivity.this, "MENU PASTAS", Toast.LENGTH_SHORT).show();
            }
        });

        btnComplementos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminPrincipalActivity.this, "MENU COMPLEMENTOS", Toast.LENGTH_SHORT).show();
            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(AdminPrincipalActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(AdminPrincipalActivity.this, "Saliendo", Toast.LENGTH_SHORT).show();
            }
        });
        getUserInfo();

    }

    public void getUserInfo(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuario").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){
                    String name =datasnapshot.child("Nombre").getValue().toString();
                    String lastname = datasnapshot.child("Apellido").getValue().toString();
                    String emailaddress = datasnapshot.child("Email").getValue().toString();

                    txtNom.setText(name);
                    txtApe.setText(lastname);
                    txtCorreo.setText(emailaddress);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
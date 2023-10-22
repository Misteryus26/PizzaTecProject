package com.example.pizzatec.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzatec.R;
import com.example.pizzatec.documentos.DerechosActivity;
import com.example.pizzatec.documentos.PrivacidadActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class RegistrarActivity extends AppCompatActivity {

    Button btnRegistrar;
    EditText etNombre, etApellido, etEmail, etContrasena;
    TextView tvPrivacidad, tvDerechos;
    LinearLayout tvIngresar;

    String nombre, apellido, email, password;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        etNombre = findViewById(R.id.etNombre_Registrar);
        etApellido = findViewById(R.id.etApellidos_Registrar);
        etEmail = findViewById(R.id.etEmail_Registrar);
        etContrasena = findViewById(R.id.etContraseña_Registrar);
        tvPrivacidad = findViewById(R.id.tvPrivacidadReg);
        tvDerechos = findViewById(R.id.tvDerechosrEG);
        tvIngresar = findViewById(R.id.tvYatengoCuenta);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = etNombre.getText().toString();
                apellido = etApellido.getText().toString();
                email = etEmail.getText().toString();
                password = etContrasena.getText().toString();

                if(!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    if (password.length() >= 6){
                        registroUsuario();
                    }
                    else{
                        Toast.makeText(RegistrarActivity.this, "Debe tener 6 caracteres mínimo", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistrarActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                finish();
            }
        });

        tvPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarActivity.this, PrivacidadActivity.class));
                finish();
            }
        });

        tvDerechos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarActivity.this, DerechosActivity.class));
                finish();
            }
        });
    }

    private void registroUsuario(){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("Nombre", nombre);
                    map.put("Apellido", apellido);
                    map.put("Email", email);
                    map.put("Contraseña", password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                                finish();
                                Toast.makeText(RegistrarActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegistrarActivity.this, "Error al autenticar datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistrarActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
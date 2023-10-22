package com.example.pizzatec.administrador.mantenimiento.pasta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.pizzatec.R;
import com.example.pizzatec.administrador.mantenimiento.pizza.ListaPizzaActivity;
import com.example.pizzatec.administrador.mantenimiento.pizza.Pizza;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrarPastasActivity extends AppCompatActivity {

    @BindView(R.id.nombrePastaEditText)
    EditText mNombreEditText;

    @BindView(R.id.precioPastaEditText)
    EditText mPrecioEditText;

    @BindView(R.id.urlPizzaEditText)
    EditText mUrlEditText;

    @BindView(R.id.descPastaEditText)
    EditText mDescripcionEditText;

    @BindView(R.id.pastaButton)
    Button mPizzarButton;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pastas);

        ButterKnife.bind(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Pasta");

        mPizzarButton.setOnClickListener(v -> {
            String nombre = mNombreEditText.getText().toString();
            String precio = mPrecioEditText.getText().toString();
            String descripcion = mDescripcionEditText.getText().toString();
            int url = Integer.parseInt(mUrlEditText.getText().toString());

            Pasta mPasta = new Pasta(nombre, precio, descripcion, url);
            String id = mDatabaseReference.push().getKey();
            if (id != null) { mDatabaseReference.child(id).setValue(mPasta); }

            Intent intent=new Intent(this, ListaPastasActivity.class);
            startActivity(intent);
        });

    }
}
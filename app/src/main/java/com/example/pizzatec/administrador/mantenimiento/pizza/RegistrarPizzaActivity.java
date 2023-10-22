package com.example.pizzatec.administrador.mantenimiento.pizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.pizzatec.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrarPizzaActivity extends AppCompatActivity {

    @BindView(R.id.nombrePizzaEditText)
    EditText mNombreEditText;

    @BindView(R.id.precioPizzaEditText)
    EditText mPrecioEditText;

    @BindView(R.id.urlPizzaEditText)
    EditText mUrlEditText;

    @BindView(R.id.descPizzaEditText)
    EditText mDescripcionEditText;

    @BindView(R.id.pizzaButton)
    Button mPizzarButton;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pizza);

        ButterKnife.bind(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Pizza");

        mPizzarButton.setOnClickListener(v -> {
            String name = mNombreEditText.getText().toString();
            String anime = mPrecioEditText.getText().toString();
            String description = mDescripcionEditText.getText().toString();
            int url = Integer.parseInt(mUrlEditText.getText().toString());

            Pizza mPizza = new Pizza(name, anime, description, url);
            String id = mDatabaseReference.push().getKey();
            if (id != null) { mDatabaseReference.child(id).setValue(mPizza); }

            Intent intent=new Intent(this, ListaPizzaActivity.class);
            startActivity(intent);
        });

    }
}
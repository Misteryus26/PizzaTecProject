package com.example.pizzatec.administrador.mantenimiento.pizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzatec.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActualizarPizzaActivity extends AppCompatActivity {
    @BindView(R.id.nombrePizzaEditText)
    EditText mNombreEditText;

    @BindView(R.id.precioPizzaEditText)
    EditText mPrecioEditText;

    @BindView(R.id.urlPizzaEditText)
    EditText mUrlEditText;

    @BindView(R.id.descPizzaEditText)
    EditText mDescripcionEditText;

    @BindView(R.id.pizzaButton)
    Button mPizzaButton;

    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_pizza);

        ButterKnife.bind(this);
        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("key");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Pizza").child(mKey);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Pizza charater = dataSnapshot.getValue(Pizza.class);

                if (charater.getNombre() != null) {
                    mNombreEditText.setText(charater.getNombre());
                }

                if (charater.getPrecio()!= null) {
                    mPrecioEditText.setText(charater.getPrecio());
                }

                if (charater.getDescripcion() != null) {
                    mDescripcionEditText.setText(charater.getDescripcion());
                }

                mUrlEditText.setText(String.valueOf(charater.getUrl()));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ActualizarPizzaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mPizzaButton.setOnClickListener(v -> {
            mDatabaseReference.child("nombre").setValue(mNombreEditText.getText().toString());
            mDatabaseReference.child("precio").setValue(mPrecioEditText.getText().toString());
            mDatabaseReference.child("descripcion").setValue(mDescripcionEditText.getText().toString());
            mDatabaseReference.child("url").setValue(Integer.parseInt(mUrlEditText.getText().toString()));
            finish();
        });

    }
}
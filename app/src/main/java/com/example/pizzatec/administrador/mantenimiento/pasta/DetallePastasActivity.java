package com.example.pizzatec.administrador.mantenimiento.pasta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzatec.R;
import com.example.pizzatec.administrador.mantenimiento.pizza.DetallePizzaActivity;
import com.example.pizzatec.administrador.mantenimiento.pizza.Pizza;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetallePastasActivity extends AppCompatActivity {
    @BindView(R.id.pizzaImageView)
    ImageView mPizzaImageView;

    @BindView(R.id.nombrePizzaTextView)
    TextView mNombreTextView;

    @BindView(R.id.descPizzaTextView)
    TextView mDescripcionTextView;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pastas);

        ButterKnife.bind(this);
        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("key");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Pasta").child(mKey);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                com.example.pizzatec.administrador.mantenimiento.pizza.Pizza charater = dataSnapshot.getValue(Pizza.class);

                if (charater.getNombre() != null) {
                    mNombreTextView.setText(charater.getNombre());
                }

                if (charater.getDescripcion() != null) {
                    mDescripcionTextView.setText(charater.getDescripcion());
                }

                int[] charactersImages= {R.drawable.goku,R.drawable.luffy,R.drawable.naruto,R.drawable.usagi_tsukino};
                if (charater.getUrl() < 4) {
                    mPizzaImageView.setImageResource(charactersImages[charater.getUrl()]);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(DetallePastasActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
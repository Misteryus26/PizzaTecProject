package com.example.pizzatec.administrador.mantenimiento.pasta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pizzatec.R;
import com.example.pizzatec.administrador.mantenimiento.pizza.ListaPizzaActivity;
import com.example.pizzatec.administrador.mantenimiento.pizza.Pizza;
import com.example.pizzatec.administrador.mantenimiento.pizza.PizzaAdapter;
import com.example.pizzatec.administrador.mantenimiento.pizza.RegistrarPizzaActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaPastasActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.characterRecyclerView)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.newFloatingActionButton)
    FloatingActionButton mNewFloatingActionButton;

    PastaAdapter mPastaAdapter;

    LinearLayoutManager mLayoutManager;

    private ArrayList<Pasta> mListPasta;

    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pastas);

        ButterKnife.bind(this);

        mListPasta = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Pasta");

        mNewFloatingActionButton.setOnClickListener(v -> {
            Intent intent=new Intent(ListaPastasActivity.this, RegistrarPastasActivity.class);
            startActivity(intent);
        });

        Recycler();
    }

    public void Recycler() {

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPastaAdapter = new PastaAdapter(mListPasta);
        mRecyclerView.setAdapter(mPastaAdapter);
        Content();
        deleteSwipe();
    }

    private void Content() {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mListPasta.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Pasta pasta = postSnapshot.getValue(Pasta.class);

                    if (pasta != null) {
                        pasta.setKey(postSnapshot.getKey());
                    }

                    mListPasta.add(pasta);

                }
                mPastaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ListaPastasActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteSwipe() {

        ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mDatabaseReference.child(mListPasta.get(viewHolder.getAdapterPosition()).getKey()).setValue(null);
                mPastaAdapter.deleteItem(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
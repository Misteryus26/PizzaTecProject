package com.example.pizzatec.menu.comprar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pizzatec.R;
import com.example.pizzatec.administrador.mantenimiento.pasta.ListaPastasActivity;
import com.example.pizzatec.administrador.mantenimiento.pizza.ListaPizzaActivity;
import com.example.pizzatec.databinding.FragmentComprarBinding;
import com.example.pizzatec.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ComprarFragment extends Fragment {

    private ComprarViewModel comprarViewModel;
    private FragmentComprarBinding binding;

    LinearLayout btnPizza, btnPasta, btnComple;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        comprarViewModel =
                new ViewModelProvider(this).get(ComprarViewModel.class);

        binding = FragmentComprarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view  = inflater.inflate(R.layout.fragment_comprar, container, false);

        final TextView textView = binding.textComprar;
        comprarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.pizzatec;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pizzatec.databinding.ActivityPrincipalMenuBinding;

public class PrincipalMenuActivity extends AppCompatActivity {

    private ActivityPrincipalMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_inicio, R.id.navigation_perfil, R.id.navigation_comprar, R.id.navigation_miscompras, R.id.navigation_notificaciones)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_principal_menu);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }



}
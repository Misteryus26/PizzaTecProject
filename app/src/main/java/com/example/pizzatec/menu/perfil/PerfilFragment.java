package com.example.pizzatec.menu.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pizzatec.R;
import com.example.pizzatec.databinding.FragmentPerfilBinding;
import com.example.pizzatec.documentos.DerechosActivity;
import com.example.pizzatec.documentos.PrivacidadActivity;
import com.example.pizzatec.login.LoginActivity;
import com.example.pizzatec.menu.miscompras.MisComprasFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilFragment extends Fragment {

    public static final String TAG = "PerfilFragment";

    private PerfilViewModel perfilViewModel;
    private FragmentPerfilBinding binding;
    TextView nombre, apellido, email;
    LinearLayout btnDatos, btnMisDirecciones, btnMisTarjetas, btnCompras, btnCupones, btnPrivacidad, btnDesarrollador;
    Button btnCerrar;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view  = inflater.inflate(R.layout.fragment_perfil, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        nombre = (TextView) view.findViewById(R.id.nomUser);
        apellido = (TextView) view.findViewById(R.id.apeUser);
        email = (TextView) view.findViewById(R.id.correoUser);
        btnDatos = (LinearLayout) view.findViewById(R.id.btnMisDatos);
        btnMisDirecciones = (LinearLayout) view.findViewById(R.id.btnMisDirecciones);
        btnMisTarjetas = (LinearLayout) view.findViewById(R.id.btnMisTarjetas);
        btnCompras = (LinearLayout) view.findViewById(R.id.btnMisCompras);
        btnCupones = (LinearLayout) view.findViewById(R.id.btnMisCupones);
        btnPrivacidad = (LinearLayout) view.findViewById(R.id.btnPoliticasPrivacidad);
        btnDesarrollador = (LinearLayout) view.findViewById(R.id.btnDerechosDesarrollador);
        btnCerrar = (Button) view.findViewById(R.id.btnCerrarSesion);

        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Falta completar esta opción", Toast.LENGTH_SHORT).show();
            }
        });

        btnMisDirecciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Falta completar esta opción", Toast.LENGTH_SHORT).show();
            }
        });

        btnMisTarjetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Falta completar esta opción", Toast.LENGTH_SHORT).show();
            }
        });

        btnCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Falta completar esta opción", Toast.LENGTH_SHORT).show();
            }
        });

        btnCupones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Falta completar esta opción", Toast.LENGTH_SHORT).show();
            }
        });

        btnPrivacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PrivacidadActivity.class));
                getActivity().finish();
            }
        });

        btnDesarrollador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DerechosActivity.class));
                getActivity().finish();
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getActivity(), "Saliendo", Toast.LENGTH_SHORT).show();
            }
        });
        getUserInfo();


        //Texto cabecera
        final TextView textView = binding.textPerfil;
        perfilViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return view;
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

                    nombre.setText(name);
                    apellido.setText(lastname);
                    email.setText(emailaddress);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
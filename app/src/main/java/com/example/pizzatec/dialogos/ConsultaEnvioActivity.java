package com.example.pizzatec.dialogos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pizzatec.R;

public class ConsultaEnvioActivity extends AppCompatActivity {


    ImageView imgCancelar, imgDelivery, imgTienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_envio);

        imgCancelar = findViewById(R.id.btnCancelar);
        imgDelivery = findViewById(R.id.btnDelivery);
        imgTienda = findViewById(R.id.btnRecoger);

        imgCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConsultaEnvioActivity.this, "A elegido la modalidad DELIVERY", Toast.LENGTH_SHORT).show();
            }
        });

        imgTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConsultaEnvioActivity.this, "A elegido la modalidad RECOJO EN TIENDA", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
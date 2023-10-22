package com.example.pizzatec.menu.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzatec.R;
import com.example.pizzatec.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {

    private InicioViewModel homeViewModel;
    private FragmentInicioBinding binding;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view  = inflater.inflate(R.layout.fragment_inicio, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        PromocionData[] promocionData = new PromocionData[]{
                new PromocionData("2Da A 1 Sol", "¡Llévate la 2da Pasta a S/ 1 TODOS LOS MARTES Y JUEVES! Aplica para todas nuestras pizzas de carta.", "S/. 42.90",R.drawable.dosxunocombosignature),
                new PromocionData("Black Combo Personal Power", "1 Pasta Clásica Grande + 1 Fugazza", "S/. 24.90 (Antes 55.80)",R.drawable.combopower),
                new PromocionData("Black Combo Super Familiar", "1 Pasta Familiar en sabores seleccionados + 1 porción de Cinnamon Rolls + 1 Mini Cheesesticks", "S/. 39.90",R.drawable.combosuperfamiliar),
                new PromocionData("Triple Pack", "3 Pizzas grandes seleccionadas + 1 porción de Cinnamon Rolls.", "S/. 79.90",R.drawable.triplepack),
                new PromocionData("Black Tripack", "3 Pizzas grandes seleccionadas ", "S/. 59.90",R.drawable.blackweek),
                new PromocionData("Combinación Perfecta", "1 Pasta Grande Seleccionada + 1 Gaseosa de 1LT", "S/. 29.90",R.drawable.combinacionperfecta),
                new PromocionData("Combo Papadias 3 En 1", "1 Pasta Grande Seleccionada + 1 Papadia a eleccción + 1 Gaseosa 1Lt.", "S/. 39.90",R.drawable.combopapadiastresenuno),
                new PromocionData("Super Combo Papadias", "2 Pizzas Grandes Seleccionadas + 2 Papadias a eleccción.", "S/. 59.90",R.drawable.supercombopapadias),
                new PromocionData("Las Carnívoras", "Lleva 2 Pizzas Grandes Carnívoras.", "S/. 49.90",R.drawable.pjcarnivoras),
                new PromocionData("2X1 Combo Signature", "2 Pizzas Familiares Signature + 2 Gaseosas de 1Lt + 2 porciones de Pepperoni rolls o Ham rolls.", "S/. 86.70",R.drawable.dosxunocombosignature),
        };

        PromocionAdapter promocionAdapter = new PromocionAdapter(promocionData, InicioFragment.this);
        recyclerView.setAdapter(promocionAdapter);

        final TextView textView = binding.textInicio;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
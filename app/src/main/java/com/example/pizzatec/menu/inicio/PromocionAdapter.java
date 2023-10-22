package com.example.pizzatec.menu.inicio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzatec.R;

public class PromocionAdapter extends RecyclerView.Adapter<PromocionAdapter.ViewHolder>{

    PromocionData[] promocionData;
    Context context;

    public PromocionAdapter(PromocionData[] promocionData, InicioFragment fragment){
        this.promocionData = promocionData;
        this.context = fragment.getActivity();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.promocion_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PromocionData promocionDataList = promocionData[position];
        holder.titlePromo.setText(promocionDataList.getPromocion());
        holder.descrPromo.setText(promocionDataList.getDescripcion());
        holder.precioPromo.setText(promocionDataList.getPrecio());
        holder.imagePromo.setImageResource(promocionDataList.getPromoImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Promocion : "+promocionDataList.getPromocion(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return promocionData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagePromo;
        TextView titlePromo;
        TextView descrPromo;
        TextView precioPromo;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imagePromo = itemView.findViewById(R.id.imageViewer);
            titlePromo = itemView.findViewById(R.id.tituloPromo);
            descrPromo = itemView.findViewById(R.id.descPromo);
            precioPromo = itemView.findViewById(R.id.precioPromo);
        }
    }
}

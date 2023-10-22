package com.example.pizzatec.administrador.mantenimiento.pasta;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzatec.R;
import com.example.pizzatec.ViewHolder;
import com.example.pizzatec.administrador.mantenimiento.pizza.ActualizarPizzaActivity;
import com.example.pizzatec.administrador.mantenimiento.pizza.DetallePizzaActivity;
import com.example.pizzatec.administrador.mantenimiento.pizza.Pizza;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastaAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<Pasta> mPastaList;

    public PastaAdapter(List<Pasta> pastaList) {
        mPastaList = pastaList;
    }

    @Override
    public void onBindViewHolder(com.example.pizzatec.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public com.example.pizzatec.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pasta_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mPastaList != null & mPastaList.size() > 0) {
            return mPastaList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Pasta> pastaList) {
        mPastaList.addAll(pastaList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (mPastaList != null & mPastaList.size() > 0) {
            mPastaList.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends com.example.pizzatec.ViewHolder {

        @BindView(R.id.pastaImageView)
        ImageView mPastaImageView;

        @BindView(R.id.nombrePastaTextView)
        TextView mNombreTextView;

        @BindView(R.id.precioPastaTextView)
        TextView mPrecioTextView;

        @BindView(R.id.descPastaTextView)
        TextView mDescripcionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            mPastaImageView.setImageDrawable(null);
            mNombreTextView.setText("");
            mPrecioTextView.setText("");
            mDescripcionTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            Pasta mPasta = mPastaList.get(position);

            int[] charactersImages= {R.drawable.pastas,R.drawable.pastas,R.drawable.pastas,R.drawable.pizzaimg};

            if (mPasta.getUrl() < 4) {
                mPastaImageView.setImageResource(charactersImages[mPasta.getUrl()]);
            }

            if (mPasta.getNombre() != null) {
                mNombreTextView.setText(mPasta.getNombre());
            }

            if (mPasta.getDescripcion() != null) {
                mDescripcionTextView.setText(mPasta.getDescripcion());
            }

            if (mPasta.getPrecio() != null) {
                mPrecioTextView.setText(mPasta.getPrecio());
            }

            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), DetallePizzaActivity.class);
                intent.putExtra("key",  mPasta.getKey());
                itemView.getContext().startActivity(intent);
            });

            itemView.setOnLongClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), ActualizarPizzaActivity.class);
                intent.putExtra("key",  mPasta.getKey());
                itemView.getContext().startActivity(intent);
                return false;
            });

        }
    }

}

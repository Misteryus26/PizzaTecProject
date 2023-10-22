package com.example.pizzatec.administrador.mantenimiento.pizza;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PizzaAdapter extends RecyclerView.Adapter<ViewHolder>{
    private final List<Pizza> mPizzaList;

    public PizzaAdapter(List<Pizza> pizzaList) {
        mPizzaList = pizzaList;
    }

    @Override
    public void onBindViewHolder(com.example.pizzatec.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public com.example.pizzatec.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mPizzaList != null & mPizzaList.size() > 0) {
            return mPizzaList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Pizza> pizzaList) {
        mPizzaList.addAll(pizzaList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (mPizzaList != null & mPizzaList.size() > 0) {
            mPizzaList.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends com.example.pizzatec.ViewHolder {

        @BindView(R.id.pizzaImageView)
        ImageView mPizzaImageView;

        @BindView(R.id.nombrePizzaTextView)
        TextView mNombreTextView;

        @BindView(R.id.precioPizzaTextView)
        TextView mPrecioTextView;

        @BindView(R.id.descPizzaTextView)
        TextView mDescripcionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            mPizzaImageView.setImageDrawable(null);
            mNombreTextView.setText("");
            mPrecioTextView.setText("");
            mDescripcionTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            Pizza mPizza = mPizzaList.get(position);

            int[] charactersImages= {R.drawable.pizzaimg,R.drawable.pizzaimg,R.drawable.pizzaimg,R.drawable.pizzaimg};

            if (mPizza.getUrl() < 4) {
                mPizzaImageView.setImageResource(charactersImages[mPizza.getUrl()]);
            }

            if (mPizza.getNombre() != null) {
                mNombreTextView.setText(mPizza.getNombre());
            }

            if (mPizza.getDescripcion() != null) {
                mDescripcionTextView.setText(mPizza.getDescripcion());
            }

            if (mPizza.getPrecio() != null) {
                mPrecioTextView.setText(mPizza.getPrecio());
            }

            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), DetallePizzaActivity.class);
                intent.putExtra("key",  mPizza.getKey());
                itemView.getContext().startActivity(intent);
            });

            itemView.setOnLongClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), ActualizarPizzaActivity.class);
                intent.putExtra("key",  mPizza.getKey());
                itemView.getContext().startActivity(intent);
                return false;
            });

        }
    }
}

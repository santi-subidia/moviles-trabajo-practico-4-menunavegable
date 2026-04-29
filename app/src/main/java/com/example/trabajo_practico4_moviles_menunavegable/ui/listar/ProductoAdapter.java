package com.example.trabajo_practico4_moviles_menunavegable.ui.listar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo_practico4_moviles_menunavegable.Producto;
import com.example.trabajo_practico4_moviles_menunavegable.databinding.ItemProductoBinding;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductoBinding binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto p = productos.get(position);
        holder.binding.tvCodigo.setText("Código: " + p.getCodigo());
        holder.binding.tvDescripcion.setText(p.getDescripcion());
        holder.binding.tvPrecio.setText("$" + p.getPrecio());
    }

    @Override
    public int getItemCount() {
        return productos != null ? productos.size() : 0;
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ItemProductoBinding binding;

        public ProductoViewHolder(@NonNull ItemProductoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
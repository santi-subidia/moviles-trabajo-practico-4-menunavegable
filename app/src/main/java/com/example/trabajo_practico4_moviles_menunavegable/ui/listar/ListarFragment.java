package com.example.trabajo_practico4_moviles_menunavegable.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.trabajo_practico4_moviles_menunavegable.databinding.FragmentListarBinding;
import com.example.trabajo_practico4_moviles_menunavegable.ui.ProductoViewModel;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ProductoViewModel viewModel;
    private ProductoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        adapter = new ProductoAdapter(new ArrayList<>());
        binding.rvProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvProductos.setAdapter(adapter);

        viewModel.getProductosLiveData().observe(getViewLifecycleOwner(), productos -> {
            adapter.setProductos(productos);
        });

        viewModel.cargarProductosOrdenados();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
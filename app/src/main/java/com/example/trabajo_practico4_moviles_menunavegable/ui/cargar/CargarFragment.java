package com.example.trabajo_practico4_moviles_menunavegable.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajo_practico4_moviles_menunavegable.databinding.FragmentCargarBinding;
import com.example.trabajo_practico4_moviles_menunavegable.ui.ProductoViewModel;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private ProductoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        binding.btnGuardar.setOnClickListener(v -> {
            String sCodigo = binding.etCodigo.getText().toString();
            String descripcion = binding.etDescripcion.getText().toString();
            String sPrecio = binding.etPrecio.getText().toString();

            viewModel.agregarProducto(sCodigo, descripcion, sPrecio);
        });

        viewModel.getMensajeError().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                viewModel.resetMensajeError();
            }
        });

        viewModel.getOperacionExitosa().observe(getViewLifecycleOwner(), exito -> {
            if (exito != null && exito) {
                Toast.makeText(getContext(), "Producto guardado con éxito", Toast.LENGTH_SHORT).show();
                binding.etCodigo.setText("");
                binding.etDescripcion.setText("");
                binding.etPrecio.setText("");

                viewModel.getOperacionExitosa().setValue(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
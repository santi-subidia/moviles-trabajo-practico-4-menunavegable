package com.example.trabajo_practico4_moviles_menunavegable.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajo_practico4_moviles_menunavegable.MainActivity;
import com.example.trabajo_practico4_moviles_menunavegable.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Producto>> productosLiveData = new MutableLiveData<>();
    private MutableLiveData<String> mensajeError = new MutableLiveData<>();
    private MutableLiveData<Boolean> operacionExitosa = new MutableLiveData<>();

    public ProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Producto>> getProductosLiveData() {
        return productosLiveData;
    }

    public MutableLiveData<String> getMensajeError() {
        return mensajeError;
    }

    public MutableLiveData<Boolean> getOperacionExitosa() {
        return operacionExitosa;
    }

    public void agregarProducto(String sCodigo, String descripcion, String sPrecio) {
        if (sCodigo == null || sCodigo.trim().isEmpty() || 
            descripcion == null || descripcion.trim().isEmpty() || 
            sPrecio == null || sPrecio.trim().isEmpty()) {
            mensajeError.setValue("Todos los campos son obligatorios");
            return;
        }

        int codigo;
        double precio;

        try {
            codigo = Integer.parseInt(sCodigo.trim());
        } catch (NumberFormatException e) {
            mensajeError.setValue("El código debe ser un número entero válido");
            return;
        }

        try {
            precio = Double.parseDouble(sPrecio.trim());
        } catch (NumberFormatException e) {
            mensajeError.setValue("El precio debe ser un número válido");
            return;
        }

        for (Producto existente : MainActivity.listaProductos) {
            if (existente.getCodigo() == codigo) {
                mensajeError.setValue("Código duplicado");
                return;
            }
        }

        Producto p = new Producto(codigo, descripcion.trim(), precio);
        MainActivity.listaProductos.add(p);
        mensajeError.setValue(null);
        operacionExitosa.setValue(true);
        cargarProductosOrdenados();
    }

    public void cargarProductosOrdenados() {
        List<Producto> copia = new ArrayList<>(MainActivity.listaProductos);
        Collections.sort(copia, (a, b) -> a.getDescripcion().compareToIgnoreCase(b.getDescripcion()));
        productosLiveData.setValue(copia);
    }

    public void resetMensajeError(){
        mensajeError.setValue(null);
    }
}
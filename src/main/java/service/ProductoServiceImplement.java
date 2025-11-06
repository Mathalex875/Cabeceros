package service;

import models.Producto;

import models.Producto;
import java.util.Arrays;
import java.util.List;

public class ProductoServiceImplement implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"laptop","Computacion", 874.21),
                new Producto(2L, "Mouse", "Inalambrico", 20.00),
                new Producto(3L,"Samsung", "Samsung", 2000.00),
                new Producto(4L,"Impresora", "Electronico",500.00),
                new Producto(5L,"Cocina","Electrodomestico",450.00));
    }
}

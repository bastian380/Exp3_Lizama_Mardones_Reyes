package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.springboot.api.fullrest.entities.Producto;
import com.duoc.springboot.api.fullrest.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements productoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Optional<Producto> delete(Producto producto) {
        Optional<Producto> productoOptional = productoRepository.findById(producto.getIdProducto());
        productoOptional.ifPresent(productoDb -> {
            productoRepository.delete(producto);
        });
        return productoOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> findByAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByAll'");
    }
}
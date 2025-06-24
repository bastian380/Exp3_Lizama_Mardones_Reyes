package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.springboot.api.fullrest.entities.Sucursal;
import com.duoc.springboot.api.fullrest.repositories.SucursalRepository;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    @Transactional
    public Optional<Sucursal> delete(Sucursal sucursal) {
        Optional<Sucursal> sucursalOptional = sucursalRepository.findById(sucursal.getIdSucursal());
        sucursalOptional.ifPresent(sucursalDb -> {
            sucursalRepository.delete(sucursal);
        });
        return sucursalOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> findAll() {
        return (List<Sucursal>) sucursalRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id);
    }

    @Override
    @Transactional
    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }
}

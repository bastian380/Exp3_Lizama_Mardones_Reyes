package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.springboot.api.fullrest.entities.GerenteSucursal;
import com.duoc.springboot.api.fullrest.repositories.GerenteSucursalRepository;

@Service
public class GerenteSucursalServiceImpl implements GerenteSucursalService {

    @Autowired
    private GerenteSucursalRepository gerenteSucursalRepository;

    @Override
    @Transactional
    public Optional<GerenteSucursal> delete(GerenteSucursal gerente) {
        Optional<GerenteSucursal> gerenteOptional = gerenteSucursalRepository.findById(gerente.getId_gerente());
        gerenteOptional.ifPresent(gerenteDb -> {
            gerenteSucursalRepository.delete(gerente);
        });
        return gerenteOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GerenteSucursal> findAll() {
        return (List<GerenteSucursal>) gerenteSucursalRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GerenteSucursal> findById(Long id) {
        return gerenteSucursalRepository.findById(id);
    }

    @Override
    @Transactional
    public GerenteSucursal save(GerenteSucursal gerente) {
        return gerenteSucursalRepository.save(gerente);
    }
}
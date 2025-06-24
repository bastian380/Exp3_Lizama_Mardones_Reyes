package com.duoc.springboot.api.fullrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.springboot.api.fullrest.entities.Pedido;
import com.duoc.springboot.api.fullrest.repositories.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Optional<Pedido> delete(Pedido unPedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(unPedido.getId_pedido());
        pedidoOptional.ifPresent(pedidoDb -> {
            pedidoRepository.delete(unPedido);
        });
        return pedidoOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findAll() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    @Transactional
    public Pedido save(Pedido unPedido) {
        return pedidoRepository.save(unPedido);
    }
}

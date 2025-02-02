package com.unir.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.pedidos.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}

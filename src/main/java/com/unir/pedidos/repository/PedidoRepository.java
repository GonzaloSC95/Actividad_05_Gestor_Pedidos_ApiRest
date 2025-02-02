package com.unir.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.pedidos.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	//Devolver la lista de pedidos gestionados por el comercial que coincida con ese id. Usar el PedidoDto como salida.
	public List<Pedido> findByComercial_IdComercial(int idComercial);
	
}

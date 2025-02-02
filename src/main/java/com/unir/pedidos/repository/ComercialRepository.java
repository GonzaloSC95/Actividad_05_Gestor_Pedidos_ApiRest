package com.unir.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.pedidos.entity.Comercial;

public interface ComercialRepository extends JpaRepository<Comercial, Integer>{
	
	//Devolver la lista de los comerciales que han atendido pedidos del cliente que coincida con ese id.
	public List<Comercial> findByPedidos_Cliente_IdCliente(int idCliente);
	
	//Devolver la lista de comerciales que no  han atendido ningún  pedido
	public List<Comercial> findByPedidosIsNull();

}

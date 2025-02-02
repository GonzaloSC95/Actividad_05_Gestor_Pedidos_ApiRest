package com.unir.pedidos.service;

import java.util.List;
import java.util.Map;

import com.unir.pedidos.entity.Pedido;

public interface PedidoService {
	
	//Metodos
	
	public List<Pedido> GetPedidos();
	
	public Pedido GetPedido(int id);
	
	public List<Pedido> GetPedidosByComercial(int idComercial);
	
	public Map<String, Double> GetTotalByComercial();
	
	public Pedido AltaPedido(Pedido pedido);
	
	public Pedido UpdatePedido(Pedido pedido);
	
	public int DeletePedido(int id);

}

package com.unir.pedidos.service;

import java.util.List;

import com.unir.pedidos.entity.Comercial;

public interface ComercialService {
	
	//Metodos
	
	public List<Comercial> GetComerciales();
	
	//Devolver los datos del comercial cuyo id coincida
	public Comercial GetComercial(int id);
	
	//Devolver la lista de los comerciales que han atendido pedidos del cliente que coincida con ese id.
	public List<Comercial> GetComercialByCliente(int idCliente);
	
	//Devolver la lista de comerciales que no  han atendido ningún  pedido
	public List<Comercial> GetComercialSinPedidos();
	
	public Comercial AltaComercial(Comercial comercial);
	
	public Comercial UpdateComercial(Comercial comercial);
	
	public int DeleteComercial(int id);

}

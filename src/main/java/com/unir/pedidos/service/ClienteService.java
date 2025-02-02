package com.unir.pedidos.service;

import java.util.List;

import com.unir.pedidos.entity.Cliente;

public interface ClienteService {

	// Metodos

	public List<Cliente> GetClientes();

	public Cliente GetCliente(int id);

	public Cliente AltaCliente(Cliente cliente);

	public Cliente UpdateCliente(Cliente cliente);

	public int DeleteCliente(int id);
}

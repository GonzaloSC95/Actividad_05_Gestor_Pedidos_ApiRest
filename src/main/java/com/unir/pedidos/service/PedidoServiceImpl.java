package com.unir.pedidos.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.pedidos.entity.Pedido;
import com.unir.pedidos.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public List<Pedido> GetPedidos() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido GetPedido(int id) {
		return pedidoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Pedido> GetPedidosByComercial(int idComercial) {
		return pedidoRepository.findByComercial_IdComercial(idComercial);
	}

	@Override
	public Map<String, Double> GetTotalByComercial() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		Map<String, Double> mapTotal = pedidos.stream().collect(Collectors.groupingBy(Pedido::GetNombreComercial, Collectors.summingDouble(Pedido::getImporte)));
		return mapTotal;
	}

	@Override
	public Pedido AltaPedido(Pedido pedido) {
		try {
			return pedidoRepository.save(pedido);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Pedido UpdatePedido(Pedido pedido) {
		try {
			if(pedidoRepository.findById(pedido.getIdPedido()).isEmpty()) {
				throw new Exception("No existe el pedido");
			}
			return pedidoRepository.save(pedido);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int DeletePedido(int id) {
		try {
			if(pedidoRepository.findById(id).isEmpty()) {
				throw new Exception("No existe el pedido");
			}
			pedidoRepository.deleteById(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}

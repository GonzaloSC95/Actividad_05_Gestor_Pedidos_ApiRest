package com.unir.pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.pedidos.entity.Comercial;
import com.unir.pedidos.repository.ComercialRepository;

@Service
public class ComercialServiceImpl implements ComercialService{
	
	@Autowired
	private ComercialRepository comercialRepository;

	@Override
	public List<Comercial> GetComerciales() {
		return comercialRepository.findAll();
	}

	@Override
	public Comercial GetComercial(int id) {
		return comercialRepository.findById(id).orElse(null);
	}

	@Override
	public List<Comercial> GetComercialByCliente(int idCliente) {
		return comercialRepository.findByPedidos_Cliente_IdCliente(idCliente);
	}

	@Override
	public List<Comercial> GetComercialSinPedidos() {
		return comercialRepository.findByPedidosIsNull();
	}

	@Override
	public Comercial AltaComercial(Comercial comercial) {
		try {
			return comercialRepository.save(comercial);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Comercial UpdateComercial(Comercial comercial) {
		try {
			if(comercialRepository.findById(comercial.getIdComercial()).isEmpty())
			{
				throw new Exception("No existe el comercial");
			}
			return comercialRepository.save(comercial);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int DeleteComercial(int id) {
		try {
			if(comercialRepository.findById(id).isEmpty())
			{
				throw new Exception("No existe el comercial");
			}
			comercialRepository.deleteById(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}

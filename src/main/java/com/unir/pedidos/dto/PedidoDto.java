package com.unir.pedidos.dto;

import java.io.Serializable;
import java.util.Date;

import org.modelmapper.ModelMapper;
import com.unir.pedidos.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PedidoDto implements Serializable{
	
	//SerialVersionUID
	private static final long serialVersionUID = 1L;

	//ModelMapper
	private static ModelMapper modelMapper;

	// Atributos
	@EqualsAndHashCode.Include
	private int idPedido;

	private double importe;
	private Date fecha;
	private int idComercial;
	private int idCliente;

	// Métodos
	/**
	 * Método que convierte un Pedido a un PedidoDto
	 * 
	 * @return PedidoDto
	 */
	public PedidoDto mapFromPedido(Pedido pedido) {
		modelMapper = new ModelMapper();
		return modelMapper.map(pedido, PedidoDto.class);
	}

}

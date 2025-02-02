package com.unir.pedidos.dto;

import org.modelmapper.ModelMapper;

import com.unir.pedidos.entity.Comercial;

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
public class ComercialDto {
	
	//ModelMapper
	private static ModelMapper modelMapper;
	
	//Atributos
	@EqualsAndHashCode.Include
	private int idComercial;
	
	private String nombre;
    private String apellido1;
    private String apellido2;
    private Double comision;
	
	//Métodos
	/**
	 * Método que convierte un Comercial a un ComercialDto
	 * 
	 * @return ComercialDto
	 */
	public ComercialDto mapFromComercial(Comercial comercial) {
		modelMapper = new ModelMapper();
		return modelMapper.map(comercial, ComercialDto.class);
	}
	

}

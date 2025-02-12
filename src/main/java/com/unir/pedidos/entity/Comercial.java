package com.unir.pedidos.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//Anotaciones de Lombok
@NoArgsConstructor
@AllArgsConstructor
/*@Getter
@Setter
@ToString
@EqualsAndHashCode*/ //Estas anotaciones se pueden usar en lugar de @Data
@Data //Esta anotación genera los métodos getter, setter, toString, equals y hashCode
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Esta anotación genera los métodos equals y hashCode solo para el atributo idFamilia)
//Anotaciones de JPA
@Entity
@Table(name="comerciales")
@Schema(description = "Comerciales de la empresa")
public class Comercial implements Serializable{
	
	// Atributos
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_comercial")
	@Schema(description = "Identificador del comercial")
    private Integer idComercial;

	@Schema(description = "Nombre del comercial")
    private String nombre;
	@Schema(description = "Primer apellido del comercial")
    private String apellido1;
	@Schema(description = "Segundo apellido del comercial")
    private String apellido2;
	@Schema(description = "Comisión del comercial")
    private Double comision;
    
    @OneToMany(mappedBy = "comercial", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Pedidos gestionados por el comercial")
    private List<Pedido> pedidos;

}

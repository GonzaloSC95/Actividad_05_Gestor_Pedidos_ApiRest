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
@Table(name="clientes")
@Schema(description = "Clientes de la empresa")
public class Cliente implements Serializable{
	
	//Atributos
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name="id_cliente")
	@Schema(description = "Identificador del cliente")
    private Integer idCliente;

	@Schema(description = "Nombre del cliente")
    private String nombre;
	@Schema(description = "Primer apellido del cliente")
    private String apellido1;
	@Schema(description = "Segundo apellido del cliente")
    private String apellido2;
	@Schema(description = "Ciudad del cliente")
    private String ciudad;
	@Schema(description = "Categoría del cliente")
    private Integer categoria;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Pedidos realizados por el cliente")
    private List<Pedido> pedidos;

}

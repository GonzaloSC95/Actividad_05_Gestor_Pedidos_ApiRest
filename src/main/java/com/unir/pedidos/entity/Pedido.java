package com.unir.pedidos.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name="pedidos")
@Schema(description = "Pedidos de los clientes")
public class Pedido implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_pedido")
	@Schema(description = "Identificador del pedido")
	private Integer idPedido;

	@Schema(description = "Importe del pedido")
    private Double importe;
    
    @Temporal(TemporalType.DATE)
    @Schema(description = "Fecha del pedido")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @Schema(description = "Cliente que realiza el pedido")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_comercial", nullable = false)
    @Schema(description = "Comercial que atiende el pedido")
    private Comercial comercial;

    //Metodos
    @Schema(description = "Obtiene el nombre completo del cliente")
    public String GetNombreComercial(){
        return this.comercial.getNombre() + " " + this.comercial.getApellido1() + " " + this.comercial.getApellido2();
    }
}

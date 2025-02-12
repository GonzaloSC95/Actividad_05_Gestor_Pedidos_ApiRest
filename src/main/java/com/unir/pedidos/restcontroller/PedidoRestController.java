package com.unir.pedidos.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.pedidos.dto.PedidoDto;
import com.unir.pedidos.entity.Pedido;
import com.unir.pedidos.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedido")
public class PedidoRestController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/byComercial/{idComercial}")
	@Operation(description = "Devolver la lista de pedidos gestionados por el comercial "
			+ "que coincida con ese id. Usar el PedidoDto como salida.")
	@Parameter(name = "idComercial", description = "El id del comercial", required = true, schema = @Schema(type = "integer"))
	@ApiResponse(responseCode = "200", description = "Lista de pedidos gestionados por el comercial.")
	public ResponseEntity<List<PedidoDto>> GetPedidosByComercial(@PathVariable int idComercial) {
		List<Pedido> pedidos = pedidoService.GetPedidosByComercial(idComercial);
		if (pedidos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<PedidoDto> pedidosDto = new ArrayList<PedidoDto>();
		for (Pedido pedido : pedidos) {
			pedidosDto.add(new PedidoDto().mapFromPedido(pedido));
		}
		return new ResponseEntity<>(pedidosDto,HttpStatus.OK);
	}
	
	@GetMapping("/totalByComercial")
	@Operation(description = "Obtener la suma de los importes de los pedidos gestionados por cada comercial.  "
			+ "Obtener un Map<String,Double>, con el nombre y apellidos de cada comercial, y el importe total.")
	@ApiResponse(responseCode = "200", description = "Mapa con el nombre y apellidos de cada comercial, y el importe total.")
	public ResponseEntity<Map<String, Double>> GetTotalByComercial() {
		Map<String, Double> mapTotal = pedidoService.GetTotalByComercial();
		if (mapTotal == null) {
			return new ResponseEntity<>(mapTotal, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mapTotal, HttpStatus.OK);
	}

}

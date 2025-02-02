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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedido")
public class PedidoRestController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/byComercial/{idComercial}")
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
	public ResponseEntity<Map<String, Double>> GetTotalByComercial() {
		Map<String, Double> mapTotal = pedidoService.GetTotalByComercial();
		if (mapTotal == null) {
			return new ResponseEntity<>(mapTotal, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mapTotal, HttpStatus.OK);
	}

}

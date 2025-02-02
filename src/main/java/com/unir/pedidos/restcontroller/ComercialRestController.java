package com.unir.pedidos.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.pedidos.dto.ComercialDto;
import com.unir.pedidos.entity.Comercial;
import com.unir.pedidos.entity.Pedido;
import com.unir.pedidos.service.ComercialService;
import com.unir.pedidos.service.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/comercial")
public class ComercialRestController {
	
	@Autowired
	private ComercialService comercialService;

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/{idComercial}")
	public ResponseEntity<ComercialDto> GetComercial(@PathVariable int idComercial) {
		Comercial comercial = comercialService.GetComercial(idComercial);
		if (comercial == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ComercialDto comercialDto = new ComercialDto();
		return new ResponseEntity<>(comercialDto.mapFromComercial(comercial),HttpStatus.OK);
	}
	
	@GetMapping("/byCliente/{idCliente}")
	public ResponseEntity<List<ComercialDto>> GetComercialByCliente(@PathVariable int idCliente) {
		List<Comercial> comerciales = comercialService.GetComercialByCliente(idCliente);
		if (comerciales.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ComercialDto> comercialesDto = new ArrayList<ComercialDto>();
		for (Comercial comercial : comerciales) {
			comercialesDto.add(new ComercialDto().mapFromComercial(comercial));
		}
		return new ResponseEntity<>(comercialesDto, HttpStatus.OK);
	}
	
	@GetMapping("/sinpedidos")
	public ResponseEntity<List<ComercialDto>> GetComercialSinPedidos() {
		List<Comercial> comerciales = comercialService.GetComercialSinPedidos();
		if (comerciales.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ComercialDto> comercialesDto = new ArrayList<ComercialDto>();
		for (Comercial comercial : comerciales) {
			comercialesDto.add(new ComercialDto().mapFromComercial(comercial));
		}
		return new ResponseEntity<>(comercialesDto, HttpStatus.OK);
	}
	
	@PostMapping("/alta")
	public ResponseEntity<ComercialDto> AltaComercial(@RequestBody Comercial comercial) {
		Comercial comercialAlta = comercialService.AltaComercial(comercial);
		if (comercialAlta == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ComercialDto comercialDto = new ComercialDto();
		return new ResponseEntity<>(comercialDto.mapFromComercial(comercialAlta), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/eliminar/{idComercial}")
	public ResponseEntity<Integer> EliminarComercial(@PathVariable int idComercial) {
		List<Pedido> pedidos = pedidoService.GetPedidosByComercial(idComercial);
		if (!pedidos.isEmpty()) {
			return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		int eliminado = comercialService.DeleteComercial(idComercial);
		switch (eliminado) {
		case 1:
			return new ResponseEntity<>(eliminado, HttpStatus.OK);
		default:
			return new ResponseEntity<>(eliminado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/comercial")
public class ComercialRestController {
	
	@Autowired
	private ComercialService comercialService;

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/{idComercial}")
	@Operation(description = "Devolver los datos del comercial cuyo id coincida.")
	@Parameter(name = "idComercial", description = "El id del comercial", required = true, schema = @Schema(type = "integer"))
	@ApiResponse(responseCode = "200", description = "Datos del comercial.")
	public ResponseEntity<ComercialDto> GetComercial(@PathVariable int idComercial) {
		Comercial comercial = comercialService.GetComercial(idComercial);
		if (comercial == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ComercialDto comercialDto = new ComercialDto();
		return new ResponseEntity<>(comercialDto.mapFromComercial(comercial),HttpStatus.OK);
	}
	
	@GetMapping("/byCliente/{idCliente}")
	@Operation(description = "Devolver la lista de los comerciales que han atendido pedidos del cliente que coincida con ese id.")
	@Parameter(name = "idCliente", description = "El id del cliente", required = true, schema = @Schema(type = "integer"))
	@ApiResponse(responseCode = "200", description = "Lista de comerciales que han atendido pedidos del cliente.")
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
	@Operation(description = "Devolver la lista de comerciales que no  han atendido ningún  pedido.")
	@ApiResponse(responseCode = "200", description = "Lista de comerciales que no han atendido ningún pedido.")
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
	@Operation(description = "Dar de alta un nuevo comercial.")
	@ApiResponse(responseCode = "201", description = "Comercial dado de alta.")
	public ResponseEntity<ComercialDto> AltaComercial(@RequestBody Comercial comercial) {
		Comercial comercialAlta = comercialService.AltaComercial(comercial);
		if (comercialAlta == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ComercialDto comercialDto = new ComercialDto();
		return new ResponseEntity<>(comercialDto.mapFromComercial(comercialAlta), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/eliminar/{idComercial}")
	@Operation(description = "Eliminar de la bbdd el comercial cuyo id coincida, siempre y cuando no tenga pedidos a su cargo. Informar a la salida.")
	@Parameter(name = "idComercial", description = "El id del comercial", required = true, schema = @Schema(type = "integer"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comercial eliminado."),
			@ApiResponse(responseCode = "500", description = "Error interno.")
	})
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

package gft.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.dto.cliente.ClienteMapper;
import gft.dto.cliente.ConsultaClienteDTO;
import gft.dto.cliente.RegistroClienteDTO;
import gft.entities.Cliente;
import gft.services.ClienteService;

@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping // map: converte o tipo de uma classe para outra
	public ResponseEntity<Page<ConsultaClienteDTO>> buscarClientes(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(clienteService.listarTodosOsClientes(pageable).map(ClienteMapper::fromEntity));
	}

	@PostMapping
	public ResponseEntity<ConsultaClienteDTO> salvarCliente(@RequestBody RegistroClienteDTO dto) {
		Cliente cliente = clienteService.salvarCliente(ClienteMapper.fromDTO(dto));
		return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
	}

	@GetMapping("{id}") // localhost:8080/v1/filiais/1 -> /1 se eu quiser id = 1
	public ResponseEntity<ConsultaClienteDTO> buscarCliente(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarCliente(id);

		return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
	}

	@PutMapping("{id}")
	public ResponseEntity<ConsultaClienteDTO> alterarCliente(@RequestBody RegistroClienteDTO dto, @PathVariable Long id) {
		try {
			Cliente cliente = clienteService.atualizarCliente(ClienteMapper.fromDTO(dto), id);

			return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}


	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaClienteDTO> excluirCliente(@PathVariable Long id) {
		try {
			clienteService.excluirCliente(id);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
}

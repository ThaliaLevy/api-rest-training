package gft.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.dto.filial.ConsultaFilialDTO;
import gft.dto.filial.FilialMapper;
import gft.dto.filial.RegistroFilialDTO;
import gft.entities.Filial;
import gft.services.FilialService;

@RestController
@RequestMapping("v1/filiais")
public class FilialController {

	private final FilialService filialService;

	public FilialController(FilialService filialService) {
		this.filialService = filialService;
	}

	@GetMapping // map: converte o tipo de uma classe para outra
	public ResponseEntity<List<ConsultaFilialDTO>> buscarFiliais() {
		return ResponseEntity.ok(filialService.listarTodasAsFiliais().stream().map(FilialMapper::fromEntity)
				.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<ConsultaFilialDTO> salvarFilial(@RequestBody RegistroFilialDTO dto) {
		Filial filial = filialService.salvarFilial(FilialMapper.fromDTO(dto));
		return ResponseEntity.ok(FilialMapper.fromEntity(filial));
	}

	@GetMapping("{id}") // localhost:8080/v1/filiais/1 -> /1 se eu quiser id = 1
	public ResponseEntity<ConsultaFilialDTO> buscarFilial(@PathVariable Long id) {
		try {
			Filial filial = filialService.buscarFilial(id);

			return ResponseEntity.ok(FilialMapper.fromEntity(filial));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<ConsultaFilialDTO> alterarFilial(@RequestBody RegistroFilialDTO dto, @PathVariable Long id) {
		try {
			Filial filial = filialService.atualizarFilial(FilialMapper.fromDTO(dto), id);

			return ResponseEntity.ok(FilialMapper.fromEntity(filial));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaFilialDTO> excluirFilial(@PathVariable Long id) {
		try {
			filialService.excluirFilial(id);
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
}

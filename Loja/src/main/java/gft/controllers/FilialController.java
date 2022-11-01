package gft.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping				//map: converte o tipo de uma classe para outra
	public ResponseEntity<List<ConsultaFilialDTO>> buscarFiliais() {
		return ResponseEntity.ok(filialService.listarTodasAsFiliais()
							 .stream()
							 .map(FilialMapper::fromEntity)
							 .collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<ConsultaFilialDTO> salvarFilial(@RequestBody RegistroFilialDTO dto) {
		Filial filial = filialService.salvarFilial(FilialMapper.fromDTO(dto));
		return ResponseEntity.ok(FilialMapper.fromEntity(filial));
	}
}

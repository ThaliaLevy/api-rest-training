package gft.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.dto.etiqueta.ConsultaEtiquetaDTO;
import gft.dto.etiqueta.EtiquetaMapper;
import gft.dto.etiqueta.RegistroEtiquetaDTO;
import gft.entities.Etiqueta;
import gft.services.EtiquetaService;

@RestController
@RequestMapping("v1/etiquetas")
public class EtiquetaController {

	private final EtiquetaService etiquetaService;

	public EtiquetaController(EtiquetaService etiquetaService) {
		this.etiquetaService = etiquetaService;
	}

	@PostMapping("/vincular")
	public ResponseEntity<ConsultaEtiquetaDTO> salvarEtiqueta(@RequestBody RegistroEtiquetaDTO dto) {
		Etiqueta etiqueta = etiquetaService.salvarEtiqueta(EtiquetaMapper.fromDTO(dto));
		return ResponseEntity.ok(EtiquetaMapper.fromEntity(etiqueta));
	}

	
/*
	
	@GetMapping("{id}") // localhost:8080/v1/filiais/1 -> /1 se eu quiser id = 1
	public ResponseEntity<ConsultaEtiquetaDTO> buscarEtiqueta(@PathVariable Long id) {
		Etiqueta etiqueta = etiquetaService.buscarEtiqueta(id);

		return ResponseEntity.ok(EtiquetaMapper.fromEntity(etiqueta));
	}

	@GetMapping // map: converte o tipo de uma classe para outra
	public ResponseEntity<Page<ConsultaEtiquetaDTO>> buscarEtiquetas(@PageableDefault Pageable pageable) {
		return ResponseEntity.ok(etiquetaService.listarTodaAsEtiquetas(pageable).map(EtiquetaMapper::fromEntity));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ConsultaEtiquetaDTO> alterarEtiqueta(@RequestBody RegistroEtiquetaDTO dto, @PathVariable Long id) {
		try {
			Etiqueta etiqueta = etiquetaService.atualizarEtiqueta(EtiquetaMapper.fromDTO(dto), id);

			return ResponseEntity.ok(EtiquetaMapper.fromEntity(etiqueta));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}


	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaEtiquetaDTO> excluirEtiqueta(@PathVariable Long id) {
		try {
			etiquetaService.excluirEtiqueta(id);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}*/
}

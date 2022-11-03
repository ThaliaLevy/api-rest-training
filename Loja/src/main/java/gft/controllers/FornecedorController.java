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

import gft.dto.fornecedor.ConsultaFornecedorDTO;
import gft.dto.fornecedor.FornecedorMapper;
import gft.dto.fornecedor.RegistroFornecedorDTO;
import gft.entities.Fornecedor;
import gft.services.FornecedorService;

@RestController
@RequestMapping("v1/fornecedores")
public class FornecedorController {

	private final FornecedorService fornecedorService;

	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@GetMapping // map: converte o tipo de uma classe para outra
	public ResponseEntity<Page<ConsultaFornecedorDTO>> buscarFornecedores(@PageableDefault Pageable pageable) {				
		return ResponseEntity.ok(fornecedorService.listarTodosOsFornecedores(pageable).map(FornecedorMapper::fromEntity));
	}

	@PostMapping
	public ResponseEntity<ConsultaFornecedorDTO> salvarFornecedor(@RequestBody RegistroFornecedorDTO dto) {
		Fornecedor fornecedor = fornecedorService.salvarFornecedor(FornecedorMapper.fromDTO(dto));
		return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
	}

	@GetMapping("{id}") // localhost:8080/v1/filiais/1 -> /1 se eu quiser id = 1
	public ResponseEntity<ConsultaFornecedorDTO> buscarFornecedor(@PathVariable Long id) {
		Fornecedor fornecedor = fornecedorService.buscarFornecedor(id);

		return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
	}

	@PutMapping("{id}")
	public ResponseEntity<ConsultaFornecedorDTO> alterarFornecedor(@RequestBody RegistroFornecedorDTO dto, @PathVariable Long id) {
		try {
			Fornecedor fornecedor = fornecedorService.atualizarFornecedor(FornecedorMapper.fromDTO(dto), id);

			return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaFornecedorDTO> excluirFornecedor(@PathVariable Long id) {
		try {
			fornecedorService.excluirFornecedor(id);
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
}

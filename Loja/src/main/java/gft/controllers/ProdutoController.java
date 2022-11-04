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

import gft.dto.produto.ConsultaProdutoDTO;
import gft.dto.produto.ProdutoMapper;
import gft.dto.produto.RegistroProdutoDTO;
import gft.entities.Produto;
import gft.services.ProdutoService;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController {

	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping // map: converte o tipo de uma classe para outra
	public ResponseEntity<Page<ConsultaProdutoDTO>> buscarProdutos(@PageableDefault Pageable pageable) {				
		return ResponseEntity.ok(produtoService.listarTodosOsProdutos(pageable).map(ProdutoMapper::fromEntity));
	}

	@PostMapping
	public ResponseEntity<ConsultaProdutoDTO> salvarProduto(@RequestBody RegistroProdutoDTO dto) {
		Produto produto = produtoService.salvarProduto(ProdutoMapper.fromDTO(dto));
		return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
	}

	@GetMapping("{id}") // localhost:8080/v1/filiais/1 -> /1 se eu quiser id = 1
	public ResponseEntity<ConsultaProdutoDTO> buscarProduto(@PathVariable Long id) {
		Produto produto = produtoService.buscarProduto(id);

		return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
	}

	@PutMapping("{id}")
	public ResponseEntity<ConsultaProdutoDTO> alterarProduto(@RequestBody RegistroProdutoDTO dto, @PathVariable Long id) {
		try {
			Produto produto = produtoService.atualizarProduto(ProdutoMapper.fromDTO(dto), id);

			return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaProdutoDTO> excluirProduto(@PathVariable Long id) {
		try {
			produtoService.excluirProduto(id);
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
}

package gft.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gft.entities.Fornecedor;
import gft.exception.EntityNotFoundException;
import gft.repositories.FornecedorRepository;

@Service
public class FornecedorService {

	private final FornecedorRepository fornecedorRepository;

	public FornecedorService(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}

	public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public Page<Fornecedor> listarTodosOsFornecedores(Pageable pageable) {
		return fornecedorRepository.findAll(pageable);
	}

	public Fornecedor buscarFornecedor(Long id) {
		Optional<Fornecedor> optional = fornecedorRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado!"));
	}

	public Fornecedor atualizarFornecedor(Fornecedor fornecedor, Long id) {
		Fornecedor fornecedorOriginal = this.buscarFornecedor(id);
		fornecedor.setId(fornecedorOriginal.getId());

		return fornecedorRepository.save(fornecedor);
	}

	public void excluirFornecedor(Long id) {
		Fornecedor fornecedorServiceOriginal = this.buscarFornecedor(id);

		fornecedorRepository.delete(fornecedorServiceOriginal);
	}
}

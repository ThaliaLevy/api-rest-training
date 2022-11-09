package gft.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import gft.entities.Estoque;
import gft.entities.Filial;
import gft.entities.Produto;
import gft.repositories.EstoqueRepository;

@Service
public class EstoqueService {

	private final EstoqueRepository estoqueRepository;
	
	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}
	
	public Integer acrescentaQuantidadeEstoque(Long produtoId, Long filialId, Integer quantidade) {
		Optional<Estoque> opEstoque = estoqueRepository.findByProduto_IdAndFilial_Id(produtoId, filialId);
		
		if(opEstoque.isPresent()) {
			Estoque estoque = opEstoque.get();
			estoque.setQuantidade(estoque.getQuantidade() + quantidade);
			
			return estoqueRepository.save(estoque).getQuantidade();
		}
		
		Estoque estoque = new Estoque();
		estoque.setProduto(new Produto(produtoId));
		estoque.setFilial(new Filial(filialId));
		estoque.setQuantidade(quantidade);
		
		return estoqueRepository.save(estoque).getQuantidade();
	}
}

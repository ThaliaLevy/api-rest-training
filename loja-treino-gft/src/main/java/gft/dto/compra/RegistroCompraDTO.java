package gft.dto.compra;

import java.util.List;

public class RegistroCompraDTO {

	private Long fornecedorId;
	private List<RegistroItemCompraDTO> itens;

	public RegistroCompraDTO() {}
	
	public RegistroCompraDTO(Long fornecedorId, List<RegistroItemCompraDTO> itens) {
		this.fornecedorId = fornecedorId;
		this.itens = itens;
	}

	public Long getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public List<RegistroItemCompraDTO> getItens() {
		return itens;
	}

	public void setItens(List<RegistroItemCompraDTO> itens) {
		this.itens = itens;
	}
}

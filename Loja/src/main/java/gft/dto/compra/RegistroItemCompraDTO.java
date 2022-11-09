package gft.dto.compra;

import java.math.BigDecimal;

public class RegistroItemCompraDTO {


	private Long produtoId;
	private Integer quantidade;
	private BigDecimal valor;
	
	public RegistroItemCompraDTO() {}
	
	public RegistroItemCompraDTO(Long produtoId, Integer quantidade, BigDecimal valor) {
		this.produtoId = produtoId;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}

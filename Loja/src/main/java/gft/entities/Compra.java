package gft.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_compra")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Filial filial;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@OneToMany(cascade = CascadeType.PERSIST)	//PERSIST -> gerar compra antes de salvar os itens
	@JoinColumn(name = "compra_id")
	private List<ItemCompra> itens;

	public Compra() {}
	
	public Compra(Long id, Filial filial, Fornecedor fornecedor, List<ItemCompra> itens) {
		this.id = id;
		this.filial = filial;
		this.fornecedor = fornecedor;
		this.itens = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItemCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}
}

package gft.dto.produto;

public class ConsultaProdutoDTO {

	private Long id;
	private String nome;
	private String descricao;
	private String unidade;

	public ConsultaProdutoDTO() {}

	public ConsultaProdutoDTO(Long id, String nome, String descricao, String unidade) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.unidade = unidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
}

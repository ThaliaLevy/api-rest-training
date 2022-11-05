package gft.dto;

public class TokenDTO {

	private String token;

	public TokenDTO(String token) {
		this.token = token;
	}

	public TokenDTO() {}	//sem construtor (avaliar se ele fez)

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

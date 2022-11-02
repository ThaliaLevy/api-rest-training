package gft.exception;

//classe que exibirá exceção personalizada 
public class LojaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public LojaException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

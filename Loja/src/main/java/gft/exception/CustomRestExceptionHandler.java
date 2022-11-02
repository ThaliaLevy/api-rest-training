package gft.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice		//classe que será chamada sempre que houver alguma exceção
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

}

package gft.exception.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import gft.dto.exception.ApiErrorDTO;
import gft.exception.AlreadyReportedException;
import gft.exception.BadRequestException;
import gft.exception.ForbiddenException;
import gft.exception.NotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity<ApiErrorDTO> notFoundException(NotFoundException ex, WebRequest request){
		
		ApiErrorDTO apiError = new ApiErrorDTO(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler({ForbiddenException.class})
	public ResponseEntity<ApiErrorDTO> forbiddenException(ForbiddenException ex, WebRequest request){
		
		ApiErrorDTO apiError = new ApiErrorDTO(new Date(), ex.getMessage(), HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler({BadRequestException.class})
	public ResponseEntity<ApiErrorDTO> badRequestException(BadRequestException ex, WebRequest request){
		
		ApiErrorDTO apiError = new ApiErrorDTO(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler({AlreadyReportedException.class})
	public ResponseEntity<ApiErrorDTO> alreadyReportedException(AlreadyReportedException ex, WebRequest request){
		
		ApiErrorDTO apiError = new ApiErrorDTO(new Date(), ex.getMessage(), HttpStatus.ALREADY_REPORTED);
		
		return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}

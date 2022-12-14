package com.levythalia.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levythalia.converters.NumberConverter;
import com.levythalia.math.SimpleMath;

/* @Controller -> necessita de uma view para exibir seus dados.
 * para obter o comportamento do RestController usando @Controller, basta tb utilizar a anotação @ResponseBody.
 * @RestController -> resposta em forma de json.
 */

@RestController 
public class MathController {
	
	private SimpleMath simpleMath = new SimpleMath();
	
	/* path params (value) torna obrigatório o parâmetro dentro de {}.
	 * bom colocar qual o tipo do metodo pq conforme a aplicação cresce, pode ser
	   que sejam inseridos outros metodos no mesmo RequestMapping.
	 * counter.incrementAndGet() -> auto incrementa o id	
	 */
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",	method = RequestMethod.GET)												
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
					  @PathVariable(value = "numberTwo") String numberTwo) throws Exception {	
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			 throw new UnsupportedOperationException("Por favor, insira um valor numérico.");
		}
		return simpleMath.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/division/{numberOne}/{numberTwo}",	method = RequestMethod.GET)												
	public Double division(@PathVariable(value = "numberOne") String numberOne,
					  @PathVariable(value = "numberTwo") String numberTwo) throws Exception {	
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			 throw new UnsupportedOperationException("Por favor, insira um valor numérico.");
		}
		return simpleMath.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}",	method = RequestMethod.GET)												
	public Double subtraction(@PathVariable(value = "numberOne") String numberOne,
					  @PathVariable(value = "numberTwo") String numberTwo) throws Exception {	
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			 throw new UnsupportedOperationException("Por favor, insira um valor numérico.");
		}
		return simpleMath.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}",	method = RequestMethod.GET)												
	public Double multiplication(@PathVariable(value = "numberOne") String numberOne,
					  @PathVariable(value = "numberTwo") String numberTwo) throws Exception {	
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			 throw new UnsupportedOperationException("Por favor, insira um valor numérico.");
		}
		return simpleMath.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
}

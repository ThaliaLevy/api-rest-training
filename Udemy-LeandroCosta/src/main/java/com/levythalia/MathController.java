package com.levythalia;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/* @Controller -> necessita de uma view para exibir seus dados.
 * para obter o comportamento do RestController usando @Controller, basta tb utilizar a anotação @ResponseBody.
 * @RestController -> resposta em forma de json.
 */

@RestController 
public class MathController {
	
	/* path params (value) torna obrigatório o parâmetro dentro de {}.
	 * bom colocar qual o tipo do metodo pq conforme a aplicação cresce, pode ser
	   que sejam inseridos outros metodos no mesmo RequestMapping.
	 * counter.incrementAndGet() -> auto incrementa o id	
	 */
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",	method = RequestMethod.GET)				
															
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
					  @PathVariable(value = "numberTwo") String numberTwo) {	
		return 1D;
	}
}

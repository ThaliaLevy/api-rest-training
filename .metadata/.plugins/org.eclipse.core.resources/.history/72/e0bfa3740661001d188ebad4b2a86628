package com.levythalia;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller    //necessita de uma view para exibir seus dados
//para obter o comportamento do RestController usando @Controller, basta tb utilizar a anotação @ResponseBody

@RestController //resposta em forma de json
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/")	//@ResquetParam é opcional. Para usar: http://localhost:8080/?name=Thalia
	public Greeting greeting(@RequestParam(value = "name", defaultValue= "world") String name) {
		
							//auto incrementar o id				
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}

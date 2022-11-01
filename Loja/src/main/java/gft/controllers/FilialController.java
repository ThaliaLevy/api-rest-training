package gft.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.dto.ConsultaFilialDTO;
import gft.dto.EnderecoDTO;

@RestController
@RequestMapping("v1/filiais")
public class FilialController {

	@GetMapping
	public ResponseEntity<List<ConsultaFilialDTO>> buscarFiliais() {
		
		EnderecoDTO endereco1 = new EnderecoDTO();
		endereco1.setLogradouro("Rua Pedro Américo");
		
		ConsultaFilialDTO filial1 = new ConsultaFilialDTO();
		filial1.setNome("Filial A");
		filial1.setEndereco(endereco1);
	
		EnderecoDTO endereco2 = new EnderecoDTO();
		endereco2.setLogradouro("Avenida Atlântica");
		
		ConsultaFilialDTO filial2 = new ConsultaFilialDTO();
		filial2.setNome("Filial A");
		filial2.setEndereco(endereco2);
		
		List<ConsultaFilialDTO> lista = List.of(filial1, filial2);
		
		return ResponseEntity.ok(lista);
	}
	
}

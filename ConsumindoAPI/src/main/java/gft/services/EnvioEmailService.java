package gft.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import gft.dto.listaNoticias.ListaNoticiasDTO;
import gft.dto.usuario.RegistroEtiquetasDoUsuarioDTO;
import gft.entities.Etiqueta;
import reactor.core.publisher.Mono;

@Service
public class EnvioEmailService {
	
	private final WebClient webClient;
	private final JavaMailSender mailSender;
	
	public EnvioEmailService(WebClient webClient, JavaMailSender mailSender) {
		this.webClient = webClient;
		this.mailSender = mailSender;
	}

	public List<Object> enviarEmail(List<RegistroEtiquetasDoUsuarioDTO> registros, String date) {
		List<Object> registroNoticiasPorUsuario = new ArrayList<>();
		List<String> etiquetasDoUsuario = new ArrayList<>();
		
		for (RegistroEtiquetasDoUsuarioDTO registro : registros) {
			for (Etiqueta etiqueta : registro.getEtiquetas()) {
				Mono<ListaNoticiasDTO> monoNoticia = consumirApiNoticias(etiqueta.getNome(), date);
				ListaNoticiasDTO noticias = monoNoticia.block();
				
				etiquetasDoUsuario.add(etiqueta.getNome());
				registroNoticiasPorUsuario.add(noticias.getList());
			}

			String nomesEtiquetasFormatados = formatarExibicacaoTexto(etiquetasDoUsuario);

			enviarEmailParaUsuario(registro.getEmail(), date, registroNoticiasPorUsuario, nomesEtiquetasFormatados);

			limparLists(etiquetasDoUsuario, registroNoticiasPorUsuario);
		}
		
		return registroNoticiasPorUsuario;
	}
	
	public Mono<ListaNoticiasDTO> consumirApiNoticias(String nomeEtiqueta, String date) {
		return this.webClient.method(HttpMethod.GET)
		.uri(uriBuilder -> uriBuilder
				.path("api/")
				.queryParam("q", nomeEtiqueta)
				.queryParam("date", date).build())
		.retrieve()
		.bodyToMono(ListaNoticiasDTO.class);
	}

	public String formatarExibicacaoTexto(List<String> etiquetasDoUsuario) {
		int i = 0;
		String fraseFormatada = "";
		
		for (String etiqueta : etiquetasDoUsuario) {
			if (i == 0) {
				fraseFormatada = etiqueta;
				i++;
			} else {
				fraseFormatada = fraseFormatada + ", " + etiqueta;
			}
		}
		
		return fraseFormatada.toLowerCase();
	}
	
	public void enviarEmailParaUsuario(String email, String date, List<Object> registroNoticiasPorUsuario, String nomesEtiquetasFormatados) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Notícias do dia! (" + date + ")");
		message.setText("Tópicos: " + nomesEtiquetasFormatados + ".\n\n" + registroNoticiasPorUsuario);

		mailSender.send(message);
	}
	
	public void limparLists(List<String> etiquetasDoUsuario, List<Object> registroNoticiasPorUsuario) {
		etiquetasDoUsuario.clear();
		registroNoticiasPorUsuario.clear();
	}
}

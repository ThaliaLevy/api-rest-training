package gft.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import gft.entities.Etiqueta;
import gft.entities.HistoricoParametros;
import gft.entities.ListaNoticias;
import gft.entities.Usuario;
import gft.repositories.EtiquetaRepository;
import gft.repositories.HistoricoParametrosRepository;
import reactor.core.publisher.Mono;

@Service
public class EtiquetaService {

	private final EtiquetaRepository etiquetaRepository;
	private final HistoricoParametrosRepository historicoParametrosRepository;
	private final WebClient webClient;

	public EtiquetaService(EtiquetaRepository etiquetaRepository, WebClient webClient, HistoricoParametrosRepository historicoParametrosRepository) {
		this.etiquetaRepository = etiquetaRepository;
		this.historicoParametrosRepository = historicoParametrosRepository;
		this.webClient = webClient;
	}
//AINDA PRECISA TESTAR
	
	//TODAS AS EXCEPTIONS PRECISAM SER REVISADAS *** NA CLASSE CONTROLLER PRECISO TIRAR OS IFS 
	// DEIXAR CLASSES EXCEPTION: CUSTOMIZED.. ENTITYNOTFOUND
	public Etiqueta salvarEtiqueta(Etiqueta etiqueta) throws Exception {
		try {
			Etiqueta etiquetaExistente = buscarEtiquetaPorNome(etiqueta.getNome());

			if (verificarSeEtiquetaJaExisteParaUsuario(etiqueta.getNome(), etiqueta.getUsuarios()).isEmpty()) {
				atualizarEtiqueta(etiqueta, etiquetaExistente.getId());
				return etiqueta;
			} 
			
			throw new EntityNotFoundException("Etiqueta não está cadastrada para o usuário.");
		} catch (Exception e) {
			etiquetaRepository.save(etiqueta);
			return etiqueta;
		}
	}

	public Collection<Etiqueta> verificarSeEtiquetaJaExisteParaUsuario(String nomeEtiqueta, List<Usuario> usuario) {
		return etiquetaRepository.findByNomeAndUsuariosIn(nomeEtiqueta, usuario);
	}

	public Etiqueta atualizarEtiqueta(Etiqueta etiqueta, Long id) {
		Etiqueta etiquetaOriginal = this.buscarEtiqueta(id);

		List<Usuario> usuarios = new ArrayList<>();
		usuarios.addAll(etiquetaOriginal.getUsuarios());
		usuarios.addAll(etiqueta.getUsuarios());

		etiqueta.setUsuarios(usuarios);
		etiqueta.setId(etiquetaOriginal.getId());

		return etiquetaRepository.save(etiqueta);
	}

	public Etiqueta buscarEtiquetaPorNome(String nome) {
		return etiquetaRepository.findByNome(nome);	 
	}

	public Etiqueta buscarEtiqueta(Long id) {
		Optional<Etiqueta> optional = etiquetaRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Etiqueta não encontrada!"));
	}

	public ListaNoticias webClient(Usuario usuario, String q, String date) {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(usuario);

		Collection<Etiqueta> etiquetaExistente = verificarSeEtiquetaJaExisteParaUsuario(q, usuarios);

		if (!etiquetaExistente.isEmpty()) {
			Mono<ListaNoticias> monoNoticia = this.webClient.method(HttpMethod.GET)
												  .uri(uriBuilder -> uriBuilder
														  .path("api/")
														  .queryParam("q", q)
														  .queryParam("date", date)
														  .build())
												  .retrieve()
												  .bodyToMono(ListaNoticias.class);

			ListaNoticias noticia = monoNoticia.block();

			historicoParametrosRepository.save(new HistoricoParametros(null, q.toLowerCase(), date, usuario.getId()));
			return noticia;
		}
//testar
		throw new EntityNotFoundException("teste!");
	}

	public List<String> listarEtiquetasMaisAcessadas() {
		List<HistoricoParametros> historicoCompletoDeParametros = historicoParametrosRepository.findAll();
		List<String> etiquetas = new ArrayList<>();

		for (HistoricoParametros parametros : historicoCompletoDeParametros) {
			etiquetas.add(parametros.getEtiqueta());
		}

		List<String> etiquetasOrdenadasPorOcorrencia = new ArrayList<>();
		Set<String> st = new HashSet<String>(etiquetas);
		for (String s : st)
			etiquetasOrdenadasPorOcorrencia.add(Collections.frequency(etiquetas, s) + " etiqueta(s): " + s);

		Collections.sort(etiquetasOrdenadasPorOcorrencia, Collections.reverseOrder());

		return etiquetasOrdenadasPorOcorrencia;
	}

	public List<HistoricoParametros> visualizarParametrosAcessadosHoje(Usuario usuarioAutenticado, String data) {
		if (usuarioAutenticado.getPerfil().getNome().equals("Admin")) {
			return historicoParametrosRepository.findByData(data);
		} else {
			List<HistoricoParametros> ocorrenciasDoIdDoUsuario = historicoParametrosRepository.findByIdUsuarioAndData(usuarioAutenticado.getId(), data);
			if (ocorrenciasDoIdDoUsuario.isEmpty()) {
				return null;
			}
			return ocorrenciasDoIdDoUsuario;
		}
	}
}

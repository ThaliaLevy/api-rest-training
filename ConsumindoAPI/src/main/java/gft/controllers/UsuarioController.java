package gft.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gft.dto.etiqueta.EtiquetaMapper;
import gft.dto.etiqueta.RegistroEtiquetaDTO;
import gft.dto.usuario.RegistroEtiquetasDoUsuarioDTO;
import gft.dto.usuario.RegistroUsuarioDTO;
import gft.dto.usuario.UsuarioMapper;
import gft.entities.HistoricoParametros;
import gft.entities.ListaNoticias;
import gft.entities.Usuario;
import gft.exception.ForbiddenException;
import gft.services.EnvioEmailService;
import gft.services.EtiquetaService;
import gft.services.UsuarioService;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final EtiquetaService etiquetaService;
	private final EnvioEmailService envioEmailService;

	public UsuarioController(UsuarioService usuarioService, EtiquetaService etiquetaService, EnvioEmailService envioEmailService) {
		this.usuarioService = usuarioService;
		this.etiquetaService = etiquetaService;
		this.envioEmailService = envioEmailService;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/cadastrar")
	public ResponseEntity salvarUsuario(@RequestBody RegistroUsuarioDTO dto) {
		Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (usuarioAutenticado.getPerfil().getNome().equals("Admin")) {
				usuarioService.salvarUsuario(UsuarioMapper.fromDTO(dto));
				
				return  ResponseEntity.status(HttpStatus.OK).body("Usuário cadastrado com sucesso!");
		}	
		
		throw new ForbiddenException("Cadastro de usuários só pode ser realizado por perfil administrador.");
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/etiquetas/vincular")
	public ResponseEntity salvarEtiqueta(@RequestBody RegistroEtiquetaDTO dto) throws Exception {
		Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (usuarioAutenticado.getPerfil().getNome().equals("Usuario")) {
			etiquetaService.salvarEtiqueta(EtiquetaMapper.fromDTO(usuarioAutenticado, dto));
			
			return ResponseEntity.status(HttpStatus.OK).body("Etiqueta vinculada ao usuário com sucesso!");
		}

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cadastro de etiquetas só pode ser realizado por perfil sem administrador.");
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/noticias/antigas")
	public ResponseEntity obterRespostaApiNoticiasAntigas(@RequestParam String q, @RequestParam String date) {
		Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (usuarioAutenticado.getPerfil().getNome().equals("Usuario")) {
			ListaNoticias noticias = etiquetaService.webClient(usuarioAutenticado, q, date);
			
			return ResponseEntity.ok(noticias);
		}
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Consulta de notícias só pode ser realizada por perfil sem administrador.");
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/noticias/hoje")
	public ResponseEntity obterRespostaApiNoticiasHoje(@RequestParam String q) throws ParseException {
		Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (usuarioAutenticado.getPerfil().getNome().equals("Usuario")) {
			ListaNoticias noticias = etiquetaService.webClient(usuarioAutenticado, q, verificarDataDeHoje());

			return ResponseEntity.ok(noticias);
		}
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Consulta de notícias só pode ser realizada por perfil sem administrador.");
	}

	private String verificarDataDeHoje() {
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		DateFormat f = DateFormat.getDateInstance(DateFormat.DATE_FIELD);

		return f.format(data);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/etiquetas-mais-acessadas")
	public ResponseEntity visualizarEtiquetasMaisAcessadas(@PageableDefault(sort = "asc") Pageable pageable) {
		Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (usuarioAutenticado.getPerfil().getNome().equals("Admin")) {
			return ResponseEntity.ok(etiquetaService.listarEtiquetasMaisAcessadas());
		}

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Histórico de etiquetas mais acessadas só pode ser visualizado por perfil administrador.");
	}

	@GetMapping("/parametros-acessados")
	public List<HistoricoParametros> visualizarParametrosAcessadosHoje() {
		Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<HistoricoParametros> ocorrenciasDoIdDoUsuario = etiquetaService.visualizarParametrosAcessadosHoje(usuarioAutenticado, verificarDataDeHoje());
		
		return ocorrenciasDoIdDoUsuario;
	}

	@RequestMapping(path = "/email-send", method = RequestMethod.GET)
	public ResponseEntity<String> enviarEmail() {
		Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (usuarioAutenticado.getPerfil().getNome().equals("Admin")) {
			List<RegistroEtiquetasDoUsuarioDTO> registros = usuarioService.buscarEtiquetasVinculadasAUsuarios();
			
			envioEmailService.enviarEmail(registros, verificarDataDeHoje());
			
			return ResponseEntity.status(HttpStatus.OK).body("E-mail de notícias enviado com sucesso!");
		}
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Envio de e-mails de notícias só pode ser realizado por perfil administrador."); 
	}
}

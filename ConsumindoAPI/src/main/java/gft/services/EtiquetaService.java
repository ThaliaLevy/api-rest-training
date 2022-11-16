package gft.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gft.entities.Etiqueta;
import gft.entities.Usuario;
import gft.exception.EntityNotFoundException;
import gft.repositories.EtiquetaRepository;

@Service
public class EtiquetaService {

	private final EtiquetaRepository etiquetaRepository;

	public EtiquetaService(EtiquetaRepository etiquetaRepository) {
		this.etiquetaRepository = etiquetaRepository;
	}

	public boolean verificarSeEtiquetaJaExiste(Etiqueta etiqueta) {
		String nomeNovaEtiqueta = etiqueta.getNome();
		Collection<Etiqueta> etiquetaDoRepository = etiquetaRepository.findByNome(nomeNovaEtiqueta);
		System.out.println("Nome " + etiquetaDoRepository.toString());

		
		List<Usuario> usuariosEtiqueta = etiqueta.getUsuarios();
		Collection<Etiqueta> usuariosDoRepository = etiquetaRepository.findByUsuariosIn(usuariosEtiqueta);
		System.out.println("Usuarios " + usuariosDoRepository);
		
		if(etiquetaDoRepository.isEmpty()) {
			return false;
		}
		return false;
	}
	
	public Etiqueta salvarEtiqueta(Etiqueta etiqueta) {
		if(verificarSeEtiquetaJaExiste(etiqueta) == false) {
			return etiquetaRepository.save(etiqueta);
		}
		
		return null;
	}

	public Page<Etiqueta> listarTodaAsEtiquetas(Pageable pageable) {
		return etiquetaRepository.findAll(pageable);
	}

	public Etiqueta buscarEtiqueta(Long id) {
		Optional<Etiqueta> optional = etiquetaRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Etiqueta não encontrada!"));
	}

	public Etiqueta atualizarEtiqueta(Etiqueta etiqueta, Long id) {
		Etiqueta etiquetaOriginal = this.buscarEtiqueta(id);
		etiqueta.setId(etiquetaOriginal.getId());

		return etiquetaRepository.save(etiqueta);
	}

	public void excluirEtiqueta(Long id) {
		Etiqueta etiquetaServiceOriginal = this.buscarEtiqueta(id);

		etiquetaRepository.delete(etiquetaServiceOriginal);
	}
	
	/*
	 * public boolean verificarSeEtiquetaJaExiste(Etiqueta etiqueta) {
		Etiqueta nome = etiquetaRepository.findByNome(etiqueta.getNome());

		System.out.println(nome);

		/*
		 * String nomeNovaEtiqueta = etiqueta.getNome(); List<Usuario> usuarios =
		 * etiqueta.getUsuarios();
		 * 
		 * 
		 * System.out.println(etiquetaRepository.findByNome(nomeNovaEtiqueta));
		 * System.out.println(nomeNovaEtiqueta); System.out.println(usuarios);
		 * 
		 * //List<Usuario> usuariosDoRepository =
		 * etiquetaRepository.findByUsuarios(usuarios);
		 * 
		 * 
		 * 
		 * //System.out.println(usuariosDoRepository);
		 * 
		 * 
		 * //Collection<Etiqueta> etiquetaDoRepository =
		 * etiquetaRepository.findByNome(nomeNovaEtiqueta); Collection<Etiqueta>
		 * etiquetaDoRepository = null; int i = 0;
		 * 
		 * etiquetaDoRepository =
		 * etiquetaRepository.findByNomeAndUsuarios(nomeNovaEtiqueta,
		 * etiqueta.getUsuarios()); System.out.println(etiquetaDoRepository);
		 * 
		 * for(Usuario item : etiqueta.getUsuarios()){ if(item == etiquetaDoRepository)
		 * { System.out.println(etiquetaDoRepository); } }
		 */

		// NÃO ESTOU CONSEGUINDO FAZER A VALIDAÇÃO DO USUARIO *******

		// i++;
		// }

//java.lang.IllegalArgumentException: Parameter value [Usuario [id=1, nome=null, email=null, senha=null, perfil=null]] did not match expected type [java.util.Collection (n/a)]

		/*
		 * for(Usuario item : etiqueta.getUsuarios()){ etiquetaDoRepository =
		 * etiquetaRepository.findByNomeAndUsuarios(nomeNovaEtiqueta, item);
		 * System.out.println(etiquetaDoRepository); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * if(etiquetaDoRepository.isEmpty()) { return false; }
		 
		return false;
	}
	  */
}

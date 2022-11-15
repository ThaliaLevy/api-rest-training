package gft.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gft.entities.Etiqueta;
import gft.exception.EntityNotFoundException;
import gft.repositories.EtiquetaRepository;

@Service
public class EtiquetaService {

	private final EtiquetaRepository etiquetaRepository;

	public EtiquetaService(EtiquetaRepository etiquetaRepository) {
		this.etiquetaRepository = etiquetaRepository;
	}

	public Etiqueta salvarEtiqueta(Etiqueta etiqueta) {
		return etiquetaRepository.save(etiqueta);
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
}
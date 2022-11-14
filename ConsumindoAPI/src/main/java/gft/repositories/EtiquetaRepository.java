package gft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gft.entities.Etiqueta;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
	Page<Etiqueta> findAll(Pageable pageable);
}

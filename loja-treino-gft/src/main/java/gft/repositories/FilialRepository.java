package gft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.entities.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {

	Page<Filial> findAll(Pageable pageable);
}

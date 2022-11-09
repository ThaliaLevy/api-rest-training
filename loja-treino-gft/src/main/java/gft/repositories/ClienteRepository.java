package gft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gft.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Page<Cliente> findAll(Pageable pageable);
}

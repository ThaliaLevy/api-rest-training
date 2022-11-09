package gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.entities.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{

}

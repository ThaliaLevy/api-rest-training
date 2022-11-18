package gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.entities.HistoricoParametros;

@Repository
public interface HistoricoParametrosRepository extends JpaRepository<HistoricoParametros, Long> {

}

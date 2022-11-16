package gft.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gft.entities.Etiqueta;
import gft.entities.Usuario;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
	//@Query("SELECT u FROM Etiqueta u WHERE u.nome =: nomeNovaEtiqueta")
	Collection<Etiqueta> findByNome(String nomeNovaEtiqueta);
	
	Collection<Etiqueta> findByUsuariosIn(List<Usuario> usuarios);
	
	// @Query("SELECT u FROM Etiqueta u WHERE nome = ?1 and usuarios = ?2")
	// Collection<Etiqueta> findByNomeAndUsuarios(String nome, List<Usuario> usuarios);
	
	//@Query("SELECT u FROM Etiqueta u WHERE u.usuarios = ?1")
	//List<Etiqueta> findByUsuarios(Usuario usuarios);
	
	//@Query("SELECT obj FROM Etiqueta obj JOIN FETCH obj.usuarios")
	//List<Etiqueta> findEtiquetaUsuarios();
	
	//Page<Etiqueta> findAll(Pageable pageable);

	
	/*
	 * @Query("SELECT u FROM Etiqueta u WHERE u.nome =: nome")
	Etiqueta findByNome(@Param("nome") String nome);
	
	// @Query("SELECT u FROM Etiqueta u WHERE nome = ?1 and usuarios = ?2")
	// Collection<Etiqueta> findByNomeAndUsuarios(String nome, List<Usuario> usuarios);
	
	//@Query("SELECT u FROM Etiqueta u WHERE u.usuarios = ?1")
	//List<Etiqueta> findByUsuarios(Usuario usuarios);
	
	//@Query("SELECT obj FROM Etiqueta obj JOIN FETCH obj.usuarios")
	//List<Etiqueta> findEtiquetaUsuarios();
	
	Page<Etiqueta> findAll(Pageable pageable);

	
	//@Query("SELECT u FROM Etiqueta u WHERE u.usuarios =: usuarios")
	//List<Usuario> findByUsuarios(List<Usuario> usuarios);
	
	
 */
	 
}

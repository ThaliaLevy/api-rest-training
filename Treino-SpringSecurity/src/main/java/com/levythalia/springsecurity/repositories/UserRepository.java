package com.levythalia.springsecurity.repositories;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/* A query lida com o objeto e não com o banco, por isso os dados precisam ser
	 * buscados de acordo com como os atributos foram criados na entity. */
	@Query("SELECT u FROM User WHERE u.email =: emailParametro")
	User findByEmail(@Param("email") String emailParametro);
}

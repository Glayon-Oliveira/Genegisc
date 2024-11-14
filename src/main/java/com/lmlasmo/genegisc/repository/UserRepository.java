package com.lmlasmo.genegisc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lmlasmo.genegisc.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findByProfileNameContaining(String profileName);
	
	public Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE YEAR(u.birthDate) = :year")
	public List<User> findByBirthYear(@Param("year") int year);
	
	@Query("SELECT u FROM User u WHERE MOUNT(u.birthDate) = :mount")
	public List<User> findByBirthMount(@Param("mount") int mount);
	
	@Query("SELECT u FROM User u WHERE YEAR(u.birthDate) >= :year")
	public List<User> findByBirthDateStartingFromYear(@Param("year") int year);

}

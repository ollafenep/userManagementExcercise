package com.exercise.userManagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserRepository extends JpaRepository<User_data, Integer>{

	
	List<User_data> findByName(String name);
	List<User_data> findBySurname(String surname);
	List<User_data> findByNameAndSurname(String name, String surname);
	
}

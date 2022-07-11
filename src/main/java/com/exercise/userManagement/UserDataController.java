package com.exercise.userManagement;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {
	private final UserRepository userRepo;
	
	public UserDataController(UserRepository repository) {
		userRepo = repository;
	}

	@GetMapping("/users")
	List<User_data> getUsersByName(@RequestParam(name="name", required=false) String name, @RequestParam(name="surname", required=false) String surname) {
		if(name != null && surname != null) {
			return userRepo.findByNameAndSurname(name, surname);
		} else if (name != null) {
			return userRepo.findByName(name);
		} else if (surname != null) {
			return userRepo.findBySurname(surname);
		}
		return userRepo.findAll();
	}
	
	@PostMapping("/users")
	User_data createUser(@RequestBody User_data newUser) {
		return userRepo.save(newUser);
	}
	
	@GetMapping("/users/{userId}")
	User_data getUser(@PathVariable int userId) {
		return userRepo.findById(userId).orElseThrow();
	}
	
	@PutMapping("/users/{userId}")
	User_data updateUser(@PathVariable int userId, @RequestBody User_data userDto) {
		User_data userToUpdate = userRepo.findById(userId).orElseThrow();
		userToUpdate.setName(userDto.getName());
		userToUpdate.setSurname(userDto.getSurname());
		userToUpdate.setEmail(userDto.getEmail());
		userToUpdate.setCreation_date(userDto.getCreation_date());
		return userRepo.save(userToUpdate);
	}
	
	@DeleteMapping("/users/{userId}")
	void deleteUser(@PathVariable int userId) {
		User_data userToDelete = userRepo.findById(userId).orElseThrow();
		userRepo.delete(userToDelete);
	}
	
	@PatchMapping("/users/{userId}")
	User_data patchUser(@PathVariable int userId, @RequestBody User_data userDto) {
		User_data userToUpdate = userRepo.findById(userId).orElseThrow();
		userToUpdate.setEmail(userDto.getEmail());
		userToUpdate.setCreation_date(userDto.getCreation_date());
		return userRepo.save(userToUpdate);
	}
	
}

package com.exercise.userManagement;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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
	@ResponseStatus(HttpStatus.CREATED)
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
	
	@RequestMapping(path = "/createUsersFromCSV", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	List<User_data> addUsersFromCSV(@RequestPart MultipartFile file) throws IOException {
		String fileContent = new String(file.getBytes());
		String pattern = "yyyy-MM-dd";
		List<User_data> added_users = new ArrayList<>();
		String[] usersToAdd = fileContent.split("\n");
		for(String s: usersToAdd) {
			String[] userData = s.split(",");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String creation_date = simpleDateFormat.format(new Date());
			User_data new_user = new User_data(userData[0], userData[1], userData[2], creation_date);
			added_users.add(new_user);
			userRepo.save(new_user);
		}
		return added_users;
	}
	
}

package com.exercise.userManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Entity
public class User_data {
	private @Id @GeneratedValue int id;
	private String name;
	private String surname;
	private String email;
	private String creation_date;
	
	User_data() {		
	}
	
	public User_data(String name, String surname, String email, String creation_date) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.creation_date = creation_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	
}

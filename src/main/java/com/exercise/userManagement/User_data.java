package com.exercise.userManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user_data")
@Entity
public class User_data {
	private @Id @GeneratedValue int id;
	private String name;
	private String surname;
	private String email;
	private String creationDate;
	private String updateDate;
	
	User_data() {		
	}
	
	public User_data(String name, String surname, String email, String creation_date) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.creationDate = creation_date;
	}

	public User_data(String name, String surname, String email, String creation_date, String update_date) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.creationDate = creation_date;
		this.updateDate = update_date;
	}

	public User_data(String email, String update_date) {
		super();
		this.email = email;
		this.updateDate = update_date;
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

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creation_date) {
		this.creationDate = creation_date;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}

package com.animeweb.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	String id;
	@Column(name = "password")
	String passWord;
	@Column(name = "fullname")
	String fullName;
	String email;
	Boolean admin;
	@OneToMany(mappedBy = "user")
	List<Favorite> favorites;

	public User() {
	}
	

	public User(String id, String passWord, String fullName, String email, Boolean admin) {
		super();
		this.id = id;
		this.passWord = passWord;
		this.fullName = fullName;
		this.email = email;
		this.admin = admin;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

}

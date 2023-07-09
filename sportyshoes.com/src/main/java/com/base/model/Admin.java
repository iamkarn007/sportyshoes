package com.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin {
	
	@Id
	String username;
	
	String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + "]";
	}
	
	

}

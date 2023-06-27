package com.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	private String username;
	
	private String password;
	
	private String purchasehistory;

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

	public String getPurchasehistory() {
		return purchasehistory;
	}

	public void setPurchasehistory(String purchasehistory) {
		this.purchasehistory = purchasehistory;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", purchasehistory=" + purchasehistory + "]";
	}

	public User(String username, String password, String purchasehistory) {
		super();
		this.username = username;
		this.password = password;
		this.purchasehistory = purchasehistory;
	}
	

}

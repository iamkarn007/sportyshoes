package com.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 

import java.time.LocalDateTime;      

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	private String username;
	
	private LocalDateTime Date;
	
	private int pid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getDate() {
		return Date;
	}

	public void setDate(LocalDateTime date) {
		Date = date;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Transaction(String username, LocalDateTime date, int pid) {
		super();
		this.username = username;
		Date = date;
		this.pid = pid;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	} 
}

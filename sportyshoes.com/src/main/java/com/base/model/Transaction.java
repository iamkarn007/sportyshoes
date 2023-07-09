package com.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 
   

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transId;
	
	public int getTransId() {
		return transId;
	}


	public void setTransId(int transId) {
		this.transId = transId;
	}
	private String username;
	
	private String date;
	
	private int pid;
	
	private int cid;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	} 
	public Transaction(int transId, String username, String date, int pid, int cid) {
		super();
		this.transId = transId;
		this.username = username;
		this.date = date;
		this.pid = pid;
		this.cid = cid;
	}

}

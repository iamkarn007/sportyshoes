package com.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String pname;
	
	public Product(int pid, String pname, Category category, long price) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.category = category;
		this.price = price;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cid", referencedColumnName = "cid")
	private Category category;
	
	private long price;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public Category getCategory() {
		return category;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", category=" + category + ", price=" + price + "]";
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	

}

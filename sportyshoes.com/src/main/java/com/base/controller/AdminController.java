package com.base.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.model.Admin;
import com.base.model.Category;
import com.base.model.Product;
import com.base.model.ProductDTO;
import com.base.model.User;
import com.base.repo.AdminRepo;
import com.base.repo.CategoryRepo;
import com.base.repo.ProductRepo;
import com.base.repo.TransactionRepo;
import com.base.repo.UserRepo;
import com.base.service.CategoryService;
import com.base.model.Transaction;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	CategoryRepo categoryrepo;
	
	@Autowired
	ProductRepo productrepo;
	
	@Autowired
	TransactionRepo transrepo;
	
	@Autowired
	AdminRepo adminrepo;
	
	@Autowired
	UserRepo userrepo;
	
	@GetMapping("/getAllCategory")
	public String getCategory() {
		return categoryservice.getAllCategory().toString();
		
	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		try {
			Category category1 = categoryrepo.save(new Category(category.getCid(), category.getCname()));
			return new ResponseEntity<>(category1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") int id) {
		try {
			categoryrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productdto) {
		try {
			Product product = new Product();
			product.setPid(productdto.getPid());
			product.setPname(productdto.getPname());
			product.setPrice(productdto.getPrice());
			product.setCategory(categoryrepo.findById(productdto.getCatId()).get());
			productrepo.save(product);
			System.out.println(product);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") int id) {
		try {
			productrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAllTransaction")
	public List<Transaction> getTransaction() {
		List<Transaction> trans = transrepo.findAll();
		return trans;
	}   
	
	@PostMapping("/changePassword")
	ResponseEntity<Admin> changePassword(@RequestBody Admin admin){
		try {
			Admin admin1 = adminrepo.save(new Admin(admin.getUsername(), admin.getPassword()));
			return new ResponseEntity<>(admin1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public String loginAccess(@RequestBody Admin admin) {
		Optional<Admin> admin1= adminrepo.findById(admin.getUsername());
		if(!admin1.isPresent()) {
			System.out.println("Not Present");
			return "No Admin with that Username is Present";
		}
		else {
			if(!admin1.get().getPassword().equals(admin.getPassword())) {
				System.out.println("Table Details "+admin1.get().getPassword());
				System.out.println("Entered Details "+admin.getPassword());
				return "Invalid Credential";
			}
			else {
				return "Successfully Logged in";
			}
		}
	}
	
	@PostMapping("/findUser")
	public String findUser(@RequestBody User user) {
		Optional<User> user1 = userrepo.findById(user.getUsername());
		if(user1.isPresent()) {
			System.out.println("User Found with Username: " + user.getUsername());
			return "User Found with Username: " + user.getUsername();
		}
		else {
			return "User not Found with Username: " + user.getUsername();
		}
	}
	
	@GetMapping("/getAllUser")
	public String getUser() {
		List<User> user = userrepo.findAll();
		return user.toString();
	}
	
	@GetMapping("/transaction/getByDate")
	public List<Transaction> getDetails(@RequestBody Transaction trans){
		List<Transaction> trans1 = transrepo.findByDate(trans.getDate());
		return trans1;
	}
	
	@GetMapping("/transaction/getByCid")
	public List<Transaction> getByCid(@RequestBody Transaction trans){
		List<Transaction> trans1 = transrepo.findByCid(trans.getCid());
		return trans1;
	}
	
	
}

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.model.Admin;
import com.base.model.Product;
import com.base.model.Transaction;
import com.base.model.User;
import com.base.repo.ProductRepo;
import com.base.repo.TransactionRepo;
import com.base.repo.UserRepo;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepo userrepo;
	
	@Autowired
	ProductRepo productrepo;
	
	@Autowired
	TransactionRepo transrepo;

	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			User user1 = userrepo.save(new User(user.getUsername(), user.getPassword(),user.getPurchasehistory()));
			return new ResponseEntity<>(user1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteUser/{username}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("username") String username) {
		try {
			userrepo.deleteById(username);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PostMapping("/purchase/{username}")
	public ResponseEntity<Transaction> purchaseProduct(@PathVariable("username") String username, @RequestBody Transaction transaction){
		try {
			Transaction trans = transrepo.save(new Transaction(transaction.getTransId(),username, transaction.getDate(), transaction.getPid(), transaction.getCid()));
			return new ResponseEntity<>(trans, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/login")
	public String loginAccess(@RequestBody User user) {
		Optional<User> user1= userrepo.findById(user.getUsername());
		if(!user1.isPresent()) {
			System.out.println("Not Present");
			return "No User with that Username is Present";
		}
		else {
			if(!user1.get().getPassword().equals(user.getPassword())) {
				System.out.println("Table Details "+user1.get().getPassword());
				System.out.println("Entered Details "+user.getPassword());
				return "Invalid Credential";
			}
			else {
				return "Successfully Logged in";
			}
		}
	}
}

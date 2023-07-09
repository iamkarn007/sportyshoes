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
	
	@GetMapping("/getuser")
	public String getUser() {
		List<User> user = userrepo.findAll();
		return user.toString();
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		try {
			User user1 = userrepo.save(new User(user.getUsername(), user.getPassword(),user.getPurchasehistory()));
			return new ResponseEntity<>(user1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteuser/{username}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("username") String username) {
		try {
			userrepo.deleteById(username);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PostMapping("/purchase/{pid}")
//	public ResponseEntity<User> purchaseTable(@PathVariable("pid") int pid, @RequestBody User user){
//		StringBuilder PurchaseData = new StringBuilder();
//		Optional<Product> prod=productrepo.findById(pid);
//		String password = userrepo.findById(null)
//		PurchaseData.append(prod.get().getPid());
//		PurchaseData.append("*");
//		PurchaseData.append(prod.get().getPname());
//		PurchaseData.append("*");
//		PurchaseData.append(prod.get().getPrice());
//		PurchaseData.append("*");
//		PurchaseData.append(prod.get().getCategory());
//		PurchaseData.append(";");
//		System.out.println(productrepo.findById(pid));
//		
//		try {
//			User user1 = userrepo.save(new User(user.getUsername(), user.getPassword(),PurchaseData.toString()));
//			return new ResponseEntity<>(user1, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	
	
	
	@PostMapping("/user/purchase/{username}")
	public ResponseEntity<Transaction> purchaseProduct(@PathVariable("username") String username, @RequestBody Transaction transaction){
		try {
			Transaction trans = transrepo.save(new Transaction(username, transaction.getDate(), transaction.getPid()));
			return new ResponseEntity<>(trans, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

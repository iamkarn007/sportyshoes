package com.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.model.User;
import com.base.repo.UserRepo;

@RestController
public class UserController {

	@Autowired
	UserRepo userrepo;
	
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
}

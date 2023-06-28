package com.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.model.Category;
import com.base.model.Product;
import com.base.model.ProductDTO;
import com.base.repo.CategoryRepo;
import com.base.repo.ProductRepo;
import com.base.service.CategoryService;

@RestController
public class AdminController {
	
	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	CategoryRepo categoryrepo;
	
	@Autowired
	ProductRepo productrepo;
	
	@GetMapping("/category")
	public String getCategory() {
		return categoryservice.getAllCategory().toString();
		
	}
	
	@PostMapping("/addcategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		try {
			Category category1 = categoryrepo.save(new Category(category.getCid(), category.getCname()));
			return new ResponseEntity<>(category1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deletecategory/{id}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("idd") int id) {
		try {
			categoryrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productdto) {
		try {
			Product product = new Product();
			product.setPid(productdto.getPid());
			product.setPname(productdto.getPname());
			product.setPrice(productdto.getPrice());
			product.setCategory(categoryrepo.findById(productdto.getCatId()).get());
			productrepo.save(product);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteproduct/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") int id) {
		try {
			productrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

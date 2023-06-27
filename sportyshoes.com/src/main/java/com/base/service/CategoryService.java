package com.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.model.Category;
import com.base.repo.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo categoryrepo;
	
	public List<Category> getAllCategory(){
	
		List<Category> category = categoryrepo.findAll();
	
		return category;

	}
	
}

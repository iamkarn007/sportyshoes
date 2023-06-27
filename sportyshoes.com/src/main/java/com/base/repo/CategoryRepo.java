package com.base.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {


}

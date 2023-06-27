package com.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}

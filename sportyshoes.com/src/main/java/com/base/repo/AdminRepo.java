package com.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, String> {

}

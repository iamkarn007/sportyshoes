package com.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, String> {

}

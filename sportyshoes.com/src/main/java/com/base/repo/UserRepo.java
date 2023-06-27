package com.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.User;

public interface UserRepo extends JpaRepository<User, String> {

}

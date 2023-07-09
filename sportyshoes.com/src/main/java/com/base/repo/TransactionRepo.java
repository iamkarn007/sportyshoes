package com.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}

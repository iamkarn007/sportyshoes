package com.base.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	
	List<Transaction> findByDate(String date);
	List<Transaction> findByCid(int cid);
}

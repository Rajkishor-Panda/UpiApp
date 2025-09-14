package com.upiapp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upiapp.demo.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByFromMobileOrToMobile(String from, String to);
}

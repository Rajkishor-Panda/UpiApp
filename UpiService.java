package com.upiapp.demo.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upiapp.demo.model.Transaction;
import com.upiapp.demo.model.User;
import com.upiapp.demo.repo.TransactionRepository;
import com.upiapp.demo.repo.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpiService {
	
	@Autowired
	private  UserRepository userRepo;
	@Autowired
	private  TransactionRepository txRepo;
	
	public User register(User u) {
        return userRepo.save(u);
    }
	
	public long getBalance(String mobile) {
        return userRepo.findById(mobile)
                .map(User::getBalance)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
	
	
	 @Transactional
	    public Transaction sendMoney(String from, String to, long amt, String pin, String remark) {
	        User sender = userRepo.findById(from).orElseThrow(() -> new RuntimeException("Sender not found"));
	        User receiver = userRepo.findById(to).orElseThrow(() -> new RuntimeException("Receiver not found"));

	        if (!sender.getPin().equals(pin)) {
	            throw new RuntimeException("Invalid PIN");
	        }
	        if (sender.getBalance() < amt) {
	            throw new RuntimeException("Insufficient balance");
	        }

	        sender.setBalance(sender.getBalance() - amt);
	        receiver.setBalance(receiver.getBalance() + amt);

	        userRepo.save(sender);
	        userRepo.save(receiver);

	        Transaction tx = new Transaction(null, from, to, amt, Instant.now(), remark,pin);
	        return txRepo.save(tx);
	    }

	    public List<Transaction> getTransactions(String mobile) {
	        return txRepo.findByFromMobileOrToMobile(mobile, mobile);
	    }

}

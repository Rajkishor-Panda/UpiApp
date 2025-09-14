package com.upiapp.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upiapp.demo.model.SendMoneyRequest;
import com.upiapp.demo.model.Transaction;
import com.upiapp.demo.model.User;
import com.upiapp.demo.service.UpiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // allow frontend calls
public class UpiController {
	@Autowired
	private UpiService upiservice;
	
	@GetMapping("/")
    public String home() {
        return "UPI Demo App is running 🚀";
    }
	
	 @PostMapping("/register")
	    public ResponseEntity<User> register(@RequestBody User u) {
	        return ResponseEntity.ok(upiservice.register(u));
	    }

	    @GetMapping("/balance/{mobile}")
	    public ResponseEntity<Map<String, Object>> balance(@PathVariable String mobile) {
	        long balance = upiservice.getBalance(mobile);
	        return ResponseEntity.ok(Map.of("mobile", mobile, "balancePaise", balance));
	    }

	    @PostMapping("/send")
	    public ResponseEntity<?> send(@RequestBody SendMoneyRequest req) {
	        try {
	            Transaction tx = upiservice.sendMoney(
	                req.getFromMobile(),
	                req.getToMobile(),
	                req.getAmount(),
	                req.getPin(),
	                req.getRemark()
	            );
	            return ResponseEntity.ok(tx);
	        } catch (RuntimeException ex) {
	            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
	        }
	    }


	    @GetMapping("/transactions/{mobile}")
	    public ResponseEntity<List<Transaction>> transactions(@PathVariable String mobile) {
	        return ResponseEntity.ok(upiservice.getTransactions(mobile));
	    }

}

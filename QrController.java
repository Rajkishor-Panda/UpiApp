package com.upiapp.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/qr")
public class QrController {

    @GetMapping("/upiString")
    public ResponseEntity<Map<String, String>> getUpiString(
            @RequestParam String mobile,
            @RequestParam long amount) {
        String upiStr = String.format("upi://pay?pa=%s&am=%d&cu=INR", mobile, amount/100);
        return ResponseEntity.ok(Map.of("upiString", upiStr));
    }
}

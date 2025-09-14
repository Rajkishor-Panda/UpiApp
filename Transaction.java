package com.upiapp.demo.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction") 
public class Transaction {
  
	public Transaction(String id, String fromMobile, String toMobile, long amount, Instant timestamp, String remark,
			String pin) {
		super();
		this.id = id;
		this.fromMobile = fromMobile;
		this.toMobile = toMobile;
		this.amount = amount;
		this.timestamp = timestamp;
		this.remark = remark;
		this.pin = pin;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String id;

    private String fromMobile;
    private String toMobile;
    private long amount; // in paise
    private Instant timestamp;
    private String remark;
    private String pin;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromMobile() {
		return fromMobile;
	}
	public void setFromMobile(String fromMobile) {
		this.fromMobile = fromMobile;
	}
	public String getToMobile() {
		return toMobile;
	}
	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", fromMobile=" + fromMobile + ", toMobile=" + toMobile + ", amount=" + amount
				+ ", timestamp=" + timestamp + ", remark=" + remark + ", pin=" + pin + "]";
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
    
}

